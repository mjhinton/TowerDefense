package critter;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import model.Board;

/*A Wave is a Group of critters. Depending on how many waves the player has played, the waves will consist of more and more difficult critters
 * with higher rewards
 */
public class Wave{
	static final int EASY = 3;
	static final int MEDIUM = 10;
	static final int HARD = 20; 
	static final long DEFAULT_DELAY = 10;
	
	private ArrayList<Critter> critterBank;
	private Iterator<Critter> iterator;
	private int x;
	private double difficulty;
	private boolean waveInProgress;
	private Board board;
	
	public Wave(int n, Board cBoard){
		this.critterBank = new ArrayList<Critter>();
		this.iterator = critterBank.iterator();
		this.difficulty = (double) n;
		this.waveInProgress = false;
		this.board = cBoard;
		this.x = n;
	}

	public void setUpBank(){
		System.out.println("generating normal critters");
		generateCritters("normal", (3*x+5), board);
		System.out.println("normal critters generated");
		
		if (x>EASY){
			generateCritters("heavy", x/2, board);
			generateCritters("smart", x/3, board);
		}
		if (x>MEDIUM){
			generateCritters("ghost", x/3, board);
			generateCritters("shielded", x/3, board);
			generateCritters("smart", x/3, board);
		}
		if (x>HARD){
			generateCritters("monster", x/2, board);
		}
		
		for(int j = 0; j < critterBank.size(); j++) critterBank.get(j).increaseDifficulty(1+difficulty/15); //can be modified to change difficulty of waves
		
		iterator = critterBank.iterator();
	}
	
	public void generateCritters(String type, int quantity, Board board){
		for(int i = 0; i < quantity; i++){
			Critter critter = CritterFactory.spawn(type, board);
			System.out.println("spawned");
			critterBank.add(critter);
			System.out.println(1 + " " + type + "critter added to the critterbank");
		}
	}
	
	public void releaseCritters() throws InterruptedException{
		this.setUpBank();
		while (iterator.hasNext()){
			Critter c = iterator.next();
			Thread x = new CritterThread(c);
			x.start();
			System.out.println(c.toString()+ " has been set down");
			TimeUnit.MILLISECONDS.sleep((long)(DEFAULT_DELAY/(0.5*difficulty)));
			// the general idea is that the delay will get smaller and smaller as the player progresses through the waves.
		}	
	}
	
	public void paintCritters(Graphics g){
		while(iterator.hasNext()){
			Critter c = iterator.next();
			c.drawCritter(g);
		}
	}
	
	public boolean waveInProgress(){
		while (iterator.hasNext()){
			for (int i=0; i<critterBank.size(); i++){
					Critter c = iterator.next();
					if ((c.getHealth()>0)||(c.reachedGoal != true)){
						waveInProgress = true;
						return waveInProgress;
					}	
			}
		}
			return waveInProgress;
	}
}