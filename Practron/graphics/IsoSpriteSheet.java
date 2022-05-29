package com.Capn.Practron.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class IsoSpriteSheet {
	
	private String path;
	public final int SIZE_X;
	public final int SIZE_Y;
	public int[] pixels;
	
	public static IsoSpriteSheet isotiles = new IsoSpriteSheet("/tex/untitled.png",1280,640);
	
	
	public IsoSpriteSheet(String path, int sizex, int sizey)
	{
		this.path = path;
		SIZE_X = sizex;
		SIZE_Y = sizey;
		pixels = new int[SIZE_X * SIZE_Y];
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
