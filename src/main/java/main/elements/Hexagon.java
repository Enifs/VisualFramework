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
	public Hexagon(double size)
	{
		this.sideDimension = size;
		this.innerR = this.sin60 * this.sideDimension;

		double angle = Math.PI / 6;

		Vector2D vector = new Vector2D(this.sideDimension, 0);
		vector = vector.rotate(angle);

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


	@Override
	public Rectangle2D getBounds()
	{
		Point2D tl =
				this.getPosition().minus(
						new Vector2D(this.sideDimension /2,this.sideDimension /2 ));

		return new Rectangle2D(tl.getX(), tl.getY(), this.sideDimension, this.sideDimension);
	}


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

	@Override
	public void setPosition(Point2D position)
	{
		super.setPosition(position);
		this.text.setPosition(this.getPosition());
		this.buildPolygon();
	}

	public Point2D getNeighbourCenter(int direction)
	{
		return this.getPosition().plus(this.neighbourVectors.get(direction));
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


	double sideDimension;
	double innerR;

	Polygon polygon;
	double sin60 = Math.sin(Math.PI/3);
	Map<Integer, Point2D> points = new HashMap<>();
	Map<Integer, Vector2D> vertexVectors = new HashMap<>();
	Map<Integer, Vector2D> neighbourVectors = new HashMap<>();

	TextElement text = new TextElement();

	Color borderColor = Color.BLACK;
	Color background = Color.WHITE;
}
