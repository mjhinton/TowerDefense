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

import common.ReadWriteTxtFile;

import map.Map;

public class PanelMenuButtons extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton bPlayGame;
	private JButton bMapEditor;
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
		bMapEditor = new JButton("Map Editor");
		bPlayGame = new JButton("Play Game");
		bExit = new JButton("Exit");
		
		//Add them to screen
		pnButtonsContainer = new JPanel(new GridLayout(3,1));
		//pnButtonsContainer.setPreferredSize(new Dimension((int)(this.getSize().getWidth()*2/3),(int)(this.getSize().getHeight())));

		pnButtonsContainer.add(bPlayGame);
		pnButtonsContainer.add(bMapEditor);
		pnButtonsContainer.add(bExit);
		
		bPlayGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //open panel of saved maps
            	//for now, it just opens the test map 
            	File savedMaps = new File("lib/maps");
        		
            	JFileChooser chooser = new JFileChooser();
            	chooser.setCurrentDirectory(savedMaps);
        		
        	    int returnVal = chooser.showOpenDialog(mbView.getMainPanel());
        	    
        	    if(returnVal == JFileChooser.APPROVE_OPTION){
        	    	String[] testArrayMap = ReadWriteTxtFile.readTxtFileAsStringArray(chooser.getSelectedFile().getAbsolutePath());
                	Map loadedMap = new Map("testMap", 15, testArrayMap);
                	mbView.getController().getGame().setBoardMap(loadedMap);
                	mbView.switchPanel("PanelGame");
        	    }
            	
            }
        });
        
        bMapEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                mbView.switchPanel("PanelMapEditor");
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
