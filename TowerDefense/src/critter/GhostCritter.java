package critter;


import java.awt.Point;

import javax.swing.ImageIcon;

import model.Game;

/**
 * This class represents a sub-type of Critter, the Ghost Critter.
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */

public class GhostCritter extends Critter{
	private boolean visible;
	private static ImageIcon ii = new ImageIcon("lib/images/critter/jellyfish.png");
	private final static Point PIXEL_OFFSET=new Point (5,5);
	
	public GhostCritter(Game game){
		super(1,5,15,10,ii, game);
		visible = true;
	}
	
	public boolean getDirection(){
		return true;
	}
	
	public boolean getShield(){
		return false;
	}

	public boolean getVisibility(){
		visible = !visible;
		return visible;
	}
	public String toString(){
		return "GhostCritter";
	}

	@Override
	public Point getPixelOffset() {
		return PIXEL_OFFSET;
	}

}