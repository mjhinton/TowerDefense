package critter;

//The shielded critter is slightly slower than the normal critter, and more difficult to kill. Towers must have stronger attacks to damage 
//these critters.
public class ShieldedCritter extends Critter{
private boolean shield;
		public ShieldedCritter(){
			super(4,5,10,7);
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
		
}
