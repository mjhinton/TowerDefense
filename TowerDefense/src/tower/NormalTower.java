package tower;

import java.awt.Image;
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

public class NormalTower extends Tower{
	
	Image image;

	public NormalTower(int x, int y, LinkedList<Tower> towers){
		super(x,y, towers);
		//addTower();
	}
	
	public NormalTower(int x, int y){
		super(x,y);
		//addTower();
	}

	/*
	//buy a tower
	public void addTower(){
		towers.add(this);
	}
	*/
	
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