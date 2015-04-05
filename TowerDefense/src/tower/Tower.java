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

public class Tower extends Subject{
	
	public int MAX_FIRE_INDEX=100;
	
	protected Game game;
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
	protected Bullet bullet;
	protected boolean activeBullet;
	protected boolean bulletReached;
	protected ArrayList<Critter> inRange = new ArrayList<Critter>();
	protected boolean lowestHealth;
	protected boolean highestHealth;
	protected boolean closest;
	protected boolean farthest;
	protected int currFireIndex;
	
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
		bulletRange = 1; //range of bullet explosion
		power = 1; //power of bullets
		fireRate = 1; //rate of fire
		isSpecial = false; //if tower has special effects
		specialmod = 1;	 //special effect value	
		currFireIndex=0;
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
		if(inRange==null && !activeBullet) bullet = null;
		
		if (currFireIndex>=MAX_FIRE_INDEX){
			targetsInRange(game.getWave().getCritterBank());
		
			if(inRange!=null){
				if(activeBullet&&inRange.size()!=0) bullet.moveBullet(inRange);
				else{ 
					bullet = new Bullet(this.position, game);
					bulletReached = false;
					activeBullet = true;
				}
		}
			
			currFireIndex=0;
		}else{
			currFireIndex=(int)(currFireIndex+fireRate*5);
		}
		
		
	}

	public void targetsInRange(ArrayList<Critter> critters){
		OUTER: for(int i = 0; i < critters.size(); i++){
				if(distance(position.getX(), position.getY(), critters.get(i).getX(), critters.get(i).getY())<range){
					if(inRange!=null){
						for(int j = 0; j <inRange.size(); j++) if(critters.get(i).getRef()==inRange.get(j).getRef()) continue OUTER;
					}
					inRange.add(critters.get(i));
				}
		}
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
		this.lowestHealth = true;
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
		// TODO Auto-generated method stub
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
	
	public Bullet getBullet(){
		return bullet;
	}
	
	public boolean hasActiveBullet(){
		return activeBullet;
	}
	
	public Game getGame(){
		return game;
	}
	
	public double distance(double x1, double y1, double x2, double y2){
		return Math.sqrt(Math.pow((y2-y1), 2)+Math.pow((x2-x1), 2));
	}
	public double distanceSquared(double x1, double y1, double x2, double y2){
		return Math.pow((y2-y1), 2)+Math.pow((x2-x1), 2);
	}
}
