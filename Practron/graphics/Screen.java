package com.Capn.Practron.graphics;

import java.awt.Color;
import java.util.Random;

import com.Capn.Practron.entity.mob.Chaser;
import com.Capn.Practron.entity.mob.Mob;
import com.Capn.Practron.entity.mob.Star;
import com.Capn.Practron.entity.projectile.Projectile;
import com.Capn.Practron.level.Tile.Tile;
import com.Capn.Practron.util.Vector2i;

//0xFFFF60FC is the transparency color (or 0xFF60FC)


public class Screen {

	public int width;

	public int height;
	
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE -1;
	public int xOff;
	public int yOff;
	public static int trans = 0xFFFF60FC;
	public static int tiletrans = 0xFF0070EC;
	public int [] tiles = new int[4096]; //64x64 tiles
	
	private Random random = new Random(); 
	
	public Screen(int width, int height)
	{
		this.width = width;
		this.height = height;
		pixels = new int[width*height];//50,400 -1
		
		for (int i = 0; i< MAP_SIZE * MAP_SIZE; i++)
		{
			tiles[i] = random.nextInt(0xffffff);
			tiles[0] = 0;
		}
	}
	public void clear()
	{
		for(int i =0;i<pixels.length;i++)
		{
			pixels[i]=0;
			
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed)
	{
		if(fixed){
		xp -= xOff;
		yp -= yOff;
		}
		for ( int y = 0; y < sprite.getHeight();y++)
		{
			int ya = y + yp;
			for ( int x = 0; x < sprite.getWidth();x++)
			{
				int xa = x + xp;
				if ( xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width]= sprite.pixels[x+y * sprite.getWidth()];
			}
		}
	}
	
	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed)
	{
		if(fixed){
		xp -= xOff;
		yp -= yOff;
		}
		for ( int y = 0; y < sheet.HEIGHT;y++)
		{
			int ya = y + yp;
			for ( int x = 0; x < sheet.WIDTH;x++)
			{
				int xa = x + xp;
				if ( xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width]= sheet.pixels[x+y * sheet.WIDTH];
			}
		}
	}
	
	
	
	
	public void renderTile(int xp, int yp, Tile tile)
	{
		xp -= xOff;
		yp -= yOff;
		
		for (int y = 0; y < tile.sprite.SIZE;y++)
		{
		int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE;x++)
			{
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa<0){
					xa = 0;
				}
				int col = tile.sprite.pixels[x+(y*tile.sprite.SIZE)];
				if (col != tiletrans){
					pixels [xa+ (ya*width)] = col;
				}
				//pixels [xa+ (ya*width)] = tile.sprite.pixels[x+(y*tile.sprite.SIZE)];
				
				
			}
		}
	}
	
	public void renderProjectile(int xp, int yp, Projectile p)
	{
		xp -= xOff;
		yp -= yOff;
		
		for (int y = 0; y < p.getSpriteSize();y++)
		{
		int ya = y + yp;
			for (int x = 0; x < p.getSpriteSize();x++)
			{
				int xa = x + xp;
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break;
				if (xa<0){
					xa = 0;
				}
				int projectileTrans = p.getSprite().pixels[x+(y*p.getSprite().SIZE)];
				
				
				if(projectileTrans != 0xFFFF60FC){
				pixels [xa+ (ya*width)] = projectileTrans;
				}
				
			}
		}
	}
	
	public void renderMob(int xp, int yp, Sprite sprite, int flip)
	{
		xp -= xOff;
		yp -= yOff;
		
		for (int y = 0; y < 32;y++)
		{
		int ya = y + yp;
		int ys = y; //this is the variable for flipping on the y axis
		if (flip == 2|| flip ==3)
		{
			ys = 31-y; //if flip is 1 or 3, render the pixels on the y axis in reverse order
		}
			for (int x = 0; x < 32;x++)
			{
				int xa = x + xp;
				int xs = x; //variable for flipping on the x axis
				if (flip == 1 || flip == 3)
				{
					xs = 31-x; // if the flip variable is 1 or 3, render pixels on the x axis in reverse order
				}
				
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break;
				if (xa<0){
					xa = 0;
				}
				int col = sprite.pixels[xs+(ys*32)];
				if (col != trans){
					pixels [xa+ (ya*width)] = col;
				}
				
				
			}
		}
	}
	
	public void renderMob(int xp, int yp, Mob mob)
	{
		xp -= xOff;
		yp -= yOff;
		
		for (int y = 0; y < 32;y++)
		{
		int ya = y + yp;
		int ys = y; //this is the variable for flipping on the y axis
		
			for (int x = 0; x < 32;x++)
			{
				int xa = x + xp;
				int xs = x; //variable for flipping on the x axis
				
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break;
				if (xa<0){
					xa = 0;
				}
				int col = mob.getSprite().pixels[xs+(ys*32)];
				if ((mob instanceof Chaser) && col == 0xff00E8D8 )
				{
					col = 0xff57AE60;
				}
				if ((mob instanceof Star) && col == 0xff00E8D8 )
				{
					col = 0xffE8E83A;
					//col = ~col;
				}
				
				if (col != trans){
					pixels [xa+ (ya*width)] = col;
				}
				
				
			}
		}
	}
	
	public void setOff(int xOff, int yOff)
	{
		this.xOff = xOff;
		this.yOff = yOff;
		
		
	}
	
	public void drawRect(int xp, int yp, int width, int height, int col, boolean fixed) {
		if(fixed)
		{
			xp -= xOff;
			yp -= yOff;
		}
		
		for (int x = xp;x<xp+width; x++)
		{
			if(x<0 || x >=this.width || yp>=this.height)
			{
				continue;
			}
			if(yp>0)pixels[x+ yp*this.width]=col;
			if(yp + height >= this.height)
			{
				continue;
			}
			if(yp+height>0)pixels[x+ (yp+height)*this.width]= col;
		}
		
		for(int y = yp; y<=yp + height;y++)
		{
			if (xp >= this.width || y <0 ||y>=this.height)
			{
				continue;
			}
			if(xp>0)pixels[xp + y*this.width] = col;
			if(xp +width >= this.width)continue;
			if(xp+width>0)pixels[xp+width + y*this.width]=col;
		}
	}
	
	public void drawLine(Vector2i start, Vector2i goal, int col, boolean fixed) {
		// TODO Auto-generated method stub
		for(int x = start.getX(); x<= goal.getX();x++){
			if(x < 0 || x >= this.width || start.getY()>=this.height){continue;}
			if(start.getX() >0)pixels[x + (start.getY()+goal.getY()*this.width)] = Color.CYAN.getRGB();
			
		}
		
	}
	public void drawPixel(Vector2i start, Vector2i goal, boolean fixed) {
		// TODO Auto-generated method stub
		int xp = start.getX();
		int yp = start.getY();
		int width = 2;
		int height = 2;
		if(fixed)
		{
			xp -= xOff;
			yp -= yOff;
		}
		
		for (int x = xp;x<xp+width; x++)
		{
			if(x<0 || x >=this.width || yp>=this.height)
			{
				continue;
			}
			if(yp>0)pixels[x+ yp*this.width]=Color.ORANGE.getRGB();
			if(yp + height >= this.height)
			{
				continue;
			}
			if(yp+height>0)pixels[x+ (yp+height)*this.width]= Color.ORANGE.getRGB();
		}
	}
	
	
	
	
	
	
	
	
}
