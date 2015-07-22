
package main.elements;


import java.awt.Color;

import main.Size;
import main.elements.jComponentWrappers.ComboBox;
import main.layout.relations.ChainRelation;
import main.ContentPanel;
import main.layout.LayoutConstant;


public class LabeledComboBox<T> extends RectangularElement
{
	public LabeledComboBox(String label, ContentPanel contentPanel)
	{
		this.label = label;
		this.color = new Color(0,0,0,0);
		this.addRelation(new ChainRelation(this, LayoutConstant.ORIENTATION_HORIZONTAL,
			LayoutConstant.ALIGNMENT_MIN,
			10));
		this.setPreferredSize(new Size(300, 30));

		TextElement textElement = new TextElement(label);
		this.comboBox = new ComboBox(contentPanel);

		this.addChild(textElement);
		this.addChild(this.comboBox);
	}

	@Override
	public void discard()
	{
		this.comboBox.discard();
	}

	public ComboBox<T> getComboBox()
	{
		return this.comboBox;
	}

	ComboBox<T> comboBox;
	String label;
}
