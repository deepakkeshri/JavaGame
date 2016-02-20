import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	Handler handler;
	boolean keyPressed[] = new boolean[4];
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		for(int i=0;i<handler.objects.size();i++){
			GameObject tempObject = handler.objects.get(i);
			//Player
			if(tempObject.id == ID.Player){
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {tempObject.setVely(-5); keyPressed[0]=true;}
				if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){ tempObject.setVely(5); keyPressed[1]=true;}
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){ tempObject.setVelx(-5); keyPressed[2]=true;}
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){ tempObject.setVelx(5); keyPressed[3]=true;}
				
			}
		}
		if(key == KeyEvent.VK_P){
			if(Game.pause) Game.pause = false;
			else Game.pause = true;
				
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i=0;i<handler.objects.size();i++){
			GameObject tempObject = handler.objects.get(i);
			//Player
			if(tempObject.id == ID.Player){
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){  keyPressed[0]=false;}
				if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){keyPressed[1]=false;}
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {keyPressed[2]=false;}
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {keyPressed[3]=false;}
				
				//vertical
				if(!keyPressed[0] && !keyPressed[1])
					tempObject.setVely(0);
				
				//horizontal
				if(!keyPressed[2] && !keyPressed[3])
					tempObject.setVelx(0);
				
			}
		}
	}

}
