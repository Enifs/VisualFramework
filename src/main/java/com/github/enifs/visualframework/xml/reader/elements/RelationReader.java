//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

//
// Armands  02.07.2015.
// HanabiSim
//


package com.github.enifs.visualframework.xml.reader.elements;


import com.github.enifs.visualframework.xml.XMLUtils;
import com.github.enifs.visualframework.xml.reader.templates.XMLRelationTemplate;


public class RelationReader extends EmptyReader
{
	public RelationReader(EmptyReader reader, XMLRelationTemplate relationTemplate)
	{
		super(reader);
		this.relationTemplate = relationTemplate;
	}


	public void parseAttribute(String attribute, String value)
	{
		if (attribute.equals(XMLUtils.TYPE))
		{
			this.relationTemplate.setType(value);
		}
		else
		{
			this.relationTemplate.putAttribute(attribute, value);
		}
	}


	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private XMLRelationTemplate relationTemplate;
}
