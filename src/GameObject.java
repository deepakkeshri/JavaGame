import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected int x,y;
	protected ID id;
	int velx,vely;	
	
	public GameObject(int x,int y,ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setId(ID id){
		this.id = id;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public ID getId(){
		return this.id;
	}
	
	public void setVelx(int velx){
		this.velx = velx;
	}
	
	public void setVely(int vely){
		this.vely = vely;
	}
	
	public int getVelx(){
		return this.velx;
	}
	public int getVely(){
		return this.velx;
	}
}
