package map;

/**
 * This class allows the creation of start path cell objects for a map. It
 * extends the PathCell class.
 * 
 * @author Michael Hinton
 */
public class PathStartCell extends PathCell {

	final public static char charID = 'S';

	public PathStartCell() {

	}

	public String print() {
		return String.valueOf(charID);
	}
}
