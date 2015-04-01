package model;

import java.awt.Graphics;
import java.awt.Point;

import tower.Tower;
import map.*;

public class Board {

	private Map map;
	private Tower[][] towers;
	private int width;
	private int height;
	private Path path;
	
	public Board(Map mapInput){
		this.map=mapInput;
		width=map.getWidth();
		height=map.getHeight();
		towers= new Tower[width][height];
		path=map.getPath();
	}
	
	public boolean addTower(Tower tower, Point c){
		try {
			if(towers[c.x][c.y]==null){
				if (map.getCell(c) instanceof SceneryCell){
					towers[c.x][c.y]=tower;
					return true;
				}else{
					System.err.println("Tower already exists at this location.");
					return false;
				}
			}else{
				System.err.println("Tower already exists at this location.");
				return false;
			}
		}catch (IndexOutOfBoundsException e){
			System.err.println("IndexOutOfBoundsException: " + e.getMessage());
			return false;
		}
	}
	
	public boolean removeTower(Point c){
		try {
			if(towers[c.x][c.y]!=null){
				towers[c.x][c.y]=null;
				return true;
			}else{
				System.err.println("No tower exists at this location.");
				return false;
			}
		}catch (IndexOutOfBoundsException e){
			System.err.println("IndexOutOfBoundsException: " + e.getMessage());
			return false;
		}
	}
	
	public Path getPath(){
		return path;
	}

	public void paintBoard(Graphics g) {
		map.paintMap(g);
		for (int i=0;i<width;i++){
			for (int j=0; j<height; j++){
				if (towers[i][j]!= null){
					g.drawImage(towers[i][j].getImage(), i * Map.CELL_PIXEL_SIZE, j
							* Map.CELL_PIXEL_SIZE, null);
					;
				}
			}
		}
		
	}
	
}
