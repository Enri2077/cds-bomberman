package level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Bomb;
import entity.Entity;
import entity.Player;
import entity.Type;
import graphics.Screen;
import graphics.Sprite;

public class Level {

	private String path;
	protected int width, height;
	public int creeps;
	public List<Player> players;
	public List<Bomb> bomb;
	//public static List<Flame> flame;
	public List<Entity> brick;
    public Type matrix[][];
	
	//public static Level level1 = new Level("/textures/levels/Level" + Level.levelCounter + ".png", Level.levelCounter);
	//public static Level level2 = new Level("/textures/levels/Level2.png", 2);

	public Level(int screenWidth,int screenHeight) {
		this.width = screenWidth/Sprite.SPRITE_SIZE;
		this.height = screenHeight/Sprite.SPRITE_SIZE;
		this.bomb = new ArrayList<Bomb>();
		//flame = new ArrayList<Flame>();
		this.brick = new ArrayList<Entity>();
		this.players = new ArrayList<Player>();
		this.matrix =  new Type[this.width][this.height];
		for(int i=0;i<this.width;i++){
			for(int j=0;j<this.height;j++){
				matrix[i][j] = Type.FLOOR;
			}
		}
		
		for(int i=0;i<this.width;i++){
			
			brick.add(new Entity(i,0,Type.BRICK));
			matrix[i][0] = Type.BRICK;
			brick.add(new Entity(i,this.height-1,Type.BRICK));
			matrix[i][this.height-1] = Type.BRICK;
		}
		
		for(int j=0;j<this.height;j++){
			
			brick.add(new Entity(0,j,Type.BRICK));
			matrix[0][j] = Type.BRICK;
			brick.add(new Entity(this.width-1,j,Type.BRICK));
			matrix[this.width-1][j] = Type.BRICK;
		}
		
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if(i%2==0 && j%2==0){
					brick.add(new Entity(i,j,Type.BRICK));
					matrix[i][j] = Type.BRICK;
				}
			}
		}
	}
}
