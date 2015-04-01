package critter;

public class CritterFactory {
	public static Critter spawn(String id){
		id = id.toLowerCase();
		if(id.equals("ghost")){
			return new GhostCritter();
		}
		else if(id.equals("heavy")){
			return new HeavyCritter();
		}
		else if(id.equals("monster")){
			return new MonsterCritter();
		}
		else if(id.equals("normal")){
			return new NormalCritter();
		}
		else if(id.equals("shielded")){
			return new ShieldedCritter();
		}
		else if(id.equals("smart")){
			return new SmartCritter();
		}
		return null;
	}
}
