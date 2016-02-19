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
