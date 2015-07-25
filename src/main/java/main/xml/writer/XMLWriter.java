//
// Armands  02.07.2015.
// HanabiSim
//


package main.xml.writer;


import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import main.elements.Element;
import main.layout.relations.ChainRelation;
import main.layout.relations.Relation;
import main.layout.relations.SimpleRelation;
import main.xml.XMLUtils;
import org.xml.sax.helpers.AttributesImpl;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class XMLWriter
{
	/**
	 * This method create new file with given name and save to it root element and all its children and
	 * relations.
	 * @param file String that represents file name (and location)
	 * @param rootElement Root element that will be as first one.
	 * @throws Exception
	 */
	public static void writeXMLFile(String file, Element rootElement) throws Exception
	{
		OutputStream outputStream = new FileOutputStream(file);
		XMLSerializer xmlSerializer = new XMLSerializer(outputStream, new OutputFormat("xml", "utf-8", true));
		xmlSerializer.startDocument();
		xmlSerializer.startElement(null, null, XMLUtils.ELEMENTS, new AttributesImpl());

		XMLWriter.saveElement(xmlSerializer, rootElement);

		xmlSerializer.endElement(null, null, XMLUtils.ELEMENTS);
		xmlSerializer.endDocument();

		System.out.println("XML ready!");
	}


	/**
	 * This method save all element and its children and relations to file.
	 * @param serializer XMLSerializer that will be used to save to file.
	 * @param element Target element, that need to be saved.
	 * @throws Exception
	 */
	private static void saveElement(XMLSerializer serializer, Element element) throws Exception
	{
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("", XMLUtils.NAME, "", "", element.getId());

		serializer.startElement(null, null, XMLUtils.ELEMENT, attributes);

		serializer.startElement(null, null, XMLUtils.ELEMENTS, new AttributesImpl());

		for (Element childElement : element.getChildren())
		{
			XMLWriter.saveElement(serializer, childElement);
		}

		serializer.endElement(null, null, XMLUtils.ELEMENTS);

		serializer.startElement(null, null, XMLUtils.RELATIONS, new AttributesImpl());

		for (Relation relation : element.getRelations())
		{
			switch (relation.getType())
			{
    			case simple_relation:
				    XMLWriter.saveSimpleRelation(serializer, ((SimpleRelation) relation));
					break;
				case chain_relation:
					XMLWriter.saveChainRelation(serializer, ((ChainRelation) relation));
					break;
			}
		}

		serializer.endElement(null, null, XMLUtils.RELATIONS);

		serializer.endElement(null, null, XMLUtils.ELEMENT);
	}


	/**
	 * This method saves relation to file.
	 * @param serializer XMLSerializer that will be used to save relation to file.
	 * @param simpleRelation Relation that need to be saved.
	 * @throws Exception
	 */
	private static void saveSimpleRelation(XMLSerializer serializer,
		SimpleRelation simpleRelation) throws Exception
	{
		AttributesImpl attributes = new AttributesImpl();

		attributes.addAttribute("", XMLUtils.TYPE, "", "", String.valueOf(simpleRelation.getType()));
		attributes.addAttribute("", XMLUtils.MIN_ELEMENT, "", "", simpleRelation.getMinElement().getId());
		attributes.addAttribute("", XMLUtils.MAX_ELEMENT, "", "", simpleRelation.getMaxElement().getId());
		attributes.addAttribute("", XMLUtils.ALIGNMENT, "", "", simpleRelation.getAlignment().toString());
		attributes.addAttribute("", XMLUtils.ORIENTATION, "", "", simpleRelation.getOrientation().toString());
		attributes.addAttribute("", XMLUtils.WEIGHT, "", "", simpleRelation.getWeight() + "");

		serializer.startElement(null, null, XMLUtils.RELATION, attributes);
		serializer.endElement(null, null, XMLUtils.RELATION);
	}


	/**
	 * This method saves relation to file.
	 * @param serializer XMLSerializer that will be used to save relation to file.
	 * @param chainRelation Relation that need to be saved.
	 * @throws Exception
	 */
	private static void saveChainRelation(XMLSerializer serializer,
		ChainRelation chainRelation) throws Exception
	{
		AttributesImpl attributes = new AttributesImpl();

		attributes.addAttribute("", XMLUtils.TYPE, "", "", String.valueOf(chainRelation.getType()));
		attributes.addAttribute("", XMLUtils.SPACING, "", "", String.valueOf(chainRelation.getSpacing()));
		attributes.addAttribute("", XMLUtils.ALIGNMENT, "", "", chainRelation.getAlignment().toString());
		attributes.addAttribute("", XMLUtils.ORIENTATION, "", "", chainRelation.getOrientation().toString());

		serializer.startElement(null, null, XMLUtils.RELATION, attributes);
		serializer.endElement(null, null, XMLUtils.RELATION);
	}
}
