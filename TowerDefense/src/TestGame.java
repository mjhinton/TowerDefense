


import common.ReadWriteTxtFile;
import critter.Critter;
import map.Map;
import model.Game;
import presentation.View;



public class TestGame {

	public static void main(String[] args) {

		View app=new View();
		app.switchPanel("PanelGame");
		
		Game testGame;
		
		String[] testArrayMap = ReadWriteTxtFile
				.readTxtFileAsStringArray("lib/testMaps/15x15map.txt");
		Map testMap = new Map("testMap", 15, testArrayMap);

		app.getController().startGame(testMap);
		testGame=app.getController().getGame();
		
		System.out.println(testGame.getBoard().getMap().getPath().print());
		System.out.println(Critter.STANDARD_SPEED);

		
		try {
			testGame.generateWave();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
