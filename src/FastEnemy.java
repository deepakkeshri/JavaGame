import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{
	
	Handler handler;
	
	public FastEnemy(int x,int y,ID id,Handler handler){
		super(x,y,id);
		this.handler = handler;
		velx = 2;
		vely = 10;
	}

	@Override
	public void tick() {
		x += velx; 
		y += vely;
		if(x <= 0 || x > Game.WIDTH-32) velx *= -1; 
		if(y <= 0 || y > Game.HEIGHT-32) vely *= -1; 
		handler.addObject(new Trail(x,y,ID.Trail,Color.blue,16,16,0.01f,handler));
	}

	@Override
	public void render(Graphics g) {
		if(this.id == ID.FastEnemy){
			g.setColor(Color.blue);
			g.fillRect((int)x, (int)y, 16, 16);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16);
	}

}
