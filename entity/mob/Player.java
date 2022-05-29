package com.Capn.Practron.entity.mob;

import java.util.List;

import com.Capn.Practron.Game;
import com.Capn.Practron.Input.Kbd;
import com.Capn.Practron.Input.Mouse;
import com.Capn.Practron.entity.Entity;
import com.Capn.Practron.entity.projectile.GooShot;
import com.Capn.Practron.entity.projectile.MegaShot;
import com.Capn.Practron.entity.projectile.Projectile;
import com.Capn.Practron.graphics.AnimatedSprite;
import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.Sprite;
import com.Capn.Practron.graphics.SpriteSheet;
import com.Capn.Practron.util.Debug;

public class Player extends Mob
{
	private Kbd input;
	private Sprite sprite;
	private int anim = 0;
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down,32,32,6);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up,32,32,6);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left,32,32,6);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right,32,32,6);
	private AnimatedSprite animSprite = down;
	
	Projectile p;
	public int fireRate = 0;
	public int buttontick = 20;//time in frames before the game checks for a lower tier input again
	
	
	
	
	public Player(Kbd input)
	{
		this.input = input;
		sprite = Sprite.player;
	}
	
	public Player(int x, int y, Kbd input)
	{
		this.x = x;
		this.y = y;
		this.input = input;
		fireRate = MegaShot.FIRE_RATE;
		this.weapon = 0;
		
	}
	public void clear()
	{ 
		for (int i = 0; i < level.getProjectiles().size(); i++)
		{
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved())
			{
				level.getProjectiles().remove(i);
			}
		}
		
	}
	
	public void update()
	{
		double speed = 2;
		if(walking){
			animSprite.update();
		}
		else animSprite.setFrame(0);
		if(fireRate > 0)
		{
			fireRate--;
		}
		
		if(buttontick > 0)
		{
			buttontick--;
		}
		if (input.weaponup && buttontick <=0)
		{
			weapon++;
			if (weapon >8) weapon = 8;
			System.out.println("weapon up" + weapon);
			buttontick = Game.buttontick;
		}
		if(input.weapondown && buttontick <= 0 )
		{
			weapon--;
			if(weapon <=0) weapon = 0;
			System.out.println("weapon down" + weapon);
			buttontick = Game.buttontick;
		}
		
		
		double xa = 0;
		double ya = 0;
		
		if(anim<6000){ anim++;}
		else {anim = 0;}
		
		
		
		if (input.up)
		{
			ya-= speed;
		animSprite = up;
		}
		
		if (input.down)
		{
			ya+= speed;
			animSprite = down;
		}
		
		if (input.left)
		{
			xa-= speed;
			animSprite = left;
		}
		
		if (input.right)
		{
			xa+= speed;
			animSprite = right;
		}
		
		if(xa !=0 || ya!= 0)
		{if (xa != 0 && ya !=0)
		{
			xa*=.4;
			ya*=.4;
			//xa *= .9;
			//ya *= .9;
		}
			move(xa,ya);
			walking = true;
		}
		else {walking = false;}
		clear();
		updateShooting();
	}
	
	private void updateShooting() {
		if(Mouse.getB()==1 && fireRate <= 0)
		{
			double dx = Mouse.getX()- Game.getWindowWidth()/2;
			double dy = Mouse.getY()- Game.getWindowHeight()/2;
			double dir = Math.atan2(dy, dx);
			shoot(x,y,dir,weapon);
			
			//shoot(x+5,y+5,dir+.2);
			
			//shoot(x-5,y-5,dir-.2);
			if(weapon == 0)
			{
			fireRate = MegaShot.FIRE_RATE;
			}
			else
			if(weapon == 1)
			{
				fireRate = GooShot.FIRE_RATE;
			}
			else{
				fireRate = 20;//forces the fire rate to be 20 as a catch all. This is just a placeholder until I make 7 more weapons or so.
			}
		}
		
	}

	public void render(Screen screen)
	{
		int flip = 0;
		// direction 3 is left, 1 is right, 0 is down, 2 is up
		/*if(dir == 0) 
			{
			sprite = Sprite.playerSideGun0;
			if(walking)
			{
				if(walking)
				{
					if(anim %20 > 10)
					{
						sprite = Sprite.playerSide1;
					}
				}
			}
			}
		if(dir == 1 || dir == 3) 
		{
			sprite = Sprite.playerSideGun0;
			if(walking)
			{
				if(anim %20 > 10)
				{
					sprite = Sprite.playerSideGun1;
				}
				else{
					sprite = Sprite.playerSideGun2;
				}
			}
		}
		*/
		//if(dir == 2) {sprite = Sprite.playerSide0;}
		if(dir == Direction.LEFT) {flip = 1;}
		//System.out.println("Direction is: "+ dir);
		 
		sprite = animSprite.getSprite();
		 screen.renderMob((int)(x), (int)(y), sprite, flip);
			//Debug.drawRect(screen, x, y+16, 16, 16, 0xc00d31, true);
			Debug.drawRect(screen, x, y, ((x )- 0%2*16) /16, ((y + 0)- 1/2*16) /16, 0xffffff, true);
		
		
		/*
		 * if (dir == 0) sprite = Sprite.player_forward;
		 * if (dir == 1) sprite = Sprite.player_right;
		 * etc.
		 * 
		 * for the flip, 1 is x flip, 2 is y flip, 3 is xy flip
		 */
		
		}
}
