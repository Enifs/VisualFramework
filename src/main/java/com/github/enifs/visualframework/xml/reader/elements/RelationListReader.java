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
import com.github.enifs.visualframework.xml.XMLUtils;
import com.github.enifs.visualframework.xml.reader.templates.XMLElementTemplate;
import com.github.enifs.visualframework.xml.reader.templates.XMLRelationTemplate;


public class RelationListReader extends EmptyReader
{
	public RelationListReader(EmptyReader reader, XMLData dataSaver, XMLElementTemplate parentTemplate)
	{
		super(reader);
		this.dataSaver = dataSaver;
		this.parentTemplate = parentTemplate;
	}


	public EmptyReader newTag(String tag)
	{
		EmptyReader reader;

		if (tag.equals(XMLUtils.RELATION))
		{
			XMLRelationTemplate relationTemplate = new XMLRelationTemplate();
			relationTemplate.setElementTemplate(parentTemplate);
			reader = new RelationReader(this, relationTemplate);
			this.dataSaver.addRelationTemplate(relationTemplate);
		}
		else
		{
			reader = new EmptyReader(this);
		}

		return reader;
	}


	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private XMLElementTemplate parentTemplate;

	private XMLData dataSaver;
}
