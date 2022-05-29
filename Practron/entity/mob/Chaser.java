package com.Capn.Practron.entity.mob;

import java.util.List;

import com.Capn.Practron.entity.mob.Mob.Direction;
import com.Capn.Practron.graphics.AnimatedSprite;
import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.SpriteSheet;

public class Chaser extends Mob{

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummyWalk,32,32,3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummyWalk,32,32,3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummyWalk,32,32,3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummyWalk,32,32,3);
	
	private AnimatedSprite shoot = new AnimatedSprite(SpriteSheet.dummyShot,32,32,4);
	private AnimatedSprite idle = new AnimatedSprite(SpriteSheet.dummyIdle,32,32,3);
	
	private AnimatedSprite animSprite = idle;
	
	private int time = 0;
	
	private double xa = 0;
	private double ya = 0;
	private double speed = 1;
	
	public Chaser (int x, int y)
	{
		this.x = x << 4;
		this.y = y << 4;
		//sprite = animSprite.getSprite();
	}
	
	protected void move()
	{
		xa = 0;
		ya = 0;
		
		Player player = level.getClientPlayer();
		List<Player> players = level.getPlayers(this,50);
		if(players.size() > 0)
			{
			if (x < player.getX() || x < player.getX() - 1 || x < player.getX() + 1)
			{
				xa+=speed;
			}
			
			if (x > player.getX() || x> player.getX() - 1 || x> player.getX() + 1)
			{
				xa-=speed;
			}
			
			
			if (y < player.getY() || y < player.getY() - 1 || y < player.getY() + 1)
			{
				ya+=speed;
			}
			
			if (y > player.getY() || y > player.getY() - 1 || y > player.getY() + 1)
			{
				ya-=speed;
			}
		}
		
		if(xa !=0 || ya!= 0)
		{
			
			

			move(xa,ya);
			walking = true;
			
		}
		else {
			walking = false;
			
		
		}
	}
	
	public void update() {
		
		
		move();
		if (walking) animSprite.update();
		else {
			animSprite.setFrame(0);
			}
		
		if (ya < 0)
		{
		animSprite = up;
		dir = Direction.UP;
		}
		
		if (ya > 0)
		{
			animSprite = down;
			dir = Direction.DOWN;
		}
		
		if (xa < 0)
		{
			animSprite = left;
			dir = Direction.LEFT;
		}
		
		if (xa > 0)
		{
			animSprite = right;
			dir = Direction.RIGHT;
		}
		
		
		
		
	}

	public void render(Screen screen) {
		
		//int flip = 0;
		//if(dir == Direction.RIGHT) {flip = 1;}
		sprite = animSprite.getSprite();
		screen.renderMob((x-16), (y-16), this);
	}

	
	
}
