
package main.editor;


import java.awt.Color;

import main.Size;
import main.elements.RectangularElement;
import main.elements.button.ButtonAction;
import main.layout.relations.ChainRelation;
import main.layout.relations.Relation;
import main.layout.relations.SimpleRelation;
import main.elements.Element;
import main.elements.button.Button;
import main.layout.LayoutConstant;


public class RelationArea extends RectangularElement
{
	public RelationArea(Editor editor)
	{
		this.editor = editor;

		this.setColor(Color.WHITE);
		this.setId("RelationArea");
		this.setPreferredSize(new Size(100, 100));

		this.addRelation(
			new ChainRelation(
				this,
				LayoutConstant.ORIENTATION_VERTICAL,
				LayoutConstant.ALIGNMENT_MIN, 20));
	}

	public void populateContent(Element element)
	{
		this.removeAllChildren();

		for (Relation relation : element.getRelations())
		{
			switch (relation.getType())
			{
				case simple_relation:
					this.addChild(new SimpleRelationDisplay((SimpleRelation) relation, this));
					break;
				case chain_relation:
					this.addChild(new ChainRelationDisplay((ChainRelation) relation, this));
					break;
			}
		}

		this.addChild(new NewRelation(element, editor));
		this.addChild(new NewChainRelation(element, editor));
	}

	private class NewRelation extends Button
	{
		public NewRelation(final Element element, final Editor editor)
		{
			super("Add New Relation");
			this.setColor(Color.GREEN);
			this.addAction(new ButtonAction()
			{
				@Override
				public void action()
				{
					SimpleRelation simpleRelation =
						new SimpleRelation(element, element, LayoutConstant.ORIENTATION_HORIZONTAL, 0,
							LayoutConstant.ALIGNMENT_MIN);

					element.addRelation(simpleRelation);
					editor.relationEditing.setRelation(simpleRelation);
					editor.relationArea.populateContent(element);
				}
			});
		}
	}

	private class NewChainRelation extends Button
	{
		public NewChainRelation(final Element element, final Editor editor)
		{
			super("Add New Chain Relation");
			this.setColor(Color.CYAN);
			this.addAction(new ButtonAction()
			{
				@Override
				public void action()
				{
					ChainRelation chainRelation =
						new ChainRelation(element, LayoutConstant.ORIENTATION_HORIZONTAL,
							LayoutConstant.ALIGNMENT_MIN,
							0);

					element.addRelation(chainRelation);
					editor.relationEditing.setRelation(chainRelation);
					editor.relationArea.populateContent(element);
				}
			});
		}
	}

	Editor editor;
}
