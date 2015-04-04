package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import map.Map;

import common.ReadWriteTxtFile;

public class PanelMapEditorOptions extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private View meView;
	
	private JButton bSaveMap, bSaveMapAs, bPlayMap, bMainMenu, bOpenMap;

	public PanelMapEditorOptions(View view){

		Dimension dim=new Dimension(View.SCREEN_WIDTH-View.SCREEN_HEIGHT, View.SCREEN_HEIGHT);
		this.meView=view;
		
		this.setBackground(Color.GRAY);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		bSaveMap = new JButton ("Save Map");
		bSaveMapAs = new JButton ("Save Map As");
		bPlayMap = new JButton("Play Map");
		bMainMenu = new JButton("Main Menu");
		bOpenMap = new JButton("Open Saved Map");
		
		JPanel pnButtonsContainer = new JPanel(new GridLayout(5,1));
		
		pnButtonsContainer.add(bSaveMap);
		pnButtonsContainer.add(bSaveMapAs);
		pnButtonsContainer.add(bOpenMap);
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
                //open the Game panel with the map that was just created.
            	
            	PanelMapEditorMap currentMapEditor = meView.mp.pnlMapEd.pnMap;
            
            	if (currentMapEditor.tryInitiatingPath()==true)
            	{ 
            	//set up board from the editor
            	meView.model.getGame().setUpBoardFromEditor(currentMapEditor.getMapEdited());
            	//initiate the path
            	meView.model.getGame().getBoard().getMap().initPath();
            	//switch panel
            	meView.switchPanel("PanelGame");
            	}
            	else
            		System.out.println("Cannot play this map, invalid path");
            }
        });
        
        bMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                meView.switchPanel("PanelMenu");
            }
        });	
        bOpenMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //open panel of saved maps
            	//for now, it just opens the test map 
            	String[] testArrayMap = ReadWriteTxtFile
            			.readTxtFileAsStringArray("lib/testMaps/15x15map.txt");
            	Map testMap = new Map("testMap", 15, testArrayMap);

            	
            }
        });	
        
        this.add(pnButtonsContainer, BorderLayout.EAST);
		
	}

	public View getView() {
		return meView;
	}
}
