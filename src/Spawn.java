import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random ran = new Random();
	
	private int scoreKeep = 0;
	private int LEVEL_CHANGE = 250;
	
	public Spawn(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick(){
		scoreKeep ++;
		
		if( (hud.getScore()+LEVEL_CHANGE)% LEVEL_CHANGE == 0){
			scoreKeep = 0;
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
			if (hud.getLevel() == 8){
				handler.addObject(new SmartEnemy(ran.nextInt(Game.WIDTH),ran.nextInt(Game.HEIGHT),ID.SmartEnemy,handler));
			}
		}
		
	}

}
