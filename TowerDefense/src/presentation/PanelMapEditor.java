package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PanelMapEditor extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private PanelMapEditorMap pnMap;
	private PanelMapEditorOptions pnOptions;

	public PanelMapEditor() {

		this.setBackground(Color.BLUE);
		this.setPreferredSize(new Dimension(View.SCREEN_WIDTH,
				View.SCREEN_HEIGHT));
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setLayout(new BorderLayout());
		
		pnMap=new PanelMapEditorMap();
		pnOptions=new PanelMapEditorOptions();
		
		//add panels to container
		this.add(pnMap, BorderLayout.WEST);
		this.add(pnOptions, BorderLayout.EAST);
		

	}
}
