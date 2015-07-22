
package main;


import java.awt.Graphics2D;


/**
 * Interface to implement for objects that will be later drawn in
 */
public interface Drawable
{
	/**
	 * Implementation of this method takes care of drawing any Object implementing
	 * this interface.
	 *
	 * @param g Graphics2D object to draw stuff into.
	 */
	void draw(Graphics2D g);
}
