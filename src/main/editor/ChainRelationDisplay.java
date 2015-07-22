
package main.editor;


import java.awt.Color;
import java.awt.Graphics2D;

import main.elements.RectangularElement;
import main.elements.TextElement;
import main.elements.button.ButtonAction;
import main.layout.LayoutConstant;
import main.layout.relations.ChainRelation;
import main.layout.relations.SimpleRelation;
import main.elements.Element;
import main.elements.button.Button;


public class ChainRelationDisplay extends RectangularElement
{
	public ChainRelationDisplay(ChainRelation chainRelation, RelationArea relationArea)
	{
		this.chainRelation = chainRelation;
		this.relationArea = relationArea;

		TextElement e1 = new TextElement("'" + this.chainRelation.getOwner().getId() + "'");
		TextElement orientation = new TextElement("'" + this.chainRelation
			.getOrientation().name() + "'");
		TextElement spacing = new TextElement("'" + String.valueOf(this.chainRelation
			.getSpacing()) + "'");

		e1.setHitTestInvisible(true);
		orientation.setHitTestInvisible(true);
		spacing.setHitTestInvisible(true);

		Button deleteButton = new Button("delete");
		deleteButton.setColor(Color.RED);
		deleteButton.addAction(new Delete());

		SimpleRelation
			r1 = new SimpleRelation(e1, orientation, LayoutConstant.ORIENTATION_HORIZONTAL, 10,
			LayoutConstant.ALIGNMENT_MIN);
		SimpleRelation r2 = new SimpleRelation(orientation, spacing, LayoutConstant.ORIENTATION_HORIZONTAL, 10,
			LayoutConstant.ALIGNMENT_MIN);
		SimpleRelation r3 = new SimpleRelation(spacing, deleteButton, LayoutConstant.ORIENTATION_HORIZONTAL, 10,
			LayoutConstant.ALIGNMENT_MIN);

		this.addChild(e1);
		this.addChild(orientation);
		this.addChild(spacing);
		this.addChild(deleteButton);

		this.addRelation(r1);
		this.addRelation(r2);
		this.addRelation(r3);
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
			Element owner  = chainRelation.getOwner();
			chainRelation.getOwner().removeRelation(chainRelation);
			relationArea.populateContent(owner);
		}
	}

	ChainRelation chainRelation;
	private RelationArea relationArea;
}
