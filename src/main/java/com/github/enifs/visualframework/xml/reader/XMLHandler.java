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


package com.github.enifs.visualframework.xml.reader;


import com.github.enifs.visualframework.xml.reader.elements.EmptyReader;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * This class overwrite DefaultHandler that helps to read elements and its attributes.
 */
public class XMLHandler extends DefaultHandler
{
	public XMLHandler(EmptyReader reader)
	{
		this.reader = reader;
	}


	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
		throws SAXException
	{
		this.reader = this.reader.newTag(qName);

		for (int i = 0; i < attributes.getLength(); i++)
		{
			this.reader.parseAttribute(attributes.getQName(i), attributes.getValue(i));
		}
	}


	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		this.reader = this.reader.getReader();
	}



	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private EmptyReader reader;
}
