package map;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class allows the creation of end path cell objects for a map. It extends
 * the PathCell class.
 * 
 * @author Michael Hinton
 */
public class PathEndCell extends PathCell {

	final public static char CHAR_ID = 'E';

	Image image;

	public PathEndCell() {

	}

	public String print() {
		return String.valueOf(CHAR_ID);
	}

	public Image getImage() {
		ImageIcon i = new ImageIcon("lib/images/map/grass_scenery.png");
		image = i.getImage();
		return image;
	}
}
