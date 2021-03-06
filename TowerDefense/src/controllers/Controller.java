package controllers;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import critter.Wave;
import map.Map;
import model.Game;
import model.MapEditor;
import model.Model;
import presentation.View;

/**
 * This class is the controller of the application, which handles user input,
 * timers, and updating.
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */
public class Controller implements ActionListener {

	protected int gameSpeedMultiplier;

	protected View view;
	protected Model model;
	protected String currentPanel;

	protected MapEditor mapEditor;
	protected Wave currWave;
	protected Game currGame;
	private Timer timer;

	private boolean gameAlreadyStarted;

	public Controller(View view, Model model) {
		this.gameSpeedMultiplier = 1;
		this.view = view;
		this.model = model;
		this.currentPanel = "PanelMenu";
		view.getMainPanel().switchPanel(currentPanel);

		// temp
		this.gameAlreadyStarted = false;

		this.currGame = model.getGame();
		this.currWave = currGame.getWave();
		this.mapEditor = model.getEditor();

		// Start timer
		timer = new Timer(View.TIMEOUT, this);
		timer.start();
	}

	/**
	 * Is called to determine which Panel to paint.
	 * 
	 * @param g
	 *            Graphic to be painted on.
	 */
	public void paintComponent(Graphics g) {

		if (currentPanel.equals("PanelMain")) {

		} else if (currentPanel.equals("PanelGame")) {
			currGame.paintGame(g);
		} else if (currentPanel.equals("PanelMapEditor")) {
			mapEditor.paintMapEditor(g);
		}

	}

	public void draw() {

		view.getMainPanel().repaint();
	}

	/**
	 * Update method called every time instant. Determines which part of the
	 * application to update based on which Panel is currently open.
	 * 
	 */
	public void update() {
		currentPanel = view.getMainPanel().getCurrentPanel();
		view.update();

		if (currentPanel.equals("PanelMain")) {

		} else if (currentPanel.equals("PanelGame")) {
			if (currGame.gameOver()) {
				JOptionPane.showMessageDialog(view, "Game Over. Restart?");
				this.startGame(currGame.getBoard().getMap());
			} else {
				currGame.updateGame();
			}

		} else if (currentPanel.equals("PanelMapEditor")) {

		}

	}

	/**
	 * This method is called every time instance.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		for (int i = 0; i < this.gameSpeedMultiplier; i++) {
			update();
		}
		draw();
	}

	/**
	 * Starts a game with the given map.
	 * 
	 * @param map
	 *            The map to be used in the game.
	 */
	public void startGame(Map map) {
		currGame = new Game(map);
		currWave = currGame.getWave();
		gameAlreadyStarted = true;
	}

	// returns the currently running game
	public Game getGame() {
		return currGame;
	}

	/**
	 * Plays the next wave in the game or starts new game with given map if game
	 * hasn't been started.
	 * 
	 * @param map
	 *            The map to be used in the game.
	 */
	public void playGame(Map map) {
		if (gameAlreadyStarted == true) {
			try {
				getGame().generateWave();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			view.getController().startGame(map);
			// System.out.println("Game had not begun yet so let's start it and then generate a wave");
			startGame(map);
			try {
				getGame().generateWave();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setGameSpeed(int i) {
		this.gameSpeedMultiplier = i;
		currGame.setGameSpeed(i);
	}

	public int getGameSpeed() {
		return this.gameSpeedMultiplier;
	}

	/**
	 * Toggles between pausing and resuming the timer.
	 * 
	 */
	public void pausePlay() {
		if (timer.isRunning()) {
			timer.stop();
		} else {
			timer.restart();
		}
	}


}
