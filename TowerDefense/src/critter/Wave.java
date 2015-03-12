package critter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

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
	
	public Wave(int n){
		this.critterBank = new ArrayList<Critter>();
		this.iterator = critterBank.iterator();
		this.difficulty = (double) n;
		this.waveInProgress = false;
		this.x = n;
	}

	public void setUpBank(){
		int i;
		for (i=0; i<(3*x+5); i++){			
			critterBank.add(new NormalCritter());
			System.out.println(1+" Normal Critters added to critterbank");
			}
		
		if (x>EASY){
			i = 0;
			for (i=0; i<(x/2); i++){
				critterBank.add(new HeavyCritter());
				System.out.println(1+" Heavy Critters added to critterbank");
			}
			i = 0;
			for (i=0; i<(x/3); i++){
				critterBank.add(new SmartCritter());
				System.out.println(1+ " Normal Critters added to critterbank");
			}
		}
		if (x>MEDIUM){
			i = 0;
			for (i=0; i<(x/3); i++){
				critterBank.add(new GhostCritter());
				System.out.println(1+" Ghost Critters added to critterbank");
				critterBank.add(new ShieldedCritter());
				System.out.println(1+" Shielded Critters added to critterbank");
			}
			i = 0;
			for (i=0; i<(x/3); i++){
				critterBank.add(new SmartCritter());
				System.out.println(1+" Smart Critters added to critterbank");
			}
		}
		if (x>HARD){
			i = 0;
			for (i = 0; i<(x/2); i++){
				critterBank.add(new MonsterCritter());
				System.out.println(1+" Monster Critters added to critterbank");
			}
		}
		iterator = critterBank.iterator();
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