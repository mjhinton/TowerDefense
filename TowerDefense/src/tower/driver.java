package tower;

/*
ECSE 321 Programming Assignment 1
Winter 2015
Jenna Mar
260590119
 */
import java.util.LinkedList;

public class driver {
	public static void main(String[] args) {

		Tower testBasic = new NormalTower(2, 3);
		Tower testFreezing = new FreezingTower(1, 1);
		Tower testMonster = new MonsterTower(5, 5);
		/*
		 * LinkedList<Tower> towerlist = new LinkedList<Tower>();
		 * towerlist.add(testBasic); towerlist.add(testFreezing);
		 * towerlist.add(testMonster);
		 */

		System.out.println("Placed 3 towers.\n");

		// add a list of critters on the map
		LinkedList<Critter> critters = new LinkedList<Critter>();
		Critter crit1 = new Critter(0, 0);
		Critter crit2 = new Critter(6, 8);
		critters.add(crit1);
		critters.add(crit2);

		// declare initial status
		System.out.println("Two critters have been deployed. Critter 1 has "
				+ crit1.hp + " health and critter 2 has " + crit2.hp + ".");

		/*
		 * all towers scan through and shoot for (Tower i : towerlist){
		 * i.inRange(critters); }
		 */

		testBasic.inRange(critters);

		System.out.println("The basic tower attacked the critters.");
		System.out.println("critter 1 " + crit1.HealthStatus() + ".");
		System.out.println("critter 2 " + crit2.HealthStatus() + ".\n");

		testFreezing.inRange(critters);

		System.out.println("The freezing tower attacked the critters.");
		System.out.println("critter 1 now has a speed value of " + crit1.speed
				+ ".");
		System.out.println("critter 2 now has a speed value of " + crit2.speed
				+ ".\n");

		testMonster.inRange(critters);

		System.out.println("The heavy tower attacked the critters.");
		System.out.println("critter 1 " + crit1.HealthStatus() + ".");
		System.out.println("critter 2 " + crit2.HealthStatus() + ".\n");

		System.out.println("The basic tower will now be sold for "
				+ testBasic.value + ".");
		testBasic.sellTower(testBasic);

		testFreezing.increaseLevel();
		System.out.println("Freezing tower was upgraded, new level is "
				+ testFreezing.level + " and new cost to upgrade further is "
				+ testFreezing.cost + ".");

	}
}