


import map.Map;
import model.Game;
import presentation.View;



public class TestGame {

	public static void main(String[] args) {

		System.out.println("print");
		System.out.println("print");

		View app=new View();
		app.switchPanel("PanelGame");
		
		Game testGame;
		
		Map testMap=Map.getPackagedMap("15x15map");
		
		app.getController().startGame(testMap);
		testGame=app.getController().getGame();
		
		

		
	}
}
