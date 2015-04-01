package tower;

import java.awt.Point;
import java.util.LinkedList;

import critter.Critter;

public class Bullet extends Tower{

	//private char bulletType; TODO
	
	private Point position;
	private double velocity;
	private double velocity_X;
	private double velocity_Y;
	private double theta;
	
	public Bullet(double x, double y, LinkedList<Tower> towerlist){
		super((int) x, (int) y, towerlist);
		//bulletType = super.getTowerType(); TODO //note: no need for polymorphism as no significant differences between bullet behaviors TODO: need to assign sprite based on this type
		this.position.setLocation(super.position.getX(), super.position.getY());
		
		velocity = 40; //TODO: decide on an appropriate velocity
		//TODO: implement making sprite display
	}
	//TODO: make move bullet refresh the position of the critter between every call
	public void moveBullet(LinkedList<Critter> enemies){ //defines potential movement directions of bullet
		theta = Math.atan2(enemies.peek().position.getY()-this.position.getY(), enemies.peek().position.getX()-this.position.getX());
		velocity_X = velocity * Math.cos(theta);
		velocity_Y = velocity * Math.sin(theta);
		
		if(this.position.getX() + velocity_X > enemies.get(0).position.getX() && this.position.getY() + velocity_Y > enemies.get(0).position.getY()) {
			damageEnemies(enemies);
			super.bulletHasReached = true;
		}
		
		this.position.setLocation(this.position.getX()+velocity_X, this.position.getY()+velocity_Y);
	}
	
	public void damageEnemies(LinkedList<Critter> enemies){
		for(Critter j : enemies){
			if (super.isSpecial == false){
				j.setHealth((int) (j.getHealth() - super.power));
			}
			else {
				j.setSpeed(j.getSpeed() * super.specialmod);
			}
		}
	}
}
