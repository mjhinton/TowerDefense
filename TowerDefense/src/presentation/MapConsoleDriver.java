package presentation;

import map.Map;

/**
 * This driver class tests the observer design pattern implemented for the Map
 * class.
 * 
 * @author Michael Hinton
 */
public class MapConsoleDriver {

	public static void main(String[] args) {
		Map m1 = new Map("Map1",20,10);
		MapConsole mc1 = new MapConsole(m1);
		m1.addObserver(mc1);

		// Make multiple changes to the map to test that the observer notices
		// and implements its update
		m1.makePathStartCell(0, 0);
		for (int i = 1; i < 19; i++) {
			m1.makePathCell(0, i);
		}
		m1.makePathEndCell(0, 19);

	}
}
