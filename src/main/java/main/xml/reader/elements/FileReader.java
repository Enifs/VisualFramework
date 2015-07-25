//
// Armands  02.07.2015.
// HanabiSim
//


package main.xml.reader.elements;


import main.xml.XMLUtils;
import main.xml.reader.XMLData;


public class FileReader extends EmptyReader
{
	public FileReader(EmptyReader reader, XMLData data)
	{
		super(reader);
		this.data = data;
	}


	public EmptyReader newTag(String tag)
	{
		EmptyReader reader;

		if (tag.equals(XMLUtils.ELEMENTS))
		{
			reader = new ElementListReader(this, this.data, null);
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


	private XMLData data;
}
