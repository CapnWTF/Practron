package com.Capn.Practron.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Kbd implements KeyListener{

	private boolean[] keys = new boolean[120];
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public boolean weaponup;
	public boolean weapondown;
	
	public void update()
	{
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		weaponup = keys[KeyEvent.VK_F];
		weapondown = keys[KeyEvent.VK_G];
	}
	
	public void keyPressed(KeyEvent e){
		keys[e.getKeyCode()] = true;
		
	}//end keyPressed

	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}//end keyReleased

	
	public void keyTyped(KeyEvent e) {
		
		
	}//end keyTyped
	
	}//end Kbd
