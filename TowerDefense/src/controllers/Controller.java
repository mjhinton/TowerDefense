package controllers;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import critter.Wave;
import map.Map;
import model.Game;
import model.Model;
import presentation.View;

public class Controller implements ActionListener {

	protected View view;
	protected Model model;
	
	protected Wave currWave;
	
	protected Game currGame;
	
	private Timer timer;
	Random rand = new Random();
	
	public Controller(View view, Model model){
		this.view=view;
		this.model=model;
		
		//Start timer
		timer = new Timer(View.TIMEOUT,this);
		timer.start();
	}
	
	public void paintComponent(Graphics g){
		currGame.paintGame(g);
	}
	
	
	public void draw(){
		view.getMainPanel().repaint();
	}
	
	public void update(){
		currWave=currGame.getWave();
		if (currWave.waveInProgress()) currWave.updateCritterPositions();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		draw();
		
	}
	
	//starts a game with the input map
	public void startGame(Map map){
		currGame=new Game(map);
		currWave = currGame.getWave();
	}
	
	//returns the currently running game
	public Game getGame(){
		return currGame;
	}
	

}
