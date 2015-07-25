

package main;


import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

import main.deviceinput.Mouse;
import main.editor.ContentPaneObserver;
import main.editor.Editor;
import main.deviceinput.Keys;


public class Frame extends JFrame
{
	public Frame(String title, Dimension dimension)
	{
		super(title);
		this.dimension = dimension;
		this.setSize(this.dimension);
		this.contentPanel = new ContentPanel(dimension);

		this.setContentPane(this.contentPanel);

		this.mouse = new Mouse();
		this.keys = new Keys();

		this.contentPanel.addMouseListener(this.mouse);
		this.contentPanel.addMouseMotionListener(this.mouse);
		this.contentPanel.addKeyListener(this.keys);
		this.addKeyListener(this.keys);

		this.contentPanel.grabFocus();

		Timer timer = new Timer(1000 / this.FPS, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				long start = System.currentTimeMillis();
				repaint();

				long time = System.currentTimeMillis() - start;
			}
		});

		this.addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentResized(ComponentEvent e)
			{
				super.componentResized(e);
				contentPanel.getRoot().setPreferredSize(new Size(e.getComponent().getSize()));
			}
		});

		timer.setRepeats(true);
		timer.start();

		final Timer timer2 = new Timer(250, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (checkForMagic())
				{
					editorEnabled = true;
					Editor editor = new Editor();
						getContentPanel().removeMouseListener(mouse);
						getContentPanel().removeMouseMotionListener(mouse);
					editor.setObserver(new ContentPaneObserver(getContentPanel()));
				}
			}
		});

		timer2.setRepeats(true);
		timer2.start();
	}

	boolean editorEnabled = false;

	private boolean checkForMagic()
	{
		if (!editorEnabled && Keys.pressedKeys[KeyEvent.VK_CONTROL] && Keys.pressedKeys[KeyEvent.VK_F3])
		{
			return true;
		}

		return false;
	}


	public ContentPanel getContentPanel()
	{
		return this.contentPanel;
	}


	public void setContentPanel(ContentPanel contentPanel)
	{
		this.contentPanel = contentPanel;
	}


	public Dimension getDimension()
	{
		return this.dimension;
	}


	public void setDimension(Dimension dimension)
	{
		this.dimension = dimension;
	}

	public Mouse mouse;
	private Keys keys;
	private ContentPanel contentPanel;

	private Dimension dimension;
	private int FPS = 60;
}
