package tower;

/*
*ECSE 321 Programming Assignment 1
*Winter 2015
*Jenna Mar
*260590119
*
*This class creates a basic Tower.
*/

public class NormalTower extends Tower{
	

	public NormalTower(double x, double y){
		super(x,y);
		addTower();
	}

	//buy a tower
	public void addTower(){
		towers.add(this);
	}
}