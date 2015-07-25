

package main.layout;


import main.Size;
import main.elements.Element;


/**
 * This class is responsible for logic of calculating final size of a layout component.
 * It should respect min, max and preferred size of the element.
 * todo: It should also respect layout style properties in the future.
 */
public class SizeFunctor
{
	public SizeFunctor(Size layoutSize, Element element)
	{
		this.layoutSize = layoutSize;
		this.element = element;
	}

	public Size run()
	{
		this.resultSize = new Size(this.layoutSize);
		this.obeyMinimumSize();
		this.obeyMaximumSize();
		this.adjustForPreferredSize();

		return this.resultSize;
	}


	private void adjustForPreferredSize()
	{
		Size pref = this.element.getPreferredSize();
		Size max = this.element.getMaxSize();
		Size min = this.element.getMinSize();

		if (min.getW() <= pref.getW() && max.getW() >= pref.getW())
		{
			if (this.layoutSize.getW() <= pref.getW())
			{
				this.resultSize.setW(pref.getW());
			}
		}

		if (min.getH() <= pref.getH() && max.getH() >= pref.getH())
		{
			if (this.layoutSize.getH() <= pref.getH())
			{
				this.resultSize.setH(pref.getH());
			}
		}
	}


	private void obeyMaximumSize()
	{
		if (this.resultSize.getW() > element.getMaxSize().getW())
		{
			this.resultSize.setW(element.getMaxSize().getW());
		}

		if (this.resultSize.getH() > element.getMaxSize().getH())
		{
			this.resultSize.setH(element.getMaxSize().getH());
		}
	}


	private void obeyMinimumSize()
	{
		if (this.resultSize.getW() < element.getMinSize().getW())
		{
			this.resultSize.setW(element.getMinSize().getW());
		}

		if (this.resultSize.getH() < element.getMinSize().getH())
		{
			this.resultSize.setH(element.getMinSize().getH());
		}
	}


	private Size resultSize;

	/**
	 * Layout size is the size, layout initially calculates for raw graph.
	 */
	private Size layoutSize;
	private Element element;
}
