package physics;

import java.util.ArrayList;

import entity.Bomb;
import entity.Entity;
import entity.Direction;
import entity.Flame;
import entity.Player;
import entity.Type;
import input.Keyboard;
import level.Level;
import level.PointXY;

public class Updater {
	public Level level;
	public ArrayList<Bomb> toRemoveBomb = new ArrayList<Bomb>();
	public ArrayList<Entity> toRemoveBrick = new ArrayList<Entity>();

	public Updater(Level level){
		this.level = level;
	}
	
	public void update(){
		updatePlayers();
		checkItems();
		updateBomb();
		updateFlame();
		level.bomb.removeAll(toRemoveBomb);
		level.brick.removeAll(toRemoveBrick);

	}
	
	public void checkItems(){
		
	}
	
	public void updateBomb(){
		//ArrayList<Bomb> toRemove = new ArrayList<Bomb>();
		for(Bomb bomb : level.bomb){
			bomb.bombCounter--;
			if(bomb.bombCounter<=0){
				destroyBomb(bomb);
			}
		}
		//level.bomb.removeAll(toRemove);
	}
	
	public void destroyBomb(Bomb bomb){
		bomb.player.bombsInHand++;
		level.matrix[bomb.x][bomb.y] = Type.FLOOR;
		createFlame(bomb);
		toRemoveBomb.add(bomb);
	}
	
	public void destroyBrick(Entity brick){
		//System.out.println("Destroing BRICK "+b.x+ " "+b.y);
		level.matrix[brick.x][brick.y] = Type.FLOOR;
		toRemoveBrick.add(brick);
		level.matrix[brick.x][brick.y] = Type.BOMBITEM;
		level.item.add(new Entity(brick.x,brick.y,Type.BOMBITEM));
	}
	
	public void updateFlame(){
		ArrayList<Flame> toRemove = new ArrayList<Flame>();
		for(Flame f : level.flame){
			f.counter--;
			if(f.counter<=0){
				level.matrix[f.x][f.y] = Type.FLOOR;
				toRemove.add(f);
			}
		}
		level.flame.removeAll(toRemove);
	}
	
	public boolean checkType(Type t){
		return t!=Type.BLOCK;
		//return (t!=Type.BLOCK && t!=Type.BRICK);
	}
	
	public void createFlame(Bomb b){
		boolean upBrick = true;
		boolean downBrick = true;
		boolean rightBrick= true;
		boolean leftBrick = true;

		for(int i = 1; i<=b.range;i++){
			Flame center = new Flame(b.x,b.y);
			level.flame.add(center);
			level.matrix[center.x][center.y] = Type.FLAME;

			try{		
				Flame up = new Flame(b.x,b.y-i);
				if (checkType(level.matrix[up.x][up.y]) && upBrick){
					if(level.matrix[up.x][up.y] == Type.BOMB){
						destroyBomb(level.getBomb(up.x, up.y));
					}
					if(level.matrix[up.x][up.y] == Type.BRICK){
						destroyBrick(level.getBrick(up.x, up.y));
						upBrick = b.player.nuclearBomb;
					}
					level.flame.add(up);
					level.matrix[up.x][up.y] = Type.FLAME;
				}else
					upBrick = false;
			}
			catch(Exception e){}
			
			try{
				Flame down = new Flame(b.x,b.y+i);
				if (checkType(level.matrix[down.x][down.y]) && downBrick){
					if(level.matrix[down.x][down.y] == Type.BOMB){
						destroyBomb(level.getBomb(down.x, down.y));
					}
					if(level.matrix[down.x][down.y] == Type.BRICK){
						destroyBrick(level.getBrick(down.x, down.y));
						downBrick = b.player.nuclearBomb;
					}					
					level.flame.add(down);
					level.matrix[down.x][down.y] = Type.FLAME;
				}else
					downBrick = false;
			}
			catch(Exception e){}
			
			try{
				Flame right = new Flame(b.x+i,b.y);
				if (checkType(level.matrix[right.x][right.y]) && rightBrick){
					if(level.matrix[right.x][right.y] == Type.BOMB){
						destroyBomb(level.getBomb(right.x, right.y));
					}
					if(level.matrix[right.x][right.y] == Type.BRICK){
						destroyBrick(level.getBrick(right.x, right.y));
						rightBrick = b.player.nuclearBomb;
					}
					level.flame.add(right);
					level.matrix[right.x][right.y] = Type.FLAME;
				}else
					rightBrick = false;
			}
			catch(Exception e){}
			
			try{
				Flame left = new Flame(b.x-i,b.y);
				if (checkType(level.matrix[left.x][left.y]) && leftBrick){
					if(level.matrix[left.x][left.y] == Type.BOMB){
						destroyBomb(level.getBomb(left.x, left.y));
					}
					if(level.matrix[left.x][left.y] == Type.BRICK){
						destroyBrick(level.getBrick(left.x, left.y));
						leftBrick = b.player.nuclearBomb;
					}					
					level.flame.add(left);
					level.matrix[left.x][left.y] = Type.FLAME;
				}else
					leftBrick = false;
			}
			catch(Exception e){}
				
		}
	}
	
	public void updatePlayers(){
		for (Player player : level.players)
			updatePlayer(player);
	}
	
	public void updatePlayer(Player player){
		player.updateAnimation();
		Keyboard key = player.input;
		
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
		
		//System.out.format("x:%f \t y:%f \t d:%s\n", p.X, p.Y, d );
		
	}
	
}
