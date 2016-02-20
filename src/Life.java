import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Life extends GameObject{
	
	Handler handler;
	private int timer = 100;
	
	public Life(float x,float y,ID id,Handler handler){
		super(x,y,id);
		this.handler = handler;
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		if(this.id == ID.Life){
			g.setColor(Color.white);
			g.fillRect((int)x, (int)y, 28, 28);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,28,28);
	}
	
	public int getTimer(){
		return this.timer;
	}
	
	public void setTimer(int timer){
		this.timer = timer;
	}

}