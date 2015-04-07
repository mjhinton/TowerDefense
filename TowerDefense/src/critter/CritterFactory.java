package critter;

import model.Game;

/**
 * This class is responsible for instantiating the Critters.
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */

public class CritterFactory {
	public static Critter spawn(String id, Game game){
		id = id.toLowerCase();
		if(id.equals("ghost")){
			return new GhostCritter(game);
		}
		else if(id.equals("heavy")){
			return new HeavyCritter(game);
		}
		else if(id.equals("monster")){
			return new MonsterCritter(game);
		}
		else if(id.equals("normal")){
			return new NormalCritter(game);
		}
		else if(id.equals("shielded")){
			return new ShieldedCritter(game);
		}
		else if(id.equals("smart")){
			return new SmartCritter(game);
		}
		return null;
	}
}
