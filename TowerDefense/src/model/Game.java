package model;

import java.awt.Graphics;

import map.Map;
import critter.CritterWaveGenerator;

public class Game {
	
	private Board board;
	private CritterWaveGenerator waveGenerator;
	//private TowerManager (make a towerManager class?)
	
	//add tower manager as a parameter too?
	
	//as of now, the game constructor just opens a new, randomly generated map on a new board. 
	//later, we should have another constructor or something in order to initialize a new game with a map that the player made. 
	public Game(Map map){
		this.board = new Board(map);
		this.waveGenerator = new CritterWaveGenerator(board);
	}

	
	
	public Board getBoard(){
		return board;
	}
	
	public CritterWaveGenerator getGenerator(){
		return waveGenerator;
	}



	public void paintGame(Graphics g) {
		// TODO Auto-generated method stub
		board.paintBoard(g);
		waveGenerator.getWave().paintCritters(g);
	}
	
	public void startWave(){
		try {
			waveGenerator.generateWave();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}
