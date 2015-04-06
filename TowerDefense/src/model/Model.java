package model;

import map.Map;

public class Model {
		private Game game;
		private MapEditor mapeditor;
		
		public Model(){
			this.game = new Game(new Map());
			this.mapeditor = new MapEditor(new Map());
		}
		
		public void setMap(String name, int x, int y){
			Map map = new Map(name, x, y);
			this.game = new Game(map);
			this.mapeditor = new MapEditor(map);
		}
		
		public Game getGame(){
			return game;
		}
		public MapEditor getEditor(){
			return mapeditor;
		}
}
