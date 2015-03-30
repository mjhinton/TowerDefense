package controllers;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import model.Model;
import presentation.View;

public class Controller implements ActionListener {

	protected View view;
	protected Model model;
	
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
		view.paintComponents(g);
	}
	
	
	public void draw(){
		
	}
	
	public void update(){
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		draw();
		
	}
	

}
