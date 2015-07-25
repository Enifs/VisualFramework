//
// Armands  02.07.2015.
// HanabiSim
//


package main.xml.reader;


import java.util.ArrayList;
import java.util.List;

import main.xml.reader.templates.XMLElementTemplate;
import main.xml.reader.templates.XMLRelationTemplate;


public class XMLData
{
	public XMLData()
	{
		this.elementTemplateList = new ArrayList<XMLElementTemplate>();
		this.relationTemplateList = new ArrayList<XMLRelationTemplate>();
	}


	public void addElementTemplate(XMLElementTemplate elementTemplate)
	{
		this.elementTemplateList.add(elementTemplate);
	}


	public void addRelationTemplate(XMLRelationTemplate relationTemplate)
	{
		this.relationTemplateList.add(relationTemplate);
	}


	// ---------------------------------------------------------------------
	// Section: Getters
	// ---------------------------------------------------------------------


	public List<XMLElementTemplate> getElementTemplateList()
	{
		return this.elementTemplateList;
	}


	public List<XMLRelationTemplate> getRelationTemplateList()
	{
		return this.relationTemplateList;
	}


	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private List<XMLElementTemplate> elementTemplateList;

	private List<XMLRelationTemplate> relationTemplateList;
}
