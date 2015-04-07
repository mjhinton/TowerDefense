package presentation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.sound.midi.Sequence;
//import javax.sound.midi.Sequencer;
import javax.swing.JPanel;

//displays whichever panel is active.
//holds all other panels: map, game, and title menu.
//also initializes the music.
public class MainPanel extends JPanel {


	private static final long serialVersionUID = 1L;

	
	private CardLayout cards;
	private PanelGame pnlGame;
	private PanelMenu pnlMenu;
	//protected for now
	protected PanelMapEditor pnlMapEd;
	private String currentPanel;
	
	private View view;
	
	private SoundPlayer bgsound = new SoundPlayer();

	public View getView() {
		return view;
	}

	public MainPanel(View view) {

		Dimension dim=new Dimension(View.SCREEN_WIDTH, View.SCREEN_HEIGHT);
		this.view=view;
		
		this.setBackground(Color.BLACK);
		this.setPreferredSize(dim);
		this.setMaximumSize(dim);
		this.setMinimumSize(dim);
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.setEnabled(true);
		this.setFocusable(false);
		this.requestFocus();
		
		cards=new CardLayout();
		this.setLayout(cards);

		pnlMenu = new PanelMenu(view);
		pnlGame = new PanelGame(view);
		pnlMapEd = new PanelMapEditor(view);
		this.add(pnlMenu, "PanelMenu");
		this.add(pnlGame, "PanelGame");
		this.add(pnlMapEd, "PanelMapEditor");
		
		currentPanel="PanelMenu";

	}
	
	public void switchPanel(String cardName){
		cards.show(this, cardName);
		currentPanel=cardName;
		switchMusic();
	}
	
	public String getCurrentPanel(){
		return currentPanel;
	}

	public PanelGame getPanelGame() {
		return pnlGame;
	}

	public void update() {
		pnlGame.update();
	}

	
	public void switchMusic(){
		
		if (bgsound.isPlaying()){
			bgsound.setPaused(true);
		}
		if (currentPanel.equals("PanelMenu")){
			Sequence sequence = bgsound.getSequence("lib/music/GS_Title.mid");
			bgsound.loadSoundBank("lib/music/GoldenSun.sf2");
			bgsound.play(sequence, true);
		}
		if (currentPanel.equals("PanelMapEditor")){
			Sequence sequence = bgsound.getSequence("lib/music/MineralTown.mid");
			bgsound.loadSoundBank("lib/music/fomt.sf2");
			bgsound.play(sequence,true);
		}
		if (currentPanel.equals("PanelGame")){
			double randomnum = Math.random();
			Sequence sequence = bgsound.getSequence("lib/music/song022.mid");
			if(randomnum < 0.3 ){
				sequence = bgsound.getSequence("lib/music/song030.mid");
			}
			else if (randomnum < 0.6){
				sequence = bgsound.getSequence("lib/music/song041.mid");
			}
			else if (randomnum < 0.9){
				sequence = bgsound.getSequence("lib/music/song050.mid");				
			}
			bgsound.loadSoundBank("lib/music/GoldenSun.sf2");
			bgsound.play(sequence, true);
		}
	}
}
