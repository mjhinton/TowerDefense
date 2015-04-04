package model;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import map.Map;
import map.MapCell;

public class MapEditor{

	int size = Map.CELL_PIXEL_SIZE;
	private Map map;
	Point cell;
	int cellX;
	int cellY;
	MapCell selectedCell;
	
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
		map.toggle(cellX, cellY);	
	}
}
