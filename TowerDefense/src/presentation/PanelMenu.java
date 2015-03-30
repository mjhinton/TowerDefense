package presentation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PanelMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelMenu() {

		this.setBackground(Color.ORANGE);
		this.setPreferredSize(new Dimension(View.SCREEN_WIDTH,
				View.SCREEN_HEIGHT));
		this.setDoubleBuffered(true);
		this.setFocusable(true);

	}
}
