package presentation;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;

public class SoundPlayer implements MetaEventListener {
	
	public static final int END_OF_TRACK_MESSAGE = 47;
	private Sequencer sequencer;
	private static Synthesizer synthesizer;
	private boolean loop;
	private boolean isPlaying;
	private Instrument[] instruments;
	
	public SoundPlayer(){		
				
	    try {
	    	synthesizer = MidiSystem.getSynthesizer();
	    	synthesizer.open();
	    	instruments = synthesizer.getAvailableInstruments();
	    	sequencer = MidiSystem.getSequencer();
		    sequencer.open();
		    sequencer.getTransmitter().setReceiver(synthesizer.getReceiver());
		    sequencer.addMetaEventListener(this);
		    loadSoundBank("lib/music/GoldenSun.sf2");
	    } catch (MidiUnavailableException ex) {
	    	sequencer = null;
	    }
	}
	
	public Sequence getSequence(String filename) {
	    try {
	      return getSequence(new FileInputStream(filename));
	    } catch (IOException ex) {
	      ex.printStackTrace();
	      return null;
	    }
	  }
		
	public Sequence getSequence(InputStream is) {
	    try {
	      if (!is.markSupported()) {
	        is = new BufferedInputStream(is);
	      }
	      Sequence s = MidiSystem.getSequence(is);
	      is.close();
	      return s;
	    } catch (InvalidMidiDataException ex) {
	      ex.printStackTrace();
	      return null;
	    } catch (IOException ex) {
	      ex.printStackTrace();
	      return null;
	    }
	  }
	
	public void play(Sequence sequence, boolean loop) {
	    if (sequencer != null && sequence != null && sequencer.isOpen()) {
	      try {
	        sequencer.setSequence(sequence);
	        sequencer.start();
	        this.loop = loop;
	        setVolume(0.5d);
	      } catch (InvalidMidiDataException ex) {
	        ex.printStackTrace();
	      }
	    }
	  }
	
	public void meta(MetaMessage event) {
	    if (event.getType() == END_OF_TRACK_MESSAGE) {
	      if (sequencer != null && sequencer.isOpen() && loop) {
	    	  sequencer.setMicrosecondPosition(0);
	    	  sequencer.start();
	      }
	    }
	  }
	
	public void close() {
	    if (sequencer != null && sequencer.isOpen()) {
	    	synthesizer.close();
	    	sequencer.close();
	    }
	  }
	
	public Sequencer getSequencer() {
	    return sequencer;
	  }
	
	public Synthesizer getSynthesizer() {
		return synthesizer;
	}
	
	public void setPaused(boolean paused) {
	    if (this.isPlaying == paused && sequencer != null && sequencer.isOpen()) {
	      this.isPlaying = (!paused);
	      if (!paused) {
	        sequencer.stop();
	      } else {
	        sequencer.start();
	      }
	    }
	  }
	
	public boolean isPlaying() {
	    return isPlaying;
	  }
	
	public void loadSoundBank(String path) {
        try {
            File f = new File(path);
            Soundbank sb = MidiSystem.getSoundbank(f);
            if (synthesizer.isSoundbankSupported(sb)){
                // unload all instruments
                for (Instrument i: instruments){
                    synthesizer.unloadInstrument(i);
                }
                synthesizer.loadAllInstruments(sb);
                instruments = synthesizer.getLoadedInstruments();
            }
            sequencer.getTransmitter().setReceiver(synthesizer.getReceiver());
            //System.out.println(". Loaded "+ (sb.getName())+" soundbank" );
        } catch (InvalidMidiDataException e) {            
            //e.printStackTrace();
        } catch (IOException e) {            
            //e.printStackTrace();
        } catch (MidiUnavailableException e) {            
            //e.printStackTrace();
        }
        instruments = synthesizer.getLoadedInstruments();       
    }
	
	//this only silences some channels. doesn't work well if not using the hardware soundbank.
	public static  void setVolume(double gain){
		//double gain = 0.9D;
		MidiChannel[] channels = synthesizer.getChannels();  
		for (int i = 0; i < channels.length; i++) {
	        channels[i].controlChange(7, (int) (gain * 127.0));
	     }
	}
}
