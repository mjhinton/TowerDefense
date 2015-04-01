package map;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapEditor extends Map implements MouseListener{

	int size = Map.CELL_PIXEL_SIZE;
	Point cell;
	int cellX;
	int cellY;
	MapCell selectedCell;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		cellX = e.getX() / size;
		cellY = e.getY() / size;
		if(e.getX()%size == 0) cellX = e.getX()/size - 1;
		if(e.getY()%size == 0) cellY = (int) e.getY()/size - 1;
		
		super.toggle(cellX, cellY);
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

}
