package entity;

import graphics.Sprite;
import level.PointXY;

public class Player extends Entity {
//	public float realX;
//	public float realY;
	public int bombRange;
	public boolean walking;
	public float speed;
	public Bomb bomb;
	public int bombsInHand;
	public int speedx;
	public int speedy;
	public int dir;
	public Sprite sprite;
	public float gridX;
	public float gridY;
	private int bombInitCounter = 3; // default value of the counter for the pllayer's bombs
	
	public Player(float x, float y) {
		this.x = (int)x;
		this.y = (int)y;
	//	this.realX = x*Sprite.SPRITE_SIZE;
	//	this.realY = y*Sprite.SPRITE_SIZE;
		this.gridX = x;
		this.gridY = y;
		walking = false;
		this.type = Type.PLAYER;
		this.sprite = Sprite.player_forward_1;
		
		// base values
		speed = .1f;
		bombsInHand = 3;
		bombRange = 3;
		
	}
	
	public void setPosition(PointXY p){
		this.gridX = p.X;
		this.gridY = p.Y;

	//	this.realX = p.X*Sprite.SPRITE_SIZE;
	//	this.realY = p.Y*Sprite.SPRITE_SIZE;
	}
	public PointXY getPosition(){
		return new PointXY(this.gridX, this.gridY);
	}
	
	/* 
	public void setPosition(PointXY p){
		this.realerX = p.X*Sprite.SPRITE_SIZE;
		this.realerY = p.Y*Sprite.SPRITE_SIZE;
	}
	 */
	/*
	public PointXY getPosition(){
		//return new PointXY(this.realX/Sprite.SPRITE_SIZE, (this.realY+Sprite.SPRITE_SIZE*1.5f)/Sprite.SPRITE_SIZE);
		return new PointXY(this.realX/Sprite.SPRITE_SIZE, (this.realY)/Sprite.SPRITE_SIZE);
	}
	*/
	
	public void updateAnimation() {
		if(frame < 7500) frame++;
		else frame = 0;
		
		if(dir == 0) {
			sprite = Sprite.player_back_1;
			if(walking) {
				if(frame % 40 > 35) {
					sprite = Sprite.player_back_8;
				}else if(frame % 40 > 30) {
					sprite = Sprite.player_back_7;
				}else if(frame % 40 > 25) {
					sprite = Sprite.player_back_6;
				}else if(frame % 40 > 20) {
					sprite = Sprite.player_back_5;
				}else if(frame % 40 > 15) {
					sprite = Sprite.player_back_4;
				}else if(frame % 40 > 10) {
					sprite = Sprite.player_back_3;
				}else {
					sprite = Sprite.player_back_2;
				}
			}
		}else if(dir == 1) {
			sprite = Sprite.player_right_1;
			if(walking) {
				if(frame % 40 > 35) {
					sprite = Sprite.player_right_8;
				}else if(frame % 40 > 30) {
					sprite = Sprite.player_right_7;
				}else if(frame % 40 > 25) {
					sprite = Sprite.player_right_6;
				}else if(frame % 40 > 20) {
					sprite = Sprite.player_right_5;
				}else if(frame % 40 > 15) {
					sprite = Sprite.player_right_4;
				}else if(frame % 40 > 10) {
					sprite = Sprite.player_right_3;
				}else {
					sprite = Sprite.player_right_2;
				}
			}
		}else if(dir == 2) {
			sprite = Sprite.player_forward_1;
			if(walking) {
				if(frame % 40 > 35) {
					sprite = Sprite.player_forward_8;
				}else if(frame % 40 > 30) {
					sprite = Sprite.player_forward_7;
				}else if(frame % 40 > 25) {
					sprite = Sprite.player_forward_6;
				}else if(frame % 40 > 20) {
					sprite = Sprite.player_forward_5;
				}else if(frame % 40 > 15) {
					sprite = Sprite.player_forward_4;
				}else if(frame % 40 > 10) {
					sprite = Sprite.player_forward_3;
				}else {
					sprite = Sprite.player_forward_2;
				}
			}
		}else {
			sprite = Sprite.player_left_1;
			if(walking) {
				if(frame % 40 > 35) {
					sprite = Sprite.player_left_8;
				}else if(frame % 40 > 30) {
					sprite = Sprite.player_left_7;
				}else if(frame % 40 > 25) {
					sprite = Sprite.player_left_6;
				}else if(frame % 40 > 20) {
					sprite = Sprite.player_left_5;
				}else if(frame % 40 > 15) {
					sprite = Sprite.player_left_4;
				}else if(frame % 40 > 10) {
					sprite = Sprite.player_left_3;
				}else {
					sprite = Sprite.player_left_2;
				}
			}
		}
		
	}

	public int getBombInitCounter() {
		return bombInitCounter;
	}

}
