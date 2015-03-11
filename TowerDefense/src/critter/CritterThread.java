package critters;

//This thread will allow the critters to move towards the EndPoint at the same time.
public class CritterThread extends Thread{
	Critter c;
	public CritterThread(Critter c){
		this.c = c;
	}
	public void run(){
		try {
			c.setDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}