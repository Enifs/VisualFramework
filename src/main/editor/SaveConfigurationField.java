
package main.editor;


import java.awt.Color;

import main.ContentPanel;
import main.Size;
import main.elements.Element;
import main.elements.RectangularElement;
import main.elements.button.ButtonAction;
import main.layout.LayoutConstant;
import main.layout.relations.ChainRelation;
import main.elements.button.Button;
import main.elements.jComponentWrappers.TextInputLine;
import main.xml.writer.XMLWriter;


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
