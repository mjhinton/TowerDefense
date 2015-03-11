package map;

/**
 * This class allows the creation of scenery objects for a map. It extends the
 * abstract MapCell class.
 * 
 * @author Michael Hinton
 */
public class SceneryCell extends MapCell {

	final public static char charID = '0';

	public SceneryCell() {

	}

	public String print() {
		return String.valueOf(charID);
	}
}
