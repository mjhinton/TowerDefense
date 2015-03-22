package critter;

//Monster critter is just a super fast, super strong critter.
public class MonsterCritter extends Critter{
	public MonsterCritter(){
		super(7,30,30,30);
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
		return "MonsterCritter";
	}
}