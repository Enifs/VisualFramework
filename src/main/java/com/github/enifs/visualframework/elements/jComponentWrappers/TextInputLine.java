
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


/**
 * This class is a wrapper for JTextField class.
 */
public class TextInputLine extends RectangularElement
{
	public TextInputLine(ContentPanel contentPanel)
	{
		this.contentPanel = contentPanel;
		this.contentPanel.add(this.textField);
		this.textField.setVisible(true);
		textField.setText("Some Text");
		this.setId("InputLine");
		this.setPreferredSize(new Size(90, 30));
	}


	@Override
	public void setPosition(Point2D position)
	{
		super.setPosition(position);
		this.textField.setBounds(
			(int) this.getPosition().getX(),
			(int) this.getPosition().getY(),
			(int) this.getLayoutSize().getW(),
			(int) this.getLayoutSize().getH());
	}


	@Override
	public void discard()
	{
		this.contentPanel.remove(this.textField);
	}


	public JTextField textField = new JTextField();
	private ContentPanel contentPanel;
}
