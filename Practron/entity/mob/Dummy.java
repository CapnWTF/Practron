package com.Capn.Practron.entity.mob;

import com.Capn.Practron.entity.mob.Mob.Direction;
import com.Capn.Practron.graphics.AnimatedSprite;
import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.Sprite;
import com.Capn.Practron.graphics.SpriteSheet;

public class Dummy extends Mob {
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummyWalk,32,32,3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummyWalk,32,32,3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummyWalk,32,32,3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummyWalk,32,32,3);
	
	private AnimatedSprite shoot = new AnimatedSprite(SpriteSheet.dummyShot,32,32,4);
	private AnimatedSprite idle = new AnimatedSprite(SpriteSheet.dummyIdle,32,32,3);
	
	private AnimatedSprite animSprite = idle;
	
	private int time = 0;
	
	private int xa = 0;
	private int ya = 0;
	
	
	public Dummy(int x, int y)
	{
	this.x = x << 4;
	this.y = y << 4;
	sprite = Sprite.dummy;
		
	}
		
	public void update()
	{
		
		time++;
		if(time %(random.nextInt(30)+30) ==0)
		{
			xa = random.nextInt(3) -1;
			ya = random.nextInt(3) -1;
			
			if( random.nextInt(3)==0)
			{
				xa = 0;
				ya = 0;
				
			}
		}
		
		
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
		
		if(xa !=0 || ya!= 0)
		{
			
			

			move(xa,ya);
			walking = true;
			
		}
		else {walking = false;}
		
		
	}
	public void render(Screen screen)
	{
		int flip = 0;
		if(dir == Direction.RIGHT) {flip = 1;}
		sprite = animSprite.getSprite();
		screen.renderMob((int)x, (int)y, sprite, flip);
	}
}
