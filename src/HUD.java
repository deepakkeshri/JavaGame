import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static float health = 100;
	private float greenValue = 200;
	private float redValue = 0;
	private int score = 0;
	private int level = 1;
	
	public void tick(){
		health = Game.clamp(health, 0, 100);
		greenValue = Game.clamp(greenValue, 0, 255);
		redValue = Game.clamp(redValue, 0, 255);
		greenValue = health*2;
		redValue = 200 - health*2;
		score++;
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color((int)redValue,(int)greenValue,0));
		g.fillRect(15, 15, (int)health*2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: "+score, 15, 62);
		g.drawString("Level: "+level, 15, 74);
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int score){
		this.score = score;
	}

}
