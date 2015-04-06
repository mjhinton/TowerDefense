package model;

import static org.junit.Assert.*;

import java.awt.Point;

import map.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tower.FreezingTower;
import tower.MonsterTower;
import tower.NormalTower;
import tower.Tower;

public class BoardTest {
	private Map testMap = Map.getPackagedMap("15x15map");
	private Game testGame = new Game (testMap);
	private Board testBoard;
	private Point testPoint1, testPoint2, testPoint3, testPoint4;
	private NormalTower testTower1;
	private FreezingTower testTower2, testTower4;
	private MonsterTower testTower3, testTower5;
	
	@Before
	public void setUp() throws Exception {
		testPoint1 = new Point(6,6);
		testPoint2 = new Point(1,1);
		testPoint3 = new Point(5,5);
		testPoint4 = new Point(14,14);
		testTower1 = new NormalTower(testPoint1, testGame);
		testTower2 = new FreezingTower(testPoint2, testGame);
		testTower3 = new MonsterTower(testPoint1, testGame);
		testBoard = testGame.getBoard();
	}

	@After
	public void tearDown() throws Exception {
		testPoint1 = null;
		testPoint2 = null;
		testTower1 = null;
		testTower2 = null;
		testBoard = null;
	}

	@Test
	public void testAddTower() {
	//Test adding tower on a scenery cell
		assertTrue(testBoard.addTower(testTower1));
	//Test trying to add tower on a path cell
		assertFalse(testBoard.addTower(testTower2));
	//Test trying to add tower on another tower
		assertFalse(testBoard.addTower(testTower3));
	}

	@Test
	public void testRemoveTower() {
		testBoard.addTower(testTower1);
		testTower4 = new FreezingTower(testPoint3, testGame);
		testBoard.addTower(testTower4);
		testTower5 = new MonsterTower(testPoint4, testGame);
		testBoard.addTower(testTower5);
	
	//Test removing each tower
		assertTrue(testBoard.removeTower(testTower1));
		assertTrue(testBoard.removeTower(testTower4));
		assertTrue(testBoard.removeTower(testTower5));
	
	//Test removing already removed tower.
		assertFalse(testBoard.removeTower(testTower5));
	}

	@Test
	public void testUpgradeTower() {
		testBoard.addTower(testTower1);
		testTower4 = new FreezingTower(testPoint3, testGame);
		testBoard.addTower(testTower4);
		testTower5 = new MonsterTower(testPoint4, testGame);
		testBoard.addTower(testTower5);
		
	//test upgrading each tower
		assertTrue(testBoard.upgradeTower(testTower1));
		assertTrue(testBoard.upgradeTower(testTower4));
		assertTrue(testBoard.upgradeTower(testTower5));
	//test upgrading tower that has been removed
		testBoard.removeTower(testTower5);
		assertFalse(testBoard.upgradeTower(testTower5));
	
	}

}
