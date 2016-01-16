package entity;

public class Entity {
	public int x;
	public int y;
	public int frame;
	public boolean removed;
	public Type type;
	
	public Entity(){
		
	}
	public Entity(int x,int y, Type type){
		this.x = x;
		this.y = y;
		this.type = type;
	}
}

