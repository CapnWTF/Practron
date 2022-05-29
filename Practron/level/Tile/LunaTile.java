package com.Capn.Practron.level.Tile;

import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.Sprite;

public class LunaTile extends Tile {

	public LunaTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x << 4, y << 4, this);
		//put a render here
	}
}
