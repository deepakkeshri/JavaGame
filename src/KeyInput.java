import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	Handler handler;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		for(int i=0;i<handler.objects.size();i++){
			GameObject tempObject = handler.objects.get(i);
			//Player
			if(tempObject.id == ID.Player){
				if(key == KeyEvent.VK_W) tempObject.setVely(-5);;
				if(key == KeyEvent.VK_S) tempObject.setVely(5);;
				if(key == KeyEvent.VK_A) tempObject.setVelx(-5);;
				if(key == KeyEvent.VK_D) tempObject.setVelx(5);;
				
			}
			if(tempObject.id == ID.Player2){
				if(key == KeyEvent.VK_UP) tempObject.setVely(-5);;
				if(key == KeyEvent.VK_DOWN) tempObject.setVely(5);;
				if(key == KeyEvent.VK_LEFT) tempObject.setVelx(-5);;
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelx(5);;
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i=0;i<handler.objects.size();i++){
			GameObject tempObject = handler.objects.get(i);
			//Player
			if(tempObject.id == ID.Player){
				if(key == KeyEvent.VK_W) tempObject.setVely(0);;
				if(key == KeyEvent.VK_S) tempObject.setVely(0);;
				if(key == KeyEvent.VK_A) tempObject.setVelx(0);;
				if(key == KeyEvent.VK_D) tempObject.setVelx(0);;
				
			}
			if(tempObject.id == ID.Player2){
				if(key == KeyEvent.VK_UP) tempObject.setVely(0);;
				if(key == KeyEvent.VK_DOWN) tempObject.setVely(0);;
				if(key == KeyEvent.VK_LEFT) tempObject.setVelx(0);;
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelx(0);;
			}
		}
	}

}
