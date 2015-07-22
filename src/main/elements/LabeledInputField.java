
package main.elements;


import java.awt.Color;

import main.ContentPanel;
import main.elements.jComponentWrappers.TextInputLine;
import main.layout.LayoutConstant;
import main.layout.relations.ChainRelation;


public class LabeledInputField extends RectangularElement
{
	public LabeledInputField(String label, ContentPanel contentPanel)
	{
		this.label = label;
		this.color = new Color(0,0,0,0);
		this.addRelation(new ChainRelation(this, LayoutConstant.ORIENTATION_HORIZONTAL,
			LayoutConstant.ALIGNMENT_MIN,
			10));

		TextElement textElement = new TextElement(label);
		this.input = new TextInputLine(contentPanel);

		this.addChild(textElement);
		this.addChild(this.input);
	}


	public TextInputLine getInput()
	{
		return input;
	}


	@Override
	public void discard()
	{
		this.getInput().discard();
	}


	TextInputLine input;
	String label;
}
