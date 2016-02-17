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


	/**
	 * This constructor creates new Polygon element from given pointList. Default background color is white.
	 *
	 * @param pointList List that contains all polygon points.
	 */
	public PolygonElement(List<Point2D> pointList)
	{
		this(pointList, Color.WHITE);
	}


	/**
	 * This constructor creates new Polygon element from given pointList with given background color.
	 *
	 * @param pointList List that contains all polygon points.
	 * @param backgroundColor Background color.
	 */
	public PolygonElement(List<Point2D> pointList, Color backgroundColor)
	{
		this(pointList, backgroundColor, Color.BLACK);
	}


	/**
	 * This constructor creates new Polygon element from given pointList with given background and border
	 * color.
	 *
	 * @param pointList List that contains all polygon points.
	 * @param backgroundColor Background color.
	 * @param borderColor Border color.
	 */
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


	/**
	 * This method resets all points coordinates, so mass center for polygon is in (0, 0) coordinate.
	 */
	public void normalizePointCoordinates()
	{
		double x = this.polygon.getBounds2D().getCenterX();
		double y = this.polygon.getBounds2D().getCenterY();

		List<Point2D> newPointList = new ArrayList<>(this.pointList.size());

		for (Point2D point : this.pointList)
		{
			newPointList.add(new Point2D(point.x() - x, point.y() - y));
		}

		this.pointList = newPointList;
	}


	/**
	 * This method create Polygon element from pointList.
	 */
	public void buildPolygon()
	{
		this.polygon = new Polygon();

		for (Point2D point : this.pointList)
		{
			this.polygon.addPoint(
				(int) (this.getPosition().x() + point.x()),
				(int) (this.getPosition().y() + point.y()));
		}
	}


	/**
	 * Object drawing method.
	 */
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


	/**
	 * Method that returns polygon bounds.
	 *
	 * @return Bounds as Rectangle2D element.
	 */
	@Override
	public Rectangle2D getBounds()
	{
		return new Rectangle2D(
			this.polygon.getBounds2D().getMinX(),
			this.polygon.getBounds2D().getMinY(),
			this.polygon.getBounds2D().getWidth(),
			this.polygon.getBounds2D().getHeight());
	}


// ---------------------------------------------------------------------
// Section: Setters
// ---------------------------------------------------------------------


	/**
	 * This method sets polygon background.
	 *
	 * @param color Background color.
	 */
	public void setBackgroundColor(Color color)
	{
		this.backgroundColor = color;
	}


	/**
	 * This method sets Border color.
	 *
	 * @param color Border color.
	 */
	public void setBorderColor(Color color)
	{
		this.borderColor = color;
	}


	/**
	 * This method sets polygon position.
	 *
	 * @param position Polygon position.
	 */
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
