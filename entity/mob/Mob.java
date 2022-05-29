package com.Capn.Practron.entity.mob;

import java.util.List;
import java.util.ArrayList;
import com.Capn.Practron.entity.Entity;
import com.Capn.Practron.entity.particle.Particle;
import com.Capn.Practron.entity.projectile.ChaseShot;
import com.Capn.Practron.entity.projectile.GooShot;
import com.Capn.Practron.entity.projectile.MegaShot;
import com.Capn.Practron.entity.projectile.Projectile;
import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.Sprite;
import com.Capn.Practron.util.Debug;

public abstract class Mob extends Entity
{
	protected static int UNIT = 16; //size of one tile
	
	protected boolean moving = false;
	protected boolean walking = false;

	protected int weapon = 0;
	
	protected enum Direction
	{
		UP,DOWN,LEFT,RIGHT, NEUTRAL
	}
	protected Direction dir;
	
	public void move(double xa, double ya)
	{
		if( xa != 0 && ya != 0)
		{
			move(xa,0);

			
			move(0,ya);
			
			return;
		}
		
		if (xa>0)
		{
			dir = Direction.RIGHT;//moving right
		}
		if (xa<0)
		{
			dir = Direction.LEFT; //moving left
		}
		if (ya<0)
		{
			dir = Direction.UP; //moving up
		}
		if (ya>0)
		{
			dir = Direction.DOWN; //moving down
		}
		
		if(ya == 0 && xa == 0)
		{
			dir = Direction.NEUTRAL;
		}
		//all values will be -1,0, or 1

//		while(xa != 0)
//		{
//			 if(xa > 1)
//			 {
//				 if(!collision(abs(xa),ya))
//					{
//					this.x += abs(xa);
//					}
//				 xa -= abs(xa);
//			 }
//			 else
//			 {
//				 if(!collision(abs(xa),ya))
//					{
//					this.x += xa;
//					}
//				 xa = 0;
//			 }
//		}
//		while(ya != 0)
//		{
//			 if(ya > 1)
//			 {
//				 if(!collision(abs(xa),ya))
//					{
//					this.y += abs(ya);
//					}
//				 ya -= abs(ya);
//			 }
//			 else
//			 {
//				 if(!collision(abs(xa),ya))
//					{
//					this.y += ya;
//					}
//				 ya = 0;
//			 }
//		}
			
for(int x =0;x<Math.abs(xa);x++)
{
	if(!collision(abs(xa),ya))
	{
		this.x+= abs(xa);
	}
}

for(int y = 0;y<Math.abs(ya);y++)
{
	if(!collision(xa,abs(ya)))
	{
		this.y+= abs(ya);
	}
}
		
		
	}
	
	private int abs( double value)
	{
		if ( value < 0) {return -1;}
		 return 1;
	}
	
	public abstract void update();
	
	protected void shoot(int x, int y, double dir, int weapon)
	{
		//dir *= 180 /Math.PI;
		Projectile p;
		//System.out.println("weapon is: " + weapon);
		if(weapon == 0)
		{
		p = new MegaShot(x,y, dir);
		}
		else
		if ( weapon == 1)
		{
			p = new MegaShot(x,y, dir);
			//p = new ChaseShot(x,y,dir); NOT YET IMPLEMENTED
		}
		else{
			 p = new GooShot(x,y,dir);
		}
		//System.out.println("Angle: "+ dir);
		level.add(p);
		//System.out.println("Angle: "+ dir);
	}
	
	private boolean collision( double xa, double ya)
	{
		boolean solid = false;
		for(int c = 0; c<4; c++)
		{
			
			double xt = ((x + xa )- c%2*16) /16; 
			double yt = ((y+16 + ya)- c/2*16) /16;
			
			int ix = (int) Math.ceil(xt);
			int iy = (int)Math.ceil(yt);
			
			if(c %2 == 0)
			{
				ix = (int) Math.floor(xt);
				//System.out.println(ix);
			}
			
			if(c /2 == 0)
			{
				iy = (int) Math.floor(yt);
				//System.out.println(iy);
			}
			//System.out.println(x + " , " + y + " , " + xa + " , " + ya + " AND ix: " + ix + " , iy " + iy);

			if (level.getTile(ix, iy).solid())
			{
				solid = true;
			};
			
		}
		
		//System.out.println(solid);
		return solid;
		
	}
	
	private boolean collision( double xa, double ya, boolean solis)
	{
		boolean solid = false;
		
		if(x < level.getTile(x,y).sprite.getX() + level.getTile(x, y).sprite.getWidth() 
				&& x + xa > level.getTile(x,y).sprite.getX()
				&& y < level.getTile(x,y).sprite.getY() + level.getTile(x,y).sprite.getHeight()
				&& y + ya > level.getTile(x,y).sprite.getY())
		{
			if(level.getTile(x, y).solid())
			{
				//System.out.println("BUMP " + level.getTile(x,y));
				
			}
		}
			
		
		//System.out.println("dont use this collision detection");
		return solid;
		
	}
	
	
	
	public abstract void render(Screen screen);
	
}
