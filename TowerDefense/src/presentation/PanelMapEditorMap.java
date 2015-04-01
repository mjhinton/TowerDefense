package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import common.ReadWriteTxtFile;

import map.Map;

//import model.*;

public class PanelMapEditorMap extends JPanel {

	private static final long serialVersionUID = 1L;
	
	//for testing
	private Map testMap;
	private String[] testArrayMap;

	public PanelMapEditorMap(){

		Dimension dim=new Dimension(View.SCREEN_HEIGHT, View.SCREEN_HEIGHT);
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		//the following code is temporary for testing
		testArrayMap = ReadWriteTxtFile
				.readTxtFileAsStringArray("lib/testMaps/3030Map.txt");
		testMap = new Map("testMap", 30, testArrayMap);
		///////
		
	}
	
	public void paint(Graphics g){
		//Model.getMap().paint(g);
		testMap.paintMap(g);
		for (int i=0; i<testArrayMap.length; i++){
			System.out.println(testArrayMap[i]);
		}
	}
}
