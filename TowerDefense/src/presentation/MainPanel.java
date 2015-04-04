package presentation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MainPanel extends JPanel {


	private static final long serialVersionUID = 1L;

	
	private CardLayout cards;
	private PanelGame pnlGame;
	private PanelMenu pnlMenu;
	//protected for now
	protected PanelMapEditor pnlMapEd;
	private String currentPanel;
	
	private View view;
	
	//private SoundPlayer bgsound = new SoundPlayer("nc83843.wav");

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
		//switchMusic();
	}
	
	public String getCurrentPanel(){
		return currentPanel;
	}
	/*
	public void switchMusic(){
		if (bgsound.isPlaying){
			bgsound.stop();
		}
		if (currentPanel.equals("PanelMenu")){
			bgsound = new SoundPlayer("nc83854.wav");
		}
		if (currentPanel.equals("PanelMapEditor")){
			bgsound = new SoundPlayer("nc83853.wav");
		}
		if (currentPanel.equals("PanelGame")){
			bgsound = new SoundPlayer("nc83843.wav");
		}
		bgsound.play();
		bgsound.loop();
	}*/
}
