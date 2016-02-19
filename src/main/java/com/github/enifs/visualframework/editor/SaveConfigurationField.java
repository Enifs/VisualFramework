
//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.editor;


import com.github.enifs.visualframework.ContentPanel;
import com.github.enifs.visualframework.Size;
import com.github.enifs.visualframework.elements.Element;
import com.github.enifs.visualframework.elements.RectangularElement;
import com.github.enifs.visualframework.elements.button.Button;
import com.github.enifs.visualframework.layout.relations.ChainRelation;
import com.github.enifs.visualframework.xml.writer.XMLWriter;
import java.awt.Color;

import com.github.enifs.visualframework.elements.button.ButtonAction;
import com.github.enifs.visualframework.layout.LayoutConstant;
import com.github.enifs.visualframework.elements.jComponentWrappers.TextInputLine;


public class SaveConfigurationField extends RectangularElement
{
	public SaveConfigurationField(ContentPanel contentPanel, final Element foreignRoot)
	{
		this.foreignRoot = foreignRoot;

		this.setColor(Color.PINK);
		this.setPreferredSize(new Size(200, 50));
		this.addRelation(new ChainRelation(this, LayoutConstant.ORIENTATION_HORIZONTAL,
			LayoutConstant.ALIGNMENT_MIN,
			10));

		this.input = new TextInputLine(contentPanel);
		input.setPreferredSize(new Size(150, 50));
		this.addChild(input);
		this.input.textField.setText("rootConfiguration");

		Button button = new Button("SAVE");
		button.setColor(Color.MAGENTA);

		button.addAction(new ButtonAction()
		{
			@Override
			public void action()
			{
				try
				{
					XMLWriter.writeXMLFile(input.textField.getText(), foreignRoot);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		this.addChild(button);
	}

	TextInputLine input;
	Element foreignRoot;
}
