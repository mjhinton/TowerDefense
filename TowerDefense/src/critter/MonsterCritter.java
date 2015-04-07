package critter;

import java.awt.Point;

import javax.swing.ImageIcon;

import model.Game;
/**
 * This class represents a sub-type of Critter, MonsterCritter, which has the highest amount of health, damage, and reward.
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */
public class MonsterCritter extends Critter{
	private static ImageIcon ii = new ImageIcon("lib/images/critter/ufo.png");
	private final static Point PIXEL_OFFSET=new Point (-20,-20);
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
	@Override
	public Point getPixelOffset() {
		return PIXEL_OFFSET;
	}
}