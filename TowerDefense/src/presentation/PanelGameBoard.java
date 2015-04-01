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
						.readTxtFileAsStringArray("lib/testMaps/3030Map.txt");
		Map testMap = new Map("testMap", 30, testArrayMap);
		testBoard=new Board(testMap);
		testBoard.addTower(new FreezingTower(4,4),new Point(4,4) );
		testBoard.addTower(new FreezingTower(20,20),new Point(20,20) );
		
		///////
	}
	
	public void paint(Graphics g){
		//Model.getMap().paint(g);
		testBoard.paintBoard(g);

	}
}
