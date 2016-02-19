
//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.editor;


import com.github.enifs.visualframework.ContentPanel;
import com.github.enifs.visualframework.Size;
import com.github.enifs.visualframework.elements.*;
import com.github.enifs.visualframework.elements.button.Button;
import com.github.enifs.visualframework.elements.button.ButtonAction;
import com.github.enifs.visualframework.layout.relations.ChainRelation;
import com.github.enifs.visualframework.layout.relations.Relation;
import com.github.enifs.visualframework.layout.relations.SimpleRelation;
import java.awt.Color;

import com.github.enifs.visualframework.layout.LayoutConstant;


public class RelationEditing extends RectangularElement
{
	public RelationEditing(Relation relation, ContentPanel contentPanel, Editor editor)
	{
		this.relation = relation;
		this.contentPanel = contentPanel;
		this.editor = editor;

		this.setId("relationEditing");
		this.setColor(Color.ORANGE);
		this.setPreferredSize(new Size(100, 100));

		this.addRelation(
			new ChainRelation(
				this,
				LayoutConstant.ORIENTATION_VERTICAL,
				LayoutConstant.ALIGNMENT_MIN, 20));

		if (relation != null)
		{
			switch (relation.getType())
			{
    			case simple_relation:
				    this.initForSimpleRelation();
					break;
				case chain_relation:
					break;
			}
		}
	}


	public void setRelation(Relation relation)
	{
		this.relation = relation;

		if (relation != null)
		{
			switch (relation.getType())
			{
				case simple_relation:
					this.initForSimpleRelation();
					break;
				case chain_relation:
					this.initForChainRelation();
					break;
			}
		}
		else
		{
			removeAllChildren();
		}
	}


	private void initForChainRelation()
	{
		this.removeAllChildren();

		final ChainRelation chainRelation = (ChainRelation) this.relation;

		final TextElement owner = new TextElement("Owner " + chainRelation.getOwner().getId());

		final LabeledComboBox<LayoutConstant> orientationInput = new LabeledComboBox("Orientation", this.contentPanel);
		orientationInput.getComboBox().comboBox.addItem(LayoutConstant.ORIENTATION_HORIZONTAL);
		orientationInput.getComboBox().comboBox.addItem(LayoutConstant.ORIENTATION_VERTICAL);
		orientationInput.getComboBox().comboBox.setSelectedItem(chainRelation.getOrientation());

		final LabeledComboBox<LayoutConstant> alignmentInput = new LabeledComboBox("Orientation", this.contentPanel);
		alignmentInput.getComboBox().comboBox.addItem(LayoutConstant.ALIGNMENT_NONE);
		alignmentInput.getComboBox().comboBox.addItem(LayoutConstant.ALIGNMENT_MIN);
		alignmentInput.getComboBox().comboBox.addItem(LayoutConstant.ALIGNMENT_MIDDLE);
		alignmentInput.getComboBox().comboBox.addItem(LayoutConstant.ALIGNMENT_MAX);
		alignmentInput.getComboBox().comboBox.setSelectedItem(chainRelation.getAlignment());

		final LabeledInputField spacingInput = new LabeledInputField("Spacing", this.contentPanel);
		spacingInput.getInput().textField.setText(chainRelation.getSpacing() + "");

		Button saveButton = new Button("save");
		saveButton.addAction(new ButtonAction()
		{
			@Override
			public void action()
			{
				chainRelation.setOrientation((LayoutConstant) orientationInput.getComboBox().comboBox.getSelectedItem());
				chainRelation.setSpacing(Double.parseDouble(spacingInput.getInput().textField.getText()));
				chainRelation.setAlignment((LayoutConstant) alignmentInput.getComboBox().comboBox.getSelectedItem());

				editor.relationArea.populateContent(relation.getOwner());
			}
		});

		saveButton.addAction(new RunObservedLayoutAction(this.foreignRoot));

		this.addChild(owner);
		this.addChild(orientationInput);
		this.addChild(alignmentInput);
		this.addChild(spacingInput);
		this.addChild(saveButton);
	}


	private void initForSimpleRelation()
	{
		this.removeAllChildren();

		final SimpleRelation simpleRelation = (SimpleRelation) this.relation;

		final LabeledComboBox<Element> minElementInput = new LabeledComboBox<>("Min Element", this.contentPanel);

		for (Element element : simpleRelation.getOwner().getChildren())
		{
			minElementInput.getComboBox().comboBox.addItem(element);
		}

		minElementInput.getComboBox().comboBox.addItem(simpleRelation.getOwner());
		minElementInput.getComboBox().comboBox.setSelectedItem(simpleRelation.getMinElement());

		final LabeledComboBox<Element> maxElementInput = new LabeledComboBox<>("Max Element", this.contentPanel);

		for (Element element : simpleRelation.getOwner().getChildren())
		{
			maxElementInput.getComboBox().comboBox.addItem(element);
		}

		maxElementInput.getComboBox().comboBox.addItem(simpleRelation.getOwner());
		maxElementInput.getComboBox().comboBox.setSelectedItem(simpleRelation.getMaxElement());

		final LabeledComboBox<LayoutConstant> orientationInput = new LabeledComboBox("Orientation", this.contentPanel);
		orientationInput.getComboBox().comboBox.addItem(LayoutConstant.ORIENTATION_HORIZONTAL);
		orientationInput.getComboBox().comboBox.addItem(LayoutConstant.ORIENTATION_VERTICAL);
		orientationInput.getComboBox().comboBox.setSelectedItem(simpleRelation.getOrientation());

		final LabeledComboBox<LayoutConstant> alignmentInput = new LabeledComboBox("Alignment", this.contentPanel);
		alignmentInput.getComboBox().comboBox.addItem(LayoutConstant.ALIGNMENT_NONE);
		alignmentInput.getComboBox().comboBox.addItem(LayoutConstant.ALIGNMENT_MIN);
		alignmentInput.getComboBox().comboBox.addItem(LayoutConstant.ALIGNMENT_MIDDLE);
		alignmentInput.getComboBox().comboBox.addItem(LayoutConstant.ALIGNMENT_MAX);
		alignmentInput.getComboBox().comboBox.setSelectedItem(simpleRelation.getAlignment());

		final LabeledInputField weightInput = new LabeledInputField("Weight", this.contentPanel);
		weightInput.getInput().textField.setText(simpleRelation.getWeight() + "");

		Button saveButton = new Button("save");
		saveButton.addAction(new ButtonAction()
		{
			@Override
			public void action()
			{
				simpleRelation.setOrientation((LayoutConstant) orientationInput.getComboBox().comboBox.getSelectedItem());
				simpleRelation.setWeight(Double.parseDouble(weightInput.getInput().textField.getText()));
				simpleRelation.setMinElement((Element) minElementInput
					.getComboBox().comboBox.getSelectedItem());
				simpleRelation.setMaxElement((Element) maxElementInput.getComboBox().comboBox.getSelectedItem());
				simpleRelation.setAlignment((LayoutConstant) alignmentInput.getComboBox().comboBox.getSelectedItem());

				editor.relationArea.populateContent(relation.getOwner());
			}
		});

		saveButton.addAction(new RunObservedLayoutAction(this.foreignRoot));

		this.addChild(minElementInput);
		this.addChild(maxElementInput);
		this.addChild(orientationInput);
		this.addChild(alignmentInput);
		this.addChild(weightInput);
		this.addChild(saveButton);
	}


	public void setForeignRoot(Element foreignRoot)
	{
		this.foreignRoot = foreignRoot;
	}

	private Editor editor;
	Relation relation;
	ContentPanel contentPanel;
	Element foreignRoot;
}
