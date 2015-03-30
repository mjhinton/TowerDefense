package presentation;

import javax.swing.JFrame;

import common.Model;
import controllers.Controller;

import java.awt.BorderLayout;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 600;
	public static final int TIMEOUT = 30;
	public static final String APP_NAME = "TestTowerDefense";
	
	protected Controller controller;
	protected Model model;
	protected MainPanel mp;
	
	public View(){
		this.init();
	}
	
	private void init(){
		
		mp=new MainPanel();
		model=new Model();
		controller = new Controller(this, model);
		this.setSize(SCREEN_WIDTH,SCREEN_HEIGHT+25);
		this.setResizable(false);
		this.setTitle(APP_NAME);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.add(mp,BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void switchPanel(String cardName){
		mp.switchPanel(cardName);
	}
}