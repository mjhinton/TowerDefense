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
	
	Image image;

	public NormalTower(Point c, Game game){
		super(c, game);
	}

	
	public Image getImage() {
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
		image = i.getImage();
		return image;
	}
}
