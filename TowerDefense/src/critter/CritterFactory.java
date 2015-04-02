package critter;

import model.Board;

public class CritterFactory {
	public static Critter spawn(String id, Board board){
		id = id.toLowerCase();
		if(id.equals("ghost")){
			return new GhostCritter(board);
		}
		else if(id.equals("heavy")){
			return new HeavyCritter(board);
		}
		else if(id.equals("monster")){
			return new MonsterCritter(board);
		}
		else if(id.equals("normal")){
			return new NormalCritter(board);
		}
		else if(id.equals("shielded")){
			return new ShieldedCritter(board);
		}
		else if(id.equals("smart")){
			return new SmartCritter(board);
		}
		return null;
	}
}
