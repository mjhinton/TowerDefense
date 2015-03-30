package presentation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PanelGameBoard extends JPanel{

	private static final long serialVersionUID = 1L;

	public PanelGameBoard(){

		Dimension dim=new Dimension(View.SCREEN_HEIGHT, View.SCREEN_HEIGHT);
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
	}
}
