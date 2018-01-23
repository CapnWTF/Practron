package com.Capn.Practron.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Capn.Practron.level.Tile.Tile;

public class SpawnLevel extends Level
{
	
	
	
	public SpawnLevel(String path) {
		super(path);
		
	}

	protected void loadLevel (String path)
	{
		
		try 
		{
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = image.getWidth();
			width = image.getWidth();
			int h = image.getHeight();
			height = image.getHeight();
			
			tiles = new int[w*h];
			image.getRGB(0, 0,w,h,tiles,0,w);
		
		} catch (IOException e) {
			
			e.printStackTrace();
			System.out.println("Level file load FAILED");
		}
		
	}
	
	//Grass = 0xFFFF00
	//Luna = 0xFFFFFF00
	//lava = 0xFF7C090D
	//sky = 0xFF00167A
	
	protected void generateLevel()
	{
		System.out.println("Tiles:" + tiles[0]);
		}
		
		
	
	
}
