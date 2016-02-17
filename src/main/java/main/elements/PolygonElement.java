//
//  Armands Vitols
//  Didzis Romanovskis
//  Ojars Vilmars Ratnieks
//
//  Manage 2015 under GPLv3
//


package main.elements;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.*;


import math.geom2d.Point2D;
import math.geom2d.polygon.Rectangle2D;


public class PolygonElement extends Element
{
	public PolygonElement(List<Point2D> pointList)
	{
		this(pointList, Color.WHITE);
	}


	public PolygonElement(List<Point2D> pointList, Color backgroundColor)
	{
		this(pointList, backgroundColor, Color.BLACK);
	}


	public PolygonElement(List<Point2D> pointList, Color backgroundColor, Color borderColor)
	{
		if (pointList.size() < 3)
		{
			// TODO Probably need to create Exceptions?
			throw new ArrayIndexOutOfBoundsException("Too small pointList");
		}

		this.pointList = pointList;
		this.buildPolygon();

		this.backgroundColor = backgroundColor;
		this.borderColor = borderColor;
	}


	public void buildPolygon()
	{
		this.polygon = new Polygon();

		for (Point2D point : this.pointList)
		{
			this.polygon.addPoint(point.getAsInt().x, point.getAsInt().y);
		}
	}


	@Override
	public void draw(Graphics2D g)
	{
		Color oldColor = g.getColor();

		g.setColor(this.backgroundColor);
		g.fillPolygon(this.polygon);

		g.setColor(this.borderColor);
		g.drawPolygon(this.polygon);

		g.setColor(oldColor);
	}


// ---------------------------------------------------------------------
// Section: Setters and Getters
// ---------------------------------------------------------------------


	@Override
	public Rectangle2D getBounds()
	{
		return new Rectangle2D(
			this.getPosition().x(),
			this.getPosition().y(),
			this.polygon.getBounds2D().getWidth(),
			this.polygon.getBounds2D().getHeight());
	}


	public void setBackgroundColor(Color color)
	{
		this.backgroundColor = color;
	}


	public void setBorderColor(Color color)
	{
		this.borderColor = color;
	}


// ---------------------------------------------------------------------
// Section: Variables
// ---------------------------------------------------------------------


	private List<Point2D> pointList;

	private Polygon polygon;

	private Color borderColor;

	private Color backgroundColor;
}
