package model;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import map.Map;
import map.MapCell;

public class MapEditor implements MouseListener{

	int size = Map.CELL_PIXEL_SIZE;
	private Map map;
	Point cell;
	int cellX;
	int cellY;
	MapCell selectedCell;
	
	public MapEditor(){
		this.map = new Map();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouse was clicked");
		cellX = e.getX() / size;
		cellY = e.getY() / size;
		if(e.getX()%size == 0) cellX = e.getX()/size - 1;
		if(e.getY()%size == 0) cellY = (int) e.getY()/size - 1;
		
		map.toggle(cellX, cellY);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {	
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public void paintMapEditor(Graphics g){
		map.paintMap(g);
	}
}
