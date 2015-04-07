package model;

import map.Map;

/**
 * this class holds the currently running game as well as the mapeditor. this is
 * stored in view and allows a central location for their panels to access them
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */

public class Model {
	private Game game;
	private MapEditor mapeditor;

	public Model() {
		this.game = new Game(new Map());
		this.mapeditor = new MapEditor(new Map());
	}

	public void setMap(String name, int x, int y) {
		Map map = new Map(name, x, y);
		this.game = new Game(map);
		this.mapeditor = new MapEditor(map);
	}

	public Game getGame() {
		return game;
	}

	public MapEditor getEditor() {
		return mapeditor;
	}
}
