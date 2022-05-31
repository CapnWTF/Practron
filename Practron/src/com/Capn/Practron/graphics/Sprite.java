package com.Capn.Practron.graphics;

import java.awt.Color;

public class Sprite {
	public final int SIZE;
	private int x, y;
	private int width;
	private int height;
	public int[] pixels;
	protected SpriteSheet sheet;
	
	//generics
	//Sample Generic Tile : public static Sprite lava = new Sprite(16,1,0,SpriteSheet.tiles);
	public static Sprite sky = new Sprite(16,Color.BLUE.getRGB());
	public static Sprite lava = new Sprite(16,Color.RED.getRGB());
	public static Sprite grass = new Sprite(16,Color.GREEN.getRGB());
	public static Sprite luna = new Sprite(16,Color.PINK.getRGB());
	public static Sprite wood = new Sprite(16,Color.YELLOW.darker().getRGB());
	public static Sprite purp = new Sprite(16,0x330066);
	public static Sprite black = new Sprite(16, Color.BLACK.getRGB());
	public static Sprite orang = new Sprite(16, Color.ORANGE.getRGB());
	public static Sprite voidSprite = new Sprite(16,0x2b6bff);
	
	//spawn level sprites
	public static Sprite floor1 = new Sprite(16,0,0,SpriteSheet.spawn);
	public static Sprite floor2 = new Sprite(16,1,0,SpriteSheet.spawn);
	public static Sprite pipe1 = new Sprite(16,0,1,SpriteSheet.spawn);
	public static Sprite pipe2 = new Sprite(16,0,2,SpriteSheet.spawn);
	public static Sprite net1 = new Sprite(16,1,1,SpriteSheet.spawn);
	public static Sprite net2 = new Sprite(16,1,2,SpriteSheet.spawn);
	public static Sprite duct1 = new Sprite(16,2,0,SpriteSheet.spawn);
	public static Sprite duct2 = new Sprite(16,2,1,SpriteSheet.spawn);
	public static Sprite duct3 = new Sprite(16,2,2,SpriteSheet.spawn);
	public static Sprite duct4 = new Sprite(16,2,3,SpriteSheet.spawn);
	public static Sprite spike = new Sprite(16,3,0,SpriteSheet.spawn);
	public static Sprite duct5 = new Sprite(16,3,1,SpriteSheet.spawn);
	public static Sprite duct6 = new Sprite(16,3,2,SpriteSheet.spawn);
	public static Sprite ladder = new Sprite(16,3,3,SpriteSheet.spawn);
	
	
	
	//player sprites
	public static Sprite player = new Sprite(32,0,1,SpriteSheet.tiles);
	public static Sprite playerSide0 = new Sprite(32,1,0,SpriteSheet.tiles);
	public static Sprite playerU = new Sprite(32,2,0,SpriteSheet.tiles);
	public static Sprite playerD = new Sprite(32,2,1,SpriteSheet.tiles);
	
	
	public static Sprite dummy = new Sprite(32,0,0,SpriteSheet.dummyIdle);
	
	public static Sprite playerSide1 = new Sprite(32,2,0,SpriteSheet.tiles);
	public static Sprite playerSide2 = new Sprite(32,3,0,SpriteSheet.tiles);
	
	public static Sprite playerSideGun0 = new Sprite(32,0,2,SpriteSheet.tiles);
	public static Sprite playerSideGun1 = new Sprite(32,1,2,SpriteSheet.tiles);
	public static Sprite playerSideGun2 = new Sprite(32,2,2,SpriteSheet.tiles);
	public static Sprite playerSideGun3 = new Sprite(32,3,2,SpriteSheet.tiles);
	
	//Projectile Sprites oyeah
	public static Sprite projectile_mega = new Sprite(16,0,0, SpriteSheet.projectile_mega);
	public static Sprite projectile_goo  = new Sprite(16,1,0, SpriteSheet.projectile_mega);
	public static Sprite cursor = new Sprite(16,1,1,SpriteSheet.projectile_mega);
	//Particle Sprites
	
	public static Sprite particle_normal = new Sprite(3, 0x136207);
	
	//sprite methods
	protected  Sprite(int width, int height,  SpriteSheet sheet){
		if(width == height) SIZE = width;
		else SIZE = -1;
		this.sheet = sheet;
		this.width = width;
		this.height = height;
	}
	
	public  Sprite(int size, int x, int y, SpriteSheet sheet)
	{
		SIZE = size;
		pixels = new int[SIZE *SIZE];
		this.width = size;
		this.height = size;
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
		
		
	}
	
	public Sprite(int width, int height, int color)
	{
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int [width* height];
		setColor(color);
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public Sprite(int size, int color)
	{
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels= new int [SIZE*SIZE];
		setColor(color);
	}
	
	
	
	public Sprite(int[] pixels, int width, int height) {
		if(width == height) {
			SIZE = width;
			}
		else
			{
			SIZE = -1;
			};
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	
	}

	private void setColor(int color)
	{
		for (int i = 0; i < width*height;i++)
		{
			pixels[i] = color;
		}
	}
	
	private void load()
	{
		for (int y = 0; y < SIZE; y++)
		{
			for(int x = 0;x < SIZE; x++)
			{
				pixels[x+(y*width)] = sheet.pixels[(x+this.x)+ (y+this.y)*sheet.WIDTH];
			}
		}
	}
	
}
