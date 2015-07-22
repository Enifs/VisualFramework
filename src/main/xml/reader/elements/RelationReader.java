//
// Armands  02.07.2015.
// HanabiSim
//


package main.xml.reader.elements;


import main.xml.XMLUtils;
import main.xml.reader.templates.XMLRelationTemplate;


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
