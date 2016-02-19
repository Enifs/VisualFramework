
//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.sampleApplication;


import com.github.enifs.visualframework.elements.Element;
import java.awt.Graphics2D;

import com.github.enifs.visualframework.deviceinput.Mouse;
import com.github.enifs.visualframework.elements.TextElement;
import math.geom2d.Point2D;
import com.github.enifs.visualframework.hitTesting.HitTesting;


public class PositionLabel extends TextElement
{
	public Element root;


	public PositionLabel(String s)
	{
		super(s);
	}


	@Override
	public void draw(Graphics2D g)
	{
		this.setPosition(new Point2D(Mouse.MOUSE_X + 5, Mouse.MOUSE_Y + 5));

		if (root != null)
		{
			Element element = new HitTesting(Mouse.MOUSE_X, Mouse.MOUSE_Y, root).test();

			if (element != null)
			{
				this.setText(element.getId());
			}
		}

		super.draw(g);
	}
}
