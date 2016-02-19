
//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.editor;


import com.github.enifs.visualframework.ContentPanel;
import com.github.enifs.visualframework.elements.Element;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.github.enifs.visualframework.Clickable;
import com.github.enifs.visualframework.hitTesting.HitTesting;


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
