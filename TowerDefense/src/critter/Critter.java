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

		//private Board gameBoard;
		private Path gamePath;
		private Board gameBoard;
		private Point endPoint;
		private int currPathIndex;
		private Point currPathCoord;
		private Point nextPathCoord;
		private int pathLength, xInt, yInt, x1, y1; //path length will depend upon the difficulty of the map. (the shorter the easier)
		private long DEFAULT_DELAY = 1000;//(subject to change)
		
		private Image appearance;
		private double speed; 
		private int health; 
		private int reward;
		private int damage;
		public Point position; //default init
		public boolean reachedGoal;
		
		public double x;
		public double y;
		
		
		public Critter(int speed, int health, int reward, int damage, ImageIcon appearance, Board gameBoard){
			this.gameBoard=gameBoard;
			this.speed = speed;
			this.health = health;
			this.reward = reward;
			this.damage = damage;
			this.appearance = appearance.getImage();
			//this.gameBoard = gameBoard;
			//System.out.println(gameBoard.toString());
			this.gamePath = gameBoard.getPath();
			//System.out.println("umm"+gamePath.print());
			this.reachedGoal = false;
			this.xInt = (int) gameBoard.getPath().getCoord(0).getX();
			System.out.println("somethinge");
			this.yInt = (int) gameBoard.getPath().getCoord(0).getY();
			this.position =new Point(xInt,yInt);
			this.pathLength = gamePath.length();
			x1 = (int) gameBoard.getPath().getEndCoord().getX();
			y1 = (int) gameBoard.getPath().getEndCoord().getY();
			this.endPoint = new Point(x1, y1);
			//this.position = startPoint; //change back to start point
		}
		
		//(each critter should look different)
		public void drawCritter(Graphics g){
			g.drawImage(appearance, (int)(this.x*Map.CELL_PIXEL_SIZE), (int)(this.y*Map.CELL_PIXEL_SIZE), null);
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

		public void increaseDifficulty(double multiplier){ //health and reward increase whenever multiplier is applied
			this.health*=multiplier;
			this.reward*=multiplier;
		}
		
		//centercoordinate 
		public void setDown() throws InterruptedException{
			int x1;
			int x2;
			int y1;
			int y2;
			
			while (health>0)
			{
				for (int j= 0; j< pathLength; j++)
				{	
					x1 = (int) gamePath.getCoord(j).getX();
					y1 = (int) gamePath.getCoord(j).getY();
					//Cannot access gamePath.getCoord(j+1) when at end of path
					if  (j==pathLength-1){
						Point p=gameBoard.getMap().getOffMapExit();
						x2=p.x;
						y2=p.y;
					}else{
						x2 = (int) gamePath.getCoord(j+1).getX();
						y2 = (int) gamePath.getCoord(j+1).getY();
					}
					
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
				
					if (this.position.getY() >= endPoint.getY() && this.position.getX() >= endPoint.getX())	{
						reachedGoal = true;
						System.out.println(this.toString() +" has reached the endpoint");
						Player.coins -= damage;
						System.out.println("Player lost "+ damage + " coins");
						break;
						}
				}
			}
		public void updatePosition(){
			if (currPathIndex==gamePath.length()-1){
				nextPathCoord=gameBoard.getMap().getOffMapExit();
			}else{
				nextPathCoord=gamePath.getCoord(currPathIndex+1);
			}
			double dx=(double)(nextPathCoord.x-currPathCoord.x);
			double dy=(double)(nextPathCoord.y-currPathCoord.y);
			
			//get new board position (double,double)
			x=x+(dx/Math.abs(dx))*speed;
			y=y+(dy/Math.abs(dy))*speed;
			
			if((int)(Math.floor(x))==nextPathCoord.x && (int)(Math.floor(y))==nextPathCoord.y){
				if(currPathIndex==gamePath.length()-1){
					reachedGoal = true;
					System.out.println(this.toString() +" has reached the endpoint");
					Player.coins -= damage;
					System.out.println("Player lost "+ damage + " coins");
				}else{
					currPathCoord=nextPathCoord;
					currPathIndex=currPathIndex+1;
				}
				
			}
			
		}
		

		public abstract boolean getShield();
		public abstract boolean getDirection();
		public abstract boolean getVisibility();
		public abstract String toString();
		
		

	
}
