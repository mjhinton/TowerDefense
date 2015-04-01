package critter;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.concurrent.TimeUnit;
import static java.lang.Math.abs;

import javax.swing.ImageIcon;

import map.*;
import model.Board;
import player.Player;

/*There are six kinds of critters so far; normal, shielded, smart, heavy, ghost, and monster.
 * We will later add more kinds of critters. All the values are subject to change.
 */
abstract public class Critter {

		private static Board gameBoard;
		private static Path gamePath = gameBoard.getPath();
		//changed some of these to non-static for ease of use, please change them as you like.
		static final Point STARTPOINT = new Point(gamePath.getCoord(0).col(), gamePath.getCoord(0).row());
		static final int PATHLENGTH = Path.length(); //path length will depend upon the difficulty of the map. (the shorter the easier)
		static final Point ENDPOINT = new Point(gamePath.getEndCoord().col(), gamePath.getEndCoord().row());
		static final long DEFAULT_DELAY = 1000;//(subject to change)
		
		private Image appearance;
		private double speed; 
		private int health; 
		private int reward;
		private int damage;
		public Point position = new Point(); //default init
		public boolean reachedGoal;
		
		public Critter(int speed, int health, int reward, int damage, ImageIcon appearance){
			this.speed = speed;
			this.health = health;
			this.reward = reward;
			this.damage = damage;
			this.appearance = appearance.getImage();
			this.position = STARTPOINT; //change back to start point
			this.reachedGoal = false;
		}
		
		//(each critter should look different)
		public void drawCritter(Graphics g){
			//g.drawImage(appearance, (int)position.getX(), (int)position.getY(), SomeSortofImageObserver);
		}
		
		public double getSpeed(){
			return speed;
		}
		
		public void setSpeed(int i){
			speed = i; 
		}
		
		public int getHealth(){
			return health;
		}
		
		public int getWorth(){
			return reward;
		}
		
		public int getDamage(){
			return damage;
		}
		
		public Point getPosition(){
			return position;
		}
		
		public void getsHit(){
			if (health==0){
				Player.coins += reward;
				System.out.println("Critter dead");
				}
			else
				health--;		
		}
		
		
		public void setHealth(int h){
			this.health = h;
		}
		
		public void setSpeed(double s){
			this.speed = s;
		}
		
		
		//WE NEED TO DECIDE ON COORDINATES; COORD, or POINT?
		public void setDown() throws InterruptedException{
			while (health>0)
			{
				for (int j= 0; j< Path.length(); j++)
				{	
					int x1 = (int) gamePath.getCoord(j).col();
					int y1 = (int) gamePath.getCoord(j).row();
					int x2 = (int) gamePath.getCoord(j+1).col();
					int y2 = (int) gamePath.getCoord(j+1).row();
					
					for (int k = 0; k<((double)abs(x2-x1)/speed)+1; k++){
						if (x2>x1){
							position.setLocation(x1+=speed, y1); 
							if (x2<=x1)
								position.setLocation(x2, y1);
						}
						else if (x1>x2){
							position.setLocation(x1-=speed,y1);
							if (x1<=x2)
								position.setLocation(x2, y1);
						}
						else if (y2>y1){
							position.setLocation(x1,y1+=speed);
							if (y1<=y2)
								position.setLocation(x1, y2);
						}
						else if (y1>y2){
							position.setLocation(x1,y1-=speed);
							if (y2<=y1)
								position.setLocation(x1, y2);
						}
					}
				}

					System.out.println(this.toString() + " is moving");
				
					if (this.position.getY() >= ENDPOINT.getY() && this.position.getX() >= ENDPOINT.getX())	{
						reachedGoal = true;
						System.out.println(this.toString() +" has reached the endpoint");
						Player.coins -= damage;
						System.out.println("Player lost "+ damage + " coins");
						break;
						}
				}
			}
		

		public abstract boolean getShield();
		public abstract boolean getDirection();
		public abstract boolean getVisibility();
		public abstract String toString();
		
		

	
}
