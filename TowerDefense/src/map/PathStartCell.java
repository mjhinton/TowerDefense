package map;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class allows the creation of start path cell objects for a map. It
 * extends the PathCell class.
 * 
 * @author Michael Hinton
 */
public class PathStartCell extends PathCell {

	final public static char CHAR_ID = 'S';

	Image image;

	public PathStartCell() {

	}

	public String print() {
		return String.valueOf(CHAR_ID);
	}

	public Image getImage() {
		ImageIcon i = new ImageIcon("lib/images/map/testPath.png");
		image = i.getImage();
		return image;
	}
}
