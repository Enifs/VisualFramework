
package main.sampleApplication;


import java.awt.Graphics2D;

import main.deviceinput.Mouse;
import main.elements.Element;
import main.elements.TextElement;
import math.geom2d.Point2D;
import main.hitTesting.HitTesting;


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
