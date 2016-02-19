//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.layout.relations;


import com.github.enifs.visualframework.elements.Element;
import com.github.enifs.visualframework.layout.LayoutConstant;


public class SimpleRelation extends Relation
{
	public SimpleRelation(Element minElement,
		Element maxElement,
		LayoutConstant orientation,
		double weight,
		LayoutConstant alignment)
	{
		super(orientation, alignment);
		this.minElement = minElement;
		this.maxElement = maxElement;
		this.weight = weight;
	}

	// ---------------------------------------------------------------------
	// Section: Setters
	// ---------------------------------------------------------------------


	public void setMinElement(Element minElement)
	{
		this.minElement = minElement;
	}


	public void setMaxElement(Element maxElement)
	{
		this.maxElement = maxElement;
	}


	public void setWeight(double weight)
	{
		this.weight = weight;
	}


	// ---------------------------------------------------------------------
	// Section: Getters
	// ---------------------------------------------------------------------


	public Element getMinElement()
	{
		return this.minElement;
	}


	public Element getMaxElement()
	{
		return this.maxElement;
	}


	public double getWeight()
	{
		return this.weight;
	}


	@Override
	public RelationType getType()
	{
		return RelationType.simple_relation;
	}

	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private Element minElement;

	private Element maxElement;

	private double weight;
}
