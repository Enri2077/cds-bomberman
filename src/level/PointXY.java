package level;

public class PointXY {
	public float X;
	public float Y;

	public PointXY() {
	}

	public PointXY(float x, float y) {
		this.X = x;
		this.Y = y;
	}
	
	public PointXY(PointXY p) {
		this.X = p.X;
		this.Y = p.Y;
	}	
}