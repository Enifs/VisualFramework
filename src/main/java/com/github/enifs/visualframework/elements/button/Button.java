
//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.elements.button;


import com.github.enifs.visualframework.elements.ImageElement;
import com.github.enifs.visualframework.elements.TextElement;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.github.enifs.visualframework.Clickable;
import com.github.enifs.visualframework.elements.RectangularElement;


public class Button extends RectangularElement implements Clickable
{
	/**
	 * This constructor create Button with given text without image.
	 * @param text String element on image.
	 */
	public Button(String text)
	{
		this.text = text;
		this.image = null;

		this.setColor(Color.GREEN);
		TextElement label = new TextElement(text);
		this.setHitTestFinalTarget(true);

		label.setFont(label.getFont().deriveFont((float) 16));

		this.setPreferredSize(label.getPreferredSize());

		this.addChild(label);
	}


	/**
	 * This constructor create Button from ImageElement without text on button.
	 * @param image ImageElement that will be on button.
	 */
	public Button(ImageElement image)
	{
		this.text = null;
		this.image = image;

		this.setColor(Color.GREEN);
		this.setHitTestFinalTarget(true);

		this.addChild(image);
	}


	/**
	 * This constructor create Button from File object without text on button.
	 * @param imageFile File object that represents image file.
	 */
	public Button(File imageFile)
	{
		this.text = null;
		this.image = new ImageElement(imageFile);

		this.setColor(Color.GREEN);
		this.setHitTestFinalTarget(true);

		this.addChild(this.image);
	}


	/**
	 * This constructor create Button from String and imageElement objects, where first represents text on
	 * button but second one represents image that will be on button
	 * @param text String that represents button text.
	 * @param image ImageElement object that represents image.
	 */
	public Button(String text, ImageElement image)
	{
		this.text = text;
		this.image = image;

		this.setColor(Color.GREEN);
		TextElement label = new TextElement(text);
		this.setHitTestFinalTarget(true);

		label.setFont(label.getFont().deriveFont((float) 16));

		this.setPreferredSize(label.getPreferredSize());

		this.addChild(this.image);
		this.addChild(label);
	}


	/**
	 * This constructor create new Button object from String and file objects, where first one is text on
	 * button and second is image file.
	 * @param text String that represents button text.
	 * @param imageFile File object that represents image file.
	 */
	public Button(String text, File imageFile)
	{
		this.text = text;
		this.image = new ImageElement(imageFile);

		this.setColor(Color.GREEN);
		TextElement label = new TextElement(text);
		this.setHitTestFinalTarget(true);

		label.setFont(label.getFont().deriveFont((float) 16));

		this.setPreferredSize(label.getPreferredSize());

		this.addChild(this.image);
		this.addChild(label);
	}


	/**
	 * This constructor create new Button object from 2 strings, where first one is text on image and second
	 * is string that represents image location.
	 * @param text Text on button
	 * @param imageLink Image path.
	 */
	public Button(String text, String imageLink)
	{
		this.text = text;
		this.image = new ImageElement(imageLink);

		this.setColor(Color.GREEN);
		TextElement label = new TextElement(text);
		this.setHitTestFinalTarget(true);

		label.setFont(label.getFont().deriveFont((float) 16));

		this.setPreferredSize(label.getPreferredSize());

		this.addChild(this.image);
		this.addChild(label);
	}



	@Override
	public void mouseClickedAction()
	{
		for (ButtonAction action : this.actions)
		{
			action.action();
		}
	}


	public void addAction(ButtonAction action)
	{
		this.actions.add(action);
	}


	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private String text;

	private List<ButtonAction> actions = new ArrayList<>();

	private ImageElement image;
}
