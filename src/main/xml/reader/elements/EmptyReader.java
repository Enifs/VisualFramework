//
// Armands  02.07.2015.
// HanabiSim
//


package main.xml.reader.elements;


/**
 * This will help to create Elements readers and contains all necessary methods for each reader.
 */
public class EmptyReader
{
	public EmptyReader(EmptyReader reader)
	{
		this.reader = reader;
	}


	public EmptyReader newTag(String tag)
	{
		return new EmptyReader(this);
	}


	public void parseAttribute(String attribute, String value)
	{
	}


	public EmptyReader getReader()
	{
		return this.reader;
	}


	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private EmptyReader reader;
}
