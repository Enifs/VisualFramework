package main.elements;

import math.geom2d.Point2D;
import math.geom2d.Vector2D;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Didzis on 17.07.2015..
 */
public class Hexagon extends PolygonElement
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
		this.neighbourVectors = new HashMap<>();

		this.angle = angle;
		this.text = text;

		this.sideDimension = size;
		double sin60 = Math.sin(Math.PI / 3);
		this.innerR = sin60 * this.sideDimension;

		this.setBackgroundColor(background);
		this.setBorderColor(borderColor);

		this.createPointList();
		this.buildPolygon();
		this.addChild(this.text);
	}


	/**
	 * This method create point list with vectors.
	 */
	private void createPointList()
	{
		Vector2D vector = new Vector2D(this.sideDimension, 0);
		vector = vector.rotate(this.angle);

		Vector2D neighbourVector = new Vector2D(this.innerR * 2.0, 0);

		for (int i = 0; i < 6; i++)
		{
			this.pointList.add(new Point2D(vector.x(), vector.y()));

			this.neighbourVectors.put(i, neighbourVector);

			vector = vector.rotate(Math.PI / 3);
			neighbourVector = neighbourVector.rotate(Math.PI / 3);
		}
	}


// ---------------------------------------------------------------------
// Section: Getters
// ---------------------------------------------------------------------


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
	}


	public void setText(String text)
	{
		this.text.setText(text);
	}


	public void setSize(double sideDimension)
	{
		this.sideDimension = sideDimension;
		this.createPointList();
		this.buildPolygon();
	}


// ---------------------------------------------------------------------
// Section: Variables
// ---------------------------------------------------------------------


	private double sideDimension;

	private double angle;

	private double innerR;

	private Map<Integer, Vector2D> neighbourVectors;

	private TextElement text;
}
