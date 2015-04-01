package tower;

/*
* ECSE 321
* Winter 2015
* Jenna Mar
* 260590119
*
* This class creates a basic Tower.
*/
import java.util.LinkedList;

public class NormalTower extends Tower{
	

	public NormalTower(int x, int y, LinkedList<Tower> towers){
		super(x,y, towers);
		//addTower();
	}

	/*
	//buy a tower
	public void addTower(){
		towers.add(this);
	}
	*/
}