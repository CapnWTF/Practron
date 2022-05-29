package com.Capn.Practron.entity.projectile;

import com.Capn.Practron.entity.Entity;
import com.Capn.Practron.graphics.Sprite;

public abstract class Projectile extends Entity
{

	protected final double xOrigin;
	protected final double yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x;
	protected double y;
	protected double nx;
	protected double ny;
	protected double speed;
	protected double fireRate;
	protected double damage;
	protected double range;
	protected double distance;
	protected int time = 0;
	
	
	public Projectile(double x, double y, double dir)
	{
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		
		this.x = x;
		this.y = y;
	}
	public Sprite getSprite()
	{
		return sprite;
	}
	public int getSpriteSize()
	{
		return sprite.SIZE;
	}
	
	protected void move()
	{
		
	}
}
