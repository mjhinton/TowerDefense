package model;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import tower.Tower;
import map.Map;
import critter.Wave;

public class Game {
	
	private Board board;
	private int currentWaveNumber;
	private Wave wave;
	private int playerCoins;
	private int playerHealth;
	private boolean gameOver;
	private LinkedList<Tower> towers;
	//private TowerManager (make a towerManager class?)
	
	//add tower manager as a parameter too?
	
	//as of now, the game constructor just opens a new, randomly generated map on a new board. 
	//later, we should have another constructor or something in order to initialize a new game with a map that the player made. 
	public Game(Map map){
		this.board = new Board(map);
		this.currentWaveNumber=1;
		this.wave=null;
		//Start with many coins just for testing purposes
		this.playerCoins=1000000;
		this.gameOver=false;
		towers=new LinkedList<Tower>();
		this.playerHealth=10;
	}
	
	public Board getBoard(){
		return board;
	}
	// this method will replace the board with whatever board the user created
	//from the map editor. 
	public void setUpBoardFromEditor(Map map){
		board.setMap(map);
	}
	
	
	public int getCoins(){
		return this.playerCoins;
	}
	
	public boolean changeCoins(int coins){
		if (coins>=0){
			playerCoins+=coins;
			return true;
		}
		else if((-1*coins)>playerCoins){
				System.out.println("Not enough coins.");
				return false;
		}
		else{
			playerCoins+=coins;
			return true;
		}
	}
	public boolean changeHealth(int health){
		if (health>=0){
			playerHealth+=health;
			return true;
		}else{
			if((-1*health)>playerHealth){
				System.out.println("Game over.");
				playerHealth=0;
				gameOver=true;
				return false;
			}else{
				playerHealth+=health;
				System.out.println(playerHealth);
				return true;
			}
		}
	}
	public boolean addTower(Tower tower){
		boolean worked;
			if (this.changeCoins(-1*tower.getCost())){
				worked = board.addTower(tower);
				if (worked){
					towers.add(tower);
					System.out.println("New tower bought.");
					return true;
				}
				return false;
			}
			return false;
	}

	public boolean removeTower(Tower tower){
		boolean worked;
		if (this.changeCoins(tower.getValue())){
			worked = board.removeTower(tower);
			if (worked){
				towers.remove(tower);
				System.out.println("Tower sold.");
				return worked;
			}
			return false;
		}
			return false;		
	}
	
	public boolean upgradeTower(Tower tower){
		boolean worked;
		if (this.changeCoins(-1*tower.getCost())){
			worked = board.upgradeTower(tower);
			return worked;
		}
			return false;		
	}

	public void updateGame(){
		
		if(wave!=null){
			wave.updateCritterPositions();
		}
	}
	public void paintGame(Graphics g) {
		board.paintBoard(g);
		if(wave!=null){
			wave.paintCritters(g);
		}
		
	}
	
	//The player will only be able to call this method if a wave is not already in progress.
	//(A wave is in progress while there are still critters alive and on the map)
	public void generateWave() throws InterruptedException{
		
		boolean flag;
		try{
			flag=(wave.waveInProgress() == false);
		}catch(NullPointerException e){
			flag=true;
		}
		if (flag){
			wave = new Wave(currentWaveNumber, this);
			System.out.println("Aww yeah, wave generated");
			wave.releaseCritters();
			System.out.println("Critters all released");
			currentWaveNumber++;
		}
		else
			System.out.println("Wave in progress, can't play.");
	}

	public Wave getWave() throws NullPointerException {
		return wave;
	}
	//temporary class

	public LinkedList<Tower> getTowers() {
		return towers;
	}

	public boolean gameOver() {
		return gameOver;
	}
	
	
}
