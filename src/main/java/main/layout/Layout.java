package main.layout;


import java.util.*;

import main.Size;
import main.layout.relations.ChainRelation;
import main.layout.relations.Relation;
import main.layout.relations.SimpleRelation;
import main.xml.XMLUtils;
import main.xml.reader.XMLData;
import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import main.elements.Element;
import main.layout.relations.RelationType;
import main.xml.reader.XMLFileReader;
import main.xml.reader.templates.XMLRelationTemplate;


public class Layout
{
	public Layout(Element root, String config)
	{
		this.root = root;

		try
		{
			if (config != null)
			{
				XMLFileReader reader = new XMLFileReader(config);
				this.configurationData = reader.getDataSaver();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.configurationData = null;
		}


		if (this.configurationData != null)
		{
			this.init();
		}
	}


	private void init()
	{
		for (XMLRelationTemplate relationTemplate :
			this.configurationData.getRelationTemplateList())
		{
			List<XMLRelationTemplate> list =
				this.relationMap.get(relationTemplate.getParentTemplate().getID());

			if (list == null)
			{
				list = new ArrayList<>();
				this.relationMap.put(relationTemplate.getParentTemplate().getID(), list);
			}

			list.add(relationTemplate);

			if (relationTemplate.getType().equals(RelationType.simple_relation.name()))
			{
				this.dataElements
					.add(relationTemplate.getAttribute(LayoutOption.min_element));
				this.dataElements
					.add(relationTemplate.getAttribute(LayoutOption.max_element));
				this.dataElements.add(relationTemplate.getParentTemplate().getID());
			}
		}
	}


	public void run()
	{
		this.resetPositions(this.root);

		if (this.configurationData != null)
		{
			this.resetElementRelations(this.root);
		}

		this.doLayout(this.root);

		this.adjustPositions(this.root);

		this.setScreenSizes(this.root);
	}


	private void resetPositions(Element element)
	{
		for (Element child : element.getChildren())
		{
			child.setPosition(new Point2D(0, 0));

			this.resetPositions(child);
		}
	}


	public void setScreenSizes(Element element)
	{
		element.setScreenSize(element.getLayoutSize());
		for (Element child : element.getChildren())
		{
			setScreenSizes(child);
		}
	}


	private void adjustPositions(Element element)
	{
		if (element.getId().equals("Rectangle - WHITE"))
		{
			int a = 423;
		}

		for (Element child : element.getChildren())
		{
			child.setPosition(element.getPosition().plus(child.getPosition()));

			this.adjustPositions(child);
		}
	}


	private void doLayout(Element element)
	{
		for (Element child : element.getChildren())
		{
			this.doLayout(child);
		}

		this.map.clear();

		this.currentRoot = element;

		Graph graph = this.constructGraph(element);

		Size size = new SizeFunctor(graph.calculateSize(), element).run();

		element.setLayoutSize(size);
	}


	private void resetElementRelations(Element element)
	{
		this.allElements.put(element.getId(), element);

		for (Element child : element.getChildren())
		{
			resetElementRelations(child);
		}

		if (element.preferConfigurationRelations())
		{
			element.clearRelation();

			List<XMLRelationTemplate> templates =
				relationMap.get(element.getId());

			if (templates != null)
			{
				for (XMLRelationTemplate template : templates)
				{
					Relation relation = this.constructRelation(template);

					if (relation != null)
					{
						element.addRelation(relation);
					}
				}
			}
		}
	}


	private Relation constructRelation(XMLRelationTemplate template)
	{
		Relation relation = null;


		if (template.getType().equals(RelationType.simple_relation.name()))
		{
			Element minElement =
				this.allElements.get(template.getAttribute(LayoutOption.min_element));
			Element maxElement =
				this.allElements.get(template.getAttribute(LayoutOption.max_element));

			if (minElement != null && maxElement != null)
			{
				double weight =
					Double.parseDouble(template.getAttribute(LayoutOption.weight));
				LayoutConstant alignment =
					LayoutConstant.valueOf(template.getAttribute(LayoutOption.alignment));
				LayoutConstant orientation = LayoutConstant
					.valueOf(template.getAttribute(LayoutOption.orientation));

				relation =
					new SimpleRelation(minElement, maxElement, orientation, weight,
						alignment);
			}
		}
		else if (template.getType().equals(RelationType.chain_relation.name()))
		{
			Element owner = this.allElements.get(template.getParentTemplate().getID());
			LayoutConstant orientation =
				LayoutConstant.valueOf(template.getAttribute(LayoutOption.orientation));
			LayoutConstant alignment =
				LayoutConstant.valueOf(template.getAttribute(LayoutOption.alignment));
			double spacing = Double.parseDouble(template.getAttribute(XMLUtils.SPACING));

			relation = new ChainRelation(owner, orientation,
				alignment,
				spacing);
		}

		return relation;
	}


	private Graph constructGraph(Element element)
	{
		Graph graph = new Graph();

		for (Element child : element.getChildren())
		{
			Node node = map.get(child);

			if (node == null)
			{
				node = new Node(child);
				map.put(child, node);
			}

			graph.nodes.add(node);
		}

		for (Relation relation : element.getRelations())
		{
			switch (relation.getType())
			{
				case simple_relation:
					Edge edge = this.createEdge((SimpleRelation) relation, graph);
					graph.addEdge(edge);
					break;
				case chain_relation:
					for (int i = 0; i < element.getChildren().size() - 1; i++)
					{
						SimpleRelation newSimpleRelation = new SimpleRelation(
							element.getChildren().get(i),
							element.getChildren().get(i + 1),
							relation.getOrientation(),
							((ChainRelation) relation).getSpacing(),
							relation.getAlignment());

						graph.addEdge(this.createEdge(newSimpleRelation, graph));
					}
					break;
			}
		}

		return graph;
	}


	public Edge createEdge(SimpleRelation simpleRelation, Graph graph)
	{
		Node minNode = map.get(simpleRelation.getMinElement());

		if (minNode == null)
		{
			minNode = new Node(simpleRelation.getMinElement());
			map.put(simpleRelation.getMinElement(), minNode);
		}

		if (!graph.nodes.contains(minNode))
		{
			graph.nodes.add(minNode);
		}

		Node maxNode = map.get(simpleRelation.getMaxElement());

		if (maxNode == null)
		{
			maxNode = new Node(simpleRelation.getMaxElement());
			map.put(simpleRelation.getMaxElement(), maxNode);
		}

		if (!graph.nodes.contains(maxNode))
		{
			graph.nodes.add(maxNode);
		}


		Edge edge = new Edge();
		edge.source = minNode;
		edge.target = maxNode;
		edge.weight = simpleRelation.getWeight();
		edge.orientation = simpleRelation.getOrientation();
		edge.alignment = simpleRelation.getAlignment();

		minNode.outEdges.add(edge);
		maxNode.inEdges.add(edge);

		return edge;
	}


	private class Graph
	{
		public void addEdge(Edge edge)
		{
			this.edges.add(edge);
			this.propagateEdge(edge.source, edge);
		}


		private void propagateEdge(Node source, Edge edge)
		{
			if (edge.target != source)
			{
				this.propagateAction(edge);

				for (Edge outEdge : edge.target.outEdges)
				{
					this.propagateEdge(source, outEdge);
				}
			}
		}


		private void propagateAction(Edge edge)
		{
			if (edge.target.element != currentRoot)
			{
				Point2D sourcePos = edge.source.element.getPosition();
				Point2D targetPos = edge.target.element.getPosition();

				double xDiff = targetPos.getX() - sourcePos.getX() -
					edge.source.element.getLayoutSize().getW();
				double yDiff = targetPos.getY() - sourcePos.getY() -
					edge.source.element.getLayoutSize().getH();

				if (edge.source.element == currentRoot)
				{
					edge.alignment = LayoutConstant.ALIGNMENT_NONE;

					xDiff += edge.source.element.getLayoutSize().getW();
					yDiff += edge.source.element.getLayoutSize().getH();
				}

				double horizontalShift = 0;
				double verticalShift = 0;

				if (edge.orientation.equals(LayoutConstant.ORIENTATION_HORIZONTAL) &&
					xDiff < edge.weight)
				{
					horizontalShift = edge.weight - xDiff;

					switch (edge.alignment)
					{
						case ALIGNMENT_MIN:
							verticalShift =
								-yDiff - edge.source.element.getLayoutSize().getH();
							break;
						case ALIGNMENT_MIDDLE:
							double d = (edge.target.element.getLayoutSize().getH() / 2) +
								(edge.source.element.getLayoutSize().getH() / 2);
							verticalShift =
								-yDiff - d;
							break;
						case ALIGNMENT_MAX:
							verticalShift =
								-yDiff - edge.target.element.getLayoutSize().getH();
							break;
					}
				}

				if (edge.orientation.equals(LayoutConstant.ORIENTATION_VERTICAL) &&
					yDiff < edge.weight)
				{
					verticalShift = edge.weight - yDiff;

					switch (edge.alignment)
					{
						case ALIGNMENT_MIN:
							horizontalShift =
								-xDiff - edge.source.element.getLayoutSize().getW();
							break;
						case ALIGNMENT_MIDDLE:
							double d = (edge.source.element.getLayoutSize().getW() / 2) +
								(edge.target.element.getLayoutSize().getW() / 2);
							horizontalShift =
								-xDiff - d;
							break;
						case ALIGNMENT_MAX:
							horizontalShift =
								-xDiff - edge.target.element.getLayoutSize().getW();
							break;
					}
				}


				targetPos = targetPos.plus(new Vector2D(horizontalShift, verticalShift));
				edge.target.element.setPosition(targetPos);
			}
		}


		List<Edge> edges = new ArrayList<>();

		List<Node> nodes = new ArrayList<>();


		public Size calculateSize()
		{
			Size size = new Size(0, 0);

			double minX = Double.POSITIVE_INFINITY;
			double maxX = Double.NEGATIVE_INFINITY;
			double minY = Double.POSITIVE_INFINITY;
			double maxY = Double.NEGATIVE_INFINITY;

			for (Node node : nodes)
			{
				minX = Math.min(node.element.getPosition().getX(), minX);
				maxX = Math.max(node.element.getPosition().getX() +
					node.element.getLayoutSize().getW(), maxX);
				minY = Math.min(node.element.getPosition().getY(), minY);
				maxY = Math.max(node.element.getPosition().getY() +
					node.element.getLayoutSize().getH(), maxY);
			}

			size.setW(maxX - minX);
			size.setH(maxY - minY);

			if (nodes.size() == 0)
			{
				size.setW(0);
				size.setH(0);
			}

			return size;
		}
	}


	private class Node
	{
		public Node(Element element)
		{
			this.element = element;
		}


		Element element;

		ArrayList<Edge> inEdges = new ArrayList<>();

		ArrayList<Edge> outEdges = new ArrayList<>();
	}


	private class Edge
	{
		Node source;

		Node target;

		double weight;

		LayoutConstant orientation;

		LayoutConstant alignment;
	}


	Element root;

	Element currentRoot = null;

	XMLData configurationData;

	Set<String> dataElements = new HashSet<>();

	Map<Element, Node> map = new HashMap<>();

	Map<String, List<XMLRelationTemplate>> relationMap = new HashMap<>();

	Map<String, Element> allElements = new HashMap<>();
}
