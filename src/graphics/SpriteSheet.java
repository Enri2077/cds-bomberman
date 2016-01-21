package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public final int SIZEX;
	public final int SIZEY;

	private String path;
	public int[] pixels;

	public static SpriteSheet bomb = new SpriteSheet(64,640, "/textures/sheets/bomb.png");
	public static SpriteSheet text = new SpriteSheet(864,864, "/textures/text/font.png");
	public static SpriteSheet tiles = new SpriteSheet(576,576, "/textures/sheets/SpriteSheet.png");
	public static SpriteSheet player = new SpriteSheet(288,640, "/textures/sheets/Player4.png");
	public static SpriteSheet level = new SpriteSheet(1168,628, "/textures/sheets/Levels.png");
	public static SpriteSheet grass = new SpriteSheet(326,281, "/textures/sheets/TilesetGrass.png");
	public static SpriteSheet flame = new SpriteSheet(262,135, "/textures/sheets/BombFlame.png");
	public static SpriteSheet items = new SpriteSheet(74,79, "/textures/sheets/Items.png");

	public SpriteSheet(int sizex,int sizey, String path) {
		this.SIZEX = sizex;
		this.SIZEY = sizey;
		this.path = path;
		pixels = new int[SIZEX * SIZEY];
		load();
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
