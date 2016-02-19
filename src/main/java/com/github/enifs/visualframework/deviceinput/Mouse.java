

//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.deviceinput;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;


public class Mouse extends MouseAdapter
{

	@Override
	public void mouseMoved(MouseEvent e)
	{
		super.mouseMoved(e);
		MOUSE_X = e.getX();
		MOUSE_Y = e.getY();
	}



	@Override
	public void mousePressed(MouseEvent e)
	{
		super.mousePressed(e);

		if (SwingUtilities.isLeftMouseButton(e))
		{
			LEFT_PRESSED = true;
		}
		else if (SwingUtilities.isRightMouseButton(e));
		{
			RIGHT_PRESSED = true;
		}
	}


	@Override
	public void mouseReleased(MouseEvent e)
	{
		super.mouseReleased(e);
		if (SwingUtilities.isLeftMouseButton(e))
		{
			LEFT_PRESSED = false;
		}
		else if (SwingUtilities.isRightMouseButton(e));
		{
			RIGHT_PRESSED = false;
		}
	}


	@Override
	public void mouseDragged(MouseEvent e)
	{
		super.mouseDragged(e);
		this.mouseMoved(e);
	}


	public static boolean LEFT_PRESSED = false;
	public static boolean RIGHT_PRESSED = false;

	public static int MOUSE_X = 0;
	public static int MOUSE_Y = 0;
}
