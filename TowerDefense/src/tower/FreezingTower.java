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
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import model.Game;

public class FreezingTower extends Tower{
	
	Image image;

	public FreezingTower(Point c, Game game){
		super(c, game);
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
		ImageIcon i = new ImageIcon("lib/images/tower/FreezingTowerx40.png");
		image = i.getImage();
		return image;
	}
}