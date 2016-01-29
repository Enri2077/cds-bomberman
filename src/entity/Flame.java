package entity;

public class Flame extends Entity {
	
	public int counter;
	
	public Flame(int x, int y) {
		this.x = x;
		this.y = y;
		this.counter = 15;
		this.type = Type.FLAME;
	}
	
}
