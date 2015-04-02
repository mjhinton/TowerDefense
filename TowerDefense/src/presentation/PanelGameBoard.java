package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import tower.FreezingTower;
import map.Map;
import map.Path;
import model.Board;
import model.Game;
import common.ReadWriteTxtFile;

public class PanelGameBoard extends JPanel{

	private static final long serialVersionUID = 1L;
	
	//for testing
	private Board testBoard;
	private Game testGame;
//<<<<<<< HEAD
	private Path testPath;
//=======
	private View view;
//>>>>>>> branch 'master' of https://github.com/mjhinton/TowerDefense.git

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
	}
	
	public void paint(Graphics g){
		//Model.getMap().paint(g);
		//testGame.paintGame(g);
		view.getController().paintComponent(g);

	}
	
}
