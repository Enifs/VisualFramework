

package main.sampleApplication;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

import main.Size;
import main.elements.RectangularElement;
import main.layout.relations.SimpleRelation;
import main.Frame;
import main.elements.jComponentWrappers.TextInputLine;
import main.layout.Layout;
import main.layout.LayoutConstant;
import main.xml.reader.XMLFileReader;


public class Sample
{
	public static void main(String[] args)
	{
		Sample sample = new Sample();
		sample.run();
	}


	private void run()
	{
		Frame frame = new Frame("Sample", new Dimension(300, 300));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);

		Layout layout = new Layout(frame.getContentPanel().getRoot(), "rootConfiguration");

		PositionLabel label = new PositionLabel("Default");


		RectangularElement rect1 = new RectangularElement();
		rect1.setMinSize(new Size(60, 60));
		rect1.setPreferredSize(new Size(100, 200));
		rect1.setColor(Color.WHITE);
		rect1.setId("Rectangle - WHITE");

		RectangularElement rect2 = new RectangularElement();
		rect2.setPreferredSize(new Size(95, 50));
		rect2.setColor(Color.YELLOW);
//		rect2.setId("Rectangle - YELLOW");

		RectangularElement rect3 = new RectangularElement();
		rect3.setPreferredSize(new Size(20, 20));
		rect3.setColor(Color.BLUE);
		rect3.setId("Rectangle - BLUE");

//		label.addChild(rect3);
		label.root = frame.getContentPanel().getRoot();

		frame.getContentPanel().addElement(rect1);
		frame.getContentPanel().addElement(rect2);
		frame.getContentPanel().addElement(rect3);
//		frame.getContentPanel().addElement(label);

		TextInputLine line = new TextInputLine(frame.getContentPanel());

		rect2.addChild(line);
//		frame.getContentPane().add(line.textField);
//		rect1.addChild(rect3);

		for (int i = 0; i < 5; i++)
		{
			RectangularElement greenRect = new RectangularElement();
			greenRect.setColor(Color.GREEN);
			greenRect.setPreferredSize(new Size(20, 20));
			greenRect.setId("Green Rectangle " + i);

			rect1.addChild(greenRect);
		}

		SimpleRelation r1 = new SimpleRelation(
			frame.getContentPanel().getRoot(),
			rect1,
			LayoutConstant.ORIENTATION_HORIZONTAL,
			10, LayoutConstant.ALIGNMENT_MIN);

		SimpleRelation r2 = new SimpleRelation(
			rect1,
			rect2,
			LayoutConstant.ORIENTATION_HORIZONTAL,
			10, LayoutConstant.ALIGNMENT_MIN);

		SimpleRelation r3 = new SimpleRelation(
			rect2,
			rect3,
			LayoutConstant.ORIENTATION_VERTICAL,
			50, LayoutConstant.ALIGNMENT_MIN);

		SimpleRelation r4 = new SimpleRelation(
				rect1,
				rect3,
				LayoutConstant.ORIENTATION_HORIZONTAL,
				30, LayoutConstant.ALIGNMENT_MIN);

		SimpleRelation r5 = new SimpleRelation(
				rect1,
				rect2,
				LayoutConstant.ORIENTATION_VERTICAL,
				5, LayoutConstant.ALIGNMENT_MIN);


//		frame.getContentPanel().getRoot().addRelation(r1);
//		frame.getContentPanel().getRoot().addRelation(r2);
//		frame.getContentPanel().getRoot().addRelation(r3);
//		frame.getContentPanel().getRoot().addRelation(r4);
//		frame.getContentPanel().getRoot().addRelation(r5);

		XMLFileReader reader = null;


//		try
//		{
//			XMLWriter.writeXMLFile("rootConfiguration", frame.getContentPanel().getRoot());
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}

//		try
//		{
//			reader =  new XMLFileReader("rootConfiguration");
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//
//		if (reader != null)
//		{
//			XMLData dataSaver = reader.getDataSaver();
//		}

		layout.run();


//		Editor editor = new Editor();
//		frame.getContentPanel().removeMouseListener(frame.mouse);
//		frame.getContentPanel().removeMouseMotionListener(frame.mouse);
//		editor.setObserver(new ContentPaneObserver(frame.getContentPanel()));
	}
}
