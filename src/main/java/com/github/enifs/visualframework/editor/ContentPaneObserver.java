
//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.editor;


import com.github.enifs.visualframework.elements.Element;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.github.enifs.visualframework.ContentPanel;
import com.github.enifs.visualframework.hitTesting.HitTesting;


public class ContentPaneObserver extends MouseAdapter
{
	public ContentPaneObserver(ContentPanel root)
	{
		this.root = root;
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		super.mouseMoved(e);
		HitTesting hitTesting = new HitTesting(e.getX(), e.getY(), root.getRoot());

		Element element = hitTesting.test();

		if (element != null)
		{
			editor.hitTestHoverName.setText("Mouse Over = '" + element.getId() + "'");
		}
	}


	@Override
	public void mouseClicked(MouseEvent e)
	{
		super.mouseClicked(e);

		HitTesting hitTesting = new HitTesting(e.getX(), e.getY(), root.getRoot());
		Element element = hitTesting.test();

		if (element != null)
		{
			this.editor.selectedElement = element;
			this.editor.selectedElementName.setText("Selected = '" + element.getId() + "'");
			this.editor.relationArea.populateContent(element);
			this.editor.relationEditing.setRelation(null);

			editor.myLayout.run();
		}
	}


	public void setEditor(Editor editor)
	{
		this.editor = editor;
	}


	public ContentPanel root;
	public Editor editor;
}
