package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import tower.FreezingTower;
import map.Map;
import model.Board;
import common.ReadWriteTxtFile;

public class PanelGameBoard extends JPanel{

	private static final long serialVersionUID = 1L;
	
	//for testing
	private Board testBoard;

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
						.readTxtFileAsStringArray("lib/testMaps/15x15map.txt");
		Map testMap = new Map("testMap", 15, testArrayMap);
		testBoard=new Board(testMap);
		testBoard.addTower(new FreezingTower(4,4),new Point(4,4) );
		testBoard.addTower(new FreezingTower(14,14),new Point(14,14) );
		
		///////
	}
	
	public void paint(Graphics g){
		//Model.getMap().paint(g);
		testBoard.paintBoard(g);

	}
}
