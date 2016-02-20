import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Menu extends MouseAdapter{
	
	Game game;
	Handler handler;
	Random ran;
	HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud){
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		ran = new Random();
	}
	
	public void mousePressed(MouseEvent e){
		
		int mx = e.getX();
		int my = e.getY();
		int menuX = Game.WIDTH /2;
		
		//Menu Page
		if(Game.gameState == Game.State.Menu){
			//play
			if(mouseOver(mx,my,menuX-100,100,200,80)){
				Game.gameState = Game.State.Game;
				handler.addObject(new Player(Game.WIDTH/2-30,Game.HEIGHT/2,ID.Player,handler));
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(ran.nextInt(Game.WIDTH),ran.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
				hud.setLevel(1);
				hud.setScore(0);
				AudioPlayer.getSound("sound").play();
			}
			
			//quit
			if(mouseOver(mx,my,menuX-100,300,200,80)){
				System.exit(1); 
			}
			
			//HElP
			if(mouseOver(mx,my,menuX-100,200,200,80)){
				Game.gameState = Game.State.Help; 
				AudioPlayer.getSound("sound").play();
			}
		}
		
		//Help Page
		if(Game.gameState == Game.State.Help){
			//back
			if(mouseOver(mx,my,menuX-100,300,200,80)){
				Game.gameState = Game.State.Menu;
				AudioPlayer.getSound("sound").play();
			}
		}
		
		//Game Over Page
		if(Game.gameState == Game.State.Over){
			//Try Again
			if(mouseOver(mx,my,menuX-100,200,200,80)){
				Game.gameState = Game.State.Game;
				handler.addObject(new Player(Game.WIDTH/2-30,Game.HEIGHT/2,ID.Player,handler));
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(ran.nextInt(Game.WIDTH),ran.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
				hud.setLevel(1);
				hud.setScore(0);
				AudioPlayer.getSound("sound").play();
			}
			//Menu
			if(mouseOver(mx,my,menuX-100,300,200,80)){
				Game.gameState = Game.State.Menu;
				AudioPlayer.getSound("sound").play();
			}
		}
		
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	public boolean mouseOver(int mx,int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width)
			if(my > y && my < y + height)
				return true;
		return false; 
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		int menuX = Game.WIDTH /2;
		Font fnt = new Font("arial",1,50);
		Font fnt2 = new Font("arial",1,30);
		
		if(Game.gameState == Game.State.Menu){
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Menu", menuX-70, 80);
			
			g.setFont(fnt2);
			//g.setColor(Color.red);
			g.drawRect(menuX-100, 100, 200, 80);
			g.setColor(Color.white);
			g.drawString("Play", menuX-30, 150);
			
			//g.setColor(Color.red);
			g.drawRect(menuX-100, 200, 200, 80);
			g.setColor(Color.white);
			g.drawString("Help", menuX-30, 250);
			
			//g.setColor(Color.red);
			g.drawRect(menuX-100, 300, 200, 80);
			g.setColor(Color.white);
			g.drawString("Quit", menuX-30, 350);
		}
		else if(Game.gameState == Game.State.Help){
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", menuX-70, 80);
			
			g.setFont(fnt2);
			g.drawString("Press AWSD to move player and dodge",20,200);

			g.drawRect(menuX-100, 300, 200, 80);
			g.setColor(Color.white);
			g.drawString("Back", menuX-30, 350);
		}
		else if(Game.gameState == Game.State.Over){
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", menuX-140, 80);
			
			g.setFont(fnt2);
			g.drawString("Your Score: "+ hud.getScore(),200,150);
			
			g.drawRect(menuX-100, 200, 200, 80);
			g.setColor(Color.white);
			g.drawString("Play Again", menuX-80, 250);
			
			//g.setColor(Color.red);
			g.drawRect(menuX-100, 300, 200, 80);
			g.setColor(Color.white);
			g.drawString("Menu", menuX-40, 350);
		}
		
		
		
	}

}
