package com.Capn.Practron.level.Tile.Spawn;

import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.Sprite;
import com.Capn.Practron.level.Tile.Tile;

public class NetTile extends Tile {
	public NetTile(Sprite sprite) {
		super(sprite);
		
	}
	
	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x << 4, y << 4, this);
		//put a render here
	}

}
