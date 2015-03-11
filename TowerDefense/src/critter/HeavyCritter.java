package critters;

//The heavy critter is stronger and harder to kill than the normal ones.
public class HeavyCritter extends Critter {

		public HeavyCritter(){
			super(3,10,20,20, false);
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
