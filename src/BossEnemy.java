import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject{
	
	Handler handler;
	Random ran = new Random();
	GameObject player;
	
	private int timer = 40;
	private int timer2 = 20;
	
	public BossEnemy(float x,float y,ID id,Handler handler){
		super(x,y,id);
		this.handler = handler;
		velx = 0;
		vely = 2;
	}

	@Override
	public void tick() {
		
		if(timer <= 0)
			vely=0;
		else
			timer--;
		
		if(timer <= 0)
			timer2--;
		
		if(timer2 <=0 ){
			if(velx==0) velx = 2;
			if(velx >=0 )
				velx += 0.002f;
			if(velx <0 )
				velx -= 0.004f;
			velx = Game.clamp(velx, -10, 10);
			int spawn = ran.nextInt(20);
			if(spawn == 5) handler.addObject(new BossBullet((int)x+32, (int)y+32,ID.BasicEnemy,handler));
			//if(spawn2 == 5) handler.addObject(new SmartEnemy((int)x+32, (int)y+32,ID.SmartEnemy,handler));
		}
		
		x += velx; 
		y += vely;
		if(x <= 0 || x > Game.WIDTH-64) velx *= -1; 
		//if(y <= 0 || y > Game.HEIGHT-64) vely *= -1; 
		for(int i=0;i<handler.objects.size();i++)
			if(handler.objects.get(i).getId() == ID.Player)
				player = handler.objects.get(i);
		if(player.getY() < y){
			y -= 10;
		}
	}

	@Override
	public void render(Graphics g) {
		if(this.id == ID.BossEnemy){
			g.setColor(Color.red);
			g.fillRect((int)x, (int)y, 64, 64);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,64,64);
	}

}
