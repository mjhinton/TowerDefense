


import map.Map;
import presentation.View;



public class TestGame {

	public static void main(String[] args) {


		View app=new View();
		app.switchPanel("PanelGame");

		
		Map testMap=Map.getPackagedMap("medium");
		
		app.getController().startGame(testMap);
		//app.getController().playGame(testMap);

				
	}
}
