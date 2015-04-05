package presentation;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.LineUnavailableException;

import common.BigClip;

public class SoundPlayer {
	
	private static Clip clip;
	public static boolean isPlaying = false;
	private String name;
	private static FloatControl volume;
	
	public SoundPlayer(){
		//empty constructor
	}
	
	public SoundPlayer(String input){		
		try {
			//clip = new BigClip();
			name = input;
			clip = AudioSystem.getClip();
	        File file = new File("lib/music/" + name);
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
	        clip.open(inputStream);
	        //if the buffer size is too large, catch and try the BigClip solution...
	        //but it works pretty strangely...
	      } catch (LineUnavailableException e){
				try{
					BigClip clip = new BigClip();
					File file = new File("lib/music/" + name);
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			        clip.open(inputStream);
				}
				catch (Exception e2){
					e2.printStackTrace();
				}
	      }
			catch (Exception e) {
	        e.printStackTrace();
	      } 
	}
	
	public void play(){
        clip.setFramePosition(0);
        clip.start();
        isPlaying = true;
    }
	
	public void stop(){
		clip.stop();
		isPlaying = false;
    }
	
	public void loop(){
        if (name.equals("nc83853.wav")){
        	clip.setLoopPoints(0, 5304034);
        	//53040134
        	//clip.setMicrosecondPosition(120200000);
        	//System.out.println(clip.getFrameLength());
        }
        if (name.equals("nc83843.wav")){
        	clip.setLoopPoints(1122833, 6102088);
        }
        if (name.equals("nc83854.wav")){
        	clip.setLoopPoints(0, 4860979);
        }
		clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
	
	public static void setVolume(float gainAmount){
		if (isPlaying){
			volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			BooleanControl muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
			if (gainAmount == -20){
				muteControl.setValue(true);
			}
			else{
				muteControl.setValue(false);
				volume.setValue(gainAmount);
			}
		}
	}
}
