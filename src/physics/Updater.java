package physics;

import entity.Direction;
import entity.Player;
import entity.Type;
import input.Keyboard;
import level.Level;
import level.PointXY;

public class Updater {
	public Keyboard key;
	public Level level;
	
	public Updater(Keyboard key,Level level){
		this.key = key;
		this.level = level;
	}
	
	public void update(){
		Player player = level.players.get(0);
		player.updateAnimation();
		//PointXY pRight = new PointXY();
		//PointXY pLeft = new PointXY();
		//pRight.X = (player.realX + +Sprite.SPRITE_SIZE/2)/Sprite.SPRITE_SIZE;
		//pLeft.X = (player.realX+Sprite.SPRITE_SIZE*1.5f)/Sprite.SPRITE_SIZE;

		//pLeft.Y = (player.realY +Sprite.SPRITE_SIZE*1.8f)/Sprite.SPRITE_SIZE;
		//pRight.Y = (player.realY +Sprite.SPRITE_SIZE*1.8f)/Sprite.SPRITE_SIZE;
		
	//	p.X = (pRight.X+pLeft.X)/2;
	//	p.Y = (player.realY+Sprite.SPRITE_SIZE)/Sprite.SPRITE_SIZE;
	//	System.out.println("Rx:"+p.X +" X:"+(int)p.X+" Ry:"+p.Y+" Y:"+(int)(p.Y+.5f));
		
		PointXY p = player.getPosition();		
		
		if(key.down || key.up || key.right || key.left)
			player.walking = true;
		else
			player.walking = false;

	/*	level.getContent(p);
		level.getContentNorth(p);
		level.getContentEast(p);
		level.getContentSouth(p);
		level.getContentWest(p);
		*/
		
		Direction d = Direction.NONE;

		if(key.down || key.up){
			if(p.X%1.0 <= 0.4) d = Direction.RIGHT;
			else if(p.X%1.0 > 0.6) d = Direction.LEFT;
			
			else if(key.down ) d = Direction.DOWN;
			else if(key.up) d = Direction.UP;
		}
		if(key.right || key.left){
			if(p.Y%1.0 <= 0.4) d = Direction.DOWN;
			else if(p.Y%1.0 > 0.6) d = Direction.UP;
			
			else if(key.right) d = Direction.RIGHT;
			else if(key.left) d = Direction.LEFT;
		}
		
		boolean ignoreObstacles = false;
		PointXY newP = new PointXY(p);
		
		switch(d){
			case UP:
				newP.Y -= player.speed;
				if(ignoreObstacles || level.getContentUp(newP)==Type.FLOOR) player.setPosition(newP);
				player.dir = 0;
				break;
			case DOWN:
				newP.Y += player.speed;
				if(ignoreObstacles || level.getContentDown(newP)==Type.FLOOR) player.setPosition(newP);
				player.dir = 2;
				break;
			case RIGHT:
				newP.X += player.speed;
				if(ignoreObstacles || level.getContentRight(newP)==Type.FLOOR) player.setPosition(newP);
				player.dir = 3;
				break;
			case LEFT:
				newP.X -= player.speed;
				if(ignoreObstacles || level.getContentLeft(newP)==Type.FLOOR) player.setPosition(newP);
				player.dir = 1;
				break;
			case NONE:
				break;
		}
		
		if(key.space){
			level.spawnBomb(player);
		}
		
		if(key.esc) player.setPosition(new PointXY(1.5f, 1.5f));
		
		System.out.format("x:%f \t y:%f \t d:%s\n", p.X, p.Y, d );
		
		
		/*
		if(key.down){
			player.dir = 2;
			if(level.getContentSouth(p.X, p.Y)==Type.FLOOR)
				d = Direction.SOUTH;
			else{
				if(p.X%1 <= 0.25)	d = Direction.EAST;
				else				d = Direction.WEST;
			}
		}
		if(key.up){
			player.dir = 0;
			if(level.getContentNorth(p.X, p.Y)==Type.FLOOR)
				d = Direction.NORTH;
			else{
				if(p.X%1 <= 0.25)	player.realX+=player.speed;
				else				player.realX-=player.speed;
			}
		}
		if(key.right){
			player.dir = 3;
			if(level.getContentEast(p.X, p.Y)==Type.FLOOR)
				d = Direction.EAST;
			else{
				if(p.Y%1 >= 0.5)	player.realY+=player.speed;
				else				player.realY-=player.speed;
			}
		}
		if(key.left){
			player.dir = 1;
			if(level.getContentWest(p.X, p.Y)==Type.FLOOR)
				d = Direction.WEST;
			else{
				if(p.Y%1 >= 0.5)	player.realY+=player.speed;
				else				player.realY-=player.speed;
			}
		}*/
		
	}
	
}
