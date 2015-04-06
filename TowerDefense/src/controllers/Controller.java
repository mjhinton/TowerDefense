package controllers;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import critter.Wave;
import map.Map;
import model.Game;
import model.MapEditor;
import model.Model;
import presentation.View;

public class Controller implements ActionListener {

	protected int gameSpeedMultiplier;
	
	protected View view;
	protected Model model;
	protected String currentPanel;
	
	protected MapEditor mapEditor;
	protected Wave currWave;
	protected Game currGame;
	private Timer timer;
	//temp
	private boolean gameAlreadyStarted;
	
	public Controller(View view, Model model){
		this.gameSpeedMultiplier=1;
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
		view.update();
		
		if (currentPanel.equals("PanelMain")){
			
		}else if (currentPanel.equals("PanelGame")){
			if (currGame.gameOver()){
				JOptionPane.showMessageDialog(view, "Game Over. Restart?");
				this.startGame(currGame.getBoard().getMap());
			}else{
				currGame.updateGame();
			}
			
				
		}else if (currentPanel.equals("PanelMapEditor")){
			
		}

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		for (int i=0;i<this.gameSpeedMultiplier;i++){
			update();
		}
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
			//System.out.println("Game already started, so lets just generate a wave");
			try {
				view.getController().getGame().generateWave();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			view.getController().startGame(map);
			//System.out.println("Game had not begun yet so let's start it and then generate a wave");
			try {
				view.getController().getGame().generateWave();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setGameSpeed(int i) {
		this.gameSpeedMultiplier=i;
		currGame.setGameSpeed(i);
	}
	public int getGameSpeed(){
		return this.gameSpeedMultiplier;
	}
	
	public void pausePlay(){
		if (timer.isRunning()){
			timer.stop();
		}else{
			timer.restart();
		}
	}

	//testing

	
}
