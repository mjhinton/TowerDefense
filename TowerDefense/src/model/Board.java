package model;

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
		towers= new Tower[height][width];
		path=map.getPath();
	}
	
	public boolean addTower(Tower tower, Coord c){
		try {
			if(towers[c.row()][c.col()]==null){
				if (map.getCell(c) instanceof SceneryCell){
					towers[c.row()][c.col()]=tower;
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
	
	public boolean removeTower(Coord c){
		try {
			if(towers[c.row()][c.col()]!=null){
				towers[c.row()][c.col()]=null;
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
	
}
