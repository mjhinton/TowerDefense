package critter;

import javax.swing.ImageIcon;

//The smart critter will reverse directions to avoid attacks from towers (if there are "bullets" nearby)
//(this isn't entirely complete yet)
public class SmartCritter extends Critter{
	private boolean direction;
	private static ImageIcon ii = new ImageIcon("placeholderCritter.png");
		public SmartCritter(){
			super(5,5,15,10, ii);
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
}
