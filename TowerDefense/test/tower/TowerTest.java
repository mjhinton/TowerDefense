package tower;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import map.Map;
import model.Board;
import model.Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.Controller;
import presentation.View;
import critter.Critter;
import critter.Wave;

public class TowerTest {
	private Map testMap = Map.getPackagedMap("15x15map");
	private View testView = new View();
	private Controller testController;
	private Game testGame;
	private Board testBoard;
	private Wave testWave;
	private NormalTower testTower1;
	private FreezingTower testTower2;
	private MonsterTower testTower3;
	private ArrayList<Critter> testCritters;
	
	
	@Before
	public void setUp() throws Exception {
		testController = testView.getController();

		testController.startGame(testMap);
		testGame = testView.getModel().getGame();
		testBoard = testGame.getBoard();
		testWave = testGame.getWave();
		
		testTower1 = new NormalTower(new Point(0,0), testGame);
		testTower2 = new FreezingTower(new Point(1,4), testGame);
		testTower3 = new MonsterTower(new Point(12,12), testGame);
	}

	@After
	public void tearDown() throws Exception {
		testGame = null;
		testBoard = null;
		testWave = null;
		testTower1 = null;
		testTower2 = null;
		testTower3 = null;
	}

	@Test
	public void testIncreaseLevel() {
	//Test that for a normal tower the cost for the next level increases by 120, value increases, fireRate increases, and power increases, and level ups.
		int prevCost = testTower1.getCost();
		int prevLevel = testTower1.getLevel();
		double prevFireRate = testTower1.getFireRate();
		double prevPower = testTower1.getPower();
		double prevRange = testTower1.getRange();
		double prevSpecialMod = testTower1.getSpecialmod();

		testTower1.increaseLevel();
		
		assertEquals("Cost increases", prevCost+120, testTower1.getCost(), 0);
		assertEquals("Value increases", testTower1.getCost()*prevLevel*0.6, testTower1.getValue(), 0 );
		assertEquals("Firerate increases", prevFireRate*1.1, testTower1.getFireRate(), 0);
		assertEquals("Level ups", prevLevel+1, testTower1. getLevel(), 0);
		assertEquals("Power ups", prevPower*1.5, testTower1. getPower(), 0);
		assertNotEquals("Range should not increase", prevRange+1, testTower1.getRange(), 0);
		assertNotEquals("Specialmod should not decrease", prevSpecialMod -0.05, testTower1.getSpecialmod(), 0);
	
	//Test that the special tower also has its range increased and specialmod decreased
		double prevRange2 = testTower2.getRange();
		double prevSpecialMod2 = testTower2.getSpecialmod();
		testTower2.increaseLevel();
		assertEquals("Range increases", prevRange2+1, testTower2.getRange(), 0);
		assertEquals("Specialmod decreases", prevSpecialMod2-0.05, testTower2.getSpecialmod(), 0);
	
	//Test that it only increases the level when the level is less than five
		testTower2.increaseLevel();
		testTower2.increaseLevel();
		testTower2.increaseLevel();
		
		int prevLevel2 = testTower2.getLevel();
		testTower2.increaseLevel();
		assertEquals("level should remain the same", prevLevel2, testTower2.getLevel(), 0);
	}
	
	//Also tests targetsInRange(ArrayList<Critter>), getTarget(ArrayList<Critter>), and ShootBullet(Critter)
	@Test
	public void testFire() throws InterruptedException {
		testController.playGame(testMap);
		fail("Not yet implemented");
		
	}

	


}
