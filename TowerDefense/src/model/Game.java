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
	}
	
	public Board getBoard(){
		return board;
	}
	
	public int getCoins(){
		return playerCoins;
	}
	
	public boolean changeCoins(int coins){
		if (coins>=0){
			playerCoins+=coins;
			return true;
		}else{
			if((-1*coins)>playerCoins){
				System.out.println("Not enough coins");
				return false;
			}else{
				playerCoins-=coins;
				return true;
			}
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
				playerHealth-=health;
				return true;
			}
		}
	}
	public boolean addTower(Tower tower){
		if (playerCoins>=tower.getCost()){
			boolean worked= board.addTower(tower);
			if (worked){
				this.changeCoins(-1*tower.getCost());
				towers.add(tower);
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		
		
	}
	public boolean removeTower(Tower tower){
		boolean worked= board.removeTower(tower);
		if (worked){
			this.changeCoins(tower.getValue());
			towers.remove(tower);
			return true;
		}else{
			return false;
		}
		
	}
	
	public boolean upgradeTower(Tower tower){
		boolean worked= board.upgradeTower(tower);
		if (worked){
			this.changeCoins(-1*tower.getCost());
			return true;
		}else{
			return false;
		}
		
	}

	public void paintGame(Graphics g) {
		board.paintBoard(g);
	/*	if(wave!=null){
			wave.paintCritters(g);
		}*/
		
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

	public LinkedList<Tower> getTowers() {
		return towers;
	}

	public boolean gameOver() {
		return gameOver;
	}
	
	
}
