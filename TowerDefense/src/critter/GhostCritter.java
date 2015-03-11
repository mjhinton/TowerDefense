package critters;

//Ghost critters blink in and out of visibility; the towers will bee able to see them only half of the time.
public class GhostCritter extends Critter{
	private boolean visible = true;
	
	public GhostCritter(){
		super(5,5,25,10, false);
	}
	
	public boolean getDirection(){
		return true;
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