package com.Capn.Practron.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/tex/player/spritesheet.png",256);
	public static SpriteSheet spawn = new SpriteSheet("/tex/SpawnLevel/spawntiles.png", 64);
	public static SpriteSheet projectile_mega = new SpriteSheet("/tex/Projectiles/Mega.png", 48);
	
	
	
	public SpriteSheet(String path, int size)
	{
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}//end SpriteSheet
	
	private void load()
	{
	try {
		BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
		int w = image.getWidth();
		int h = image.getHeight();
		image.getRGB(0, 0,w,h,pixels,0,w);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
}
