package tower;

import java.awt.Image;
import java.awt.Point;
/*
* ECSE 321
* Winter 2015
* Jenna Mar
* 260590119
*
* This class creates a basic Tower.
*/
import java.util.LinkedList;

import javax.swing.ImageIcon;

import model.Game;

public class NormalTower extends Tower{
	
	Image image;

	public NormalTower(Point c, Game game){
		super(c, game);
		//addTower();
	}

	
	public Image getImage() {
		ImageIcon i = new ImageIcon("lib/images/tower/BasicTowerx40.png");
		image = i.getImage();
		return image;
	}
}