package critter;

import javax.swing.ImageIcon;

import model.Board;
import model.Game;

//Monster critter is just a super fast, super strong critter.
public class MonsterCritter extends Critter{
	private static ImageIcon ii = new ImageIcon("test_critter.png");
	public MonsterCritter(Game game){
		super(0.8,30,30,30, ii, game);
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