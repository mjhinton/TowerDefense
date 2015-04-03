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

import model.Game;

public class NormalTower extends Tower{
	
	Image image;

	public NormalTower(int x, int y, Game game){
		super(x,y, game);
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
		ImageIcon i = new ImageIcon("lib/images/tower/BasicTowerx40.png");
		image = i.getImage();
		return image;
	}
}