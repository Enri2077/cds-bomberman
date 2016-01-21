package graphics;

public class Sprite {

	private int x, y;
	public int SIZEX;
	public int SIZEY;
	public static int PSIZEX = 32;
	private SpriteSheet sheet;
	public int[] pixels;
	public static final int SPRITE_SIZE = 16;
	public static Sprite brick_on_fire_1 = new Sprite(64, 3, 0, SpriteSheet.tiles);
	public static Sprite brick_on_fire_2 = new Sprite(64, 4, 0, SpriteSheet.tiles);
	public static Sprite brick_on_fire_3 = new Sprite(64, 5, 0, SpriteSheet.tiles);
	public static Sprite brick_on_fire_4 = new Sprite(64, 6, 0, SpriteSheet.tiles);
	public static Sprite brick_on_fire_5 = new Sprite(64, 7, 0, SpriteSheet.tiles);

	public static Sprite brick_sprite = new Sprite(16, 0, 1, SpriteSheet.level);
	public static Sprite floor_sprite = new Sprite(16, 0, 2, SpriteSheet.grass);
	public static Sprite border_sprite = new Sprite(16, 2, 1, SpriteSheet.level);

	public static Sprite player_forward_1 = new Sprite(PSIZEX, 0, 2, SpriteSheet.player);
	public static Sprite player_forward_2 = new Sprite(PSIZEX, 1, 2, SpriteSheet.player);
	public static Sprite player_forward_3 = new Sprite(PSIZEX, 0, 2, SpriteSheet.player);
	public static Sprite player_forward_4 = new Sprite(PSIZEX, 2, 2, SpriteSheet.player);
	public static Sprite player_forward_5 = new Sprite(PSIZEX, 0, 2, SpriteSheet.player);
	public static Sprite player_forward_6 = new Sprite(PSIZEX, 1, 2, SpriteSheet.player);
	public static Sprite player_forward_7 = new Sprite(PSIZEX, 0, 2, SpriteSheet.player);
	public static Sprite player_forward_8 = new Sprite(PSIZEX, 2, 2, SpriteSheet.player);

	public static Sprite player_back_1 = new Sprite(PSIZEX, 0, 0, SpriteSheet.player);
	public static Sprite player_back_2 = new Sprite(PSIZEX, 1, 0, SpriteSheet.player);
	public static Sprite player_back_3 = new Sprite(PSIZEX, 0, 0, SpriteSheet.player);
	public static Sprite player_back_4 = new Sprite(PSIZEX, 2, 0, SpriteSheet.player);
	public static Sprite player_back_5 = new Sprite(PSIZEX, 0, 0, SpriteSheet.player);
	public static Sprite player_back_6 = new Sprite(PSIZEX, 1, 0, SpriteSheet.player);
	public static Sprite player_back_7 = new Sprite(PSIZEX, 0, 0, SpriteSheet.player);
	public static Sprite player_back_8 = new Sprite(PSIZEX, 2, 0, SpriteSheet.player);

	public static Sprite player_left_1 = new Sprite(PSIZEX, 0, 1, SpriteSheet.player);
	public static Sprite player_left_2 = new Sprite(PSIZEX, 1, 1, SpriteSheet.player);
	public static Sprite player_left_3 = new Sprite(PSIZEX, 0, 1, SpriteSheet.player);
	public static Sprite player_left_4 = new Sprite(PSIZEX, 2, 1, SpriteSheet.player);
	public static Sprite player_left_5 = new Sprite(PSIZEX, 0, 1, SpriteSheet.player);
	public static Sprite player_left_6 = new Sprite(PSIZEX, 1, 1, SpriteSheet.player);
	public static Sprite player_left_7 = new Sprite(PSIZEX, 0, 1, SpriteSheet.player);
	public static Sprite player_left_8 = new Sprite(PSIZEX, 2, 1, SpriteSheet.player);
	
	public static Sprite player_right_1 = new Sprite(PSIZEX, 0, 3, SpriteSheet.player);
	public static Sprite player_right_2 = new Sprite(PSIZEX, 1, 3, SpriteSheet.player);
	public static Sprite player_right_3 = new Sprite(PSIZEX, 0, 3, SpriteSheet.player);
	public static Sprite player_right_4 = new Sprite(PSIZEX, 2, 3, SpriteSheet.player);
	public static Sprite player_right_5 = new Sprite(PSIZEX, 0, 3, SpriteSheet.player);
	public static Sprite player_right_6 = new Sprite(PSIZEX, 1, 3, SpriteSheet.player);
	public static Sprite player_right_7 = new Sprite(PSIZEX, 0, 3, SpriteSheet.player);
	public static Sprite player_right_8 = new Sprite(PSIZEX, 2, 3, SpriteSheet.player);

	public static Sprite bomb_1 = new Sprite(16, 0, 3, SpriteSheet.level);
	public static Sprite bomb_2 = new Sprite(64, 8, 2, SpriteSheet.tiles);
	public static Sprite bomb_3 = new Sprite(64, 8, 3, SpriteSheet.tiles);
	
	public static Sprite cool_bomb_1 = new Sprite(16, 0, 1, SpriteSheet.bomb);
	public static Sprite cool_bomb_2 = new Sprite(16, 0, 2, SpriteSheet.bomb);
	public static Sprite cool_bomb_3 = new Sprite(16, 0, 3, SpriteSheet.bomb);

	public static Sprite flame_1 = new Sprite(16, 6, 3, SpriteSheet.flame);
	public static Sprite flame_2 = new Sprite(16, 1, 4, SpriteSheet.flame);
	public static Sprite flame_3 = new Sprite(16, 2, 4, SpriteSheet.flame);
	public static Sprite flame_4 = new Sprite(16, 3, 4, SpriteSheet.flame);
	public static Sprite flame_5 = new Sprite(16, 4, 4, SpriteSheet.flame);


	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZEX = SIZEY = size;
		this.x = x * SIZEX;
		this.y = y * SIZEY;
		this.sheet = sheet;
		pixels = new int[SIZEX * SIZEY];
		load();
	}
	
	public Sprite(int sizex,int sizey, int x, int y, SpriteSheet sheet) {
		SIZEX = sizex;
		SIZEY = sizey;
		this.x = x * SIZEX;
		this.y = y * SIZEY;
		this.sheet = sheet;
		pixels = new int[SIZEX * SIZEY];
		load();
	}

	public void load() {
		for (int y = 0; y < SIZEY; y++) {
			for (int x = 0; x < SIZEX; x++) {
				pixels[x + y * SIZEY] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZEX];
			}
		}
	}

}
