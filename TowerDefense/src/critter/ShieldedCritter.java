package critter;

import javax.swing.ImageIcon;

import model.Board;

//The shielded critter is slightly slower than the normal critter, and more difficult to kill. Towers must have stronger attacks to damage 
//these critters.
public class ShieldedCritter extends Critter{
	private boolean shield;
	private static ImageIcon ii = new ImageIcon("test_critter.png");
	
		public ShieldedCritter(Board board){
			super(0.7,5,10,7, ii, board);
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

		public void knockOffShield(){
			//once the shield gets knocked off, the critter is faster.
			setSpeed(4);
		}
}
