package critter;

import javax.swing.ImageIcon;

//The normal critter is the default critter, smallest reward, easiest to kill.
public class NormalCritter extends Critter{
	private static ImageIcon ii = new ImageIcon("placeholderCritter.png");
		public NormalCritter(){
			super(3,5,5,5, ii);
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
}
