//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.sampleApplication;

import com.github.enifs.visualframework.elements.Hexagon;
import com.github.enifs.visualframework.HexGridManager;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Created by Didzis on 18.07.2015..
 */
public class HexagonSampleKeyAdapter extends KeyAdapter
{
	public HexagonSampleKeyAdapter(HexagonSample sample, HexGridManager manager)
	{
		this.sample = sample;
		this.manager = manager;
		this.selectedHexagon = manager.getOrigin();
		this.select(this.selectedHexagon);
	}

	private void select(Hexagon hexagon)
	{
		if (this.selectedHexagon != null)
		{
			this.selectedHexagon.setBorderColor(Color.BLACK);
		}

		this.selectedHexagon = hexagon;
		this.selectedHexagon.setBorderColor(Color.RED);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		super.keyPressed(e);

		switch (e.getKeyCode())
		{
			case KeyEvent.VK_A:
				this.move(3);
				break;
			case KeyEvent.VK_W:
				this.move(4);
				break;
			case KeyEvent.VK_E:
				this.move(5);
				break;
			case KeyEvent.VK_D:
				this.move(0);
				break;
			case KeyEvent.VK_X:
				this.move(1);
				break;
			case KeyEvent.VK_Z:
				this.move(2);
				break;
			case KeyEvent.VK_S:
				this.selectedHexagon.setBackgroundColor(sample.gerRandomColor());
				break;
		}
	}

	private void move(int direction)
	{
		Hexagon nextHexagon = manager.addNeighbour(this.selectedHexagon, direction);
		manager.activate(nextHexagon);
		this.select(nextHexagon);
		nextHexagon.setBackgroundColor(sample.gerRandomColor());
	}

	HexagonSample sample;
	HexGridManager manager;
	Hexagon selectedHexagon;
}
