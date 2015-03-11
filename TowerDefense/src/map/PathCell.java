package map;

/**
 * This class allows the creation of path cell objects for a map. It extends the
 * abstract MapCell class.
 * 
 * @author Michael Hinton
 */
public class PathCell extends MapCell {

	final public static char charID = '+';

	public PathCell() {

	}

	public String print() {
		return String.valueOf(charID);
	}
}
