package critter;

import java.util.concurrent.TimeUnit;
import map.*;
import player.Player;

/*There are six kinds of critters so far; normal, shielded, smart, heavy, ghost, and monster.
 * We will later add more kinds of critters. All the values are subject to change.
 */
abstract public class Critter {

		//import map to be used
		private static Map playermap;
	
		//changed some of these to non-static for ease of use, please change them as you like.
		final Coord STARTPOINT = new Coord(playermap.getStart().row(), playermap.getStart().col());
		static final int PATHLENGTH = Path.length(); //path length will depend upon the difficulty of the map. (the shorter the easier)
		final Coord ENDPOINT = new Coord(playermap.getEnd().row(), playermap.getEnd().col());
		static final long DEFAULT_DELAY = 1000;//(subject to change)
		
		private double speed; 
		private int health; 
		private int reward;
		private int damage;
		private boolean shield;
		public Coord position = new Coord(0,0); //default init
		public boolean reachedGoal;
		
		public Critter(int a, int b, int c, int d, boolean e){
			this.speed = a;
			this.health = b;
			this.reward = c;
			this.damage = d;
			this.shield = e;
			this.position = STARTPOINT; //change back to startpoint
			this.reachedGoal = false;
		}
		
		public double getSpeed(){
			return speed;
		}
		public int getHealth(){
			return health;
		}
		public int getWorth(){
			return reward;
		}
		public int getDifficulty(){
			return reward/5;
		}
		public int getDamage(){
			return damage;
		}
		public void getsHit(){
			if (health==0){
				Player.coins += reward;
				System.out.println("Critter dead");
				}
			else
				health--;		
		}
		public boolean getShield(){
			return shield;
		}
		
		public void setHealth(int h){
			this.health = h;
		}
		
		public void setSpeed(double s){
			this.speed = s;
		}

		public void setDown() throws InterruptedException{
			while (health>0)
			{
				TimeUnit.MILLISECONDS.sleep(DEFAULT_DELAY);
				this.position += speed;
				
				System.out.println(this.toString() + " is moving");
				
				if (this.position.row() >= ENDPOINT.row() && this.position.col() >= ENDPOINT.col())
					{
					reachedGoal = true;
					System.out.println(this.toString() +" has reached the endpoint");
					Player.coins -= damage;
					System.out.println("Player lost "+ damage + " coins");
					break;
					}
			}
		}
		public abstract boolean getDirection();
		public abstract boolean getVisibility();
		public abstract String toString();
		
		

	
}
