//
// Armands  02.07.2015.
// HanabiSim
//


package main.xml.reader.templates;



public class XMLElementTemplate
{
	public XMLElementTemplate()
	{
		this.id = null;
		this.elementTemplate = null;
	}


	// ---------------------------------------------------------------------
	// Section: Setters
	// ---------------------------------------------------------------------


	public void setID(String id)
	{
		this.id = id;
	}


	public void setElementTemplate(XMLElementTemplate elementTemplate)
	{
		this.elementTemplate = elementTemplate;
	}


	// ---------------------------------------------------------------------
	// Section: Getters
	// ---------------------------------------------------------------------


	public XMLElementTemplate getParentTemplate()
	{
		return this.elementTemplate;
	}


	public String getID()
	{
		return this.id;
	}


	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private XMLElementTemplate elementTemplate;

	private String id;
}
