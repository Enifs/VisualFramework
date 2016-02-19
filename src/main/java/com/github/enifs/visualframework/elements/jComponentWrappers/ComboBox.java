
//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.elements.jComponentWrappers;


import com.github.enifs.visualframework.ContentPanel;
import com.github.enifs.visualframework.Size;
import com.github.enifs.visualframework.elements.RectangularElement;

import javax.swing.*;

import math.geom2d.Point2D;


public class ComboBox<T> extends RectangularElement
{
	public ComboBox(ContentPanel contentPanel)
	{
		this.contentPanel = contentPanel;
		this.contentPanel.add(this.comboBox);
		this.comboBox.setVisible(true);
		this.setId(this.getId() + " ComboBox");
		this.setPreferredSize(new Size(250, 30));
	}


	@Override
	public void setPosition(Point2D position)
	{
		super.setPosition(position);
		this.comboBox.setBounds(
			(int) this.getPosition().getX(),
			(int) this.getPosition().getY(),
			(int) this.getLayoutSize().getW(),
			(int) this.getLayoutSize().getH());
	}

	@Override
	public void discard()
	{
		this.contentPanel.remove(this.comboBox);
	}

	public JComboBox<T> comboBox = new JComboBox<T>();
	private ContentPanel contentPanel;
}
