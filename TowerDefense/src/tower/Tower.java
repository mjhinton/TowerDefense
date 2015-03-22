package tower;

/*
* ECSE 321 Programming Assignment 1
* Winter 2015
* Jenna Mar
* 260590119
* This is the main Tower class
*/
import java.util.*;
import critter.*;
import java.awt.Point;
import player.Player;

public class Tower {

	protected LinkedList<Tower> towers;
	protected Point position;
	protected int size;
	protected int cost;
	protected int level;
	protected int value;
	protected int range;
	protected int bulletRange;
	protected double power;
	protected double fireRate;
	protected boolean isSpecial;
	protected double specialmod; //value determining amount of enemy attribute modification via special effects

	public Tower(int x, int y){
		position = new Point(x,y);
		this.initAttr();
		towers = new LinkedList<Tower>();
	}

	//sell a specified tower
	public void sellTower(Tower input){
		Player.coins += input.value;
		towers.remove(input);
	}

	//initialize default attributes
	public void initAttr(){
		size = 1; //number of coord blocks tower takes up
		cost = 100; //buying cost
		level = 1; //upgrade level
		value = (int) (cost * level * 0.6); //selling value
		range = 3; //range of tower
		bulletRange = 1; //range of bullet explosion
		power = 1; //power of bullets
		fireRate = 1; //rate of fire
		isSpecial = false; //if tower has special effects
		specialmod = 1;	 //special effect value	
	}

	//increase the level of the tower
	public void increaseLevel(){
		if (Player.coins >= this.cost){
			
			cost += 100*1.2; //cost for next level
			value = (int) (cost * level * 0.6); //recalculate selling value
			fireRate *= 1.2;
			if (this.isSpecial == true){
				range++;
			}
			else {
				power *= 1.5; //increase power, etc.
			}
			level++;
			Player.coins -= this.cost;
		}
		else {
			System.out.println("Insufficient funds. Upgrade failed.");
		}
	}

	//check to see if critter is in range
	//Shoot at the closest critter in range
	//otherwise print message no critters found
	public void inRange(LinkedList<Critter> critters){
		LinkedList<Critter> nearbyCritters = new LinkedList<Critter>();
		Critter closest = null;

		//check list of critters
		for (Critter i : critters){
			//find out which critter is the closest out of the critters in range
			//using the distance formula			
			if (closest != null && Math.sqrt(Math.pow((i.position.getX() - this.position.getX()),2) + 
					Math.pow((i.position.getY() - this.position.getY()),2)) <= Math.sqrt(Math.pow((closest.position.getX() - 
					this.position.getX()),2) + Math.pow((closest.position.getY() - this.position.getY()),2))) { 				
				closest = i;
			}

			else if (i.position.getX() <= this.position.getX() + this.range && i.position.getY() <= this.position.getY() + this.range){
				closest = i;
			}
		}
		//check critters in the bullet's range and add to a new linked list
		for (Critter k : critters){
			if (closest != null && (k.position.getX() <= (closest.position.getX() + this.bulletRange) && 
				k.position.getY() <= (closest.position.getY() + this.bulletRange))){
				
				nearbyCritters.add(k);
			}
		}
		//inflict bullet effect on the critters in bullet range		
		if (nearbyCritters != null){
			fire(nearbyCritters);
		}
		else {
			System.out.println("There were no Critters in range...");
		}
	}

	//point tower bullet towards critter coordinate, inflict damage
	//if tower is special (slowing) type, change enemy speed
	//return true if the function was successful
	public boolean fire(LinkedList<Critter> enemies){
		
		for (Critter j : enemies){
			if (this.isSpecial == false){
				j.setHealth((int) (j.getHealth() - this.power));
			}
			else {
				j.setSpeed(j.getSpeed() * specialmod);
			}
		}
		return true;	
	}
}