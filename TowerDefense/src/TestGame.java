


import map.Map;
import model.Game;
import presentation.View;



public class TestGame {

	public static void main(String[] args) {

		View app=new View();
		app.switchPanel("PanelGame");
		
		//Game testGame;
		
		Map testMap=Map.getPackagedMap("15x15map");

		
		
		
		app.getController().startGame(testMap);
		//testGame=app.getController().getGame();
		
		
		try {
			app.getController().getGame().generateWave();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
