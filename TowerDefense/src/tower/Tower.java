package tower;

/*
*ECSE 321 Programming Assignment 1
*Winter 2015
*Jenna Mar
*260590119
* This is the main Tower class
*/
import java.util.*;

public class Tower {

	protected LinkedList<Tower> towers;
	protected double pos_X;
	protected double pos_Y;
	protected double size;
	protected int cost;
	protected int level;
	protected int value;
	protected int range;
	protected int bulletRange;
	protected double power;
	protected double fireRate;
	protected boolean special;
	protected double specialmod; //value determining amount of enemy attribute modification via special effects

	public Tower(double x, double y){
		pos_X = x;
		pos_Y = y;
		this.initAttr();
		towers = new LinkedList<Tower>();
	}

	//sell a specified tower
	public void sellTower(Tower input){
		towers.remove(input);
	}

	//initialize default attributes
	public void initAttr(){
		size = 1; //size of tower
		cost = 100; //buying cost
		level = 1; //upgrade level
		value = (int) (cost * level * 0.6); //selling value
		range = 3; //range of tower
		bulletRange = 1; //range of bullet explosion
		power = 1; //power of bullets
		fireRate = 1; //rate of fire
		special = false; //if tower has special effects
		specialmod = 1;	 //special effect value	
	}

	//increase the level of the tower
	public void increaseLevel(){
		cost += 100*1.2; //cost for next level
		value = (int) (cost * level * 0.6); //recalculate selling value
		power *= 1.5; //increase power, etc.
		fireRate *= 1.2;
		level++;
	}

	//check to see if critter is in range
	//Shoot at the closest critter in range
	public void inRange(LinkedList<Critter> critters){
		LinkedList<Critter> nearbyCritters = new LinkedList<Critter>();
		Critter closest = null;

		//check list of critters
		for (Critter i : critters){
			//find out which critter is the closest out of the critters in range
			//using the distance formula			
			if (closest != null && Math.sqrt(Math.pow((i.pos_X - this.pos_X),2) + Math.pow((i.pos_Y - this.pos_Y),2)) <= 
				Math.sqrt(Math.pow((closest.pos_X - this.pos_X),2) + Math.pow((closest.pos_Y - this.pos_Y),2))) { 				
				closest = i;
			}

			else if (i.pos_X <= this.pos_X + this.range && i.pos_Y <= this.pos_Y + this.range){
				closest = i;
			}
		}
		//check critters in the bullet's range and add to a new linked list
		for (Critter k : critters){
			if (closest != null && (k.pos_X <= (closest.pos_X + this.bulletRange) && 
				k.pos_Y <= (closest.pos_Y + this.bulletRange))){
				
				nearbyCritters.add(k);
			}
		}
		//inflict bullet effect on the critters in bullet range		
		if (nearbyCritters != null){
			fire(nearbyCritters);
		}
	}

	//point tower bullet towards critter coordinate, inflict damage
	//if tower is special (slowing) type, change enemy speed
	//return true if the function was successful
	public boolean fire(LinkedList<Critter> enemies){
		
		for (Critter j : enemies){
			if (this.special == false){
				j.hp -= this.power;
			}
			else {
				j.speed *=  specialmod;
			}
		}
		return true;	
	}

}