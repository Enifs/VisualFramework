
//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.elements;


import com.github.enifs.visualframework.ContentPanel;
import com.github.enifs.visualframework.elements.jComponentWrappers.TextInputLine;
import com.github.enifs.visualframework.layout.relations.ChainRelation;
import java.awt.Color;

import com.github.enifs.visualframework.layout.LayoutConstant;


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
