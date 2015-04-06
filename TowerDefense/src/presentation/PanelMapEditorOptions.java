package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import map.Map;
import model.MapEditor;
import common.ReadWriteTxtFile;

public class PanelMapEditorOptions extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private View meView;
	
	private JButton bSaveMap, bPlayMap, bMainMenu, bOpenMap, bMapSettings;

	public PanelMapEditorOptions(final View view){

		Dimension dim=new Dimension(View.SCREEN_WIDTH-View.SCREEN_HEIGHT, View.SCREEN_HEIGHT);
		this.meView=view;
		
		this.setBackground(Color.GRAY);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		bMapSettings = new JButton ("Map Editor Settings");
		bSaveMap = new JButton ("Save Map");
		bPlayMap = new JButton("Play Map");
		bMainMenu = new JButton("Main Menu");
		bOpenMap = new JButton("Open Saved Map");
		
		JPanel pnButtonsContainer = new JPanel(new GridLayout(5,1));
		
		pnButtonsContainer.add(bMapSettings);
		pnButtonsContainer.add(bSaveMap);
		pnButtonsContainer.add(bOpenMap);
		pnButtonsContainer.add(bPlayMap);
		pnButtonsContainer.add(bMainMenu);
		
		//creates pop-up for bMapOptions
		
		bMapSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	JTextField newName = new JTextField(20);
            	JTextField newX = new JTextField(2);
            	JTextField newY = new JTextField(2);
            	
            	JPanel popup = new JPanel();
        		popup.add(new JLabel("Map name: "));
        		popup.add(newName);
        		popup.add(new JLabel(" Map width: "));
        		popup.add(newX);
        		popup.add(new JLabel(" Map height: "));
        		popup.add(newY);
        		
        		int result = JOptionPane.showConfirmDialog(null, popup, "Please enter new map settings", JOptionPane.OK_CANCEL_OPTION);
        		if(result == JOptionPane.OK_OPTION){
        			if(newName.getText().equals("")||newX.getText().equals("")||newY.equals("")){
        				if(newName.getText().equals("")&&(newX.getText().equals("")||newY.getText().equals(""))) System.out.println("Please enter a valid name and number(s).");
        				else if(newName.getText().equals("")) System.out.println("Please enter a valid name.");	
        				else if(newX.getText().equals("")||newY.getText().equals("")) System.out.println("Please enter a valid number from 1-15");
        			}
        			else meView.createEditableMap(newName.getText(), Integer.parseInt(newX.getText()), Integer.parseInt(newY.getText()));
        		}
        		meView.switchPanel("PanelMapEditor");
            }
        });
		
        bSaveMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
               MapEditor me=meView.getModel().getEditor();
               Map map=me.getMap();
               boolean flag=map.initPath(); 
               if(flag){
            	   Map.saveMap(map);
               }
               
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
            	File savedMaps = new File("lib/maps");
        		
            	JFileChooser chooser = new JFileChooser();
            	chooser.setCurrentDirectory(savedMaps);
        		
        	    int returnVal = chooser.showOpenDialog(meView.getMainPanel());
        	    
        	    if(returnVal == JFileChooser.APPROVE_OPTION){
        	    	String[] testArrayMap = ReadWriteTxtFile.readTxtFileAsStringArray(chooser.getSelectedFile().getAbsolutePath());
                	Map loadedMap = new Map("testMap", 15, testArrayMap);
                	meView.model.getEditor().setMap(loadedMap);
        	    }
            	
            }
        });	
        
        this.add(pnButtonsContainer, BorderLayout.EAST);
		
	}

	public View getView() {
		return meView;
	}
}
