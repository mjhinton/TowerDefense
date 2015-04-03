package model;

import map.Map;

public class Model {
		private Game game;
		private MapEditor mapeditor;
		
		public Model(){
			this.game = new Game(new Map());
			this.mapeditor = new MapEditor(new Map());
		}
		
		public Game getGame(){
			return game;
		}
		public MapEditor getEditor(){
			return mapeditor;
		}
}
