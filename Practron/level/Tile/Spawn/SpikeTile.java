package com.Capn.Practron.level.Tile.Spawn;

import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.Sprite;
import com.Capn.Practron.level.Tile.Tile;

public class SpikeTile extends Tile {
	
	public SpikeTile(Sprite sprite) {
		super(sprite);
		
	}
	
	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x << 4, y << 4, this);
		//put a render here
	}

	public boolean solid()
	{
		return true;
	}
}