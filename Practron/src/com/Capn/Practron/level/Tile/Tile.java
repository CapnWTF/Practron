package com.Capn.Practron.level.Tile;

import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.Sprite;
import com.Capn.Practron.level.Tile.Spawn.DuctTile;
import com.Capn.Practron.level.Tile.Spawn.FloorTile;
import com.Capn.Practron.level.Tile.Spawn.LadderTile;
import com.Capn.Practron.level.Tile.Spawn.NetTile;
import com.Capn.Practron.level.Tile.Spawn.PipeTile;
import com.Capn.Practron.level.Tile.Spawn.SpikeTile;

public class Tile 
{
	public int x;
	public int y;
	public int width;
	public int height;
	public Sprite sprite;
	
	public static Tile sky = new SkyTile(Sprite.sky);
	public static Tile grass = new GrassTile(Sprite.lava);
	public static Tile lava = new LavaTile(Sprite.grass);
	public static Tile luna = new LunaTile(Sprite.luna);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile purp = new FloorTile(Sprite.purp);
	public static Tile wood = new FloorTile(Sprite.wood);
	public static Tile black = new FloorTile(Sprite.black);
	public static Tile orang = new FloorTile(Sprite.orang);
	
	//Spawn Tiles
	public static Tile floor1 = new FloorTile(Sprite.floor1);
	public static Tile floor2 = new FloorTile(Sprite.floor2);
	public static Tile pipe1 = new PipeTile(Sprite.pipe1);
	public static Tile pipe2 = new PipeTile(Sprite.pipe2);
	public static Tile net1 = new NetTile(Sprite.net1);
	public static Tile net2 = new NetTile(Sprite.net2);
	public static Tile duct1 = new DuctTile(Sprite.duct1);
	public static Tile duct2 = new DuctTile(Sprite.duct2);
	public static Tile duct3 = new DuctTile(Sprite.duct3);
	public static Tile duct4 = new DuctTile(Sprite.duct4);
	public static Tile duct5 = new DuctTile(Sprite.duct5);
	public static Tile duct6 = new DuctTile(Sprite.duct6);
	public static Tile spike = new SpikeTile(Sprite.spike);
	public static Tile ladder = new LadderTile(Sprite.ladder);
	
	public static final int col_floor1 = 0xff187C16;
	public static final int col_floor2 = 0xff4CA581;
	public static final int col_pipe1 = 0xff600001;
	public static final int col_pipe2 = 0xffFF7F7F;
	public static final int col_net1 = 0xff600060;
	public static final int col_net2 = 0xff7C793D;
	public static final int col_duct1 = 0xffFF4300;
	public static final int col_duct2 = 0xffFFD800;
	public static final int col_duct3 = 0xff00137F;
	public static final int col_duct4 = 0xffFF1ECE;
	public static final int col_duct5 = 0xff3F647F;
	public static final int col_duct6 = 0xff4CFF00;
	public static final int col_spike = 0xff66FFF2;
	public static final int col_ladder =0xffFF9951;
	
	
	//Tile Data
	public Tile(Sprite sprite)
	{
	this.sprite = sprite;	
	}
	public void render(int x, int y, Screen screen)
	{
		//screen.renderTile(x, y, this);
		//put a render here
	}
	public boolean solid()
	{
		return false;
	}
	
}//end Tile
