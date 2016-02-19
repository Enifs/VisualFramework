
//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.editor;


import com.github.enifs.visualframework.elements.Element;
import com.github.enifs.visualframework.elements.RectangularElement;
import com.github.enifs.visualframework.elements.TextElement;
import com.github.enifs.visualframework.elements.button.Button;
import com.github.enifs.visualframework.layout.relations.SimpleRelation;
import java.awt.Color;
import java.awt.Graphics2D;

import com.github.enifs.visualframework.elements.button.ButtonAction;
import com.github.enifs.visualframework.layout.LayoutConstant;


public class SimpleRelationDisplay extends RectangularElement
{
	public SimpleRelationDisplay(SimpleRelation simpleRelation, RelationArea relationArea)
	{
		this.simpleRelation = simpleRelation;
		this.relationArea = relationArea;

		TextElement e1 = new TextElement("'" + simpleRelation.getMinElement().getId() + "'");
		TextElement e2 = new TextElement("'" + simpleRelation.getMaxElement().getId() + "'");
		TextElement orientation = new TextElement("'" + simpleRelation.getOrientation().name() + "'");
		TextElement x = new TextElement("'" + String.valueOf(simpleRelation.getWeight()) + "'");

		e1.setHitTestInvisible(true);
		e2.setHitTestInvisible(true);
		orientation.setHitTestInvisible(true);
		x.setHitTestInvisible(true);

		Button deleteButton = new Button("delete");
		deleteButton.setColor(Color.RED);
		deleteButton.addAction(new Delete());

		SimpleRelation
			r1 = new SimpleRelation(e1, e2, LayoutConstant.ORIENTATION_HORIZONTAL, 10,
			LayoutConstant.ALIGNMENT_MIN);
		SimpleRelation r2 = new SimpleRelation(e2, orientation, LayoutConstant.ORIENTATION_HORIZONTAL, 10,
			LayoutConstant.ALIGNMENT_MIN);
		SimpleRelation r3 = new SimpleRelation(orientation, x, LayoutConstant.ORIENTATION_HORIZONTAL, 10,
			LayoutConstant.ALIGNMENT_MIN);
		SimpleRelation r4 = new SimpleRelation(x, deleteButton, LayoutConstant.ORIENTATION_HORIZONTAL, 10,
			LayoutConstant.ALIGNMENT_MIN);

		this.addChild(e1);
		this.addChild(e2);
		this.addChild(orientation);
		this.addChild(x);
		this.addChild(deleteButton);

		this.addRelation(r1);
		this.addRelation(r2);
		this.addRelation(r3);
		this.addRelation(r4);
	}


	@Override
	public void draw(Graphics2D g)
	{
		// invisible
	}

	private class Delete extends ButtonAction
	{
		@Override
		public void action()
		{
			Element owner  = simpleRelation.getOwner();
			simpleRelation.getOwner().removeRelation(simpleRelation);
			relationArea.populateContent(owner);
		}
	}

	SimpleRelation simpleRelation;
	private RelationArea relationArea;
}
