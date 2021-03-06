package presentation;

import javax.swing.JFrame;

import common.IObserver;

import model.Model;
import controllers.Controller;

import java.awt.BorderLayout;

/**
 * this class is the main view. it holds controller and is the window containing
 * all other panels
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */

public class View extends JFrame implements IObserver {

	private static final long serialVersionUID = 1L;
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 600;
	public static final int TIMEOUT = 30;
	public static final String APP_NAME = "TDOne";

	protected Controller controller;
	protected Model model;
	protected MainPanel mp;

	public View() {
		this.init();
	}

	private void init() {

		mp = new MainPanel(this);
		model = new Model();
		controller = new Controller(this, model);
		this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT + 25);
		this.setResizable(false);
		this.setTitle(APP_NAME);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.add(mp, BorderLayout.CENTER);
		this.setVisible(true);
	}

	/*
	 * public void createEditableMap(String name, int x, int y){
	 * model.setMap(name, x, y); init(); }
	 */

	public void switchPanel(String cardName) {
		mp.switchPanel(cardName);
	}

	public MainPanel getMainPanel() {
		// TODO Auto-generated method stub
		return mp;
	}

	public Controller getController() {
		return controller;

	}

	public Model getModel() {
		return this.model;
	}

	public void update() {
		mp.update();
	}
}
