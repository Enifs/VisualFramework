//
// Armands  02.07.2015.
// HanabiSim
//


package main.xml.reader.elements;


import main.xml.XMLUtils;
import main.xml.reader.XMLData;
import main.xml.reader.templates.XMLElementTemplate;
import main.xml.reader.templates.XMLRelationTemplate;


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
