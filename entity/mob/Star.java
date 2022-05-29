package com.Capn.Practron.entity.mob;

import java.util.List;

import com.Capn.Practron.entity.mob.Mob.Direction;
import com.Capn.Practron.graphics.AnimatedSprite;
import com.Capn.Practron.graphics.Screen;
import com.Capn.Practron.graphics.SpriteSheet;
import com.Capn.Practron.level.Node;
import com.Capn.Practron.util.Debug;
import com.Capn.Practron.util.Vector2i;
import java.awt.Graphics;
import com.Capn.Practron.Game;

import java.awt.Color;
import java.awt.Component;

public class Star extends Mob {
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummyWalk, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummyWalk, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummyWalk, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummyWalk, 32, 32, 3);

	private AnimatedSprite shoot = new AnimatedSprite(SpriteSheet.dummyShot, 32, 32, 4);
	private AnimatedSprite idle = new AnimatedSprite(SpriteSheet.dummyIdle, 32, 32, 3);

	private AnimatedSprite animSprite = idle;

	private int time = 0;
	private int pTime = 0;
	private double xa = 0;
	private double ya = 0;
	private double speed = 1;

	List<Node> path = null;
	Vector2i start = null;
	Vector2i destination = null;
	Vector2i vec = null;
	
	public Star(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		// sprite = animSprite.getSprite();
	}

	protected void move() {
		xa = 0;
		ya = 0;

		int px = level.getPlayerAt(0).getX();
		int py = level.getPlayerAt(0).getY();

		 start = new Vector2i((getX() >> 4), (getY() >> 4));
		 destination = new Vector2i(px >> 4, py >> 4);
		 if(time%3 == 0 ){
			path = level.findPath(start, destination);
		 }
		if (path != null) {
			if (path.size() > 0) {
				 vec = path.get(path.size() - 1).tile;
				 if (x < vec.getX() << 4) xa++;
				 if (x > vec.getX() << 4) xa--;
				 if (y < vec.getY() << 4) ya++;
				 if (y > vec.getY() << 4) ya--;		}

		}

		// if(pTime %10 == 0){ //this is a timer, lets the move function run
		// every pTime frames

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {

			walking = false;

		}

		// } end of the if loop for the pTimer
	}

	public void update() {
		pTime++;

		if (walking)
			animSprite.update();
		else {
			animSprite.setFrame(0);
		}

		if (ya < 0) {
			animSprite = up;
			dir = Direction.UP;
		}

		if (ya > 0) {
			animSprite = down;
			dir = Direction.DOWN;
		}

		if (xa < 0) {
			animSprite = left;
			dir = Direction.LEFT;
		}

		if (xa > 0) {
			animSprite = right;
			dir = Direction.RIGHT;
		}

		move();

	}

	public void render(Screen screen) {

		// int flip = 0;
		// if(dir == Direction.RIGHT) {flip = 1;}
		sprite = animSprite.getSprite();
		screen.renderMob((x - 16), (y - 16), this);
		if(path != null){
//			Debug.drawLine(screen, start, goal, col, fixed);
			Debug.drawRect(screen, vec.getX()*16, vec.getY()*16, 2, 2, Color.ORANGE.getRGB(), true);
			Debug.drawRect(screen, start.getX()*16, start.getY()*16, 2, 2, Color.RED.getRGB(), true);
			Debug.drawRect(screen, destination.getX()*16, destination.getY()*16, 2, 2, Color.BLUE.getRGB(), true);
			
		}

	}
}
