package critter;

import java.awt.Point;

import javax.swing.ImageIcon;

import model.Game;

//The normal critter is the default critter, smallest reward, easiest to kill.
public class NormalCritter extends Critter{
	private static ImageIcon ii = new ImageIcon("lib/images/critter/alien6.png");
	private final static Point PIXEL_OFFSET=new Point (5,5);
		public NormalCritter(Game game){
			super(1,5,5,5, ii, game);
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
			return "NormalCritter";
		}
		@Override
		public Point getPixelOffset() {
			return PIXEL_OFFSET;
		}
}
