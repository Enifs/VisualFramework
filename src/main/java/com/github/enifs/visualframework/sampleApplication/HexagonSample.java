//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.sampleApplication;

import com.github.enifs.visualframework.elements.Element;
import com.github.enifs.visualframework.ContentPanel;
import com.github.enifs.visualframework.Frame;
import com.github.enifs.visualframework.HexGridManager;

import math.geom2d.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


/**
 * Created by Didzis on 18.07.2015..
 */
public class HexagonSample
{
	public static void main(String[] args)
	{
		new HexagonSample().run();
	}


	private void run()
	{
		frame = new Frame("HexagonSample", new Dimension(800, 600));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);

		ContentPanel contentPanel = frame.getContentPanel();
		Element root = contentPanel.getRoot();

		HexGridManager hexGridManager = new HexGridManager(root);

		frame.addKeyListener(new HexagonSampleKeyAdapter(this, hexGridManager));

		hexGridManager.activate(hexGridManager.getOrigin());
		hexGridManager.shiftOrigin(new Vector2D(400, 300));

	}

	public Color gerRandomColor()
	{
		Random random = new Random(System.currentTimeMillis());
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);

		return new Color(r, g, b);
	}

	Frame frame;
}
