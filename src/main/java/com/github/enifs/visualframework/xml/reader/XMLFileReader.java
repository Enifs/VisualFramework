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


import com.github.enifs.visualframework.xml.reader.elements.FileReader;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class XMLFileReader
{
	public XMLFileReader(String file) throws Exception
	{
		this.dataSaver = new XMLData();

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();

		reader.setContentHandler(new XMLHandler(new FileReader(null, this.dataSaver)));
		reader.parse(new InputSource(file));
	}


	public XMLData getDataSaver()
	{
		return this.dataSaver;
	}


	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private XMLData dataSaver;
}
