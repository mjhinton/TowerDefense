package model;

public class Model {
		private Game game;
		private MapEditor mapeditor;
		
		public Model(){
			this.game = new Game();
			this.mapeditor = new MapEditor();
		}
		
		public Game getGame(){
			return game;
		}
		public MapEditor getEditor(){
			return mapeditor;
		}
}
