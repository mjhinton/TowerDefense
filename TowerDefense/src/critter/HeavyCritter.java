package critter;

import javax.swing.ImageIcon;

//The heavy critter is stronger and harder to kill than the normal ones.
public class HeavyCritter extends Critter {
	
	private static ImageIcon ii = new ImageIcon("test_critter.png");
		public HeavyCritter(){
			super(2,10,20,20, ii);
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
