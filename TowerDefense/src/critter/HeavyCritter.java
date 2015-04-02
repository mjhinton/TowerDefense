package critter;

import javax.swing.ImageIcon;

import model.Board;

//The heavy critter is stronger and harder to kill than the normal ones.
public class HeavyCritter extends Critter {
	
	private static ImageIcon ii = new ImageIcon("test_critter.png");
		public HeavyCritter(Board board){
			super(0.5,10,20,20, ii, board);
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

}
