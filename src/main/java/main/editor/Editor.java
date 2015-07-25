
package main.editor;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;
import main.ContentPanel;
import main.Frame;
import main.elements.Element;
import main.elements.TextElement;
import main.layout.relations.ChainRelation;
import main.layout.Layout;
import main.layout.LayoutConstant;


public class Editor
{
	public Editor()
	{
		this.dimension = new Dimension(500, 800);
		this.frame = new Frame("Editor", this.dimension);
		this.frame.setUndecorated(true);
		this.contentPanel = this.frame.getContentPanel();

		this.frame.setContentPanel(this.contentPanel);
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.frame.setBounds(600, 0, 500, 800);

		this.frame.setVisible(true);

		this.contentPanel.addMouseListener(new EditorMouseAdapter(this.contentPanel, this));

		this.generateComponents();

		this.myLayout = new Layout(this.contentPanel.getRoot(), null);

		this.myLayout.run();

		this.frame.addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentResized(ComponentEvent e)
			{
				super.componentResized(e);
				myLayout.run();
			}
		});

		Timer timer = new Timer(500, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				myLayout.run();
			}
		});

		timer.setRepeats(true);
		timer.start();
	}


	private void generateComponents()
	{
		Element root = this.contentPanel.getRoot();
		this.contentPanel.getRoot().addRelation(
			new ChainRelation(
				root,
				LayoutConstant.ORIENTATION_VERTICAL,
				LayoutConstant.ALIGNMENT_MIN, 5));

		hitTestHoverName = new TextElement();
		hitTestHoverName.setId("hitTestElementName");
		this.contentPanel.addElement(hitTestHoverName);

		this.selectedElementName = new TextElement();
		this.selectedElementName.setId("SelectedElementName");
		this.contentPanel.addElement(selectedElementName);

		this.relationArea = new RelationArea(this);
		this.contentPanel.addElement(this.relationArea);

		this.generateRelationEditingArea();
	}


	private void generateRelationEditingArea()
	{
		this.relationEditing = new RelationEditing(null, this.contentPanel, this);
		this.contentPanel.addElement(this.relationEditing);
	}


	public void setObserver(ContentPaneObserver observer)
	{
		this.observer = observer;
		observer.root.addMouseMotionListener(observer);
		observer.root.addMouseListener(observer);
		this.observer.setEditor(this);
		this.relationEditing.setForeignRoot(observer.root.getRoot());
		this.contentPanel.addElement(new SaveConfigurationField(this.contentPanel, observer.root.getRoot()));
	}

	ContentPanel contentPanel;
	Frame frame;

	ContentPaneObserver observer;
	Dimension dimension = new Dimension(300, 500);
	Layout myLayout;

	protected TextElement hitTestHoverName;
	protected TextElement selectedElementName;
	protected Element selectedElement;
	protected RelationArea relationArea;
	protected RelationEditing relationEditing;
}
