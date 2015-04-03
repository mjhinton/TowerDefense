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
}
