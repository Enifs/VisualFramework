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
	/**
	 * Default constructor with empty point list.
	 */
	protected PolygonElement()
	{
		this.pointList = new ArrayList<>();
		this.borderColor = Color.BLACK;
		this.backgroundColor = Color.WHITE;
	}


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
// Section: Getters
// --------------------------------------------------------------------


	@Override
	public Rectangle2D getBounds()
	{
		return new Rectangle2D(
			this.getPosition().x(),
			this.getPosition().y(),
			this.polygon.getBounds2D().getWidth(),
			this.polygon.getBounds2D().getHeight());
	}


// ---------------------------------------------------------------------
// Section: Setters
// ---------------------------------------------------------------------


	public void setBackgroundColor(Color color)
	{
		this.backgroundColor = color;
	}


	public void setBorderColor(Color color)
	{
		this.borderColor = color;
	}


	@Override
	public void setPosition(Point2D position)
	{
		super.setPosition(position);
		this.buildPolygon();
	}


// ---------------------------------------------------------------------
// Section: Variables
// ---------------------------------------------------------------------


	protected List<Point2D> pointList;

	private Polygon polygon;

	private Color borderColor;

	private Color backgroundColor;
}
