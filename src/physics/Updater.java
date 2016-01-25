package physics;

import java.util.ArrayList;

import entity.Bomb;
import entity.Direction;
import entity.Flame;
import entity.Player;
import entity.Type;
import input.Keyboard;
import level.Level;
import level.PointXY;

public class Updater {
	public Level level;
	public ArrayList<Bomb> toRemove = new ArrayList<Bomb>();
	
	public Updater(Level level){
		this.level = level;
	}
	
	public void update(){
		updatePlayers();
		updateBomb();
		updateFlame();
		level.bomb.removeAll(toRemove);
	}
	
	public void updateBomb(){
		ArrayList<Bomb> toRemove = new ArrayList<Bomb>();
		for(Bomb b : level.bomb){
			b.bombCounter--;
			if(b.bombCounter<=0){
				destroyBomb(b);
			}
		}
		//level.bomb.removeAll(toRemove);
	}
	
	public void destroyBomb(Bomb b){
		b.player.bombsInHand++;
		level.matrix[b.x][b.y] = Type.FLOOR;
		createFlame(b);
		toRemove.add(b);
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
				if (level.matrix[up.x][up.y] != Type.BRICK && upBrick){
					if(level.matrix[up.x][up.y] == Type.BOMB){
						destroyBomb(level.getBomb(up.x, up.y));
					}
					level.flame.add(up);
					level.matrix[up.x][up.y] = Type.FLAME;
				}else
					upBrick = false;
				Flame down = new Flame(b.x,b.y+i);
				if (level.matrix[down.x][down.y] != Type.BRICK && downBrick){
					if(level.matrix[down.x][down.y] == Type.BOMB){
						destroyBomb(level.getBomb(down.x, down.y));
					}
					level.flame.add(down);
					level.matrix[down.x][down.y] = Type.FLAME;
				}else
					downBrick = false;
				Flame right = new Flame(b.x+i,b.y);
				if (level.matrix[right.x][right.y] != Type.BRICK && rightBrick){
					if(level.matrix[right.x][right.y] == Type.BOMB){
						destroyBomb(level.getBomb(right.x, right.y));
					}
					level.flame.add(right);
					level.matrix[right.x][right.y] = Type.FLAME;
				}else
					rightBrick = false;
				Flame left = new Flame(b.x-i,b.y);
				if (level.matrix[left.x][left.y] != Type.BRICK && leftBrick){
					if(level.matrix[left.x][left.y] == Type.BOMB){
						destroyBomb(level.getBomb(left.x, left.y));
					}
					level.flame.add(left);
					level.matrix[left.x][left.y] = Type.FLAME;
				}else
					leftBrick = false;
			}catch(Exception e){
				
			}
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
