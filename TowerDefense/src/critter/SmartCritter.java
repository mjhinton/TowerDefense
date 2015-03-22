package critter;

//The smart critter will reverse directions to avoid attacks from towers (if there are "bullets" nearby)
//(this isn't entirely complete yet)
public class SmartCritter extends Critter{
	private boolean direction;
	
		public SmartCritter(){
			super(6,5,15,10);
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
