package main;

import math.geom2d.Vector2D;
import main.elements.Element;
import main.elements.Hexagon;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Didzis on 18.07.2015..
 */
public class HexGridManager
{
	public HexGridManager(Element root)
	{
		this.root = root;
		origin = new Hexagon(size);
		this.allHexagons.add(origin);
		Point p = new Point(0,0);
		this.logicalCoordinateToHexagon.put(p, this.origin);
		this.hexagonToLogicalCoordinate.put(this.origin, p);
	}

	public Hexagon addNeighbour(Hexagon source, int direction)
	{
		Hexagon neighbour = this.findNeighbour(source, direction);

		if (neighbour == null)
		{
			neighbour = new Hexagon(this.size);

			Point p = this.hexagonToLogicalCoordinate.get(source);
			Point newPoint = this.constructNeighbouringPoint(p, direction);
			this.hexagonToLogicalCoordinate.put(neighbour, newPoint);
			this.logicalCoordinateToHexagon.put(newPoint, neighbour);
			neighbour.setPosition(source.getNeighbourCenter(direction));
//			neighbour.setText("[" + newPoint.x + ":" + newPoint.y + "]");
			this.allHexagons.add(neighbour);
		}

		return neighbour;
	}

	public Hexagon findNeighbour(Hexagon hexagon, int direction)
	{
		Point p = this.hexagonToLogicalCoordinate.get(hexagon);

		return this.logicalCoordinateToHexagon.get(this.constructNeighbouringPoint(p, direction));
	}

	private Point constructNeighbouringPoint(Point source, int direction)
	{
		int x = source.x;
		int y = source.y;

		switch (direction)
		{
			case 0:
				x++;
				break;
			case 3:
				x--;
				break;
			case 1:
			case 5:
				if (y%2 == 0)
				{
				}
				else
				{
					x++;
				}
				break;
			case 4:
			case 2:
				if (y%2 == 0)
				{
					x--;
				}
				else
				{
				}
				break;
		}

		switch (direction)
		{
			case 1:
			case 2:
				y++;
				break;
			case 4:
			case 5:
				y--;
				break;
		}

		return new Point(x, y);
	}

	public void activate(Hexagon hexagon)
	{
		this.root.addChild(hexagon);
	}

	public Hexagon getOrigin()
	{
		return origin;
	}


	public double getSize()
	{
		return size;
	}


	public void setSize(double size)
	{
		this.size = size;
	}

	public void shiftOrigin(Vector2D vector)
	{
		for (Hexagon hexagon : this.allHexagons)
		{
			hexagon.setPosition(hexagon.getPosition().plus(vector));
		}
	}


	public List<Hexagon> getAllHexagons()
	{
		return allHexagons;
	}


	Hexagon origin;
	double size = 10;

	List<Hexagon> allHexagons = new ArrayList<>();
	Map<Point, Hexagon> logicalCoordinateToHexagon = new HashMap<>();
	Map<Hexagon, Point> hexagonToLogicalCoordinate = new HashMap<>();

	Element root;
}
