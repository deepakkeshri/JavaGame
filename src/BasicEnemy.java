import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{
	
	Handler handler;
	
	public BasicEnemy(int x,int y,ID id,Handler handler){
		super(x,y,id);
		this.handler = handler;
		velx = 5;
		vely = 5;
	}

	@Override
	public void tick() {
		x += velx; 
		y += vely;
		if(x <= 0 || x > Game.WIDTH-32) velx *= -1; 
		if(y <= 0 || y > Game.HEIGHT-32) vely *= -1; 
		handler.addObject(new Trail(x,y,ID.Trail,Color.red,16,16,0.01f,handler));
	}

	@Override
	public void render(Graphics g) {
		if(this.id == ID.BasicEnemy){
			g.setColor(Color.red);
			g.fillRect(x, y, 16, 16);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,16,16);
	}

}
