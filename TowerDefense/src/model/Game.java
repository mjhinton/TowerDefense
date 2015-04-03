package model;

import java.awt.Graphics;

import map.Map;
import critter.Wave;

public class Game {
	
	private Board board;
	private int currentWaveNumber;
	private Wave wave;
	//private TowerManager (make a towerManager class?)
	
	//add tower manager as a parameter too?
	
	//as of now, the game constructor just opens a new, randomly generated map on a new board. 
	//later, we should have another constructor or something in order to initialize a new game with a map that the player made. 
	public Game(Map map){
		this.board = new Board(map);
		this.currentWaveNumber=1;
		this.wave=null;
	}
	
	public Board getBoard(){
		return board;
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
			wave = new Wave(currentWaveNumber, board);
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
	
	
}
