package presentation;

import java.util.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import tower.*;
import map.Map;
import map.Path;
import model.Board;
import model.Game;
import common.ReadWriteTxtFile;

public class PanelGameBoard extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	
	//for testing
	private Board testBoard;

	private Game testGame;

	private Path testPath;
	
	private View view;
	
	//modes to be set whether user is selling, buying, etc.
	public boolean sellMode = false;
	public boolean buyB = false;
	public boolean buyF = false;
	public boolean buyM = false;
	public boolean upgradeMode = false;

	public PanelGameBoard(View view){

		Dimension dim=new Dimension(View.SCREEN_HEIGHT, View.SCREEN_HEIGHT);
		this.view=view;
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		//the following code is temporary for testing
		String[] testArrayMap = ReadWriteTxtFile
						.readTxtFileAsStringArray("lib/testMaps/15x15map.txt");
		Map testMap = new Map("testMap", 15, testArrayMap);
		
		testGame=new Game(testMap);
		testBoard=testGame.getBoard();
		testPath = new Path();
		
		testPath.addCoord(new Point(1,1));
		testPath.addCoord(new Point(1,2));
		testPath.addCoord(new Point(1,3));
		testPath.addCoord(new Point(1,4));
		
		testBoard.setPath(testPath);
		
		testBoard.addTower(new FreezingTower(4,4),new Point(4,4) );
		testBoard.addTower(new FreezingTower(14,14),new Point(14,14));
		
		System.out.println(testBoard.getPath());
		
		//testGame.startWave();

		///////
		
		addMouseListener(this);
	}
	
	public void mouseClicked(MouseEvent e) {
	    int coordX = (e.getX()/20);
	    //in order to align a bit..
	    int coordY = (e.getY()/20 - 1);
		//this next line was just for testing
	    //System.out.println("(" + coordX + ", " + coordY + ")");
	
		if (sellMode){
	    	testBoard.removeTower(new Point(coordX, coordY));
	    }
	    else if (upgradeMode){
	    	
	    }
	    else if (buyB){
	    	testBoard.addTower(new NormalTower(coordX, coordY), new Point(coordX, coordY));
	    	repaint();
	    }
	    if (buyF){
	    	testBoard.addTower(new FreezingTower(coordX, coordY), new Point(coordX, coordY));
	    	repaint();
	    }
	    if(buyM){
	    	testBoard.addTower(new MonsterTower(coordX, coordY), new Point(coordX, coordY));
	    	repaint();
	    }
	    else {
	    	return;
	    }
	}
	
	public void mousePressed(MouseEvent e) {
	}
	
	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
	
	public void paint(Graphics g){
		//Model.getMap().paint(g);
		//testGame.paintGame(g);
		view.getController().paintComponent(g);

	}
	
}
