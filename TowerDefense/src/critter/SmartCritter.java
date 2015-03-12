package critter;

//The smart critter will reverse directions to avoid attacks from towers (if there are "bullets" nearby)
//(this isn't entirely complete yet)
public class SmartCritter extends Critter{

		public SmartCritter(){
			super(6,5,15,10, false);
		}
		
		public boolean getDirection(){
			//return false if there is a bullets nearby ;
			//otherwise,
			return true;
		}
		
		public boolean getVisibility(){ 
			return true;
		}
		public String toString(){
			return "SmartCritter";
		}
}
