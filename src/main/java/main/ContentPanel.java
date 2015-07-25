
package main;


import java.awt.*;

import javax.swing.*;

import main.elements.RectangularElement;
import main.elements.Element;


/**
 * Content Panel is the only directly extended JComponent. It is the panel everything is
 * drawn on. It manages root element directly, but not deeper elements in the hierarchy.
 * This class is also the entryPoint for the draw recursion.
 * todo: If this system starts crashing with outOfMemoryException recursion must be de-recursed.
 */
public class ContentPanel extends JPanel
{
	public ContentPanel(Dimension dimension)
	{
		this.dimension = dimension;
		this.root = new RectangularElement();
		this.root.setPreferredSize(new Size(this.dimension));
		this.root.setColor(new Color(0,0,0,20));
		this.root.setId("root");
	}


	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		this.paint(this.root, (Graphics2D) g);
	}


	private void paint(Element element, Graphics2D g)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		if (element instanceof Drawable)
		{
			(element).draw(g);

			for (Element child : element.getChildren())
			{
				paint(child, g);
			}
		}
	}


	public void addElement(Element element)
	{
		this.root.addChild(element);
	}


	public Element getRoot()
	{
		return root;
	}

	private RectangularElement root;

	private Dimension dimension;
}
