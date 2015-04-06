package critter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import map.Map;
import model.Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import presentation.View;
import common.ReadWriteTxtFile;

public class WaveTest {
	
	private String[] testArrayMap = ReadWriteTxtFile
			.readTxtFileAsStringArray("lib/maps/15x15map.txt");
	private Map testMap = new Map("testMap", 15, testArrayMap);
	private Game testGame = new Game(testMap);
	private ArrayList<Critter> testCritterBank, testReleaseBank;
	private Wave testWave;
	int amtNormCrit, amtSmartCrit, amtShieldedCrit, amtHeavyCrit, amtMonsterCrit, amtGhostCrit;
	
	
	@Before
	public void setUp() throws Exception {
	testGame.generateWave();
	testWave = testGame.getWave();
	testCritterBank = testWave.getCritterBank();
	testReleaseBank = testWave.getReleaseBank();
	amtNormCrit = 0;
	amtSmartCrit = 0;
	amtShieldedCrit = 0;
	amtMonsterCrit = 0;
	amtHeavyCrit = 0;
	amtGhostCrit = 0;
	}

	@After
	public void tearDown() throws Exception {
	testMap = null;
	testGame = null;
	testWave = null;
	testCritterBank = null;
	testReleaseBank = null;

	}

	@Test
	public void testSetUpBank() {
	//test that releaseBank is instantiated and filled with the same kinds of critters
	for (int i = 0; i<8; i++){
		assertTrue(testReleaseBank.get(i) instanceof NormalCritter);
	}
	//Change the difficulty multiplier (x>HARD)
	//Now test that the right amount of each critter is instantiated at the hightest difficulty
	testWave.setWaveNumber(22);
	testWave.setUpBank();
	for (int i = 0; i<testCritterBank.size(); i++){
		if (testCritterBank.get(i) instanceof NormalCritter)
			amtNormCrit++;
		if (testCritterBank.get(i) instanceof SmartCritter)
			amtSmartCrit++;
		if (testCritterBank.get(i) instanceof ShieldedCritter)
			amtShieldedCrit++;
		if (testCritterBank.get(i) instanceof HeavyCritter)
			amtHeavyCrit++;
		if (testCritterBank.get(i) instanceof GhostCritter)
			amtGhostCrit++;
		if (testCritterBank.get(i) instanceof MonsterCritter)
			amtMonsterCrit++;
	}
	
	assertEquals("Amount of normal critters should equal 3*x+5 added to the 8 existing ones", 8+(3*22+5), amtNormCrit);
	assertEquals("Amount of heavy critters should equal x/2", 22/2, amtHeavyCrit);
	assertEquals("Amount of smart critters should equal 2x/3", 2*22/3, amtSmartCrit);
	assertEquals("Amount of shielded critters should equal x/3", 22/3, amtShieldedCrit);
	assertEquals("Amount of monster critters should equal x/2", 22/2, amtMonsterCrit);
	
	
	}

	@Test
	public void testGenerateCritters() {
	//test that the correct type of critter is spawned
	//with the correct quantity, and that they are added to the bank.
	testWave.generateCritters("smart", 5, testGame);
	
	//The first 8 critters in the bank should be Normal Critters
	for (int i =0; i<8; i++){
		Critter c = testWave.getCritterBank().get(i);
		assertTrue(c instanceof NormalCritter);
	}
	//The next five critters should be Smart Critters
	for (int i=8; i<13; i++){
		Critter c = testWave.getCritterBank().get(i);
		assertTrue(c instanceof SmartCritter);
	}
	}

	@Test
	public void testReleaseCritters() throws InterruptedException {
	//releasing timing index is 0 at first, releasing is not finished.
	testWave.releaseCritters();
	assertFalse(testWave.finishedRelease());
	
	//artificially increase releasing timing index to a high value
	int index = (int)(testWave.DEFAULT_DELAY)/(int)(0.5*testWave.getDifficulty()*View.TIMEOUT)+1;
	testWave.setReleasingTimingIndex(index);
	//artificially increase the releasing index to the last value
	int index2 = (int) testWave.getReleaseBank().size()-1;
	testWave.setReleasingIndex(index2);
	//Releasing should be finished now.
	testWave.releaseCritters();
	assertTrue(testWave.finishedRelease());
	}


	@Test
	public void testWaveInProgress() throws InterruptedException {
	//No critters killed, wave should be in progress
	assertTrue(testWave.waveInProgress());	
	//Some critters killed, wave should be in progress
	for (int i =0; i<2; i++){
			testCritterBank.get(i).getsHit(5);
		}
	assertTrue(testWave.waveInProgress());
	//All critters killed, wave should not be in progress.
	testGame.generateWave();
	for (int i=0; i<testCritterBank.size(); i++){
			testCritterBank.get(i).getsHit(10);
		}
	for (int i=0; i<testReleaseBank.size(); i++){
			testReleaseBank.get(i).getsHit(10);
	}
	assertFalse(testWave.waveInProgress());
	}


}
