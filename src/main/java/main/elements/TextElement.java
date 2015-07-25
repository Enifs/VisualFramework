//
// Armands  03.07.2015.
// HanabiSim
//


package main.elements;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import main.Drawable;
import main.Size;
import math.geom2d.polygon.Rectangle2D;


public class TextElement extends Element implements Drawable
{
	public TextElement()
	{
		super();
		this.text = "";
		this.font = new Font("Tahoma", Font.PLAIN, 12);
		this.color = Color.black;
	}


	public TextElement(String text)
	{
		super();
		this.text = text;
		this.font = new Font("Tahoma", Font.PLAIN, 12);
		this.color = Color.black;
	}


	public TextElement(String text, Font font, Color color)
	{
		super();
		this.text = text;
		this.font = font;
		this.color = color;
	}


	// ---------------------------------------------------------------------
	// Section: Override methods
	// ---------------------------------------------------------------------


	@Override
	public Rectangle2D getBounds()
	{
		java.awt.geom.Rectangle2D rect =
			this.font.getStringBounds(this.text, new FontRenderContext(new AffineTransform(), true, true));

		return new Rectangle2D(
			this.getPosition().x(),
			this.getPosition().y(),
			rect.getWidth(),
			rect.getHeight());
	}


	@Override
	public Size getScreenSize()
	{
		return new Size(this.getBounds().getWidth(), this.getBounds().getHeight());
	}


	@Override
	public void draw(Graphics2D g)
	{
		Color prevColor = g.getColor();
		Font prevFont = g.getFont();

		g.setColor(this.color);
		g.setFont(this.font);
		g.drawString(
			this.text,
			((int) this.getPosition().getX()),
			(int) (this.getPosition().getY() + this.getBounds().getHeight()) - 3);

		g.setColor(prevColor);
		g.setFont(prevFont);
	}


	@Override
	public Size getPreferredSize()
	{
		return new Size(this.getBounds().getWidth(), this.getBounds().getHeight());
	}


	@Override
	public Size getMinSize()
	{
		return new Size(this.getBounds().getWidth(), this.getBounds().getHeight());
	}


	@Override
	public Size getMaxSize()
	{
		return new Size(this.getBounds().getWidth(), this.getBounds().getHeight());
	}


	// ---------------------------------------------------------------------
	// Section: Setters and getters
	// ---------------------------------------------------------------------


	public void setText(String text)
	{
		this.text = text;
	}


	public void setFont(Font font)
	{
		this.font = font;
	}


	public void setColor(Color color)
	{
		this.color = color;
	}


	public void setColor(int red, int green, int blue)
	{
		this.color = new Color(red, green, blue);
	}


	public String getText()
	{
		return this.text;
	}


	public Font getFont()
	{
		return this.font;
	}


	public Color getColor()
	{
		return this.color;
	}


	@Override
	public String toString()
	{
		return super.toString() + " " + this.text;
	}

	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private Color color;

	private Font font;

	private String text;
}
