package critter;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import map.*;
import model.Board;
import model.Game;
import player.Player;
import presentation.View;

/*There are six kinds of critters so far; normal, shielded, smart, heavy, ghost, and monster.
 * We will later add more kinds of critters. All the values are subject to change.
 */
abstract public class Critter {
	
	final static double STANDARD_SPEED=0.2*(1000/View.TIMEOUT);

	// private Board gameBoard;
	private Path gamePath;
	private Board gameBoard;
	private int currPathIndex;
	private Point currPathCoord;
	private Point nextPathCoord;
	private boolean onPath;
	private int pathLength; // path length will depend upon
												// the difficulty of the map.
												// (the shorter the easier)

	private Image appearance;
	private double speed;
	private int health;
	private int reward;
	private int damage;
	public boolean reachedGoal;
	private Game game;

	public double x;
	public double y;

	public Critter(double speedMultiplier, int health, int reward, int damage,
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
	}

	// (each critter should look different)
	public void drawCritter(Graphics g) {
		if (this.onPath==true && this.health > 0 && this.reachedGoal == false) {
			g.drawImage(appearance, (int) (this.x * Map.CELL_PIXEL_SIZE),
				(int) (this.y * Map.CELL_PIXEL_SIZE), null);
		}
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(int i) {
		speed = i;
	}

	public int getHealth() {
		return health;
	}

	public int getWorth() {
		return reward;
	}

	public int getDamage() {
		return damage;
	}

	public double getX() {
		return x;
	}
	public double getY(){
		return y;
	}
	public boolean onPath(){
		return onPath;
	}

	public void getsHit(int hitDamage) {
		health=health-hitDamage;
		if (health <= 0) {
			health=0;
			onPath=false;
			game.changeCoins(reward);
			System.out.println("Critter dead");
		}	
	}

	public void setHealth(int h) {
		this.health = h;
	}

	public void setSpeed(double s) {
		this.speed = s;
	}

	public void increaseDifficulty(double multiplier) { // health and reward
														// increase whenever
														// multiplier is applied
		this.health *= multiplier;
		this.reward *= multiplier;
	}

	// centercoordinate
	public void setDown() throws InterruptedException {
		currPathIndex=0;
		currPathCoord=gamePath.getCoord(0);
		x=(double)currPathCoord.x;
		y=(double)currPathCoord.y;
		onPath=true;
	}

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
			x = x + (dx / Math.abs(dx)) * speed;
			y = y + (dy / Math.abs(dy)) * speed;

			if ((int) (Math.floor(x)) == nextPathCoord.x
					&& (int) (Math.floor(y)) == nextPathCoord.y) {
				if (currPathIndex == pathLength - 1) {
					reachedGoal = true;
					health=0;
					onPath=false;
					System.out.println(this.toString()
							+ " has reached the endpoint");
					Player.coins -= damage;
					System.out.println("Player lost " + damage + " coins");
				} else {
					currPathCoord = nextPathCoord;
					currPathIndex = currPathIndex + 1;
				}

			}
		}

	}

	public abstract boolean getShield();

	public abstract boolean getDirection();

	public abstract boolean getVisibility();

	public abstract String toString();

}
