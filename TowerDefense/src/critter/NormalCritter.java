package critters;

//The normal critter is the default critter, smallest reward, easiest to kill.
public class NormalCritter extends Critter{
		
		public NormalCritter(){
			super(5,5,5,5,false);
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
