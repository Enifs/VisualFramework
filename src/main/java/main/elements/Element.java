
package main.elements;


import main.Drawable;
import main.Size;
import main.layout.relations.Relation;
import math.geom2d.Point2D;
import math.geom2d.polygon.Rectangle2D;

import java.util.ArrayList;
import java.util.List;


/**
 * This class is a basic element class. Layout works with these and content panel draws
 * these.
 * todo: We might want to Take Sizes to separate level of abstraction. There should be elements that are not drawable, but are still processed by layout.
 */
public abstract class Element implements Drawable
{
	public Element()
	{
		this.setId("[Element " + nextID + "]");
		Element.nextID ++;
	}


	public Point2D getPosition()
	{
		return this.position;
	}


	public void setPosition(Point2D position)
	{
		this.position = position;
	}

	public void addChild(Element element)
	{
		element.setParent(this);
		this.children.add(element);
	}

	public void removeChild(Element element)
	{
		element.setParent(null);
		element.discard();
		this.children.remove(element);
	}


	public void removeAllChildren()
	{
		synchronized (this.children)
		{
			List<Element> copy = new ArrayList<>(this.children);

			for (Element element : copy)
			{
				this.removeChild(element);
			}
		}
	}


	public void addRelation(Relation relation)
	{
		this.relations.add(relation);
		relation.setOwner(this);
	}


	// ---------------------------------------------------------------------
	// Section: Getters
	// ---------------------------------------------------------------------

	public List<Relation> getRelations()
	{
		return this.relations;
	}


	/**
	 * This method returns list of children for current element.
	 * @return List of elements.
	 */
	public List<Element> getChildren()
	{
		return this.children;
	}


	public void clearRelation()
	{
		for (Relation relation : relations)
		{
			relation.setOwner(null);
		}

		this.relations.clear();
	}

	public void removeRelation(Relation relation)
	{
		relation.setOwner(null);
		this.relations.remove(relation);
	}

	public String getId()
	{
		return id;
	}


	public void setId(String id)
	{
		this.id = id;
	}


	public Size getScreenSize()
	{
		return screenSize;
	}


	public void setScreenSize(Size screenSize)
	{
		this.screenSize = screenSize;
	}


	public Size getLayoutSize()
	{
		return layoutSize;
	}


	public void setLayoutSize(Size layoutSize)
	{
		this.layoutSize = layoutSize;
	}

	public Size getPreferredSize()
	{
		return this.preferredSize;
	}


	public Size getMinSize()
	{
		return this.minSize;
	}


	public Size getMaxSize()
	{
		return this.maxSize;
	}


	public void setMinSize(Size minSize)
	{
		this.minSize = minSize;
	}


	public void setPreferredSize(Size preferredSize)
	{
		this.preferredSize = preferredSize;
	}


	public void setMaxSize(Size maxSize)
	{
		this.maxSize = maxSize;
	}


	public Element getParent()
	{
		return parent;
	}


	public void setParent(Element parent)
	{
		this.parent = parent;
	}


	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + ":" + this.getId();
	}

	public void discard()
	{
		// Do nothing by default.
	}


	public boolean isHitTestInvisible()
	{
		return hitTestInvisible;
	}


	public void setHitTestInvisible(boolean hitTestInvisible)
	{
		this.hitTestInvisible = hitTestInvisible;
	}


	public boolean isHitTestTransparent()
	{
		return hitTestTransparent;
	}


	public void setHitTestTransparent(boolean hitTestTransparent)
	{
		this.hitTestTransparent = hitTestTransparent;
	}


	public boolean isHitTestFinalTarget()
	{
		return hitTestFinalTarget;
	}


	public void setHitTestFinalTarget(boolean hitTestFinalTarget)
	{
		this.hitTestFinalTarget = hitTestFinalTarget;
	}

	public boolean preferConfigurationRelations()
	{
		return true;
	}

	/**
	 * todo: This method should be made final with implementation here. It should always return a combination of screen size and position.
	 */
	public abstract Rectangle2D getBounds();

	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------

	/**
	 * This field is used to store size calculated by layout. Layout classes should be
	 * only ones to use this field.
	 */
	private Size layoutSize = new Size(0, 0);

	/**
	 * This size is the final result of the layout and should always be consistent with
	 * the image on the screen.
	 */
	private Size screenSize = new Size(0, 0);

	private Size minSize = new Size(0, 0);
	private Size preferredSize = new Size(0, 0);
	private Size maxSize = new Size(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

	private Point2D position = new Point2D(0, 0);

	private List<Element> children = new ArrayList<>();

	private List<Relation> relations = new ArrayList<>();

	private String id = "defaultID";

	private Element parent;

	private static int nextID = 0;

	private boolean hitTestInvisible = false;
	private boolean hitTestTransparent = false;
	private boolean hitTestFinalTarget = false;
}
