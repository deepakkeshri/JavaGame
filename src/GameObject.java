import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected float x,y;
	protected ID id;
	float velx,vely;	
	
	public GameObject(float x,float y,ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public void setId(ID id){
		this.id = id;
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
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
	
	public float getVelx(){
		return this.velx;
	}
	public float getVely(){
		return this.velx;
	}
}
