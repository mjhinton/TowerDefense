
import java.awt.Point;

import common.ReadWriteTxtFile;
import map.Map;
import model.Game;
import presentation.View;
import tower.FreezingTower;


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

		//testGame.addTower(new FreezingTower(new Point (4,4),testGame));
		//testGame.addTower(new FreezingTower(new Point (14,14),testGame));

		
		
		try {
			testGame.generateWave();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
