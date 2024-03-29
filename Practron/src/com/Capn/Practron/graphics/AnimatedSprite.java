package com.Capn.Practron.graphics;

public class AnimatedSprite extends Sprite{

	private int frame = 0;
	private Sprite sprite;
	private int rate = 6;
	private int length = -1;
	private int time = 0;
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length)
	{
		super(width,height,sheet);
		this.length = length;
		sprite = sheet.getSprites()[0];
		if (length > sheet.getSprites().length)
		{
			System.err.println("Error! Animation Length is too Long!" + sheet.getSprites().length);
		}
		
	}
	
	public void update()
	{
		time++;
		if (time % rate == 0)
		{
		if ( frame >= length-1) frame = 0;
		else frame ++;
		sprite = sheet.getSprites()[frame];
		}
		//System.out.println(sprite + ": Frame: " + frame);
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}
	
	public void setFrameRate(int frames)
	{
		rate = frames;
	}

	public void setFrame(int index) {
		if (index > sheet.getSprites().length -1)
		{
			System.err.println("Index out of bounds in" + this);
		}
		sprite = sheet.getSprites() [index];
	}
}
