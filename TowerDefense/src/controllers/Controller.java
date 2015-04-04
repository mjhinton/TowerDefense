package controllers;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import critter.Wave;
import map.Map;
import model.Game;
import model.MapEditor;
import model.Model;
import presentation.View;

public class Controller implements ActionListener {

	protected View view;
	protected Model model;
	protected String currentPanel;
	
	protected MapEditor mapEditor;
	protected Wave currWave;
	protected Game currGame;
	private Timer timer;
	//temp
	private boolean gameAlreadyStarted;
	
	Random rand = new Random();
	
	public Controller(View view, Model model){
		this.view=view;
		this.model=model;
		this.currentPanel="PanelMenu";
		view.getMainPanel().switchPanel(currentPanel);
		
		//temp
		this.gameAlreadyStarted = false;
		
		this.currGame = model.getGame();
		this.currWave = currGame.getWave();
		this.mapEditor = model.getEditor();
		
		//Start timer
		timer = new Timer(View.TIMEOUT,this);
		timer.start();
	}
	
	public void paintComponent(Graphics g){
		
		if (currentPanel.equals("PanelMain")){
			
		}else if (currentPanel.equals("PanelGame")){
			currGame.paintGame(g);
		}else if (currentPanel.equals("PanelMapEditor")){
			mapEditor.paintMapEditor(g);
		}
		
		
	}
	
	
	public void draw(){
		
		view.getMainPanel().repaint();
	}
	
	public void update(){
		currentPanel=view.getMainPanel().getCurrentPanel();
		
		if (currentPanel.equals("PanelMain")){
			
		}else if (currentPanel.equals("PanelGame")){
			if (currGame.gameOver()){
				//TODO: do something if game is over
			}else{
				currGame.updateGame();
			}
			
				
		}else if (currentPanel.equals("PanelMapEditor")){
			
		}

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
		gameAlreadyStarted = true;
	}
	
	//returns the currently running game
	public Game getGame(){
		return currGame;
	}
	
	//temporary testing method
	public void playGame(Map map){
		if (gameAlreadyStarted==true){
			try {
				currGame.generateWave();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			this.startGame(map);
			currGame = view.getController().getGame();
			try {
				currGame.generateWave();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
