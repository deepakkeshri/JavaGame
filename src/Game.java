import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = WIDTH*3/4;
	public static final String TITLE = "Let's Play";
	private Thread thread;
	boolean running = false;
	
	public static boolean pause = false;
	
	Handler handler;
	HUD hud;
	Random ran;
	Spawn spawn;
	Menu menu;
	
	public enum State{
		Menu,
		Game,
		Help,
		Over
	};
	
	public static State gameState = State.Menu;
	
	public Game(){
		
		AudioPlayer.load();
		
		AudioPlayer.getMusic("music").loop();;
		
		handler = new Handler();
		hud = new HUD();
		ran = new Random();
		menu = new Menu(this,handler,hud);
		spawn = new Spawn(handler,hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		new Window(WIDTH,HEIGHT,TITLE,this);
		//if(gameState != State.Game){
			for(int i=0; i< 20;i++){
				handler.addObject(new MenuParticle(ran.nextInt(Game.WIDTH),ran.nextInt(Game.HEIGHT),ID.MenuParticle,handler));
			}
		//}
		
	} 
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime)/ ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running){
				render();
			}
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		
		if(gameState == State.Game){
			if(!pause){
				handler.tick();
				hud.tick();
				spawn.tick();
				if(HUD.health <= 0){
					HUD.health = 100;
					Game.gameState = State.Over;
					handler.clearEnemies();
					for(int i=0; i< 20;i++)
						handler.addObject(new MenuParticle(ran.nextInt(Game.WIDTH),ran.nextInt(Game.HEIGHT),ID.MenuParticle,handler));
				}
			}	
		}
		else if (gameState == State.Menu || gameState == State.Over){
			handler.tick();
			menu.tick();
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		if(gameState == State.Game){
			hud.render(g);
			if(pause){
				g.setFont(new Font("arial",1,30));
				g.setColor(Color.blue);
				g.drawString("Paused",Game.WIDTH-120,40);
			}
				
		}
		else if(gameState == State.Menu || gameState == State.Help || gameState == State.Over)
			menu.render(g);
		
		g.dispose();
		bs.show(); 
	}
	
	public static float clamp(float var,int min,int max){
		if(var >= max)
			return max;
		else if(var <= min)
			return min;
		else
			return var;
	}
	
	public static void main(String[] arg) throws Exception {
		new Game();
	}

}
