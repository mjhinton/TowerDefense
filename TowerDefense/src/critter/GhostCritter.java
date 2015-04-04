package critter;


import java.awt.Point;

import javax.swing.ImageIcon;

import model.Game;

//Ghost critters blink in and out of visibility; the towers will bee able to see them only half of the time.
public class GhostCritter extends Critter{
	private boolean visible;
	private static ImageIcon ii = new ImageIcon("lib/images/critter/jellyfish.png");
	private final static Point PIXEL_OFFSET=new Point (5,5);
	
	public GhostCritter(Game game){
		super(1,5,25,10,ii, game);
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

	@Override
	public Point getPixelOffset() {
		return PIXEL_OFFSET;
	}

}