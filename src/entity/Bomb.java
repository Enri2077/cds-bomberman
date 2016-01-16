package entity;

public class Bomb extends Entity {
	
	public int bombCounter;
	public int range;
	
	public Bomb(int x, int y, int range) {
		this.x = x;
		this.y = y;
		this.range = range;
		this.bombCounter = 0;
		this.type = Type.BOMB;
	}
	
}
