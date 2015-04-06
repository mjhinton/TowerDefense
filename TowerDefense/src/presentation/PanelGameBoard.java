package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.SwingUtilities;



import tower.*;
import map.Map;
import model.Game;
//import map.Map;
//import map.Path;
//import model.Board;
//import model.Game;
//import common.ReadWriteTxtFile;

public class PanelGameBoard extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	//for testing
	
//	private Board testBoard;
//
//	private Game testGame;
//
//	private Path testPath;
	
	private View view;
	
	//modes to be set whether user is selling, buying, etc.
	public boolean sellMode = false;
	public boolean buyB = false;
	public boolean buyF = false;
	public boolean buyM = false;
	public boolean upgradeMode = false;
	public boolean lowest = false;
	public boolean highest = false;
	public boolean closest = false;
	public boolean farthest = false;

	public PanelGameBoard(View view){

		Dimension dim=new Dimension(View.SCREEN_HEIGHT, View.SCREEN_HEIGHT);
		this.view=view;
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);

		addMouseListener(this);
		addMouseMotionListener(this);
		
		/*Point c = new Point((MouseInfo.getPointerInfo().getLocation().x - 
				this.getLocationOnScreen().x), (MouseInfo.getPointerInfo().getLocation().y 
				- this.getLocationOnScreen().y));
		*/
	}
	
	public void mouseClicked(MouseEvent e){
	    try{	
			int coordX = (e.getX()/Map.CELL_PIXEL_SIZE);
		    int coordY = (e.getY()/Map.CELL_PIXEL_SIZE);
			//this next line was just for testing
		    //System.out.println("(" + coordX + ", " + coordY + ")");
		    Game game=view.getController().getGame();
		    Point c= new Point(coordX, coordY);
		    Tower tower=game.getBoard().getTower(c);
		
		    if (sellMode){
				game.removeTower(tower);
				//PanelGameTowerManager.updatePF();
				//repaint();
		    }
		    else if (upgradeMode){
		    	game.upgradeTower(tower);
		    	//PanelGameTowerManager.updatePF();
		    	//repaint();
		    }
		    else if (buyB){
		    	game.addTower(new NormalTower(c, game));
		    	//PanelGameTowerManager.updatePF();
		    	//repaint();
		    }
		    else if (buyF){
		    	game.addTower(new FreezingTower(c, game));
		    	//PanelGameTowerManager.updatePF();
		    	//repaint();
		    }
		    else if(buyM){
		    	game.addTower(new MonsterTower(c, game));
		    	//PanelGameTowerManager.updatePF();
		    	//repaint();
		    }
		    else if (lowest) tower.targetLowestHealth();
		    else if (highest) tower.targetHighestHealth();
		    else if (closest) tower.targetClosest();
		    else if (farthest) tower.targetFarthest();
		    else {
		    	return;
		    }
	    }
	    catch (NullPointerException exc){
	    	
	    	System.err.print("There's nothing here");
	    	if(sellMode){
	    		System.err.println(" to sell.");
	    	}
	    	else if(upgradeMode){
	    		System.err.println(" to upgrade.");
	    	}
	    	else{
	    		System.err.println(".");
	    	}
	    }
	}
	
	public void mousePressed(MouseEvent e) {
	}
	
	public void mouseReleased(MouseEvent e) {
	}
	
	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
	
	public void mouseMoved(MouseEvent e){
		//System.out.println(e.getX() + "," + e.getY());
		Game game=view.getController().getGame();
	    if(e.getX()/Map.CELL_PIXEL_SIZE<view.getModel().getGame().getBoard().getMap().getWidth()&&e.getY()/Map.CELL_PIXEL_SIZE<view.getModel().getGame().getBoard().getMap().getHeight()){
			try{
				Tower tower=game.getBoard().getTower(new Point(e.getX()/Map.CELL_PIXEL_SIZE, e.getY()/Map.CELL_PIXEL_SIZE));

				 String sc = Character.toString((char) 8353);
				 double value = 100;
		    	if(upgradeMode && tower != null){
		    		if (tower.getLevel() == 5){
		    			this.setToolTipText("You can't upgrade this any further.");
		    		}
		    		else{
		    			if (tower.getIcon().getIconHeight() == 62){
		    				this.setToolTipText(
	    						"<html><b>Upgrade Cost: </b>" + sc + tower.getCost() +
			    				"<br><b>Level: </b>" + tower.getLevel() + "--><b>" + (tower.getLevel() + 1) +
			    				"<br>Fire Rate: </b>" + Math.round(100*(tower.getFireRate()))/value + 
			    				"--><b>" + Math.round(100*(tower.getFireRate()*1.1))/value +		    			
				    			"<br>Range: </b>" + tower.getRange() + "--><b>" + (tower.getRange() + 1) +
				    			"<br>Slowing Power: </b>" + Math.round(100*(tower.getSpecialmod()))/value + 
				    			"--><b>" + Math.round(100*(tower.getSpecialmod() - 0.05))/value +
				    			"</html>"				
		    		    	);
		    			}
		    			else{
				    		this.setToolTipText(
			    				"<html><b>Upgrade Cost: </b>" + sc + tower.getCost() +
	    	    				"<br><b>Level: </b>" + tower.getLevel() + "--><b>" + (tower.getLevel() + 1) +
	    	    				"<br>Fire Rate: </b>" + Math.round(100*(tower.getFireRate()))/value + 
	    	    				"--><b>" + Math.round(100*(tower.getFireRate()*1.1))/value +
	    	    				"<br>Damage: </b>" + Math.round(100*(tower.getPower())) + 
	    	    				"--><b>" + Math.round(100*(tower.getPower()*1.5))/value +
	    		    			"</html>"
		    		    	);			    				
		    			}
		    		}
		    	}	    
			    else if (tower != null){
			    	if (tower.getLevel() == 5){
			    		this.setToolTipText(
			    			"<html><b>Level: </b>" + tower.getLevel() + 
			    			"<br><b>Selling Value: </b>" + sc + tower.getValue() +
			    			"</html>"
			    			);
			    	}
			    	else{
				    	this.setToolTipText(
			    			"<html><b>Level: </b>" + tower.getLevel() + 
			    			"<br><b>Upgrade Cost: </b>" + sc + tower.getCost() +
			    			"<br><b>Selling Value: </b>" + sc + tower.getValue() +
			    			"</html>"
			    			);
			    	}
			    }
			    else{
			    	this.setToolTipText("");
			    }
			}catch (NullPointerException err){
				
			}
		   
	    }
	}
	
	public void mouseDragged(MouseEvent e){
	}
	
	public void paint(Graphics g){
		//Model.getMap().paint(g);
		//testGame.paintGame(g);
		view.getController().paintComponent(g);

	}
	
	public View getView(){
		return view;
	}
	
}
