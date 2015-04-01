package model;

import map.Map;
import critter.CritterWaveGenerator;

public class Game {
	
	private Board board;
	private CritterWaveGenerator waveGenerator;
	//private TowerManager (make a towerManager class?)
	
	//add tower manager as a parameter too?
	
	//as of now, the game constructor just opens a new, randomly generated map on a new board. 
	//later, we should have another constructor or something in order to initialize a new game with a map that the player made. 
	public Game(){
		this.board = new Board(new Map());
		this.waveGenerator = new CritterWaveGenerator();
	}
	
	public Board getBoard(){
		return board;
	}
	
	public CritterWaveGenerator getGenerator(){
		return waveGenerator;
	}
	
	
}
