package critter;

import static org.junit.Assert.*;

import java.awt.Point;

import map.Map;
import model.Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.ReadWriteTxtFile;

public class CritterTest {
	private String[] testArrayMap = ReadWriteTxtFile
			.readTxtFileAsStringArray("lib/maps/15x15map.txt");
	private Map testMap = new Map("testMap", 15, testArrayMap);
	private Game testGame = new Game(testMap);
	private NormalCritter testCritter = new NormalCritter(testGame);
	private SmartCritter testCritter2 = new SmartCritter(testGame);
	private Wave testWave;
	
	@Before
	public void setUp() throws Exception {
		//initiate the path on the map before every test
		testMap.initPath();
		//instantiate a new wave and add the test critters to the bank
		testWave = new Wave(1, testGame);

		//set the critter down on the path before every test.
		try {
			testCritter.setDown();
			testCritter2.setDown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		testMap = null;
		testGame = null;
		testCritter = null;
		testArrayMap = null;
	}

	@Test
	public void testGetsHit() {
		int initialCoins = testGame.getCoins();
		//need to test whether the health lowers
		testCritter.getsHit(1);
		assertEquals("Critter health must become lower", 4, testCritter.getHealth(), 0 );
		assertTrue(testCritter.onPath());
		//need to test whether on path becomes false when it dies, if it is removed from the critterBank, 
		//and whether the health is set to 0 when damage is higher than critter health
		
		//TODO:The below doesn't pass right now
		
		testCritter2.getsHit(5);
		assertEquals("Critter health must become zero", 0, testCritter.getHealth(), 0);
		assertFalse(testCritter.getWhetherOnPath());
		//need to test whether the game changes coins. 
		assertEquals("PlayerCoins must increase by reward", initialCoins+5, testGame.getCoins(), 0);
		
	}

	@Test
	public void testIncreaseDifficulty() {
		testCritter.increaseDifficulty(2);
		//health must increase by multiplier
		assertEquals("health must increase by multiplier", 10, testCritter.getHealth(), 0 );
		//reward must increase by multiplier
		assertEquals("reward must increase by multiplier", 10, testCritter.getWorth(), 0 );
	}

	@Test
	public void testSetDown() {

		Point testCurrPathCoord = testCritter.getCurrPathCoord();
		double testX = testCurrPathCoord.getX();
		double testY = testCurrPathCoord.getY();
		//doubles x and y must  be the 0th path coordinate's x and y.
		assertEquals("X coord must be the same", 1, testX, 0);
		assertEquals("Y coord must be the same", 0, testY, 0);
		//onPath must return true
		assertTrue(testCritter.onPath());
		
	}

	@Test
	public void testUpdatePosition() {
		// test case one, the critter position is updated from the start of the path
		double inityPos = testCritter.y; 
		double initxPos = testCritter.x;
		testCritter.updatePosition();
		assertEquals("Critter must move in y direction by the amount of its speed", inityPos+ testCritter.getSpeed(), testCritter.y, 0);
		assertEquals("Critter must not move in the x direction because dx=0", initxPos, testCritter.x, 0);
		
		// test case two, critter has reached the end of the path, and needs to get off map exit. 
		int testPathLength = testGame.getBoard().getPath().length();
		testCritter.setCurrPathIndex(testPathLength-1);
		testCritter.updatePosition();
		assertTrue(testGame.getBoard().getMap().getWhetherGetOffMapExitWasCalled());
		
	}

}

