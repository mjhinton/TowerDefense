package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelMapEditorOptions extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private View view;
	
	private JButton bSaveMap, bPlayMap, bMainMenu;

	public PanelMapEditorOptions(final View view){

		Dimension dim=new Dimension(View.SCREEN_WIDTH-View.SCREEN_HEIGHT, View.SCREEN_HEIGHT);
		this.view=view;
		
		this.setBackground(Color.GRAY);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		bSaveMap = new JButton ("Save Map");
		bPlayMap = new JButton("Play Map");
		bMainMenu = new JButton("Main Menu");
		
		JPanel pnButtonsContainer = new JPanel(new GridLayout(3,1));
		
		pnButtonsContainer.add(bSaveMap);
		pnButtonsContainer.add(bPlayMap);
		pnButtonsContainer.add(bMainMenu);
		
        bSaveMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
               //save the map
            }
        });
        
        bPlayMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //create a new game with the map that was just created.
            	//go to the game panel
            }
        });
        
        bMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                view.switchPanel("PanelMenu");
            }
        });	
        
        this.add(pnButtonsContainer, BorderLayout.EAST);
		
	}

	public View getView() {
		return view;
	}
}
