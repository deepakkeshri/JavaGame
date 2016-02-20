import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	Handler handler;
	
	public Player(int x,int y, ID id,Handler handler){
		super(x,y,id);
		this.handler = handler;
	}

	@Override
	public void tick() {
		x += velx; 
		y += vely;
		x = Game.clamp(x,0,Game.WIDTH-30);
		y = Game.clamp(y,0,Game.HEIGHT-50);
		handler.addObject(new Trail(x,y,ID.Trail,Color.white,32,32,0.05f,handler));
		collision();
	}
	
	public void collision(){
		for(int i=0;i<handler.objects.size();i++){
			GameObject tempObject = handler.objects.get(i);
			if(tempObject.getId() == ID.BasicEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.health -= 1;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(this.id == ID.Player){
			g.setColor(Color.white);
			g.fillRect(x, y, 30, 30);
		}
		if(this.id == ID.Player2){
			g.setColor(Color.red);
			g.fillRect(x, y, 30, 30);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,30,30);
	}

}
