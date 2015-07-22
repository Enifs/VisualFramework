//
// Armands  02.07.2015.
// HanabiSim
//


package main.xml.reader.templates;


import java.util.HashMap;
import java.util.Map;

import main.layout.LayoutOption;


public class XMLRelationTemplate
{
	public XMLRelationTemplate()
	{
		this.type = null;
		this.elementTemplate = null;
		this.attributeMap = new HashMap<String, String>(5);
	}


	// ---------------------------------------------------------------------
	// Section: Setters
	// ---------------------------------------------------------------------


	public void setType(String type)
	{
		this.type = type;
	}


	public void setElementTemplate(XMLElementTemplate elementTemplate)
	{
		this.elementTemplate = elementTemplate;
	}


	public void putAttribute(String attribute, String value)
	{
		this.attributeMap.put(attribute, value);
	}


	// ---------------------------------------------------------------------
	// Section: Getters
	// ---------------------------------------------------------------------


	public String getType()
	{
		return this.type;
	}


	public XMLElementTemplate getParentTemplate()
	{
		return this.elementTemplate;
	}


	public String getAttribute(String attribute)
	{
		return this.attributeMap.get(attribute);
	}


	public String getAttribute(LayoutOption option)
	{
		return this.attributeMap.get(option.toString());
	}


	public Map<String, String> getAttributeMap()
	{
		return this.attributeMap;
	}


	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private String type;

	private XMLElementTemplate elementTemplate;

	private Map<String, String> attributeMap;
}
