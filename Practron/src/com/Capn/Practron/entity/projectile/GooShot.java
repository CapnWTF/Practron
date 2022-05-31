package com.Capn.Practron.entity.projectile;

import com.Capn.Practron.entity.spawner.ParticleSpawner;
import com.Capn.Practron.entity.spawner.Spawner;
import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.Sprite;

public class GooShot extends Projectile{

	public static final int FIRE_RATE = 25; //higher rate is slower fire
	
	public GooShot(double x, double y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 5;
		damage = 50;
		sprite = Sprite.projectile_goo;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update()
	{
		move();
		if(level.tileCollision((int)(x + nx),(int)(y + ny),11,2,4))
		{
			nx = (nx*-1);
			//ny = (ny * -1);
			
			
		}
		/*if(level.tileCollision((int)(x + nx),(int)(0 + 0),11,2,4))
		{
			
			ny = (ny * -1);
			
			
		}*/
		if(time >= range)
		{
			remove();
		}
	}
	
	private double distance()
	{
		double dist = 0;
		dist = Math.sqrt(Math.abs( ( (xOrigin - x)*(xOrigin -x) ) + ((yOrigin - y)*(yOrigin - y) )));
		
		return dist;
	}
	
	protected void move()
	{
		
		
		x += nx;
		y += ny;
		time++;
		
		//System.out.println("Distance: " + distance());
		if(distance() > range )
		{
			remove();
		}
		
	}
	
	public void render(Screen screen)
	{
		screen.renderProjectile((int)x-8, (int)y -4, this);
	}
}
