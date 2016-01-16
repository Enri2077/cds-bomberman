package physics;

import entity.Player;
import entity.Type;
import graphics.Sprite;
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
		PointXY pRight = new PointXY();
		PointXY pLeft = new PointXY();
		PointXY pMiddle = new PointXY();
		pRight.X = (player.realX + +Sprite.SPRITE_SIZE/2)/Sprite.SPRITE_SIZE;
		pLeft.X = (player.realX+Sprite.SPRITE_SIZE*1.5f)/Sprite.SPRITE_SIZE;

		pLeft.Y = (player.realY +Sprite.SPRITE_SIZE*1.8f)/Sprite.SPRITE_SIZE;
		pRight.Y = (player.realY +Sprite.SPRITE_SIZE*1.8f)/Sprite.SPRITE_SIZE;
		
		pMiddle.X = (pRight.X+pLeft.X)/2;
		pMiddle.Y = (player.realY+Sprite.SPRITE_SIZE)/Sprite.SPRITE_SIZE;
		int newX = (int)((player.realX+Sprite.SPRITE_SIZE)/Sprite.SPRITE_SIZE);
		int newY = (int)((player.realY+Sprite.SPRITE_SIZE)/Sprite.SPRITE_SIZE);

		System.out.println("Rx:"+pMiddle.X +" X:"+(int)pMiddle.X+" Ry:"+pMiddle.Y+" Y:"+(int)(pMiddle.Y+.5f));
		
		if(key.down || key.up || key.right || key.left)
			player.walking = true;
		else
			player.walking = false;
		
		if(key.down){
			player.dir = 2;
			if(level.matrix[(int)((pMiddle.X+pLeft.X)/2)][(int)pMiddle.Y+1]!=Type.BRICK && level.matrix[(int)((pMiddle.X+pRight.X)/2)][(int)pMiddle.Y+1]!=Type.BRICK)
				player.realY+=player.speed;
		}
		if(key.up){
			player.dir = 0;
			if(level.matrix[(int)((pMiddle.X+pLeft.X)/2)][(int)(pMiddle.Y+.5f)]!=Type.BRICK && level.matrix[(int)((pMiddle.X+pRight.X)/2)][(int)(pMiddle.Y+.5f)]!=Type.BRICK)
				player.realY-=player.speed;
		}
		if(key.right){
			player.dir = 3;
			if(level.matrix[(int)pRight.X+1][(int)pRight.Y]!=Type.BRICK)
				player.realX+=player.speed;
		}
		if(key.left){
			player.dir = 1;
		    if(level.matrix[(int)pLeft.X-1][(int)pLeft.Y]!=Type.BRICK)
		    	player.realX-=player.speed;
		}
	}
	
}
