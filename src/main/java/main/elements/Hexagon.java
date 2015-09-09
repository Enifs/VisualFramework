package main.elements;

import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import math.geom2d.polygon.Rectangle2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Didzis on 17.07.2015..
 */
public class Hexagon extends Element
{
	/**
	 * Inits Hexagon with given size and default Math.PI / 6 angle.
	 *
	 * @param size
	 */
	public Hexagon(double size)
	{
		this(size, Math.PI / 6);
	}


	/**
	 * Inits Hexagon with given size and given angle.
	 *
	 * @param size
	 * @param angle
	 */
	public Hexagon(double size, double angle)
	{
		this(size, angle, Color.WHITE, Color.BLACK, new TextElement(""));
	}


	/**
	 * This constructor create new Hexagon object with all possible parameters.
	 *
	 * @param size Side size
	 * @param angle Hexagon angle
	 * @param background Background color
	 * @param borderColor Border color
	 * @param text Text element in Hexagon
	 */
	public Hexagon(double size, double angle, Color background, Color borderColor, TextElement text)
	{
		this.points = new HashMap<>();
		this.vertexVectors = new HashMap<>();
		this.neighbourVectors = new HashMap<>();

		this.angle = angle;
		this.background = background;
		this.borderColor = borderColor;
		this.text = text;

		this.sideDimension = size;
		this.innerR = this.sin60 * this.sideDimension;

		Vector2D vector = new Vector2D(this.sideDimension, 0);
		vector = vector.rotate(this.angle);

		Vector2D neighbourVector = new Vector2D(this.innerR*2, 0);

		for (int i = 0; i < 6; i++)
		{
			Point2D point = new Point2D(vector.getX(), vector.getY());
			this.vertexVectors.put(i, new Vector2D(vector.getX(), vector.getY()));
			this.neighbourVectors.put(i, neighbourVector);

			vector = vector.rotate(Math.PI / 3);
			neighbourVector = neighbourVector.rotate(Math.PI / 3);
		}

		this.buildPolygon();
		this.addChild(this.text);
	}


	// ---------------------------------------------------------------------
	// Section: Other Methods
	// ---------------------------------------------------------------------


	@Override
	public void draw(Graphics2D g)
	{
		Color oldColor = g.getColor();

		g.setColor(background);
		g.fillPolygon(this.polygon);

		g.setColor(this.borderColor);
		g.drawPolygon(polygon);

		g.setColor(oldColor);
	}


	public void buildPolygon()
	{
		this.polygon = new Polygon();

		for (int i = 0; i < 6; i++)
		{
			Point2D point = this.getPosition().plus(this.vertexVectors.get(i));
			this.polygon.addPoint(point.getAsInt().x, point.getAsInt().y);
		}
	}


	// ---------------------------------------------------------------------
	// Section: Getters
	// ---------------------------------------------------------------------


	@Override
	public Rectangle2D getBounds()
	{
		Point2D tl =
			this.getPosition().minus(
				new Vector2D(this.sideDimension /2,this.sideDimension /2 ));

		return new Rectangle2D(tl.getX(), tl.getY(), this.sideDimension, this.sideDimension);
	}


	public Point2D getNeighbourCenter(int direction)
	{
		return this.getPosition().plus(this.neighbourVectors.get(direction));
	}


	public double getSideDimension()
	{
		return this.sideDimension;
	}


	public double getAngle()
	{
		return this.angle;
	}


	// ---------------------------------------------------------------------
	// Section: Setter
	// ---------------------------------------------------------------------


	@Override
	public void setPosition(Point2D position)
	{
		super.setPosition(position);
		this.text.setPosition(this.getPosition());
		this.buildPolygon();
	}


	public void setText(String text)
	{
		this.text.setText(text);
	}


	public void setBorderColor(Color color)
	{
		this.borderColor = color;
	}


	public void setBackground(Color background)
	{
		this.background = background;
	}


	// TODO Must recalculate all vertex.
	public void setSize(double sideDimension)
	{
		this.sideDimension = sideDimension;
	}


	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private double sin60 = Math.sin(Math.PI/3);

	private double sideDimension;

	private double angle;

	private double innerR;

	private Polygon polygon;

	private Map<Integer, Point2D> points;

	private Map<Integer, Vector2D> vertexVectors;

	private Map<Integer, Vector2D> neighbourVectors;

	private TextElement text;

	private Color borderColor;

	private Color background;
}
