package map;

/**
 * This class allows the creation of end path cell objects for a map. It extends
 * the PathCell class.
 * 
 * @author Michael Hinton
 */
public class PathEndCell extends PathCell {

	final public static char charID = 'E';

	public PathEndCell() {

	}

	public String print() {
		return String.valueOf(charID);
	}
}
