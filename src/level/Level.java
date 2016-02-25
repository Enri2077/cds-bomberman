package level;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import entity.Bomb;
import entity.Entity;
import entity.Flame;
import entity.Player;
import entity.Type;
import graphics.Sprite;

public class Level {

//	private String path;
	protected int width, height;
	public int creeps;
	public List<Player> players;
	public List<Bomb> bomb;
	public List<Flame> flame;
	public List<Entity> block;
	public List<Entity> brick;

    public Type matrix[][];
	
	//public static Level level1 = new Level("/textures/levels/Level" + Level.levelCounter + ".png", Level.levelCounter);
	//public static Level level2 = new Level("/textures/levels/Level2.png", 2);

	public Level(int screenWidth,int screenHeight) {
		this.width = screenWidth/Sprite.SPRITE_SIZE;
		this.height = screenHeight/Sprite.SPRITE_SIZE;
		this.bomb = new ArrayList<Bomb>();
		this.flame = new ArrayList<Flame>();
		this.block = new ArrayList<Entity>();
		this.brick = new ArrayList<Entity>();
		this.players = new ArrayList<Player>();
		this.matrix =  new Type[this.width][this.height];
		for(int i=0;i<this.width;i++){
			for(int j=0;j<this.height;j++){
				matrix[i][j] = Type.FLOOR;
			}
		}
		
		for(int i=0;i<this.width;i++){		
			block.add(new Entity(i,0,Type.BLOCK));
			matrix[i][0] = Type.BLOCK;
			block.add(new Entity(i,this.height-1,Type.BLOCK));
			matrix[i][this.height-1] = Type.BLOCK;
		}
		
		for(int j=0;j<this.height;j++){			
			block.add(new Entity(0,j,Type.BLOCK));
			matrix[0][j] = Type.BLOCK;
			block.add(new Entity(this.width-1,j,Type.BLOCK));
			matrix[this.width-1][j] = Type.BLOCK;
		}
		
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if(i%2==0 && j%2==0){
					block.add(new Entity(i,j,Type.BLOCK));
					matrix[i][j] = Type.BLOCK;
				}
			}
		}
		
		
		//ADDING BRICK
		for(int i=2;i<width-3;i++){
			for(int j=2;j<height-3;j++){
				if(i%2!=0 && j%2!=0){
					brick.add(new Entity(i,j,Type.BRICK));
					matrix[i][j] = Type.BRICK;
				}
			}
		}
		
	}
	public Type getContent(PointXY p){
		try{
			int contentX = (int)(p.X);
			int contentY = (int)(p.Y);
			Type content = this.matrix[contentX][contentY];
	
			if(content != Type.FLOOR) System.out.format("Current\tx:%d  y:%d = %s\n", contentX, contentY, content.toString() );
			return content;
		}catch(ArrayIndexOutOfBoundsException e){
			return Type.BRICK;
		}
	}

	public Type getContentUp(PointXY p){
		//try{
			int contentX = (int)(p.X);
			int contentY = (int)(p.Y+0.55f) - 1;
			Type content = this.matrix[contentX][contentY];
			if(content == Type.BOMB){
				contentX = (int)(p.X);
				contentY = (int)(p.Y) - 1;
				content = this.matrix[contentX][contentY];
				return content;
			}
			if(content != Type.FLOOR) System.out.format("North\tx:%d  y:%d = %s\n", contentX, contentY, content.toString() );
			return content;
		//}catch(ArrayIndexOutOfBoundsException e){
		//	return Type.BRICK;
		//}
	}

	public Type getContentRight(PointXY p){
		try{
			int contentX = (int)(p.X-0.55f) + 1;
			int contentY = (int)(p.Y);
			Type content = this.matrix[contentX][contentY];
			if(content == Type.BOMB){
				contentX = (int)(p.X)+1;
				contentY = (int)(p.Y);
				content = this.matrix[contentX][contentY];
				return content;
			}
			if(content != Type.FLOOR) System.out.format("East\tx:%d  y:%d = %s\n", contentX, contentY, content.toString() );
			return content;
		}catch(ArrayIndexOutOfBoundsException e){
			return Type.BLOCK;
		}
	}

	public Type getContentDown(PointXY p){
		try{
			int contentX = (int)(p.X);
			int contentY = (int)(p.Y-0.55f) + 1;
			Type content = this.matrix[contentX][contentY];
			
			if(content == Type.BOMB){
				contentX = (int)(p.X);
				contentY = (int)(p.Y) + 1;
				content = this.matrix[contentX][contentY];
				return content;
			}
	
			if(content != Type.FLOOR) System.out.format("South\tx:%d  y:%d = %s\n", contentX, contentY, content.toString() );
			return content;
		}catch(ArrayIndexOutOfBoundsException e){
			return Type.BLOCK;
		}
	}

	public Type getContentLeft(PointXY p){
		try{
			int contentX = (int)(p.X+0.55f) - 1;
			int contentY = (int)(p.Y);
			Type content = this.matrix[contentX][contentY];
			if(content == Type.BOMB){
				contentX = (int)(p.X)-1;
				contentY = (int)(p.Y);
				content = this.matrix[contentX][contentY];
				return content;
			}
			if(content != Type.FLOOR) System.out.format("West\tx:%d  y:%d = %s\n", contentX, contentY, content.toString() );
			return content;
		}catch(ArrayIndexOutOfBoundsException e){
			return Type.BLOCK;
		}
	}
	
	public Bomb getBomb(int x,int y){	
		for(Bomb b : bomb){
			if(b.x == x && b.y == y)
				return b;
		}
		Bomb empty = null;
		return empty;
	}

	public Entity getBrick(int x,int y){	
		for(Entity b : brick){
			if(b.x == x && b.y == y)
				return b;
		}
		Entity empty = null;
		return empty;
	}

	public void spawnBomb(Player p) {
		if(p.bombsInHand>0){
			int i = (int) p.getPosition().X, j = (int) p.getPosition().Y;
			if(this.getContent(p.getPosition()) == Type.FLOOR){
				bomb.add(new Bomb(i, j, p.bombRange,p));
				p.bombsInHand--;
				matrix[i][j] = Type.BOMB;
			}
		}
	}
}
