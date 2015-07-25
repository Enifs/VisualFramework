
package main.editor;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Clickable;
import main.ContentPanel;
import main.elements.Element;
import main.hitTesting.HitTesting;


public class EditorMouseAdapter extends MouseAdapter
{
	public EditorMouseAdapter(ContentPanel contentPanel, Editor editor)
	{
		this.contentPanel = contentPanel;
		this.ownerEditor = editor;
	}


	@Override
	public void mouseClicked(MouseEvent e)
	{
		super.mouseClicked(e);

		Element element = new HitTesting(e.getX(), e.getY(), this.contentPanel.getRoot()).test();

		if (element != null)
		{
			if (element instanceof SimpleRelationDisplay)
			{
				ownerEditor.relationEditing.setRelation(((SimpleRelationDisplay) element).simpleRelation);
				ownerEditor.myLayout.run();
			}
			else if (element instanceof ChainRelationDisplay)
			{
				ownerEditor.relationEditing.setRelation(((ChainRelationDisplay) element).chainRelation);
				ownerEditor.myLayout.run();
			}

			if (element instanceof Clickable)
			{
				((Clickable) element).mouseClickedAction();
			}
		}
	}


	ContentPanel contentPanel;
	Editor ownerEditor;
}
