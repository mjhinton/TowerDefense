package critter;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import map.*;
import model.Board;
import model.Game;
import presentation.View;

/*There are six kinds of critters so far; normal, shielded, smart, heavy, ghost, and monster.
 * We will later add more kinds of critters. All the values are subject to change.
 */
abstract public class Critter {
	
	public final static double STANDARD_SPEED=1.0*((double)View.TIMEOUT)/1000.0;


	int critterRef;
	private Path gamePath;
	private Board gameBoard;
	private int currPathIndex;
	private Point currPathCoord;
	private Point nextPathCoord;
	private boolean onPath;
	private int pathLength; 

	private Image appearance;
	private double speed;
	private double health;
	private int reward;
	private int damage;
	public boolean reachedGoal;
	private Game game;

	public double x;
	public double y;

	public Critter(double speedMultiplier, double health, int reward, int damage,
			ImageIcon appearance, Game game) {
		this.game=game;
		this.gameBoard = game.getBoard();
		this.speed = speedMultiplier*STANDARD_SPEED;
		this.health = health;
		this.reward = reward;
		this.damage = damage;
		this.onPath =false;
		this.appearance = appearance.getImage();
		this.gamePath = gameBoard.getPath();
		this.reachedGoal = false;
		this.pathLength = gamePath.length();
		this.nextPathCoord = gamePath.getCoord(0);
	}


	public void drawCritter(Graphics g) {
		if (this.onPath==true && this.health > 0 && this.reachedGoal == false) {
			int xScreen=(int) (this.x * Map.CELL_PIXEL_SIZE);
			int yScreen=(int) (this.y * Map.CELL_PIXEL_SIZE);
			g.drawImage(appearance, xScreen+this.getPixelOffset().x,
				yScreen+this.getPixelOffset().y, null);

		}
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(int i) {
		speed = i;
	}
	
	public void reduceSpeed(double reduction){
		speed*=reduction;
	}

	public double getHealth() {
		return health;
	}

	public int getWorth() {
		return reward;
	}

	public int getDamage() {
		return damage;
	}
	//Getter method for testing
	public Point getCurrPathCoord(){
		return currPathCoord;
	}
	//Getter method for testing purposes.
	public boolean getWhetherOnPath(){
		return onPath;
	}
	
	public double getX() {
		return nextPathCoord.getX();
	}
	public double getY(){
		return nextPathCoord.getY();
	}
	public boolean onPath(){
		return onPath;
	}

	public void getsHit(double hitDamage) {
		health=health-hitDamage;
		if (health <= 0) {
			health=0;
			onPath=false;
			game.changeCoins(reward);
			game.removeCritter(this);
		}	
	}

	public void setHealth(int h) {
		this.health = h;
	}

	public void setSpeed(double s) {
		this.speed = s;
	}

	public void increaseDifficulty(double multiplier) { // health and reward
														// increase whenever												// multiplier is applied
		this.health *= multiplier;
		this.reward *= 1.25;
	}

	// centercoordinate
	public void setDown() throws InterruptedException {
		currPathIndex=0;
		currPathCoord=gamePath.getCoord(0);
		x=(double)currPathCoord.x;
		y=(double)currPathCoord.y;
		onPath=true;
		//System.out.println(currPathCoord);
	}
	
	//Added for testing purposes
	public void setDownAtChosenIndex(int index){
		currPathIndex = index;
		currPathCoord = gamePath.getCoord(index);
		System.out.println(currPathCoord);
		x = (double)currPathCoord.x;
		y = (double)currPathCoord.y;
		//System.out.println("index" + index + " currPathCoord" + currPathCoord);
		onPath = true;
	}
	public void setCurrPathIndex(int index){
		currPathIndex = index;
	}
	////

	public void updatePosition() {
		if (this.onPath==true && this.health > 0 && this.reachedGoal == false) {
			if (currPathIndex == pathLength - 1) {
				nextPathCoord = gameBoard.getMap().getOffMapExit();
			} else {
				nextPathCoord = gamePath.getCoord(currPathIndex + 1);
			}
			double dx = (double) (nextPathCoord.x - currPathCoord.x);
			double dy = (double) (nextPathCoord.y - currPathCoord.y);

			// get new board position (double,double)
			if (dx!=0){
				x = x + (dx / Math.abs(dx)) * speed;
			}
			if (dy!=0){
				y = y + (dy / Math.abs(dy)) * speed;

			}
			
			double dxc=Math.abs(x-currPathCoord.x);
			double dyc=Math.abs(y-currPathCoord.y);
			
			boolean flag=(dxc>1 || dyc>1);
	
			if (flag) {
				if (currPathIndex == pathLength - 1) {
					reachedGoal = true;
					health=0;
					onPath=false;
					System.out.println(this.toString()
							+ " has reached the endpoint");
					System.out.println("Player lost " + damage + " health.");
					game.changeHealth(-1*damage);
					game.removeCritter(this);
					
				} else {
					currPathCoord = nextPathCoord;
					currPathIndex = currPathIndex + 1;
				}

			}
		}
	}
	
	//Added getter method for testing purposes
	public Point getCritterPosition(){
		Point c = new Point();
		c.setLocation(this.x, this.y);
		return c;
	}
	
	public void setRef(int i){
		critterRef = i;
	}
	
	public int getRef(){
		return critterRef;
	}
	

	public abstract boolean getShield();

	public abstract boolean getDirection();

	public abstract boolean getVisibility();

	public abstract String toString();
	
	public abstract Point getPixelOffset();

}
