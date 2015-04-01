package map;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class allows the creation of path cell objects for a map. It extends the
 * abstract MapCell class.
 * 
 * @author Michael Hinton
 */
public class PathCell extends MapCell {

	final public static char CHAR_ID = '+';

	Image image;

	public PathCell() {

	}

	public String print() {
		return String.valueOf(CHAR_ID);
	}

	public Image getImage() {
		ImageIcon i = new ImageIcon("lib/images/map/test_path.png");
		image = i.getImage();
		return image;
	}
}
