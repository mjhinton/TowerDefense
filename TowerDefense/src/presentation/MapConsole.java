package presentation;

import common.IObserver;

import map.*;

/**
 * This class is an example observer of the map class.
 * 
 * @author Michael Hinton
 */

public class MapConsole implements IObserver {

	private Map map;

	public MapConsole(Map map) {
		this.map = map;
	}

	/**
	 * The specific update method for this example map observer. Prints the map
	 * as a string every time the map is changed.
	 */
	public void update() {
		String mapString = map.print();
		System.out.println(mapString);
		System.out.println();
	}
}
