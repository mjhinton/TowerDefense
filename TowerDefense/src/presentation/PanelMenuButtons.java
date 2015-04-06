package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import map.Map;

public class PanelMenuButtons extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton bNewGame;
	private JButton bMapEditor;
	private JButton bLoadGame;
	private JButton bExit;
	
	private JPanel pnButtonsContainer;
	
	private View mbView;
	//I'm unsure why the view parameter needs to be final, but eclipse refused to compile without it.
	public PanelMenuButtons(View view){
		
		Dimension dim=new Dimension(View.SCREEN_WIDTH-View.SCREEN_HEIGHT, View.SCREEN_HEIGHT);
		
		this.mbView= view;
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		//initiate buttons
		bNewGame = new JButton("New Game");
		bMapEditor = new JButton("Map Editor");
		bLoadGame = new JButton("Load Game");
		bExit = new JButton("Exit");
		
		//Add them to screen
		pnButtonsContainer = new JPanel(new GridLayout(4,1));
		//pnButtonsContainer.setPreferredSize(new Dimension((int)(this.getSize().getWidth()*2/3),(int)(this.getSize().getHeight())));

		pnButtonsContainer.add(bNewGame);
		pnButtonsContainer.add(bMapEditor);
		pnButtonsContainer.add(bLoadGame);
		pnButtonsContainer.add(bExit);
		
		
        bNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	//for temporary testing purposes, load the test map. 
            	Map testMap = Map.getPackagedMap("15x15map"); 	
            	//update the model so that it has this map instead, and then switch panel
            	mbView.getController().getGame().setBoardMap(testMap);
            	mbView.switchPanel("PanelGame");
            }
        });
        
        bMapEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                mbView.switchPanel("PanelMapEditor");
            }
        });
        
        bLoadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	File saves = new File("savedFileDatabase");
        		saves.mkdir();
        		
            	JFileChooser chooser = new JFileChooser();
            	chooser.setCurrentDirectory(saves);
        		
        	    int returnVal = chooser.showOpenDialog(mbView.getMainPanel());
        	    
        	    if(returnVal == JFileChooser.APPROVE_OPTION){
        	    	//TODO: load file
        	    }
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
		return mbView;
	}
	
}
