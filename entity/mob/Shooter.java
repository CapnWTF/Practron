package com.Capn.Practron.entity.mob;

import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.Sprite;

public class Shooter extends Mob{

	public Shooter ( int x, int y)
	{
		this.x = x <<4; //this X is X*16
		this.y = y << 4; //second verse same as the first
		sprite = Sprite.dummy;
		
	}
	
	
	public void update()
	{
	Player p = level.getClientPlayer();
	
double px = (p.getX() - x);
double py = (p.getY() - y);
double dir = Math.atan2(py, px);
shoot(x + 16 ,y + 16, dir,0);
	}
	
	public void render (Screen screen)
	{
		screen.renderMob(x, y, this);
	}
}
