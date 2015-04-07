package critter;

import java.awt.Point;

import javax.swing.ImageIcon;

import model.Game;

/**
 * The SmartCritter is a sub-type of Critter that moves towards the end point 3x faster when it senses that there is a bullet near it.
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */

public class SmartCritter extends Critter{
	
	private boolean direction;
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
