import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	GameObject player;
	
	public void tick(){
		for(int i=0;i<objects.size();i++)
			objects.get(i).tick();
	}
	
	public void render(Graphics g){
		for(int i=0;i<objects.size();i++)
			objects.get(i).render(g);
	}
	
	public void addObject(GameObject object){
		this.objects.add(object);
	}
	
	public void removeObject(GameObject object){
		this.objects.remove(object);
	}
	
	public void clearEnemies(){
		for(int i=0;i<objects.size();i++){
			if(objects.get(i).getId() == ID.Player)
				player = objects.get(i);
		}
		objects.clear();
		if(Game.gameState != Game.State.Over)
			objects.add(player);
	}

}
