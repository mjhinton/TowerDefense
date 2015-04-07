package model;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import map.Map;
import map.MapCell;

/**
 * This class is a model for the MapEditor and contains the map to be displayed in PanelMapEditor
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */

public class MapEditor{

	private int size = Map.CELL_PIXEL_SIZE;
	private Map map;
	private Point cell;
	private int cellX;
	private int cellY;
	private MapCell selectedCell;
	
	public MapEditor(Map map){
		if(map!=null)
			this.map = map;
		else
			this.map = new Map();
	}

	public void paintMapEditor(Graphics g){
		map.paintMap(g);
	}
	
	public Map getMap(){
		return map;
	}
	
	//adjusts a tile when clicked on: switches between tiles using Map's toggle method
	public void editMap(int x, int y){
		int cellX = x/size;
		int cellY = y/size;
		Point c = new Point(cellX, cellY);
		c.setLocation(cellX, cellY);
		
		if (x%size ==0){
			cellX = x/size-1;
		}
		if (y%size ==0){
			cellY = (int) y/size -1;
		}
		if(cellX>=map.getWidth()||cellY>=map.getHeight()){}
		else map.toggle(cellX, cellY);	
	}
	
	public void setMap(Map map){
		this.map = map;
	}
}
