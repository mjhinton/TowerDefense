package critter;

import java.awt.Point;

import javax.swing.ImageIcon;
import model.Game;
/**
 * This class represents a sub-type of Critter, the HeavyCritter, which is slow, has a lot of health points, and has a high reward
 * relative to the Normal Critter.
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */

public class HeavyCritter extends Critter {
	
	private static ImageIcon ii = new ImageIcon("lib/images/critter/squid.png");
	private final static Point PIXEL_OFFSET=new Point (5,5);
	
		public HeavyCritter(Game game){
			super(0.5,10,20,20, ii, game);
		}
		public boolean getShield(){
			return false;
		}
		public boolean getDirection(){
			return true;
		}
		public boolean getVisibility(){
			return true;
		}
		public String toString(){
			return "HeavyCritter";
		}
		@Override
		public Point getPixelOffset() {
			return PIXEL_OFFSET;
		}

}
