package critter;

import java.awt.Point;

import javax.swing.ImageIcon;

import model.Game;

/**
 * This class represents the ShieldedCritter, a sub-type of critter that is protected by a shield.
 * Once the shield is knocked off after being hit by enough bullets, the critter is able to move faster.
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */

public class ShieldedCritter extends Critter{
	private boolean shield;
	private static ImageIcon ii = new ImageIcon("lib/images/critter/crab.png");
	private final static Point PIXEL_OFFSET=new Point (5,5);
	
		public ShieldedCritter(Game game){
			super(0.7,5,15,7, ii, game);
			shield = true;
		}
		public boolean getShield(){
			return shield;
		}
		public boolean getDirection(){
			return true;
		}
		
		public boolean getVisibility(){
			return true;
		}
		public String toString(){
			return "ShieldedCritter";
		}

		/*
		 * This method knocks the shield off of the ShieldedCritter and allows it to move faster.
		 */
		public void knockOffShield(){
			setSpeed(4);
		}
		
		@Override
		public Point getPixelOffset() {
			return PIXEL_OFFSET;
		}
}
