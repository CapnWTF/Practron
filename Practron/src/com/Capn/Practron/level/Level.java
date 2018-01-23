package com.Capn.Practron.level;

import java.util.ArrayList;
import java.util.List;

import com.Capn.Practron.entity.Entity;
import com.Capn.Practron.entity.particle.Particle;
import com.Capn.Practron.entity.projectile.Projectile;
import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.level.Tile.Tile;

public class Level {
	
	protected  int UNIT = 16;
	protected int width;
	protected int height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	public static Level spawn = new SpawnLevel("/tex/SpawnLevel/spawnmap.png");
	
	public Level(int width, int height)
	{
		this.width = width;
		this.height= height;
		tilesInt = new int[width*height];
		generateLevel();
	}//end Level constructor
	
	public Level(String path)
	{
		loadLevel(path);
		generateLevel();
		
	}
	
	protected void generateLevel()
	{
		
	}//end generateLevel
	
	protected void loadLevel(String path)
	{
		
	}//end loadLevel
	
	public void update()
	{
		for (int i = 0; i < entities.size(); i++)
		{
			if(entities.get(i).isRemoved())
			{
				entities.remove(i);
			}
			else{
		entities.get(i).update();
			}
			
		}
		for (int i = 0; i < projectiles.size(); i++)
		{
			if(projectiles.get(i).isRemoved())
			{
				projectiles.remove(i);
			}
			else{
		projectiles.get(i).update();
			}
			
		}
		for (int i = 0; i < particles.size(); i++)
		{
			if(particles.get(i).isRemoved())
			{
				particles.remove(i);
			}
			else{
		particles.get(i).update();
			}
		}
	}//end update/tick
	
	public List<Projectile> getProjectiles()
	{
		return projectiles;
	}
	
	public boolean tileCollision( int x, int y, int size, int xOff, int yOff)
	{
		boolean solid = false;
		for(int c = 0; c<4; c++)
		{
			int xt = (x - c%2*size + xOff) >> 4; 
			int yt = (y - c/2*size + yOff) >> 4;
			if (getTile(xt, yt).solid())
			{
		solid = true;
			}
		}
		return solid;
		}
	
	public void render(int xScroll, int yScroll, Screen screen)
	{
		screen.setOff(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height +16) >> 4;
		
		for (int y = y0; y<y1; y++)
		{
			for (int x = x0; x<x1; x++)
			{
			getTile(x,y).render(x, y, screen);
				
			}
		}
		for ( int i = 0; i<entities.size(); i++)
		{
			entities.get(i).render(screen);
		}
		for ( int i = 0; i<projectiles.size(); i++)
		{
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++)
		{
		particles.get(i).render(screen);
		}
		
	}//ender render
	
	public void add(Entity e)
	{
		e.init(this);
		if(e instanceof Particle)
		{
			particles.add((Particle) e);
		}
		else if (e instanceof Projectile)
		{
			projectiles.add((Projectile)e);
		}
		else {
		entities.add(e);
		}
	}
	
	
	
	//Grass = 0xFF00FF00
	//Luna = 0xFFFFFF00
	//lava = 0xFF7C090D
	//sky = 0xFF00167A
	
	public Tile getTile(int x, int y)
	{
		if (y < 0 || x<0 || x>= width || y >= height)
		{
			return Tile.voidTile;
		}
		
		
		if(tiles[x+y*width] == Tile.col_floor1)
		{
			return Tile.floor1;
		}
		
		if(tiles[x+y*width] == Tile.col_floor2)
		{
			return Tile.floor2;
		}
		
		if(tiles[x+y*width] == Tile.col_duct1)
		{
			return Tile.duct1;
		}
		
		if(tiles[x+y*width] == Tile.col_duct2)
		{
			return Tile.duct2;
		}
		
		if(tiles[x+y*width] == Tile.col_duct3)
		{
			return Tile.duct3;
		}
		
		if(tiles[x+y*width] == Tile.col_duct4)
		{
			return Tile.duct4;
		}
		
		if(tiles[x+y*width] == Tile.col_duct5)
		{
			return Tile.duct5;
		}
		
		if(tiles[x+y*width] == Tile.col_duct6)
		{
			return Tile.duct6;
		}
		
		if(tiles[x+y*width] == Tile.col_pipe1)
		{
			return Tile.pipe1;
		}
		
		if(tiles[x+y*width] == Tile.col_pipe2)
		{
			return Tile.pipe2;
		}
		if(tiles[x+y*width] == Tile.col_net1)
		{
			return Tile.net1;
		}
		
		if(tiles[x+y*width] == Tile.col_net2)
		{
			return Tile.net2;
		}
		
		if(tiles[x+y*width] == Tile.col_spike)
		{
			return Tile.spike;
		}
		
		if(tiles[x+y*width] == Tile.col_ladder)
		{
			return Tile.ladder;
		}
		
		
		else{
		return Tile.voidTile;
		}
	}
	
	
}//end Level
