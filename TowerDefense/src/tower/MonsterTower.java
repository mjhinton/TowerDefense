package tower;

/*
* ECSE 321 Programming Assignment 1
* Winter 2015
* Jenna Mar
* 260590119
*
* This class creates a large, heavy area of effect Tower.
*/

import java.awt.Image;
import java.awt.Point;
//import java.util.LinkedList;

import javax.swing.ImageIcon;

import model.Game;

public class MonsterTower extends Tower{
	
	public static int DAMAGE=5;
	public static double RANGE=7;
	public static double BLAST_RADIUS=2;
	public static double FIRE_RATE=3;
	public static double SPECIAL_MOD=1;
	public static boolean IS_SPECIAL=false;
	public static int COST=500;
	public static int SIZE=2;
	public static int VALUE=(int) (COST * 0.6);
	
	Image image;
	Image bulletImage;
	protected char type = 'm';

	public MonsterTower(Point c, Game game){
		super(c, game);
		initAttr();
	}

	public void initAttr(){
		size = SIZE; //size of tower
		cost = COST; //buying cost
		level = 1; //upgrade level
		value = VALUE; //selling value
		range = RANGE; //range of tower
		bulletRange = BLAST_RADIUS; //range of bullet explosion
		power = DAMAGE; //power of bullets
		fireRate = FIRE_RATE; //rate of fire
		specialmod = SPECIAL_MOD;	
		isSpecial = IS_SPECIAL; //if tower has special effects
	}
	
	public ImageIcon getIcon() {
		ImageIcon i;
		if (this.level == 2){
			i = new ImageIcon("lib/images/tower/MonsterTower_lv2x40.png");
		}
		else if (this.level == 3){
			i = new ImageIcon("lib/images/tower/MonsterTower_lv3x40.png");
		}
		else if (this.level == 4){
			i = new ImageIcon("lib/images/tower/MonsterTower_lv4x40.png");
		}
		else if (this.level == 5){
			i = new ImageIcon("lib/images/tower/MonsterTower_lv5x40.png");
		}
		else{
			i = new ImageIcon("lib/images/tower/MonsterTowerx40.png");
		}
		return i;
	}
	
	public Image getImage() {
		image = getIcon().getImage();
		return image;
	}
	
	public ImageIcon getBulletIcon(){
		ImageIcon i;
		i = new ImageIcon("lib/images/projectile/obj_poisonball_16x15.png");
		return i;
	}
	
	public Image getBulletImage(){
		bulletImage = getBulletIcon().getImage();
		return bulletImage;
	}
}
