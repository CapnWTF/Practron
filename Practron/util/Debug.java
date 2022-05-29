package com.Capn.Practron.util;

import com.Capn.Practron.graphics.Screen;

public class Debug {

	
	private Debug()
	{
		
	}
	
	public static void drawRect(Screen screen, int x, int y, int width, int height, int col, boolean fixed)
	{
		
		if(y/16 <= 0 || y/16 >= screen.height || x/16 <= 0 || x/16 >= screen.width){
			return;
		}
		screen.drawRect(x,y,width,height, col, fixed);
	}
	
	public static void drawLine(Screen screen, Vector2i start, Vector2i goal, int col, boolean fixed){
		screen.drawLine(start, goal, col, fixed);
	}
	
	public static void drawPixel(Screen screen, Vector2i start, Vector2i goal, boolean fixed){
		screen.drawPixel(start, goal, fixed);
	}
}
