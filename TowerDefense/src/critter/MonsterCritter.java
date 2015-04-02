package critter;

import javax.swing.ImageIcon;

import model.Board;

//Monster critter is just a super fast, super strong critter.
public class MonsterCritter extends Critter{
	private static ImageIcon ii = new ImageIcon("test_critter.png");
	public MonsterCritter(Board board){
		super(4,30,30,30, ii, board);
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