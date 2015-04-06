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
		this.playerCoins = 10000;
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
	}

	public int getCoins() {
		return this.playerCoins;
	}

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
				System.out.println(playerHealth);
				return true;
			}
		}
	}

	public boolean addTower(Tower tower) {
		boolean worked;
		if (this.changeCoins(-1 * tower.getCost())) {
			worked = board.addTower(tower);
			if (worked) {
				towers.add(tower);
				System.out.println("New tower bought.");
				return true;
			}
			return false;
		}
		return false;
	}

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

	public boolean upgradeTower(Tower tower) {
		boolean worked;
		if (tower.getLevel() < 5 && this.changeCoins(-1 * tower.getCost())) {
			worked = board.upgradeTower(tower);
			return worked;
		} else {
			System.out
					.println("Maximum level reached. Further upgrade not possible.");
		}
		return false;
	}

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

	// The player will only be able to call this method if a wave is not already
	// in progress.
	// (A wave is in progress while there are still critters alive and on the
	// map)
	public void generateWave() throws InterruptedException {

		boolean flag;
		try {
			flag = (wave.waveInProgress() == false);
		} catch (NullPointerException e) {
			flag = true;
		}
		if (flag) {
			wave = new Wave(currentWaveNumber, this);
			System.out.println("Aww yeah, wave generated");
			wave.setUpBank();
			currentWaveNumber++;
		} else
			System.out.println("Wave in progress, can't play.");
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

	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	public void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
	}

	public void removeCritter(Critter critter) {
		this.wave.removeCritter(critter);

	}

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
	
	public int getWaveNo(){
		return currentWaveNumber;
	}
	
	public int getHealth(){
		return playerHealth;
	}

}
