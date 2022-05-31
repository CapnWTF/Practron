//abstract class for game objects and entities eyy

package com.Capn.Practron.entity;

import java.util.Random;

import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.Sprite;
import com.Capn.Practron.level.Level;

public abstract class Entity {

	protected int x;
	protected int y;
	protected Sprite sprite;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	
	
	protected void move()
	{
		
	}
	
	public void update()
	{
		
	}
	
	public void render(Screen screen)
	{
		if(sprite != null) screen.renderSprite(x, y, sprite, true);
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}
	
	
	public void remove()
	{
		
		//remove from level/screen	
	 removed = true;
	 
	}
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	public boolean isRemoved()
	{
		return removed;
	}
	
	public void init(Level level)
	{
		this.level = level;
		
	}
}
