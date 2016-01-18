package graphics;

import entity.Entity;
import entity.Player;
import entity.Type;
import level.Level;

public class Screen {
	
	public int width;
	public int height;
	public int[] pixels;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderLevel(Level level){
		renderBackground();
		renderBricks(level);
		renderBombs(level);
		renderPlayers(level);
	}
	
	private void renderBombs(Level level) {
		for(Entity e : level.bomb){
			renderTile(e.x*Sprite.SPRITE_SIZE,e.y*Sprite.SPRITE_SIZE,e.type);
		}
	}

	public void renderBackground(){
		for(int x=0;x<width;x+=Sprite.SPRITE_SIZE){
			for(int y=0;y<height;y+=Sprite.SPRITE_SIZE){
				renderTile(x,y,Type.FLOOR);
			}
		}
	}
	
	public void renderBricks(Level level){
		for(Entity e : level.brick){
			renderTile(e.x*Sprite.SPRITE_SIZE,e.y*Sprite.SPRITE_SIZE,e.type);
		}

	}
	
	public void renderTile(int xp, int yp,Type type) {
		Sprite tile = null;
		if(type==Type.FLOOR)
			tile= Sprite.floor_sprite;
		if(type==Type.BRICK)
			tile= Sprite.brick_sprite;
		if(type==Type.BOMB)
			tile= Sprite.bomb_1;
		for(int y = 0; y < Sprite.SPRITE_SIZE; y++) {
			int ya = y + yp;
			for(int x = 0; x < Sprite.SPRITE_SIZE; x++) {
				int xa = x + xp;
				if(xa < -Sprite.SPRITE_SIZE|| xa >= width || ya < 0 || ya >= height) break; 
				if(xa < 0) xa = 0;
				int col = tile.pixels[x + y * Sprite.SPRITE_SIZE];
				pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderPlayers(Level level){
		for (Player p : level.players){
			renderPlayer((int)(p.gridX*Sprite.SPRITE_SIZE)-Sprite.SPRITE_SIZE, (int)(p.gridY*Sprite.SPRITE_SIZE)-Sprite.SPRITE_SIZE-6,p.sprite);
			//renderPlayer((int)p.realX,(int)p.realY,p.sprite);
			//renderPlayer(p.x*Sprite.SPRITE_SIZE+2,p.y*Sprite.SPRITE_SIZE);
		}
	}

	public void renderPlayer(int xp, int yp,Sprite sprite) {
		for(int y = 0; y < sprite.SIZEY; y++) {
			int ya = yp + y;
			int ys = y;
			for(int x = 0; x < sprite.SIZEX; x++) {
				int xa = xp + x;
				int xs = x;
				if(x < -sprite.SIZEX || x >= width || y < 0 || y >= height) break; 
				if(x < 0) x = 0;
				int col = sprite.pixels[xs + ys * sprite.SIZEY];
				if(col == 0xFFFFFFFF) continue;
				pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderBomb(int xp, int yp, Sprite sprite) {
		xp = xp << 6;
		yp = yp << 6;
		
		for(int y = 0; y < sprite.SIZEY; y++) {
			int ya = yp + y;
			for(int x = 0; x < sprite.SIZEX; x++) {
				int xa = xp + x;
				int col = sprite.pixels[x + y * sprite.SIZEY];
				if(col == 0x00FF78) continue;
				pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderFlame(int xp, int yp, Sprite sprite) {
		xp = xp << 6;
		yp = yp << 6;
		
		for(int y = 0; y < sprite.SIZEY; y++) {
			int ya = yp + y;
			for(int x = 0; x < sprite.SIZEX; x++) {
				int xa = xp + x;
				int col = sprite.pixels[x + y * sprite.SIZEY];
				if(col == 0xFF870087) continue;
				pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderCreeps(int xp, int yp, Sprite sprite, int flip) {
		for(int y = 0; y < sprite.SIZEY; y++) {
			int ya = yp + y;
			int ys = y;
			if(flip == 0 || flip == 2) ys = (sprite.SIZEY - 1) - ys;
			for(int x = 0; x < sprite.SIZEX; x++) {
				int xa = xp + x;
				int xs = x;
				if(flip == 1 || flip == 3) xs = (sprite.SIZEX - 1) - xs;
				if(x < -sprite.SIZEX || x >= width || y < 0 || y >= height) break; 
				if(x < 0) x = 0;
				int col = sprite.pixels[xs + ys * sprite.SIZEX];
				if(col == 0xFFFFFFFF) continue;
				pixels[xa + ya * width] = col;
			}
		}
	}

}
