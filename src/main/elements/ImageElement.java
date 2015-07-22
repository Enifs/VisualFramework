//
// Armands  09.07.2015.
// HanabiSim
//


package main.elements;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Size;


/**
 * This class allow to create image element.
 */
public class ImageElement extends RectangularElement
{
	/**
	 * This constructor create new Image Element from string location.
	 * @param location
	 */
	public ImageElement(String location)
	{
		try
		{
			this.image = ImageIO.read(new File(location));

			Size imageSize = new Size(((BufferedImage) this.image).getWidth(),
				((BufferedImage) this.image).getHeight());

			this.setPreferredSize(imageSize);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * This constructor create new Image Element from File object that contains image.
	 * @param image
	 */
	public ImageElement(File image)
	{
		try
		{
			this.image = ImageIO.read(image);

			Size imageSize = new Size(((BufferedImage) this.image).getWidth(),
				((BufferedImage) this.image).getHeight());

			this.setPreferredSize(imageSize);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	@Override
	public void draw(Graphics2D g)
	{
		g.drawImage(
			this.image,
			(int) this.getPosition().getX(),
			(int) this.getPosition().getY(),
			(int) this.getScreenSize().getW(),
			(int) this.getScreenSize().getH(),
			null);
	}


	// ---------------------------------------------------------------------
	// Section: Variables
	// ---------------------------------------------------------------------


	private Image image;
}
