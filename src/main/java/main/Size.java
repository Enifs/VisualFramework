
package main;


import java.awt.Dimension;


/**
 * Simple double width and height container. mat.geom and java.awt lacks such class.
 */
public class Size
{
	public Size(double w, double h)
	{
		this.w = w;
		this.h = h;
	}


	public Size(Dimension dimension)
	{
		this(dimension.getWidth(), dimension.getHeight());
	}


	public Size(Size layoutSize)
	{
		this(layoutSize.getW(), layoutSize.getH());
	}


	public double getW()
	{
		return w;
	}


	public void setW(double w)
	{
		this.w = w;
	}


	public double getH()
	{
		return h;
	}


	public void setH(double h)
	{
		this.h = h;
	}

	public void setSize(double w, double h)
	{
		this.w = w;
		this.h = h;
	}


	public void setSize(Size size)
	{
		this.setSize(size.getW(), size.getH());
	}


	@Override
	public String toString()
	{
		return "[" + this.w + ":" + this.h + "]";
	}


	double w = 0;
	double h = 0;
}
