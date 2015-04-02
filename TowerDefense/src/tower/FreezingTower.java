package tower;

/*
* ECSE 321
* Winter 2015
* Jenna Mar
* 260590119
*
* This class creates a Tower which inflicts a slowing effect.
*/

import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class FreezingTower extends Tower{
	
	Image image;

	public FreezingTower(int x, int y, LinkedList<Tower> towers){
		super(x,y, towers);
		initAttr();
		//addTower();
	}
	public FreezingTower(int x, int y){
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
		size = 4; //size of tower
		cost = 150; //buying cost
		level = 1; //upgrade level
		value = (int) (cost * level * 0.6); //selling value
		range = 5; //range of tower
		bulletRange = 5; //range of bullet explosion
		power = 1; //power of bullets
		fireRate = 1; //rate of fire
		isSpecial = true; //if tower has special effects
		specialmod = 0.8;	
	}
	
	public Image getImage() {
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
		image = i.getImage();
		return image;
	}
}