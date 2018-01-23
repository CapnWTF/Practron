package com.Capn.Practron;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import com.Capn.Practron.Input.Kbd;
import com.Capn.Practron.Input.Mouse;
import com.Capn.Practron.entity.mob.Player;
import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.Sprite;
import com.Capn.Practron.level.Level;
import com.Capn.Practron.level.RandomLevel;
import com.Capn.Practron.level.SpawnLevel;
import com.Capn.Practron.level.TileCoord;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	private static int width = 300; //x of window
	private static int height = (width / 16) * 9; // y of window
	private static int scale = 3; //window scale
	public static String title = "Practron, yeah!";
	
	
	private Thread thread;//new thread "thread" declaration
	private JFrame frame; 
	private Kbd key;
	
	private Level level;
	private Player player;
	private boolean running = false;
	
	private Screen screen;
	public static int buttontick = 20; //number of frames between button input checks (for lower priority buttons)
	private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);//FINAL BUFFER
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	
	public Game()
	{
		Dimension size = new Dimension(width*scale, height*scale);
		setPreferredSize(size);
		screen = new Screen(width,height);
		frame = new JFrame();
		key = new Kbd();
		level = Level.spawn;
		TileCoord pSpawn = new TileCoord(20,20);
		player = new Player(pSpawn.x(),pSpawn.y(),key);
		player.init(level);
		addKeyListener(key); //always add the keylistener after you set the new Kbd object to prevent a null pointer exception
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
	}//end Game
	
	public static int getWindowWidth()
	{
		return width* scale;
	}
	public static int getWindowHeight()
	{
		return height*scale;
	}
	
	public synchronized void start() 
	{
		running = true;
		thread = new Thread(this, "display");
		thread.start();
	}//end start
	
	public synchronized void stop()
	{
		running = false;
		try
		{
		thread.join();
		} catch (InterruptedException e)
		{
		e.printStackTrace();
		}//end try/catch
	}//end stop
	
	public void run()
	{
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		requestFocus();
		
		
		while (running)
		{
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			
			lastTime = now;
			while (delta >=1){
				tick();
				ticks++;
				delta--;	
			}
			//[test line]System.out.println("game didn't crash yet...");
			render();
			frames++;
			if(System.currentTimeMillis()- timer > 1000)
			{
				timer += 1000;
				System.out.println(ticks + " tps, " + frames + "fps");
				frame.setTitle(title + "  |  "+ ticks + " , " + frames);
				ticks = 0;
				frames = 0;
			}
		}//end while running loop
		stop();
	}//end run
	
	
	
	public void tick()//updates
	{
		key.update();
		player.update();
		level.update();
	
	} //background logic and calculations (end tick)
	
	public void render()
	{
	 BufferStrategy bs = getBufferStrategy();	 
	 if (bs == null){
		 createBufferStrategy(3);
		 return;
	 }//this statement creates a buffer only if there isn't one currently
	 	
	 	screen.clear();//blanks the screen so that the render doesn't artifact all over the damn place
	 	
	 	int xScroll = player.x - screen.width/2;
	 	int yScroll = player.y - screen.height/2;
	 	
	 	level.render(xScroll, yScroll, screen);
	 	player.render(screen);
	 	
	 	
	 	for(int i = 0;i<pixels.length;i++)
	 	{
	 		
	 		pixels[i] = screen.pixels[i];
	 	}
	 	
	 Graphics g = bs.getDrawGraphics(); //keep all graphics to be displayed between this and "dispose"
	 g.fillRect(0, 0, getWidth(), getHeight());//fills the screen with a black rectangle the size of the screen.
	 
	 g.drawImage(image,0,0,getWidth(),getHeight(),null);
	 g.setColor(Color.WHITE);
	 g.setFont(new Font("Verdana",0,30));
	 g.drawString("X: " + player.x + ", Y: " + player.y, 350, 300);
	 //g.fillRect(Mouse.getX()-32, Mouse.getY()-32, 64, 64);
	 g.drawString("Button: "+ Mouse.getB(), 80,80);
	 g.dispose();//screen clear/ garbage collector
	 bs.show();//display screen buffer
	} //foreground logic and calculations(screen updates, refreshes, etc.)
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.frame.setResizable(false);//determines if the frame is resizable by the user
		game.frame.setTitle("Practron");//string to appear on frame border
		game.frame.add(game);//appends the component "Game" to the end of this container
		game.frame.pack();//changes window size to be preferred size
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);//places the window in the center of the screen.
		game.frame.setVisible(true);//makes sure the frame is visible
		
		game.start();
		
	}
}//end Game
