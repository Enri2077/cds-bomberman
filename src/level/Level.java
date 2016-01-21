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
	public List<Entity> brick;
    public Type matrix[][];
	
	//public static Level level1 = new Level("/textures/levels/Level" + Level.levelCounter + ".png", Level.levelCounter);
	//public static Level level2 = new Level("/textures/levels/Level2.png", 2);

	public Level(int screenWidth,int screenHeight) {
		this.width = screenWidth/Sprite.SPRITE_SIZE;
		this.height = screenHeight/Sprite.SPRITE_SIZE;
		this.bomb = new ArrayList<Bomb>();
		this.flame = new ArrayList<Flame>();
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

		for(int i=1; i<width-1; i++){
			for(int j=1; j<height-1; j++){
				if(ThreadLocalRandom.current().nextFloat() < 0.1 && matrix[i][j] == Type.FLOOR){
					//flame.add(new Flame(i,j));
					//matrix[i][j] = Type.FLAME;
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
			return Type.BRICK;
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
			return Type.BRICK;
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
			return Type.BRICK;
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


	public void spawnBomb(Player p) {
		if(p.bombsInHand>0){
			int i = (int) p.getPosition().X, j = (int) p.getPosition().Y;
			if(this.getContent(p.getPosition()) == Type.FLOOR){
				bomb.add(new Bomb(i, j, p.getBombInitCounter(),p));
				p.bombsInHand--;
				matrix[i][j] = Type.BOMB;
			}
		}
	}
}
