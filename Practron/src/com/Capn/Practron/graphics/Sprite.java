package com.Capn.Practron.graphics;

public class Sprite {
	public final int SIZE;
	private int x, y;
	private int width;
	private int height;
	public int[] pixels;
	private SpriteSheet sheet;
	
	//generics
	public static Sprite sky = new Sprite(16,0,0,SpriteSheet.tiles);
	public static Sprite lava = new Sprite(16,1,0,SpriteSheet.tiles);
	public static Sprite grass = new Sprite(16,0,1,SpriteSheet.tiles);
	public static Sprite luna = new Sprite(16,1,1, SpriteSheet.tiles);
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
	
	public static Sprite particle_normal = new Sprite(3, 0xAAAAAA);
	
	//sprite methods
	
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
	
	public Sprite(int size, int color)
	{
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels= new int [SIZE*SIZE];
		setColor(color);
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
				pixels[x+(y*SIZE)] = sheet.pixels[(x+this.x)+ (y+this.y)*sheet.SIZE];
			}
		}
	}
	
}
