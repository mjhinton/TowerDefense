package tower;

/*
* ECSE 321 Intro to Software Engineering
* Winter 2015
* Group 12
*
* This class creates a Tower which inflicts a slowing effect.
*/

import java.awt.Image;
import java.awt.Point;
//import java.util.LinkedList;

import javax.swing.ImageIcon;

import model.Game;

public class FreezingTower extends Tower{
	
	public final static double DAMAGE=1;
	public final static double RANGE=3;
	public final static double BLAST_RADIUS=3;
	public final static double FIRE_RATE=0.3;
	public final static double SPECIAL_MOD=0.8;
	public final static boolean IS_SPECIAL=true;
	public final static int COST=1000;
	public final static int SIZE=1;
	public final static int VALUE=(int) (COST * 0.6);
	
	Image image;
	Image bulletImage;

	public FreezingTower(Point c, Game game){
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
	
	public ImageIcon getIcon(){
		ImageIcon i;
		if (this.level == 2){
			i = new ImageIcon("lib/images/tower/FreezingTower_lv2x40.png");
		}
		else if (this.level == 3){
			i = new ImageIcon("lib/images/tower/FreezingTower_lv3x40.png");
		}
		else if (this.level == 4){
			i = new ImageIcon("lib/images/tower/FreezingTower_lv4x40.png");
		}
		else if (this.level == 5){
			i = new ImageIcon("lib/images/tower/FreezingTower_lv5x40.png");
		}
		else{
			i = new ImageIcon("lib/images/tower/FreezingTowerx40.png");
		}
		return i;
	}
	
	public Image getImage() {
		image = getIcon().getImage();
		return image;
	}
	
	public ImageIcon getBulletIcon(){
		ImageIcon i;
		i = new ImageIcon("lib/images/projectile/obj_snowball_15x15.png");
		return i;
	}
	
	public Image getBulletImage(){
		bulletImage = getBulletIcon().getImage();
		return bulletImage;
	}
	
	
}
