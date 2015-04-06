package critter;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import presentation.View;
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
	private ArrayList<Critter> releaseBank;
	private int x;
	private double difficulty;
	private boolean waveInProgress;
	private Game game;
	private boolean paused;
	private boolean finishedRelease;
	private int releasingTimingIndex;
	private int releasingIndex;
	
	public Wave(int n, Game game){
		this.game=game;
		this.critterBank = new ArrayList<Critter>();
		this.difficulty = (double) n;
		this.waveInProgress = false;
		this.x = n;
		this.finishedRelease=false;
		this.releasingTimingIndex=0;
		this.releasingIndex=0;
	}

	public void setUpBank(){
		generateCritters("normal", (3*x+5), game);
		//System.out.println("normal critters generated");
		
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
		
		releaseBank=new ArrayList<Critter>();
		System.out.println("new releaseBank created");
		for(int j = 0; j < critterBank.size(); j++){
			critterBank.get(j).increaseDifficulty(1+difficulty/15); //can be modified to change difficulty of waves
		
			releaseBank.add(critterBank.get(j));
		}
		System.out.println("critters added to releaseBank");
		
	}
	
	//methods for testing purposes
	public void addCritterToBank(Critter c){
		critterBank.add(c);
	}
	public void addCritterToReleaseBank(Critter c){
		releaseBank.add(c);
	}
	////
	
	
	public void generateCritters(String type, int quantity, Game game){
		for(int i = 0; i < quantity; i++){
			Critter critter = CritterFactory.spawn(type, game);
			critterBank.add(critter);
			System.out.println(1 + " " + type + "critter added to the critterbank");
		}
	}
	
	public void releaseCritters() throws InterruptedException{
		//this.setUpBank();
		Critter c;
		//Thread x;
		c=releaseBank.get(releasingIndex);
		if(releasingTimingIndex>=DEFAULT_DELAY/(0.5*difficulty*View.TIMEOUT)){
			releasingTimingIndex=0;
			c.setDown();
		//	System.out.println(c.toString()+ " has been set down");
		//	System.out.println("releaseBank size: "+releaseBank.size());
			if (releasingIndex>=releaseBank.size()-1){
				this.finishedRelease=true;
		//		System.out.println("Finished releasing critters.");
			}else{
				releasingIndex+=1;
			}
			
		}else{
			releasingTimingIndex+=1;
		}
		
//		for (int i =0; i<critterBank.size(); i++){
//			c=critterBank.get(i);
//			c.setRef(i);
//			try {
//				c.setDown();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			x = new CritterThread(c);
//			x.start();
//			System.out.println(c.toString()+ " has been set down");
//			TimeUnit.MILLISECONDS.sleep((long)(DEFAULT_DELAY/(0.5*difficulty*game.getGameSpeed())));
//		}
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
	
	public void removeDead(){
		for(int i = 0; i < critterBank.size(); i++) 
			if(critterBank.get(i).getHealth()<0) critterBank.remove(i);
	}
	
	public ArrayList<Critter> getCritterBank(){
		return critterBank;
	}
	//added for testing purposes
	public ArrayList<Critter> getReleaseBank(){
		return releaseBank;
	}
	
	public void removeCritter(Critter c){
		critterBank.remove(c);
	}
	
	public void togglePaused(){
		paused = !paused;
	}
	
	public boolean getPaused(){
		return paused;
	}

	public Game getGame() {
		return this.game;
	}
	
	public double getDifficulty() {
		return difficulty;
	}
	public boolean finishedRelease(){
		return finishedRelease;
	}
	
	//Setter methods for testing purposes
	public void setWaveNumber(int newX){
		this.x = newX;
	}
	public void setReleasingIndex(int index){
		this.releasingIndex = index;
	}
	public void setReleasingTimingIndex(int index){
		this.releasingTimingIndex = index;
	}

	
}
