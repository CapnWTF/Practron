package com.Capn.Practron.entity.projectile;


import java.util.List;

import com.Capn.Practron.entity.mob.Player;
import com.Capn.Practron.entity.spawner.ParticleSpawner;
import com.Capn.Practron.entity.spawner.Spawner;
import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.Sprite;
import com.Capn.Practron.level.Node;
import com.Capn.Practron.util.Vector2i;


public class ChaseShot extends Projectile{

public static final int FIRE_RATE = 250; //higher rate is slower fire
private double xa = 0;
private double ya = 0;

	public ChaseShot(double x, double y, double dir) {
		super(x, y, dir);
		range = 500;
		speed = 1;
		damage = 50;
		sprite = Sprite.projectile_goo;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update()
	{
		move();
		if(level.tileCollision((int)(x + nx),(int)(y + ny),8,6,5))
		{
			
			level.add(new ParticleSpawner((int)x,(int)y-3, 44, 54,level));
			remove();
			
		}
		
	}
	
	protected void move()
	{
		xa = 0;
		ya = 0;
		
		List<Node> path = null;
		Player player = level.getClientPlayer();
		List<Player> players = level.getPlayers(this,150);
		
		int px = (int)level.getPlayerAt(0).getX();
		int py = (int)level.getPlayerAt(0).getY();
		
		Vector2i start = new Vector2i((int)(getX()/16),(int)(getY() /16));
		Vector2i destination = new Vector2i(px / 16, py / 16);
		
			
			path = level.findPath(start, destination);
		
		
		if (path != null)
		{
			if ( path.size() > 0)
			{
				Vector2i vec = path.get(path.size()-1).tile;
				if ( x < vec.getX()*16)
				{
					xa++;
				}
				if ( x > vec.getX()*16)
				{
					xa--;
				}
				
				if ( y < vec.getY()*16)
				{
					ya++;
				}
					
				if( y > vec.getY()*16)	
					{
					ya--;
					}
				
				
			}
			if(xa !=0 || ya != 0)
			{
			x = x + xa*nx;
			y = y + ya*ny;
			}
		}
		
		
	}
	public void render(Screen screen)
	{
			screen.renderProjectile((int)x-8, (int)y -4, this);
	}	
}
