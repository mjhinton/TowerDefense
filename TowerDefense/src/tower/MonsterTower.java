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
	
	Image image;
	Image bulletImage;
	protected char type = 'm';

	public MonsterTower(Point c, Game game){
		super(c, game);
		initAttr();
	}

	public void initAttr(){
		size = 6; //size of tower
		cost = 500; //buying cost
		level = 1; //upgrade level
		value = (int) (cost * level * 0.6); //selling value
		range = 7; //range of tower
		bulletRange = 5; //range of bullet explosion
		power = 5; //power of bullets
		fireRate = 3; //rate of fire
		isSpecial = false; //if tower has special effects
		specialmod = 1;	
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
		i = new ImageIcon("lib/images/projectiles/obj_poisonball_16x15.png");
		return i;
	}
	
	public Image getBulletImage(){
		bulletImage = getBulletIcon().getImage();
		return bulletImage;
	}
}
