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
import model.Board;
import common.ReadWriteTxtFile;

public class PanelGameBoard extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	
	//for testing
	private Board testBoard;
	
	//modes to be set whether user is selling, buying, etc.
	public boolean sellMode = false;
	public boolean buyB = false;
	public boolean buyF = false;
	public boolean buyM = false;
	public boolean upgradeMode = false;
	
	LinkedList<Tower> towers = new LinkedList<Tower>();

	public PanelGameBoard(){

		Dimension dim=new Dimension(View.SCREEN_HEIGHT, View.SCREEN_HEIGHT);
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		//the following code is temporary for testing
		String[] testArrayMap = ReadWriteTxtFile
						.readTxtFileAsStringArray("lib/testMaps/3030Map.txt");
		Map testMap = new Map("testMap", 30, testArrayMap);
		testBoard=new Board(testMap);
		testBoard.addTower(new FreezingTower(4,4, towers),new Point(4,4) );
		testBoard.addTower(new FreezingTower(20,20, towers),new Point(20,20) );
		testBoard.addTower(new NormalTower(10,6, towers),new Point(10,6) );
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
	    	repaint();
	    }
	    else if (upgradeMode){
	    	testBoard.upgradeTower(new Point(coordX, coordY));
	    	repaint();
	    }
	    else if (buyB){
	    	testBoard.addTower(new NormalTower(coordX, coordY, towers), new Point(coordX, coordY));
	    	repaint();
	    }
	    if (buyF){
	    	testBoard.addTower(new FreezingTower(coordX, coordY, towers), new Point(coordX, coordY));
	    	repaint();
	    }
	    if(buyM){
	    	coordY--;
	    	testBoard.addTower(new MonsterTower(coordX, coordY, towers), new Point(coordX, coordY));
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
		testBoard.paintBoard(g);

	}
}
