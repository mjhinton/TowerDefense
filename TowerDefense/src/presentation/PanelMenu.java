package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Image imageBackground;
	private View view;

	public PanelMenu(View view) {
		
		this.view=view;

		this.setBackground(Color.ORANGE);
		this.setPreferredSize(new Dimension(View.SCREEN_WIDTH,
				View.SCREEN_HEIGHT));
		this.setDoubleBuffered(true);
		this.setFocusable(true);

	}
	
	public void paint (Graphics g){
		ImageIcon i= new ImageIcon("lib/images/ui/test_menu_background.png");
		imageBackground=i.getImage();
		g.drawImage(imageBackground, 0, 0, null);
	}
}
