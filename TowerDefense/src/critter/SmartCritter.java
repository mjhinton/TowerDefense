package critter;

import javax.swing.ImageIcon;

import model.Board;
import model.Game;

//The smart critter will reverse directions to avoid attacks from towers (if there are "bullets" nearby)
//(this isn't entirely complete yet)
public class SmartCritter extends Critter{
	private boolean direction;
	private static ImageIcon ii = new ImageIcon("lib/images/critter/test_critter.png");
		public SmartCritter(Game game){
			super(2,5,15,10, ii, game);
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
