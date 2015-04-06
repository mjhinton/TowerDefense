package tower;

/*
* ECSE 321 Introduction to Software Engineering
* Winter 2015
* Group 12
* 
* This is the main Tower class
*/
import java.util.*;

import critter.*;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import model.Game;
import common.*;
import map.Map;

public class Tower extends Subject{
	
	public int MAX_FIRE_INDEX=100;
	
	protected Game game;
	protected Point position;
	protected int size;
	protected int cost;
	protected int level;
	protected int value;
	protected int range;
	protected double bulletRange;
	protected double power;
	protected double fireRate;
	protected boolean isSpecial;
	protected double specialmod; //value determining amount of enemy attribute modification via special effects
	//protected Bullet bullet;
	//protected boolean activeBullet;
	//protected boolean bulletReached;
	protected boolean lowestHealth;
	protected boolean highestHealth;
	protected boolean closest;
	protected boolean farthest;
	protected int currFireIndex;
	protected double bulletSpeedMultiplier;
	
	public Tower(Point c, Game game){
		this.position = c;
		this.initAttr();
		this.game=game;
		this.targetLowestHealth();
	}

	//initialize default attributes
	public void initAttr(){
		size = 4; //number of coord blocks tower takes up
		cost = 100; //buying cost
		level = 1; //upgrade level
		value = (int) (cost * level * 0.6); //selling value
		range = 3; //range of tower
		bulletRange = 0; //range of bullet explosion
		power = 2; //power of bullets
		fireRate = 1; //rate of fire
		isSpecial = false; //if tower has special effects
		specialmod = 1;	 //special effect value	
		currFireIndex=0;
		bulletSpeedMultiplier=1;
	}

	//increase the level of the tower
	public void increaseLevel(){
		if (this.level < 5){
			cost += 120; //cost for next level
			value = (int) (cost * level * 0.6); //recalculate selling value
			fireRate *= 1.1;
			if (this.isSpecial == true){
				range++;
				specialmod += 0.1;
			}
			else {
				power *= 1.5; //increase power, etc.
			}
			level++;
			notifyObservers();
		}
		//these will no longer execute and are unnecessary.
				/*
		else if (this.level == 5){
			System.out.println("Maximum level reached. Further upgrade not possible.");
		}
		else{
			System.out.println("Insufficient funds. Upgrade of " + this.toString() + " failed.");
		}
		*/
	}
	
	public void fire(){
		//if(inRange==null && !activeBullet) bullet = null;
		
		ArrayList<Critter> inRange;
		Critter target;
		if (currFireIndex>=MAX_FIRE_INDEX && game.getWave()!=null){

			inRange=targetsInRange(game.getWave().getCritterBank());
		
			
			if(inRange.size()!=0){
					target=getTarget(inRange);
					shootBullet(target);	
			}
			currFireIndex=0;
		}else{
			currFireIndex=(int)(currFireIndex+fireRate*5);
		}	
	}
	public void shootBullet(Critter critter){
		Bullet bullet=new  Bullet(game, this, Map.getCenterX(critter.getX()), Map.getCenterY(critter.getY()), bulletSpeedMultiplier);
		
	}

	public ArrayList<Critter> targetsInRange(ArrayList<Critter> critters){
		ArrayList<Critter> inRange=new ArrayList<Critter>();
		for(int i = 0; i < critters.size(); i++){
			boolean flag=distanceSquared(Map.getCenterX(position.x), Map.getCenterY(position.y), Map.getCenterX(critters.get(i).getX()), Map.getCenterY(critters.get(i).getY()))<range*range;
			if(flag && critters.get(i).onPath()){
				inRange.add(critters.get(i));	
			}
		}
	return inRange;
	}
	public Critter getTarget(ArrayList<Critter> critters){
		if(lowestHealth) return lowestHealth(critters);
		else if(highestHealth) return highestHealth(critters);
		else if(closest) return closest(critters);
		else if(farthest) return lowestHealth(critters);
		else return null;
	}
	public Critter lowestHealth(ArrayList<Critter> input){
		int pointer = 0;
		int lowestHealth = 100000;
		for(int i = 0; i < input.size(); i++){
			if(input.get(i).getHealth()<lowestHealth){
				lowestHealth = input.get(i).getHealth();
				pointer = i;
			}
		}
		return input.get(pointer);
	}
	
