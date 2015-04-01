package model;

import map.Map;

public class MapEditor {

	private Map map;
	/*somewhere in controller, when the player asks to start game, ask whether they want 
	 *  1. a randomly generated map
	 *  2. to create their own map. 
	 *  
	 */
	private int width, height;
	
	//right now, this constructor just generates random map. 
	public MapEditor(){
		this.map = new Map();
	}
	
}
