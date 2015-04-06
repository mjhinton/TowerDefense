package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import common.ReadWriteTxtFile;
import map.Map;
import map.PathEndCell;
import map.PathStartCell;
import map.SceneryCell;
import model.MapEditor;
import model.Model;

//import model.*;

public class PanelMapEditorMap extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	//for testing
	private Map testMap, mapBeingEdited;
	private MapEditor mapEditor;
	private String[] testArrayMap;
	private int cellX, cellY;
	private int size = Map.CELL_PIXEL_SIZE;
	
	private View view;

	public PanelMapEditorMap(View view){

		Dimension dim=new Dimension(View.SCREEN_HEIGHT, View.SCREEN_HEIGHT);
		this.view=view;
		//testing
		//this.mapEditor = view.model.getEditor();
		this.testMap = null;
		
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		//the following code is temporary for testing
		/*testArrayMap = ReadWriteTxtFile
				.readTxtFileAsStringArray("lib/testMaps/15x15map.txt");
		testMap = new Map("testMap", 15, testArrayMap);
		///////*/
		addMouseListener(this);
		
	}
	
	public void paint(Graphics g){
		//view.model.getEditor().paintMapEditor(g);
		view.getController().paintComponent(g);

		//testMap.paintMap(g);
	/*	for (int i=0; i<testArrayMap.length; i++){
			System.out.println(testArrayMap[i]);
		}*/
		
	}

	public View getView() {
		return view;
	}
	
	public Map getMapEdited(){
		return mapBeingEdited;
	}
	//possibly temp
	public Map setMapEdited(Map map){
		mapBeingEdited = map;
		return mapBeingEdited;
	}
	
	public boolean tryInitiatingPath(){
		try{
			if (mapBeingEdited.initPath())
				return true;
		} catch (NullPointerException e){
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mapBeingEdited = view.model.getEditor().getMap();
		int x = e.getX();
		int y = e.getY(); 
		view.model.getEditor().editMap(x, y);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
