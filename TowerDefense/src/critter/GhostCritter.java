package critter;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import model.Board;

//Ghost critters blink in and out of visibility; the towers will bee able to see them only half of the time.
public class GhostCritter extends Critter{
	private boolean visible;
	private static ImageIcon ii = new ImageIcon("test_critter.png");
	
	public GhostCritter(Board board){
		super(1,5,25,10,ii, board);
		visible = true;
	}
	
	public boolean getDirection(){
		return true;
	}
	
	public boolean getShield(){
		return false;
	}
	//every time visibility is called, it switches between being visible and not. Again, subject to change
	public boolean getVisibility(){
		visible = !visible;
		return visible;
	}
	public String toString(){
		return "GhostCritter";
	}

}