	public Critter highestHealth(ArrayList<Critter> input){
		int pointer = 0;
		int highestHealth = 0;
		for(int i = 0; i < input.size(); i++){
			if(input.get(i).getHealth()>highestHealth){
				highestHealth = input.get(i).getHealth();
				pointer = i;
			}
		}
		return input.get(pointer);
	}
	
	public Critter closest(ArrayList<Critter> input){
		int pointer = 0;
		double closestDistanceSquared = 1000000;
		double currDistanceSquared;
		for(int i = 0; i < input.size(); i++){
			currDistanceSquared=distanceSquared(position.getX(), position.getY(), input.get(i).getX(), input.get(i).getY());
			if(currDistanceSquared<closestDistanceSquared){
				closestDistanceSquared = currDistanceSquared;
				pointer = i;
			}
		}
		return input.get(pointer);
	}
	
	public Critter farthest(ArrayList<Critter> input){
		int pointer = 0;
		double farthestDistanceSquared = 0;
		double currDistanceSquared;
		for(int i = 0; i < input.size(); i++){
			currDistanceSquared=distanceSquared(position.getX(), position.getY(), input.get(i).getX(), input.get(i).getY());
			if(currDistanceSquared>farthestDistanceSquared){
				farthestDistanceSquared = currDistanceSquared;
				pointer = i;
			}
		}
		return input.get(pointer);
	}
	
	public void targetLowestHealth(){
		lowestHealth = true;
		highestHealth = false;
		closest = false;
		farthest = false;
	}
	
	public void targetHighestHealth(){
		this.lowestHealth = false;
		this.highestHealth = true;
		this.closest = false;
		this.farthest = false;
	}
	
	public void targetClosest(){
		this.lowestHealth = false;
		this.highestHealth = false;
		this.closest = true;
		this.farthest = false;
	}
	
	public void targetFarthest(){
		this.lowestHealth = false;
		this.highestHealth = false;
		this.closest = false;
		this.farthest = true;
	}
	
	public String toString(){
		return("Tower at (" + position.getX() + ", " + position.getY() + ")");
	}
	
	public int getCost(){
		return this.cost;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public int getRange(){
		return this.range;
	}
	
	public double getPower(){
		return this.power;
	}
	
	public double getFireRate(){
		return this.fireRate;
	}
	
	public boolean getIsSpecial(){
		return this.isSpecial;
	}
	
	public double getSpecialmod(){
		return this.specialmod;
	}
	
	public int getSize(){
		return this.size;
	}

	public ImageIcon getIcon(){
		//empty!
		return null;
	}
	
	public Image getImage() {
		return null;
	}

	public Point getPosition() {
		return position;
	}
	
	public ImageIcon getBulletIcon(){
		return null;
	}
	
	public Image getBulletImage(){
		ImageIcon i;
		i = new ImageIcon("lib/images/projectiles/obj_snowball_15x15.png");
		Image i2 = i.getImage();
		return i2;
	}
	
	public Game getGame(){
		return game;
	}
	
	public static double distance(double x1, double y1, double x2, double y2){
		return Math.sqrt(Math.pow((y2-y1), 2)+Math.pow((x2-x1), 2));
	}
	public static double distanceSquared(double x1, double y1, double x2, double y2){
		return Math.pow((y2-y1), 2)+Math.pow((x2-x1), 2);
	}

	public double getBlastRadius() {
		return bulletRange;
	}
}
