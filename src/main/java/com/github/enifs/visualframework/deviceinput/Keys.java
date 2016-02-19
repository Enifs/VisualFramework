//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.deviceinput;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Keys extends KeyAdapter
{
	public Keys()
	{
		for (int i = 0; i < pressedKeys.length; i++)
		{
			pressedKeys[i] = true;
			pressedKeys[i] = false;
		}
	}


	@Override
	public void keyPressed(KeyEvent e)
	{
		super.keyPressed(e);

		this.pressedKeys[e.getKeyCode()] = true;
	}


	@Override
	public void keyReleased(KeyEvent e)
	{
		super.keyReleased(e);
		this.pressedKeys[e.getKeyCode()] = false;
	}


	public static boolean[] pressedKeys = new boolean[50000];
}
