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
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class MonsterTower extends Tower{
	
	Image image;

	public MonsterTower(int x, int y, LinkedList<Tower> towers){
		super(x,y, towers);
		initAttr();
		//addTower();
	}
	public MonsterTower(int x, int y){
		super(x,y);
		initAttr();
		//addTower();
	}

	/*
	//buy a tower
	public void addTower(){
		towers.add(this);
	}
	*/

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
	
	public Image getImage() {
		ImageIcon i;
		if (this.level == 2){
			i = new ImageIcon("lib/images/tower/MonsterTower_lv2x40.png");
		}
		else{
			i = new ImageIcon("lib/images/tower/MonsterTowerx40.png");
		}
		image = i.getImage();
		return image;
	}
}