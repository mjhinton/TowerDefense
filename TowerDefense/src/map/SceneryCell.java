package map;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class allows the creation of scenery objects for a map. It extends the
 * abstract MapCell class.
 * 
 * @author Michael Hinton
 */
public class SceneryCell extends MapCell {

	final public static char CHAR_ID = '0';
	
	Image image;

	public SceneryCell() {

	}

	public String print() {
		return String.valueOf(CHAR_ID);
	}
	
	public Image getImage (){
		ImageIcon i= new ImageIcon("lib/images/map/grass_scenery_border.png");
		image=i.getImage();
		return image;
	}
}
