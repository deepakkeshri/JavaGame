import java.awt.Canvas;
import java.awt.Color;
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
	
	Handler handler;
	HUD hud;
	Random ran;
	
	public Game(){
		handler = new Handler();
		hud = new HUD();
		ran = new Random();
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH,HEIGHT,TITLE,this);
		
		handler.addObject(new Player(WIDTH/2-30,HEIGHT/2,ID.Player,handler));
		handler.addObject(new BasicEnemy(ran.nextInt(WIDTH),ran.nextInt(HEIGHT),ID.BasicEnemy,handler));
		handler.addObject(new BasicEnemy(ran.nextInt(WIDTH),ran.nextInt(HEIGHT),ID.BasicEnemy,handler));
		handler.addObject(new BasicEnemy(ran.nextInt(WIDTH),ran.nextInt(HEIGHT),ID.BasicEnemy,handler));
		handler.addObject(new BasicEnemy(ran.nextInt(WIDTH),ran.nextInt(HEIGHT),ID.BasicEnemy,handler));
		handler.addObject(new BasicEnemy(ran.nextInt(WIDTH),ran.nextInt(HEIGHT),ID.BasicEnemy,handler));
	} 
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try {
			thread.join();
			running = true;
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
		handler.tick();
		hud.tick();
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
		hud.render(g);
		g.dispose();
		bs.show(); 
	}
	
	public static int clamp(int var,int min,int max){
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
