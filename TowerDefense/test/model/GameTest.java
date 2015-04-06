package model;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import map.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tower.Bullet;
import tower.NormalTower;
import tower.Tower;
import critter.Wave;

public class GameTest {
	
	private Map testMap = Map.getPackagedMap("15x15map");
	private Game testGame = new Game (testMap);
	private Board testBoard;
	private Wave testWave;
	private int testCurPlayerCoins, testCurPlayerHealth;
	private NormalTower testTower1, testTower2, testTower3;
	private LinkedList<Tower> testTowers;
	private ArrayList<Bullet> testBullets;
	
	
	@Before
	public void setUp() throws Exception {
		testBoard = testGame.getBoard();
		testWave = testGame.getWave();
		testTowers = testGame.getTowers();
		testBullets = testGame.getBullets();
		testCurPlayerCoins = testGame.getCoins();
		testCurPlayerHealth = testGame.getHealth();
		
	}

	@After
	public void tearDown() throws Exception {
		testGame = null;
		testWave = null;
		testBoard = null;
		testTowers = null;
		testBullets = null;
	
	}


	@Test
	public void testChangeCoins() {
	//Test valid case where player gains coins
		testGame.changeCoins(5);
		assertEquals("Add coins", 5+testCurPlayerCoins, testGame.getCoins(), 0);
	//Test invalid case where player does not have enough coins to purchase.
		testCurPlayerCoins = testGame.getCoins();
		testGame.changeCoins(-testCurPlayerCoins-5);
		assertEquals("Player doesn't have enough funds, coins don't change", testCurPlayerCoins, testGame.getCoins(), 0 );
	//Test valid case where player has enough funds to purchase
		testCurPlayerCoins = testGame.getCoins();
		testGame.changeCoins(-150);
		assertEquals("Player buys tower or upgrade", testCurPlayerCoins-150, testGame.getCoins(), 0 );
	
	}

	@Test
	public void testChangeHealth() {
	//Test case where player health is increased
		testCurPlayerHealth = testGame.getHealth();
		testGame.changeHealth(5);
		assertEquals("Add health", 5+testCurPlayerHealth, testGame.getHealth(), 0);
	//Test case where playerHealth is decreased
		testCurPlayerHealth = testGame.getHealth();
		testGame.changeHealth(-testCurPlayerHealth + 5);
		assertEquals("Critter reaches end of path, deals damage", 5, testGame.getHealth(), 0 );
	//Test case where player loses all health and game ends
		testCurPlayerHealth = testGame.getHealth();
		testGame.changeHealth(-testCurPlayerHealth-5);
		assertEquals("Player dies and game ends", 0, testGame.getHealth(), 0 );
	
	}

	@Test
	public void testAddTower() {
	//Test valid case where the tower is set on a scenery cell and the player has enough coins	
		testTower1 = new NormalTower(new Point (0,0), testGame);
		assertTrue(testGame.addTower(testTower1));
	//Test invalid case where player tries to set the tower on an invalid location 
		testTower2 = new NormalTower(new Point(1,0), testGame);
		assertFalse(testGame.addTower(testTower2));
	//Test case where player tries buy a tower they do not have the funds for (set player coins to 0)
		testGame.changeCoins(-testGame.getCoins());
		testTower3 = new NormalTower (new Point (14,14), testGame);
		assertFalse(testGame.addTower(testTower3));
	}

	@Test
	public void testRemoveTower() {
	//Remove tower that was added
		testTower1 = new NormalTower (new Point (12,12), testGame);
		testGame.addTower(testTower1);
		assertTrue(testGame.removeTower(testTower1));
	//Remove another tower that was added
		testTower2 = new NormalTower(new Point(0,0), testGame);
		testGame.addTower(testTower2);
		assertTrue(testGame.removeTower(testTower2));
	//Try to remove a tower that was already removed
		assertFalse(testGame.removeTower(testTower2));
		
	}

	@Test
	public void testUpgradeTower() {
		testTower1 = new NormalTower( new Point(12,12), testGame);
		testGame.addTower(testTower1);
	//Upgrade a tower once
		assertTrue(testGame.upgradeTower(testTower1));
		assertTrue(testGame.upgradeTower(testTower1));
		assertTrue(testGame.upgradeTower(testTower1));
	//Player doesn't have enough funds
		testGame.setPlayerCoins(0);
		assertFalse(testGame.upgradeTower(testTower1));
	//Player has enough funds, but tower has been fully upgraded
		testGame.setPlayerCoins(5000);
		testGame.upgradeTower(testTower1);
		assertFalse(testGame.upgradeTower(testTower1));
	}

	@Test
	public void testUpdateGame() {
		
		fail("Not yet implemented");
	
	}

	@Test
	public void testGenerateWave() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testSaveGame(){
		fail("Not yet implemented");
	}
}
