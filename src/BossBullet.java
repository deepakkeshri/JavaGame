import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossBullet extends GameObject{
	
	Handler handler;
	Random ran = new Random();
	
	public BossBullet(float x,float y,ID id,Handler handler){
		super(x,y,id);
		this.handler = handler;
		velx = ran.nextInt(5 - -5) + -5;
		vely = 5;
	}

	@Override
	public void tick() {
		x += velx; 
		y += vely;
		//if(x <= 0 || x > Game.WIDTH-32) velx *= -1; 
		//if(y <= 0 || y > Game.HEIGHT-32) vely *= -1; 
		if(y > Game.HEIGHT) handler.removeObject(this);
		handler.addObject(new Trail(x,y,ID.Trail,Color.yellow,16,16,0.01f,handler));
	}

	@Override
	public void render(Graphics g) {
		if(this.id == ID.BasicEnemy){
			g.setColor(Color.yellow);
			g.fillRect((int)x, (int)y, 16, 16);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16);
	}

}
