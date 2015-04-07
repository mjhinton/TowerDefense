package critter;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import map.Path;
import model.Board;
import model.Game;

//The smart critter will reverse directions to avoid attacks from towers (if there are "bullets" nearby)
//(this isn't entirely complete yet)
public class SmartCritter extends Critter{
	private boolean direction;
	int critterRef;
	private Path gamePath;
	private Board gameBoard;
	private int currPathIndex;
	private Point currPathCoord;
	private Point nextPathCoord;
	private boolean onPath;
	private int pathLength; 

	private double speed;
	private double health;
	private int reward;
	private int damage;
	public boolean reachedGoal;
	private Game game;

	public double x;
	public double y;
	private static ImageIcon ii = new ImageIcon("lib/images/critter/alien4.png");
	private final static Point PIXEL_OFFSET=new Point (5,5);
	
		public SmartCritter(Game game){
			super(1.8,4,20,10, ii, game);
			direction = true;
		}
		
		public boolean getShield(){
			return false;
		}
		
		public boolean getDirection(){
			//reverse with the direction if there are bullets nearby
			return direction;
		}
		
		public boolean getVisibility(){ 
			return true;
		}
		public String toString(){
			return "SmartCritter";
		}
		@Override
		public Point getPixelOffset() {
			return PIXEL_OFFSET;
		}
		
		
}
