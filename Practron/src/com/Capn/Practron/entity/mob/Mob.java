package com.Capn.Practron.entity.mob;

import java.util.List;
import java.util.ArrayList;
import com.Capn.Practron.entity.Entity;
import com.Capn.Practron.entity.particle.Particle;
import com.Capn.Practron.entity.projectile.GooShot;
import com.Capn.Practron.entity.projectile.MegaShot;
import com.Capn.Practron.entity.projectile.Projectile;
import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.Sprite;

public abstract class Mob extends Entity
{
	protected static int UNIT = 16; //size of one tile
	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	protected int weapon = 0;
	
	
	public void move(int xa, int ya)
	{
		if( xa != 0 && ya != 0)
		{
			move(xa,0);
			move(0,ya);
			return;
		}
		
		if (xa>0)
		{
			dir = 1;//moving right
		}
		if (xa<0)
		{
			dir = 3; //moving left
		}
		if (ya<0)
		{
			dir = 2; //moving up
		}
		if (ya>0)
		{
			dir = 0; //moving down
		}
		
		//all values will be -1,0, or 1
				if(!collision(xa, ya)){
					x+= xa;
					y+= ya;	
					System.out.println(collision(xa,ya));
					
				}
				else
				{
					//shoot(x,y,0.2);
					//Particle p = new Particle(x,y,50);
					//level.add(p);
					System.out.println(collision(xa,ya));
				}
	}
	
	public void update()
	{
		
	}
	protected void shoot(int x, int y, double dir, int weapon)
	{
		//dir *= 180 /Math.PI;
		Projectile p;
		if(weapon == 0)
		{
		p = new MegaShot(x,y, dir);
		}
		else{
			 p = new GooShot(x,y,dir);
		}
		System.out.println("Angle: "+ dir);
		level.add(p);
		//System.out.println("Angle: "+ dir);
	}
	
	private boolean collision( int xa, int ya)
	{
		boolean solid = false;
		for(int c = 0; c<4; c++)
		{
			int xt = ((x+xa+4) + c%2*14 - 10) / UNIT;
			int yt = ((y + ya) + c/2*22 -7)/ UNIT;
			if (level.getTile(xt, yt).solid())
			{
		solid = true;
			}
		}
		
		
		return solid;
		
	}
	
	public void render()
	{
		
	}
}
