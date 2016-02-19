
//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.layout.relations;


import com.github.enifs.visualframework.elements.Element;
import com.github.enifs.visualframework.layout.LayoutConstant;


public abstract class Relation
{
	public Relation(LayoutConstant orientation, LayoutConstant alignment)
	{
		this.orientation = orientation;
		this.alignment = alignment;
	}


	// ---------------------------------------------------------------------
	// Section: Getters
	// ---------------------------------------------------------------------


	public abstract RelationType getType();


	public LayoutConstant getOrientation()
	{
		return this.orientation;
	}


	public Element getOwner()
	{
		return owner;
	}


	public LayoutConstant getAlignment()
	{
		return this.alignment;
	}


	// ---------------------------------------------------------------------
	// Section: Setters
	// ---------------------------------------------------------------------


	public void setOrientation(LayoutConstant orientation)
	{
		this.orientation = orientation;
	}


	public void setAlignment(LayoutConstant alignment)
	{
		this.alignment = alignment;
	}


	public void setOwner(Element owner)
	{
		this.owner = owner;
	}


	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private Element owner;

	private LayoutConstant orientation;

	private LayoutConstant alignment = LayoutConstant.ALIGNMENT_MIN;
}
