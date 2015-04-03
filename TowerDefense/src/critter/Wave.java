package critter;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


import model.Game;

/*A Wave is a Group of critters. Depending on how many waves the player has played, the waves will consist of more and more difficult critters
 * with higher rewards
 */
public class Wave{
	static final int EASY = 3;
	static final int MEDIUM = 10;
	static final int HARD = 20; 
	static final long DEFAULT_DELAY = 1000;
	
	private ArrayList<Critter> critterBank;
	private int x;
	private double difficulty;
	private boolean waveInProgress;
	private Game game;
	
	public Wave(int n, Game game){
		this.game=game;
		this.critterBank = new ArrayList<Critter>();
		this.difficulty = (double) n;
		this.waveInProgress = false;
		this.x = n;
	}

	public void setUpBank(){
		System.out.println("generating normal critters");
		generateCritters("normal", (3*x+5), game);
		System.out.println("normal critters generated");
		
		if (x>EASY){
			generateCritters("heavy", x/2, game);
			generateCritters("smart", x/3, game);
		}
		if (x>MEDIUM){
			generateCritters("ghost", x/3, game);
			generateCritters("shielded", x/3, game);
			generateCritters("smart", x/3, game);
		}
		if (x>HARD){
			generateCritters("monster", x/2, game);
		}
		
		for(int j = 0; j < critterBank.size(); j++) critterBank.get(j).increaseDifficulty(1+difficulty/15); //can be modified to change difficulty of waves
		
	}
	
	public void generateCritters(String type, int quantity, Game game){
		for(int i = 0; i < quantity; i++){
			Critter critter = CritterFactory.spawn(type, game);
			System.out.println("spawned");
			critterBank.add(critter);
			System.out.println(1 + " " + type + "critter added to the critterbank");
		}
	}
	
	public void releaseCritters() throws InterruptedException{
		this.setUpBank();
		Critter c;
		Thread x;
		for (int i =0; i<critterBank.size(); i++){
			c=critterBank.get(i);
			x = new CritterThread(c);
			x.start();
			System.out.println(c.toString()+ " has been set down");
			TimeUnit.MILLISECONDS.sleep((long)(DEFAULT_DELAY/(0.5*difficulty)));
		}
	}
	
	public void paintCritters(Graphics g){
		Critter c;
		for (int i =0; i<critterBank.size(); i++){
			c=critterBank.get(i);
			c.drawCritter(g);
			//System.out.println("test print critters");
		}
		
	}
	
	public boolean waveInProgress(){
		Critter c;
		for (int i =0; i<critterBank.size(); i++){
			c=critterBank.get(i);
			if (c.getHealth()>0){
				waveInProgress = true;
				return waveInProgress;
			}
		}
		waveInProgress=false;
		return waveInProgress;
	}
	
	public void updateCritterPositions(){
		for (int i =0; i<critterBank.size(); i++){
			critterBank.get(i).updatePosition();
		}
	}
}