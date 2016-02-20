import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{
	
	Handler handler;
	GameObject player;
	
	public SmartEnemy(int x,int y,ID id,Handler handler){
		super(x,y,id);
		this.handler = handler;
		
		for(int i=0;i< handler.objects.size();i++ ){
			if(handler.objects.get(i).getId() == ID.Player)
				player = handler.objects.get(i);
		}
	}

	@Override
	public void tick() {
		
		float diffX = player.getX() - x;
		float diffY = player.getY() - y;
		
		float distance = (float) Math.sqrt(diffX*diffX + diffY*diffY);
		
		float velx = (float) ((1.0/distance)*(diffX + 16));
		float vely = (float) ((1.0/distance)*(diffY + 16));
		
		x += velx; 
		y += vely;
		//if(x <= 0 || x > Game.WIDTH-32) velx *= -1; 
		//if(y <= 0 || y > Game.HEIGHT-32) vely *= -1; 
		handler.addObject(new Trail(x,y,ID.Trail,Color.pink,16,16,0.01f,handler));
	}

	@Override
	public void render(Graphics g) {
		if(this.id == ID.BasicEnemy){
			g.setColor(Color.pink);
			g.fillRect((int)x, (int)y, 16, 16);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y,16,16);
	}

}
