import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random ran = new Random();
	
	private boolean isLife = false;
	private int LEVEL_CHANGE = 250;
	Life life;
	
	public Spawn(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick(){
		
		if( (hud.getScore()+LEVEL_CHANGE)% LEVEL_CHANGE == 0){
			hud.setLevel(hud.getLevel() + 1);
			
			if (hud.getLevel() == 2){
				handler.addObject(new BasicEnemy(ran.nextInt(Game.WIDTH),ran.nextInt(Game.HEIGHT),ID.BasicEnemy,handler));
			}
			if (hud.getLevel() == 3){
				handler.addObject(new FastEnemy(ran.nextInt(Game.WIDTH),ran.nextInt(Game.HEIGHT),ID.FastEnemy,handler));
			}
			if (hud.getLevel() == 4){
				handler.addObject(new SmartEnemy(ran.nextInt(Game.WIDTH),ran.nextInt(Game.HEIGHT),ID.SmartEnemy,handler));
			}
			if (hud.getLevel() == 5){
				handler.clearEnemies();
				handler.addObject(new BossEnemy((Game.WIDTH)/2-32,-40,ID.BossEnemy,handler));
				AudioPlayer.getSound("boss").play();

			}
			if (hud.getLevel() == 10){
				handler.addObject(new SmartEnemy(ran.nextInt(Game.WIDTH),ran.nextInt(Game.HEIGHT),ID.SmartEnemy,handler));
			}
			if (hud.getLevel() == 15){
				handler.addObject(new BossEnemy((Game.WIDTH)/2-32,-40,ID.BossEnemy,handler));
			}
		}
		
		if(!isLife){
			int spawn = ran.nextInt(10);
			if(spawn == 5){
				life = new Life(ran.nextInt(Game.WIDTH-32),ran.nextInt(Game.HEIGHT-32)-32,ID.Life,handler);
				handler.addObject(life);
				isLife = true;
			}
		}
		else{
			if(life.getTimer() <=0 ){
				handler.removeObject(life);
				isLife = false;
			}
			else
				life.setTimer(life.getTimer()-1);
		}
		
	}

}
