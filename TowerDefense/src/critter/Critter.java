package critter;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import map.*;
import model.Board;
import model.Game;
import presentation.View;

/**
 * This class allows the creation and manipulation of six different kinds of
 * critters; NormalCritters, ShieldedCritters, HeavyCritters, GhostCritters, and
 * MonsterCritters
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */

abstract public class Critter {

	public final static double STANDARD_SPEED = 1.0 * ((double) View.TIMEOUT) / 1000.0;

	int critterRef;
	private Path gamePath;
	private Board gameBoard;
	private int currPathIndex;
	private Point currPathCoord;
	private Point nextPathCoord;
	private boolean onPath;
	private int pathLength;
	private boolean isNearBullet;

	private Image appearance;
	private double speed;
	private double health;
	private int reward;
	private int damage;
	public boolean reachedGoal;
	private Game game;

	public double x;
	public double y;

	public Critter(double speedMultiplier, double health, int reward,
			int damage, ImageIcon appearance, Game game) {
		this.game = game;
		this.gameBoard = game.getBoard();
		this.speed = speedMultiplier * STANDARD_SPEED;
		this.health = health;
		this.reward = reward;
		this.damage = damage;
		this.onPath = false;
		this.appearance = appearance.getImage();
		this.gamePath = gameBoard.getPath();
		this.reachedGoal = false;
		this.pathLength = gamePath.length();
		this.nextPathCoord = gamePath.getCoord(0);
	}

	public void drawCritter(Graphics g) {
		if (this.onPath == true && this.health > 0 && this.reachedGoal == false) {
			int xScreen = (int) (this.x * Map.CELL_PIXEL_SIZE);
			int yScreen = (int) (this.y * Map.CELL_PIXEL_SIZE);
			g.drawImage(appearance, xScreen + this.getPixelOffset().x, yScreen
					+ this.getPixelOffset().y, null);

		}
	}

	/**
	 * This method deals a certain amount of damage to a Critter, and removes
	 * the Critter from the game and rewards the Player if the Critter is
	 * killed.
	 * 
	 * @param hitDamage
	 */

	public void getsHit(double hitDamage) {
		health = health - hitDamage;
		if (health <= 0) {
			health = 0;
			onPath = false;
			game.changeCoins(reward);
			game.removeCritter(this);
		}
	}

	/**
	 * This method increases the difficulty of a critter by increasing its
	 * health and reward.
	 * 
	 * @param multiplier
	 */

	public void increaseDifficulty(double multiplier) {
		this.health *= multiplier;
		this.reward *= 1.25;
	}

	/**
	 * This method sets a Critter down on the first path coordinate.
	 * 
	 * @throws InterruptedException
	 */
	public void setDown() throws InterruptedException {
		currPathIndex = 0;
		currPathCoord = gamePath.getCoord(0);
		x = (double) currPathCoord.x;
		y = (double) currPathCoord.y;
		onPath = true;
	}

	/**
	 * This method moves a Critter along the path with the Controller timer
	 */

	public void updatePosition() {
		if (this.onPath == true && this.health > 0 && this.reachedGoal == false) {

			if (!(this instanceof SmartCritter)) { // All critters other than
													// the smart critter move
													// normally (as seen below)
				moveNormally();
			} else { // SmartCritters move 5x faster when it is near a bullet.
				if (this.isNearBullet) {
					moveFaster();
				} else
					moveNormally();
			}
		}
	}

	/**
	 * This method allows a Critter to move at its usual speed along the path.
	 */

	public void moveNormally() {
		if (currPathIndex == pathLength - 1) {
			nextPathCoord = gameBoard.getMap().getOffMapExit();
		} else {
			nextPathCoord = gamePath.getCoord(currPathIndex + 1);
		}
		double dx = (double) (nextPathCoord.x - currPathCoord.x);
		double dy = (double) (nextPathCoord.y - currPathCoord.y);

		if (dx != 0) {
			x = x + (dx / Math.abs(dx)) * speed;
		}
		if (dy != 0) {
			y = y + (dy / Math.abs(dy)) * speed;

		}

		double dxc = Math.abs(x - currPathCoord.x);
		double dyc = Math.abs(y - currPathCoord.y);

		boolean flag = (dxc > 1 || dyc > 1);

		if (flag) {
			if (currPathIndex == pathLength - 1) {
				reachedGoal = true;
				health = 0;
				onPath = false;
				System.out.println(this.toString()
						+ " has reached the endpoint");
				System.out.println("Player lost " + damage + " health.");
				game.changeHealth(-1 * damage);
				game.removeCritter(this);

			} else {
				currPathCoord = nextPathCoord;
				currPathIndex = currPathIndex + 1;
			}

		}
	}

	/**
	 * This method moves a critter along the path at 5x its normal speed.
	 */
	public void moveFaster() {
		if (currPathIndex == pathLength - 1) {
			nextPathCoord = gameBoard.getMap().getOffMapExit();
		} else {
			nextPathCoord = gamePath.getCoord(currPathIndex + 1);
		}
		double dx = (double) (nextPathCoord.x - currPathCoord.x);
		double dy = (double) (nextPathCoord.y - currPathCoord.y);

		if (dx != 0) {
			x = x + (dx / Math.abs(dx)) * (5 * speed);
		}
		if (dy != 0) {
			y = y + (dy / Math.abs(dy)) * (5 * speed);

		}

		double dxc = Math.abs(x - currPathCoord.x);
		double dyc = Math.abs(y - currPathCoord.y);

		boolean flag = (dxc > 1 || dyc > 1);

		if (flag) {
			if (currPathIndex == pathLength - 1) {
				reachedGoal = true;
				health = 0;
				onPath = false;
				System.out.println(this.toString()
						+ " has reached the endpoint");
				System.out.println("Player lost " + damage + " health.");
				game.changeHealth(-1 * damage);
				game.removeCritter(this);

			} else {
				currPathCoord = nextPathCoord;
				currPathIndex = currPathIndex + 1;
			}

		}
	}

	public void setRef(int i) {
		critterRef = i;
	}

	public int getRef() {
		return critterRef;
	}

	public abstract boolean getShield();

	public abstract boolean getDirection();

	public abstract boolean getVisibility();

	public abstract String toString();

	public abstract Point getPixelOffset();

	public void setIsNearBullet(boolean b) {
		isNearBullet = b;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(int i) {
		speed = i;
	}

	public void reduceSpeed(double reduction) {
		speed *= reduction;
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

	public double getX() {
		return nextPathCoord.getX();
	}

	public double getY() {
		return nextPathCoord.getY();
	}

	public boolean onPath() {
		return onPath;
	}

	public void setHealth(int h) {
		this.health = h;
	}

	public void setSpeed(double s) {
		this.speed = s;
	}

	/**
	 * The following getters and setters were created for testing purposes
	 */

	public Point getCritterPosition() {
		Point c = new Point();
		c.setLocation(this.x, this.y);
		return c;
	}

	public void setDownAtChosenIndex(int index) {
		currPathIndex = index;
		currPathCoord = gamePath.getCoord(index);
		System.out.println(currPathCoord);
		x = (double) currPathCoord.x;
		y = (double) currPathCoord.y;
		onPath = true;
	}

	public void setCurrPathIndex(int index) {
		currPathIndex = index;
	}

	public Point getCurrPathCoord() {
		return currPathCoord;
	}

	public boolean getWhetherOnPath() {
		return onPath;
	}

}
