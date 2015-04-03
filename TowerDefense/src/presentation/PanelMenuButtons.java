package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelMenuButtons extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton bNewGame;
	private JButton bLoadGame;
	private JButton bExit;
	
	private JPanel pnButtonsContainer;
	
	private View view;
	//I'm unsure why the view parameter needs to be final, but eclipse refused to compile without it.
	public PanelMenuButtons(final View view){
		
		Dimension dim=new Dimension(View.SCREEN_WIDTH-View.SCREEN_HEIGHT, View.SCREEN_HEIGHT);
		
		this.view= view;
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		//initiate buttons
		bNewGame = new JButton("New Game");
		bLoadGame = new JButton("Load Game");
		bExit = new JButton("Exit");
		
		//Add them to screen
		pnButtonsContainer = new JPanel(new GridLayout(3,1));
		//pnButtonsContainer.setPreferredSize(new Dimension((int)(this.getSize().getWidth()*2/3),(int)(this.getSize().getHeight())));

		pnButtonsContainer.add(bNewGame);
		pnButtonsContainer.add(bLoadGame);
		pnButtonsContainer.add(bExit);
		
		
        bNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                view.switchPanel("PanelMapEditor");
            }
        });
        
        bLoadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                view.switchPanel("PanelGame");
            }
        });
        
        bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
		
		this.add(pnButtonsContainer, BorderLayout.CENTER);
	
	}
	
	public View getView(){
		return view;
	}
	
}