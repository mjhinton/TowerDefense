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
		ImageIcon i = new ImageIcon("lib/images/tower/test_tower.png");
		image = i.getImage();
		return image;
	}
}