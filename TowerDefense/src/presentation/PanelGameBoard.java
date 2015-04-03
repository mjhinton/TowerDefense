package presentation;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import map.Map;
import model.Game;
import tower.*;
//import map.Map;
//import map.Path;
//import model.Board;
//import model.Game;
//import common.ReadWriteTxtFile;

public class PanelGameBoard extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	
	//for testing
//	private Board testBoard;
//
//	private Game testGame;
//
//	private Path testPath;
	
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
		
		addMouseListener(this);
	}
	
	public void mouseClicked(MouseEvent e) {
	    int coordX = (e.getX()/Map.CELL_PIXEL_SIZE);
	    //in order to align a bit..
	    int coordY = (e.getY()/Map.CELL_PIXEL_SIZE);
		//this next line was just for testing
	    //System.out.println("(" + coordX + ", " + coordY + ")");
	    Game game=view.getController().getGame();
	    Point c= new Point(coordX, coordY);
	    Tower tower=game.getBoard().getTower(c);
	
		if (sellMode){
			
			game.removeTower(tower);
	    }
	    else if (upgradeMode){
	    	
	    }
	    else if (buyB){
	    	game.addTower(new NormalTower(c, game));
	    	repaint();
	    }
	    if (buyF){
	    	game.addTower(new FreezingTower(c, game));
	    	repaint();
	    }
	    if(buyM){
	    	game.addTower(new MonsterTower(c, game));
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
