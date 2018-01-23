//abstract class for game objects and entities eyy

package com.Capn.Practron.entity;

import java.util.Random;

import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.level.Level;

public abstract class Entity {

	public int x;
	public int y;
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
		
	}
	
	public void remove()
	{
		
		//remove from level/screen	
	 removed = true;
	 
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
