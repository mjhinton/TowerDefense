package presentation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	public static final int PANEL_ID_MENU = 0;
//	public static final int PANEL_ID_GAME = 1;
//	public static final int PANEL_ID_MAP_EDITOR = 2;

	private CardLayout cards;
	private PanelGame pnlGame;
	private PanelMenu pnlMenu;
	private PanelMapEditor pnlMapEd;
	

	public MainPanel() {

		Dimension dim=new Dimension(View.SCREEN_WIDTH, View.SCREEN_HEIGHT);
		
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

		pnlMenu = new PanelMenu();
		pnlGame = new PanelGame();
		pnlMapEd = new PanelMapEditor();
		this.add(pnlMenu, "PanelMenu");
		this.add(pnlGame, "PanelGame");
		this.add(pnlMapEd, "PanelMapEditor");
		
		pnlMenu.setVisible(true);

	}
	
	public void switchPanel(String cardName){
		cards.show(this, cardName);
	}
}
