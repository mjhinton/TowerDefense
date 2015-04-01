package presentation;

/* 
* ECSE 321
* Winter 2015 
* 260590119
* Jenna Mar
* 
* This class gives update data when a tower's level is increased.
*/

import common.*;
import tower.*;

public class LevelUpEvents implements IObserver{
	private Tower observedTower;

	public LevelUpEvents(Tower observedTower) {
		super();
		this.observedTower = observedTower;
	}

	@Override
	public void update() {
		int newLevel = observedTower.getLevel();
		int newCost = observedTower.getCost();
		int newValue = observedTower.getValue();
		double newFireRate = observedTower.getFireRate();
		
		System.out.println("The new level of the " + observedTower.toString() + " is: " + newLevel + ".");
		System.out.print("This means that the new ");
		
		//print special changed values if the tower has special properties
		if (observedTower.getIsSpecial()){
			double newSpecial = observedTower.getSpecialmod();
			int newRange = observedTower.getRange();
			System.out.print("range is " + newRange + ", the new slowing rate is " + newSpecial);
		}
		//otherwise, the tower's power is upgraded so print accordingly
		else {
			double newPower = observedTower.getPower();
			System.out.print("damaging power is " + newPower);
		}

		//print what has changed for all with the upgrade
		System.out.println(", the new cost is " + newCost + ", the new selling value is " + newValue + 
				", and the new fire rate is " + newFireRate + ".");
	}
}
