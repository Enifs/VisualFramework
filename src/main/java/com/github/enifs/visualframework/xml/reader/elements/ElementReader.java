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


import com.github.enifs.visualframework.xml.reader.XMLData;
import com.github.enifs.visualframework.xml.reader.templates.XMLElementTemplate;
import com.github.enifs.visualframework.xml.XMLUtils;


public class ElementReader extends EmptyReader
{
	public ElementReader(EmptyReader reader, XMLData dataSaver, XMLElementTemplate elementTemplate)
	{
		super(reader);
		this.dataSaver = dataSaver;
		this.elementTemplate = elementTemplate;
	}


	public EmptyReader newTag(String tag)
	{
		EmptyReader reader;

		if (tag.equals(XMLUtils.ELEMENTS))
		{
			reader = new ElementListReader(this, this.dataSaver, this.elementTemplate);
		}
		else if (tag.equals(XMLUtils.RELATIONS))
		{
			reader = new RelationListReader(this, this.dataSaver, this.elementTemplate);
		}
		else
		{
			reader = new EmptyReader(this);
		}

		return reader;
	}


	public void parseAttribute(String attribute, String value)
	{
		if (attribute.equals(XMLUtils.NAME))
		{
			this.elementTemplate.setID(value);
		}
	}


	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private XMLData dataSaver;

	private XMLElementTemplate elementTemplate;
}


