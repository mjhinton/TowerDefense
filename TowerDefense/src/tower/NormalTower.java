package tower;

import java.awt.Image;
import java.awt.Point;
/*
* ECSE 321 Introduction to Software Engineering
* Winter 2015
* Group 12
*
* This class creates a basic Tower.
*/

import javax.swing.ImageIcon;

import model.Game;

public class NormalTower extends Tower{
	
	public static int DAMAGE=1;
	public static double RANGE=3;
	public static double BLAST_RADIUS=0;
	public static double FIRE_RATE=1;
	public static double SPECIAL_MOD=1;
	public static boolean IS_SPECIAL=false;
	public static int COST=100;
	public static int SIZE=2;
	public static int VALUE=(int) (COST * 0.6);
	
	Image image;
	Image bulletImage;

	public NormalTower(Point c, Game game){
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
			i = new ImageIcon("lib/images/tower/BasicTower_lv2x40.png");
		}
		else if (this.level == 3){
			i = new ImageIcon("lib/images/tower/BasicTower_lv3x40.png");
		}
		else if (this.level == 4){
			i = new ImageIcon("lib/images/tower/BasicTower_lv4x40.png");
		}
		else if (this.level == 5){
			i = new ImageIcon("lib/images/tower/BasicTower_lv5x40.png");
		}
		else{
			i = new ImageIcon("lib/images/tower/BasicTowerx40.png");
		}
		return i;
	}
	
	public Image getImage() {
		image = getIcon().getImage();
		return image;
	}
	
	public ImageIcon getBulletIcon(){
		ImageIcon i;
		i = new ImageIcon("lib/images/projectile/obj_fireball_16x17.png");
		return i;
	}
	
	public Image getBulletImage(){
		bulletImage = getBulletIcon().getImage();
		return bulletImage;
	}
}
