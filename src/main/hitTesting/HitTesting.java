
package main.hitTesting;


import main.elements.Element;
import math.geom2d.Point2D;


public class HitTesting
{
	public HitTesting(double x, double y, Element root)
	{
		this.point = new Point2D(x, y);
		this.root = root;
	}

	public Element test()
	{
		return this.test(this.root);
	}

	private Element test(Element element)
	{
		Element returnElement = null;

		if (!element.isHitTestInvisible() && element.getBounds().contains(this.point))
		{
			if (!element.isHitTestFinalTarget())
			{
				for (Element child : element.getChildren())
				{
					Element testResult = this.test(child);

					if (testResult != null)
					{
						returnElement = testResult;
						break;
					}
					else
					{
						returnElement = null;
					}
				}
			}

			if (!element.isHitTestTransparent() && returnElement == null)
			{
				returnElement = element;
			}
		}

		return returnElement;
	}

	Point2D point;
	private Element root;
}
