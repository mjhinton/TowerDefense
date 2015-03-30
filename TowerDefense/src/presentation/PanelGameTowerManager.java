package presentation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelGameTowerManager extends JPanel{

	private static final long serialVersionUID = 1L;

	public PanelGameTowerManager(){


		Dimension dim=new Dimension(View.SCREEN_WIDTH-View.SCREEN_HEIGHT, PanelGame.GAME_SCREEN_TOWER_MANAGER_HEIGHT);
		
		this.setBackground(Color.RED);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		this.add(new JLabel("Tower Manager"));
	}
}
