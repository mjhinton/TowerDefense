package tower;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import map.Map;
import model.Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import presentation.View;
import critter.Critter;
import critter.MonsterCritter;
import critter.NormalCritter;
import critter.SmartCritter;

public class BulletTest {
	private Map testMap = Map.getPackagedMap("15x15map");
	private Game testGame = new Game(testMap);
	private Bullet testB1, testB2, testB3;
	private NormalTower testNT1 = new NormalTower(new Point (2,2), testGame);
	private FreezingTower testFT1 = new FreezingTower(new Point (14,14), testGame);
	private MonsterTower testMT1 = new MonsterTower (new Point(0,2), testGame);
	private NormalCritter testNC1 = new NormalCritter(testGame);
	private MonsterCritter testMC1 = new MonsterCritter(testGame);
	private SmartCritter testSC1 = new SmartCritter(testGame);
	
	@Before
	public void setUp() throws Exception {
		testNC1.setDown();
	//Set B1 to the bullet that was just shot
		testNT1.shootBullet(testNC1);	
		testB1 = testNT1.testBullet;
	//Set B2  to the bullet that was just shot
		testFT1.shootBullet(testNC1);
		testB2 = testFT1.testBullet;
	//Set B3 to the bullet that was just shot
		testMT1.shootBullet(testNC1);
		testB3 = testMT1.testBullet;	
	}

	@After
	public void tearDown() throws Exception {
		testGame = null;
		testMap = null;
	}

	@Test
	public void testDetectCollision() {
	//At the current bullet position as it is just shot from the Tower, it should not be within collision distance of the critter.
		assertFalse(testB1.detectCollision(testNC1));
		
	//Set the bullet within the collision distance of the critter
		testB1.setBulletPosition(1.6, 0.5);
		assertTrue(testB1.detectCollision(testNC1));
		testB1.setBulletPosition(1.6,0.6);
		assertTrue(testB1.detectCollision(testNC1));
		testB1.setBulletPosition(1.4, 0.4);
		assertTrue(testB1.detectCollision(testNC1));
	}
	
	@Test
	public void testHit(){
	//With a normal tower or monster tower the critter should get hit by the tower's power
		double initHealthNC1 = testNC1.getHealth();
		double initSpeedNC1 = testNC1.getSpeed();
		
	//Critter's health should decrease by DAMAGE = 1. (normal tower)
		testB1.hit(testNC1);
		assertEquals("Normal Hit: Critter's health should have decreased by 1", initHealthNC1-1, testNC1.getHealth(), 0 );
		assertEquals("Normal Hit: Critter's speed should remain the same", initSpeedNC1, testNC1.getSpeed(), 0);
		
	//Critter's health should decrease by Damage = 1. (monster tower)
		testB3.hit(testNC1);
		assertEquals("Monster Hit: Critter's health should have decreased by 1 more", initHealthNC1-2, testNC1.getHealth(), 0);
		assertEquals("Monster Hit: Critter's speed should remain the same", initSpeedNC1, testNC1.getSpeed(), 0);
		
	//Critter's speed should be reduced, and health should remain the same
		testB2.hit(testNC1);
		assertEquals("Freezing Hit: Critter's health should remain the same as before", initHealthNC1-2, testNC1.getHealth(), 0);
		assertEquals("Freezing Hit: Critter should have been slowed down", initSpeedNC1*testFT1.specialmod, testNC1.getSpeed(), 0);
	
	}
	
	@Test
	public void testblast() throws InterruptedException{
		ArrayList<Critter> critters = new ArrayList<Critter>();
	
	//Add the test critters to the ArrayList and set them on path
		critters.add(testMC1);
		critters.add(testSC1);
		testMC1.setDown();
		testSC1.setDown();
		
	//Move smart critter far away.
		for (int i = 0; i<150; i++)
			testSC1.updatePosition();
		
		double initHealthMC1 = testMC1.getHealth();
		double initHealthSC1 = testSC1.getHealth();

	//Normal Tower has a blast radius of zero; no critter should be hit.
		testB1.setBulletPosition(1.5, 0.4);
		testB1.blast(critters);
		assertEquals("Normal Blast: blast radius 0, so should not change", initHealthMC1, testMC1.getHealth(), 0 );
		assertEquals("Normal Blast: blast radius 0, should not change critter health", initHealthSC1, testSC1.getHealth(),0);
		
	//Monster Tower has a blast radius of one; critters in range should be hit.
		testB3.setBulletPosition(1.5, 0.4);
		testB3.blast(critters);
		assertEquals("Monster Blast: blast radius is 1, critter health should decrease by damage", initHealthMC1-1, testMC1.getHealth(), 0);
		assertEquals("Monster Blast: blast radius is 1, but critter is out of range, no change", initHealthSC1, testSC1.getHealth(), 0);
	
	}
	
	@Test
	public void testUpdateBullet() throws InterruptedException {
		
		testGame.generateWave();
		double initHealthNC1 = testNC1.getHealth();

	//Test case 1, there is a collision, and the blast radius is 0, so hit, and remove the bullet.
		testB1.setBulletPosition(1.7, 0.8);
		testB1.updateBullet();
		System.out.println(testNC1.getCurrPathCoord());
		System.out.println(testB1.getX()+ "," + testB1.getY());
		assertEquals("Normal Bullet: should hit critter and deal damage", initHealthNC1-1, testNC1.getHealth(), 0 );
	
	//Test case 2, there is a collision, and the blast radius is greater than 0, so blast critters, remove the bullet.
	//Test case 3, if bullet is at the edge of the screen, remove bullet.
		
		
	}



}
