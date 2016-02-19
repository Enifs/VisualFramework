
//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.elements;


import com.github.enifs.visualframework.Size;
import java.awt.Color;

import com.github.enifs.visualframework.elements.jComponentWrappers.ComboBox;
import com.github.enifs.visualframework.layout.relations.ChainRelation;
import com.github.enifs.visualframework.ContentPanel;
import com.github.enifs.visualframework.layout.LayoutConstant;


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
