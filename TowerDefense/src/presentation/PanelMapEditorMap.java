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
	private Map testMap;
	private MapEditor mapEditor;
	private String[] testArrayMap;
	private int cellX, cellY;
	private int size = Map.CELL_PIXEL_SIZE;
	
	private View view;

	public PanelMapEditorMap(View view){

		Dimension dim=new Dimension(View.SCREEN_HEIGHT, View.SCREEN_HEIGHT);
		this.view=view;
	//	this.mapEditor = view.model.getEditor();
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		//the following code is temporary for testing
		testArrayMap = ReadWriteTxtFile
				.readTxtFileAsStringArray("lib/testMaps/15x15map.txt");
		testMap = new Map("testMap", 15, testArrayMap);
		///////
		addMouseListener(this);
		
	}
	
	public void paint(Graphics g){
		view.model.getEditor().paintMapEditor(g);
		//testMap.paintMap(g);
	/*	for (int i=0; i<testArrayMap.length; i++){
			System.out.println(testArrayMap[i]);
		}*/
		
	}

	public View getView() {
		return view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Map mapBeingEdited = view.model.getEditor().getMap();
		cellX = e.getX() / size;
		cellY = e.getY() / size;
		Point c = new Point();
		c.setLocation(cellX, cellY);
		
		if(e.getX()%size == 0) {
			cellX = e.getX()/size - 1;}
		if(e.getY()%size == 0) {
			cellY = (int) e.getY()/size - 1;}
		
		mapBeingEdited.toggle(cellX, cellY);
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
