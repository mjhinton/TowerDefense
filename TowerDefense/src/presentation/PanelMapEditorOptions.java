package presentation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PanelMapEditorOptions extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private View view;

	public PanelMapEditorOptions(View view){

		Dimension dim=new Dimension(View.SCREEN_WIDTH-View.SCREEN_HEIGHT, View.SCREEN_HEIGHT);
		this.view=view;
		
		this.setBackground(Color.GRAY);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
	}
}
