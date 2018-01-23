package com.Capn.Practron.entity.projectile;

import com.Capn.Practron.entity.spawner.ParticleSpawner;
import com.Capn.Practron.entity.spawner.Spawner;
import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.Sprite;

public class MegaShot extends Projectile{

	public static final int FIRE_RATE = 10; //higher rate is slower fire
	
	public MegaShot(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 10;
		damage = 20;
		sprite = Sprite.projectile_mega;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update()
	{
		move();
		if(level.tileCollision((int)(x + nx),(int)(y + ny),8, 6, 5))
		{
			level.add(new ParticleSpawner((int)x,(int)y-3, 44, 54,level));
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
		
		
		System.out.println("Distance: " + distance());
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
