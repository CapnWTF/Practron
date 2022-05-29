package com.Capn.Practron.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.Capn.Practron.entity.Entity;
import com.Capn.Practron.entity.mob.Player;
import com.Capn.Practron.entity.particle.Particle;
import com.Capn.Practron.entity.projectile.Projectile;
import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.level.Tile.Tile;
import com.Capn.Practron.util.Vector2i;

public class Level {
	
	protected int UNIT = 16;
	protected int width;
	protected int height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	private List<Player> players = new ArrayList<Player>();
	
	private Comparator<Node> nodeSorter = new Comparator<Node>()
			{
		public int compare(Node n0, Node n1)
		{
			if (n1.fCost < n0.fCost) return +1;
			if (n1.fCost > n0.fCost) return -1;
			return 0;
		}
			};
	
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
		for (int i = 0; i < players.size(); i++)
		{
			if(players.get(i).isRemoved())
			{
				players.remove(i);
			}
			else{
		players.get(i).update();
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
		for (int i = 0; i < players.size(); i++)
		{
		players.get(i).render(screen);
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
		else if (e instanceof Player)
		{
			players.add((Player)e);
		}
		else {
		entities.add(e);
		}
	}
	
	
	public List<Player> getPlayers()
	{
		return players;
	}
	public Player getPlayerAt(int index)
	{
		return players.get(index);
	}
	
	public Player getClientPlayer()
	{
		return players.get(0);
	}
	
	public List<Node> findPath(Vector2i start,Vector2i goal)
	{
		
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, getDistance(start, goal));
		openList.add(current);
		while (openList.size() > 0)
		{
			Collections.sort(openList,nodeSorter);
			current = openList.get(0);
			if(current.tile.equals(goal))
				{
					List <Node> path = new ArrayList <Node>();
					while (current.parent != null)
						{
							path.add(current);
							current = current.parent;
						}
					openList.clear();
					closedList.clear();
					return path;
				}
			openList.remove(current);
			closedList.add(current);
			
			for(int i = 0;i<9;i++)
			{
				if ( i == 4)
				{
					continue;  // if the current node is node 4 (which is the moving mob's location, ignore that node)
				}
				int x = current.tile.getX();
				int y = current.tile.getY();
				int xi= (i%3) -1;
				int yi= (i/3) -1;
				
				Tile at = getTile(x+xi, y+yi);
				
				if(at == null)
					{
					continue;
					}
					
				if(at.solid())
					{
					continue;
					}
				
				Vector2i a = new Vector2i (x + xi, y +yi);
				
				double gCost = current.gCost + (getDistance(current.tile, a) == 1 ? 1 : 0.95);
				double hCost = getDistance(a, goal);
				Node node = new Node(a,current, gCost, hCost);
				if (vecInList (closedList, a) && gCost >= node.gCost)
				{
					continue;
				}
				if (!vecInList(openList, a)|| gCost < node.gCost)
				{
					openList.add(node);
				}
			}
		}
		closedList.clear();
		return null;
	}
	
	private boolean vecInList(List<Node> list, Vector2i vector) {
		for (Node n : list) {
			if (n.tile.equals(vector)) return true;
		}
		return false;
	}

	private double getDistance(Vector2i tile, Vector2i goal) {
		double dx = tile.getX() - goal.getX();
		double dy = tile.getY() - goal.getY();
		return Math.sqrt(dx * dx + dy * dy);
	}

	
	public List < Entity> getEntities (Entity e, int radius)
	{
		List <Entity> result = new ArrayList<Entity>();
		int ex = e.getX();
		int ey = e.getY();
		for (int i = 0; i < entities.size(); i++)
		{
			Entity entity = entities.get(i);
			int x = entity.getX();
			int y = entity.getY();
			
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx*dx) + (dy*dy));
			
			if ( distance <= radius) result.add(entity); 
		}
		return result;
	}
	
	public List <Player> getPlayers(Entity e, int radius)
	{
		List<Player> result = new ArrayList<Player>();
		
		int ex = e.getX();
		int ey = e.getY();
		for (int i = 0; i < players.size(); i++)
		{
			Player player = players.get(i);
			int x = player.getX();
			int y = player. getY();
			
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx*dx) + (dy*dy));
			
			if ( distance <= radius) result.add(player); 
		}
		return result;
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
			return Tile.wood;
		}
		
		if(tiles[x+y*width] == Tile.col_floor2)
		{
			return Tile.wood;
		}
		
		if(tiles[x+y*width] == Tile.col_duct1)
		{
			return Tile.purp;
		}
		
		if(tiles[x+y*width] == Tile.col_duct2)
		{
			return Tile.purp;
		}
		
		if(tiles[x+y*width] == Tile.col_duct3)
		{
			return Tile.purp;
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
			//pipe1 is solid
		}
		
		if(tiles[x+y*width] == Tile.col_pipe2)
		{
			return Tile.black;
			//pipe2 is also solid
		}
		if(tiles[x+y*width] == Tile.col_net1)
		{
			return Tile.grass;
		}
		
		if(tiles[x+y*width] == Tile.col_net2)
		{
			return Tile.grass;
		}
		
		if(tiles[x+y*width] == Tile.col_spike)
		{
			return Tile.lava;
		}
		
		if(tiles[x+y*width] == Tile.col_ladder)
		{
			return Tile.luna;
		}
		
		
		else{
		return Tile.voidTile;
		}
	}
	
	
}//end Level
