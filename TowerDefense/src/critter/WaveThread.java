package critter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class WaveThread extends Thread {

	Wave w;
	public WaveThread(Wave wave){
		this.w=wave;
		
	}
	public void run(){
		w.setUpBank();
		ArrayList<Critter> critterBank=w.getCritterBank();
		Critter c;
		Thread x;
		for (int i =0; i<critterBank.size(); i++){
			c=critterBank.get(i);
			c.setRef(i);
			try {
				c.setDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			x = new CritterThread(c);
			x.start();
			System.out.println(c.toString()+ " has been set down");
			try {
				TimeUnit.MILLISECONDS.sleep((long)(Wave.DEFAULT_DELAY/(0.5*w.getDifficulty()*w.getGame().getGameSpeed())));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
