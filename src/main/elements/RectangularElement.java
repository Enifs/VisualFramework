
package main.elements;


import java.awt.Color;
import java.awt.Graphics2D;

import math.geom2d.polygon.Rectangle2D;

public class RectangularElement extends Element
{
	public void setColor(Color color)
	{
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g)
	{
		Color prevColor = g.getColor();

		g.setColor(color);

		g.fillRect(
			(int) this.getPosition().getX(),
			(int) this.getPosition().getY(),
			(int) this.getScreenSize().getW(),
			(int) this.getScreenSize().getH());

		g.setColor(prevColor);
	}

	@Override
	public Rectangle2D getBounds()
	{
		return new Rectangle2D(
			this.getPosition().x(),
			this.getPosition().y(),
			this.getScreenSize().getW(),
			this.getScreenSize().getH());
	}

	Color color = Color.black;
}
