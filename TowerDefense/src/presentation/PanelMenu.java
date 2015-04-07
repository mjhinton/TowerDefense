package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class PanelMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Image imageBackground;
	private View view;
	private PanelMenuButtons pnMenuButtons;
	private JPanel pnSide;
	
	
	public PanelMenu(View view) {
		
		Dimension dim = new Dimension(View.SCREEN_WIDTH, View.SCREEN_HEIGHT);
		this.view=view;
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setLayout(new BorderLayout());
		
		pnSide = new JPanel (new BorderLayout());
		pnMenuButtons = new PanelMenuButtons(view);
		
		pnSide.add(pnMenuButtons);
		this.add(pnSide,BorderLayout.WEST);
		pnSide.setBackground(Color.WHITE);
		pnSide.setOpaque(true);
		
		ImageIcon i= new ImageIcon("lib/images/ui/test_menu_background.png");
		imageBackground=i.getImage();
		
	}
	
	public void paint (Graphics g){
		
		g.drawImage(imageBackground, 0, 0, null);
		super.paintComponents(g);
	}

	public View getView() {
		return view;
	}
}
