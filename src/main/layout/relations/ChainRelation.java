
package main.layout.relations;


import main.elements.Element;
import main.layout.LayoutConstant;


/**
 * Chain Relation is used for having all children of an element to have simple relations
 * between each 2 consecutive ones. Order of the children is determined by the order
 * of the children in the parent objects list of children.
 */
public class ChainRelation extends Relation
{
	public ChainRelation(Element owner,
		LayoutConstant orientation,
		LayoutConstant alignment,
		double spacing)
	{
		super(orientation, alignment);
		this.setSpacing(spacing);
		this.setOwner(owner);
	}


	// ---------------------------------------------------------------------
	// Section: Getters
	// ---------------------------------------------------------------------


	@Override
	public RelationType getType()
	{
		return RelationType.chain_relation;
	}


	public void setSpacing(double spacing)
	{
		this.spacing = spacing;
	}


	public double getSpacing()
	{
		return this.spacing;
	}


	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private double spacing = 0;
}
