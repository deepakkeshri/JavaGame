import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{
	
	Handler handler;
	Color col;
	Random ran;
	
	public MenuParticle(int x,int y,ID id,Handler handler){
		super(x,y,id);
		this.handler = handler;
		ran = new Random();
		velx = ran.nextInt(8 - -8) + -8;
		vely = ran.nextInt(8 - -8) + -8;
		col = new Color(ran.nextInt(255),ran.nextInt(255),ran.nextInt(255));
	}

	@Override
	public void tick() {
		x += velx; 
		y += vely;
		if(x <= 0 || x > Game.WIDTH-32) velx *= -1; 
		if(y <= 0 || y > Game.HEIGHT-32) vely *= -1; 
		handler.addObject(new Trail(x,y,ID.Trail,col,16,16,0.09f,handler));
	}

	@Override
	public void render(Graphics g) {
		if(this.id == ID.FastEnemy){
			g.setColor(col);
			g.fillRect((int)x, (int)y, 16, 16);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16);
	}

}
