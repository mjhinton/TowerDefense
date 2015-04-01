package critter;

/*The CritterWaveGenerator will keep track of how many waves have been played (using the counter i),
 *  so that we can incrementally increase the difficulty of the waves.
 */
public class CritterWaveGenerator{
	private int i; 
	private Wave wave;
	
	public CritterWaveGenerator(){
		this.i = 1;
		wave = null;
	}
	//The player will only be able to call this method if a wave is not already in progress.
	//(A wave is in progress while there are still critters alive and on the map)
	public void generateWave() throws InterruptedException{
		wave = new Wave(i);
		System.out.println("Aww yeah, wave generated");
		if (wave.waveInProgress() == false){
			wave.releaseCritters();
			System.out.println("Critters all released");
			i++;
		}
		else
			System.out.println("Wave in progress, can't play.");
	}
	
	public Wave getWave(){
		return wave;
	}
}