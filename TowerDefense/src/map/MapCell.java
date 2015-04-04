package map;

import java.awt.Image;

/**
 * This is an abstract class that dictates the properties of a cell object on
 * the map.
 * 
 * @author Michael Hinton
 */
public abstract class MapCell {
	// TODO: determine how to format an abstract class

	abstract public String print();
	
	abstract public Image getImage();

}
