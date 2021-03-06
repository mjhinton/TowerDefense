package model;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import common.ReadWriteTxtFile;
import tower.Bullet;
import tower.Tower;
import map.Map;
import critter.Critter;
import critter.Wave;


/**
 * This class contains everything necessary to play a game.
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */
public class Game {

	private Board board;
	private int currentWaveNumber;
	private Wave wave;
	private int playerCoins;
	private int playerHealth;
	private boolean gameOver;
	private LinkedList<Tower> towers;
	private int gameSpeedMultiplier;
	private ArrayList<Bullet> bullets;

	public Game(Map map) {
		this.board = new Board(map);
		this.currentWaveNumber = 1;
		this.wave = null;
		// Start with many coins just for testing purposes
		this.playerCoins = 2000;
		this.gameOver = false;
		towers = new LinkedList<Tower>();
		this.playerHealth = 250;
		this.gameSpeedMultiplier = 1;
		bullets = new ArrayList<Bullet>();
	}

	public Board getBoard() {
		return board;
	}

	// this method will replace the board with whatever board the user created
	// from the map editor.
	public void setUpBoardFromEditor(Map map) {
		board.setMap(map);
		board.getMap().initPath();
	}

	public int getCoins() {
		return this.playerCoins;
	}

	/**
	 * Attempts to change the coins of the game/player.
	 * 
	 * @param coins
	 *            coins to added or subtracted
	 * @return true if sufficient funds
	 */
	public boolean changeCoins(int coins) {
		if (coins >= 0) {
			playerCoins += coins;
			return true;
		} else if ((-1 * coins) > playerCoins) {
			System.out.println("Not enough coins.");
			return false;
		} else {
			playerCoins += coins;
			return true;
		}
	}

	public boolean changeHealth(int health) {
		if (health >= 0) {
			playerHealth += health;
			return true;
		} else {
			if ((-1 * health) > playerHealth) {
				System.out.println("Game over.");
				playerHealth = 0;
				gameOver = true;
				return false;
			} else {
				playerHealth += health;
				return true;
			}
		}
	}

	/**
	 * Attempts to add a tower to the board.
	 * 
	 * @param tower
	 *            tower to be placed on board
	 * @return true if valid change and change was made
	 */
	public boolean addTower(Tower tower) {
		boolean worked;
		if (this.changeCoins(-1 * tower.getCost())) {
			worked = board.addTower(tower);
			if (worked) {
				towers.add(tower);
				System.out.println("New tower bought.");
				return true;
			} else {
				this.changeCoins(tower.getCost());
				return false;
			}
		}
		return false;
	}

	/**
	 * Attempts to remove a tower from the board.
	 * 
	 * @param tower
	 *            tower to be removed from board
	 * @return true if valid change and change was made
	 */
	public boolean removeTower(Tower tower) {
		boolean worked;
		if (this.changeCoins(tower.getValue())) {
			worked = board.removeTower(tower);
			if (worked) {
				towers.remove(tower);
				System.out.println("Tower sold.");
				return worked;
			}
			return false;
		}
		return false;
	}

	/**
	 * Attempts to upgrade a tower on the board.
	 * 
	 * @param tower
	 *            tower to be upgraded on board
	 * @return true if valid change and change was made
	 */
	public boolean upgradeTower(Tower tower) {
		boolean worked;
		if (tower.getLevel() == 5) {
			System.out
					.println("Maximum level reached. Further upgrade not possible.");
		} else if (tower.getLevel() < 5
				&& this.changeCoins(-1 * tower.getCost())) {
			worked = board.upgradeTower(tower);
			return worked;
		}
		return false;
	}

	/**
	 * Updates the various components of the games.
	 * 
	 */
	public void updateGame() {

		if (wave != null) {
			this.getWave().removeDead();
			// System.out.println(this.getWave().getCritterBank().size());
			if (this.getWave().getCritterBank().size() == 0) {
				wave = null;
			} else {
				wave.updateCritterPositions();
				if (wave.finishedRelease() == false) {
					try {
						wave.releaseCritters();
						// System.out.println("release is good");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			for (int i = 0; i < towers.size(); i++)
				towers.get(i).fire();
			for (int j = 0; j < bullets.size(); j++)
				bullets.get(j).updateBullet();
		}

	}

	public void paintGame(Graphics g) {
		board.paintMap(g);
		if (wave != null) {
			wave.paintCritters(g);
			for (int j = 0; j < bullets.size(); j++)
				bullets.get(j).drawBullet(g);
		}
		board.paintTowers(g);
	}

	/**
	 * Attempts to generate a wave of critters.
	 * 
	 */
	public void generateWave() throws InterruptedException {

		boolean flag;
		try {
			flag = (wave.waveInProgress() == false);
		} catch (NullPointerException e) {
			flag = true;
		}
		if (flag) {
			wave = new Wave(currentWaveNumber, this);
			System.out.println("Wave " + currentWaveNumber + " begun.");
			wave.setUpBank();
			currentWaveNumber++;
			System.out.println("This wave contains "
					+ wave.getReleaseBank().size() + " critters.");
		} else
			System.out.println("A wave is already in progress.");
	}

	public Wave getWave() throws NullPointerException {
		return wave;
	}

	public LinkedList<Tower> getTowers() {
		return towers;
	}

	public boolean gameOver() {
		return gameOver;
	}

	public void setGameSpeed(int i) {
		this.gameSpeedMultiplier = i;

	}

	public int getGameSpeed() {
		return this.gameSpeedMultiplier;
	}

	public void setBoardMap(Map newmap) {
		this.board.setMap(newmap);
		this.board.getMap().initPath();
	}

	//Adds a bullet to bullet list
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	//Remove a bullet from bullet list
	public void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
	}

	// Method for testing purposes
	public void addCritter(Critter critter) {
		this.wave.addCritterToBank(critter);
	}

	// Method for testing purposes
	public void removeCritter(Critter critter) {
		this.wave.removeCritter(critter);

	}

	// Getter method added for testing.
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	/**
	 * Saves the game as a text file.
	 * 
	 */
	public void saveGame() {
		Map map = this.board.getMap();
		String mapString = map.print();
		String gameString = this.playerCoins + "\n" + this.playerHealth + "\n"
				+ (this.currentWaveNumber - 1) + "\n" + mapString;
		try {
			if (ReadWriteTxtFile.writeTxtFileFromStringArray(gameString)) {
				System.out.println("Game saved.");
			}

		} catch (IOException e) {
			System.out.println("Unable to save game.");
			e.printStackTrace();
		}
	}

	public int getWaveNo() {
		return currentWaveNumber;
	}

	public int getHealth() {
		return playerHealth;
	}

	// Setter method for testing purposes
	public void setPlayerCoins(int value) {
		playerCoins = value;
	}

}
