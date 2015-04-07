package critter;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import presentation.View;
import model.Game;

/**
 * A Wave is a collection of Critters. The Player's objective is to destroy all the Critters in a wave before they reach
 * the end of the path. Waves of varying configurations of Critters can be generated depending on how far along the Player is in the Game.
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */

public class Wave {
	static final int EASY = 3;
	static final int MEDIUM = 10;
	static final int HARD = 20;
	static final long DEFAULT_DELAY = 1000;

	private ArrayList<Critter> critterBank;
	private ArrayList<Critter> releaseBank;
	private int x;
	private double difficulty;
	private boolean waveInProgress;
	private Game game;
	private boolean paused;
	private boolean finishedRelease;
	private int releasingTimingIndex;
	private int releasingIndex;

	public Wave(int n, Game game) {
		this.game = game;
		this.critterBank = new ArrayList<Critter>();
		this.difficulty = (double) n;
		this.waveInProgress = false;
		this.x = n;
		this.finishedRelease = false;
		this.releasingTimingIndex = 0;
		this.releasingIndex = 0;
	}

	public void setUpBank() {
		generateCritters("normal", (3 * x + 5), game);

		if (x > EASY) {
			generateCritters("heavy", x / 2, game);
			generateCritters("shielded", x / 3, game);
			generateCritters("smart", x / 6, game);
		}
		if (x > MEDIUM) {
			generateCritters("ghost", x / 3, game);
			generateCritters("shielded", x / 3, game);
			generateCritters("smart", x / 4, game);
		}
		if (x > HARD) {
			generateCritters("monster", x / 2, game);
		}

		releaseBank = new ArrayList<Critter>();

		for (int j = 0; j < critterBank.size(); j++) {
			critterBank.get(j).increaseDifficulty(Math.pow(difficulty, 1.005)); // can
																				// be
																				// modified
																				// to
																				// change
																				// difficulty
																				// of
																				// waves

			releaseBank.add(critterBank.get(j));
		}

	}

	// Adder methods for testing purposes.
	public void addCritterToBank(Critter c) {
		critterBank.add(c);
	}

	public void addCritterToReleaseBank(Critter c) {
		releaseBank.add(c);
	}

	// //

	// Calls the CritterFactory to spawn a certain amount of a certain type of
	// critters in the current game.
	public void generateCritters(String type, int quantity, Game game) {
		for (int i = 0; i < quantity; i++) {
			Critter critter = CritterFactory.spawn(type, game);
			critterBank.add(critter);
		}
	}

	public void releaseCritters() throws InterruptedException {
		Critter c;
		c = releaseBank.get(releasingIndex);
		if (releasingTimingIndex >= DEFAULT_DELAY
				/ (0.5 * difficulty * View.TIMEOUT)) {
			releasingTimingIndex = 0;
			c.setDown();
			if (releasingIndex >= releaseBank.size() - 1) {
				this.finishedRelease = true;
				System.out.println("Finished releasing critters.");
			} else {
				releasingIndex += 1;
			}

		} else {
			releasingTimingIndex += 1;
		}
	}

	public void paintCritters(Graphics g) {
		Critter c;
		for (int i = 0; i < critterBank.size(); i++) {
			c = critterBank.get(i);
			c.drawCritter(g);
		}
	}

	//
	public boolean waveInProgress() {
		Critter c;
		for (int i = 0; i < critterBank.size(); i++) {
			c = critterBank.get(i);
			if (c.getHealth() > 0) {
				waveInProgress = true;
				return waveInProgress;
			}
		}
		waveInProgress = false;
		return waveInProgress;
	}

	public void updateCritterPositions() {
		for (int i = 0; i < critterBank.size(); i++) {
			critterBank.get(i).updatePosition();
		}
	}

	public void removeDead() {
		for (int i = 0; i < critterBank.size(); i++)
			if (critterBank.get(i).getHealth() < 0)
				critterBank.remove(i);
	}

	public ArrayList<Critter> getCritterBank() {
		return critterBank;
	}

	// added for testing purposes
	public ArrayList<Critter> getReleaseBank() {
		return releaseBank;
	}

	public void removeCritter(Critter c) {
		critterBank.remove(c);
	}

	public void togglePaused() {
		paused = !paused;
	}

	public boolean getPaused() {
		return paused;
	}

	public Game getGame() {
		return this.game;
	}

	public double getDifficulty() {
		return difficulty;
	}

	public boolean finishedRelease() {
		return finishedRelease;
	}

	// Setter methods for testing purposes
	public void setWaveNumber(int newX) {
		this.x = newX;
	}

	public void setReleasingIndex(int index) {
		this.releasingIndex = index;
	}

	public void setReleasingTimingIndex(int index) {
		this.releasingTimingIndex = index;
	}

}
