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

/**
 * This class contains buttons for the modification of MapEditor, as well as their behaviors
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */

public class PanelMapEditorOptions extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private View meView;
	
	private JButton bSaveMap, bPlayMap, bMainMenu, bLoadMap, bMapSettings, bResetMap;
	
	public PanelMapEditorOptions(final View view){

		Dimension dim=new Dimension(View.SCREEN_WIDTH-View.SCREEN_HEIGHT, View.SCREEN_HEIGHT);
		this.meView=view;
		
		this.setBackground(Color.GRAY);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		//creates buttons for a panel at the right of the MapEditor
		bMapSettings = new JButton ("Map Editor Settings");
		bSaveMap = new JButton ("Save Map");
		bPlayMap = new JButton("Play Map");
		bMainMenu = new JButton("Main Menu");
		bLoadMap = new JButton("Load Saved Map");
		bResetMap = new JButton("Reset Map");
		
		//creates the panel
		JPanel pnButtonsContainer = new JPanel(new GridLayout(6,1));
		
		//adds the buttons to the panel
		pnButtonsContainer.add(bMapSettings);
		pnButtonsContainer.add(bSaveMap);
		pnButtonsContainer.add(bLoadMap);
		pnButtonsContainer.add(bPlayMap);
		pnButtonsContainer.add(bResetMap);
		pnButtonsContainer.add(bMainMenu);
		
		//creates pop-up for bMapOptions
		
		//brings up a pop-up that inut of the mapName, width, and height for the map being edited
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
        			else {
        				Map newMap = new Map(newName.getText(), Integer.parseInt(newX.getText()), Integer.parseInt(newY.getText()));
        				//meView.createEditableMap(newName.getText(), Integer.parseInt(newX.getText()), Integer.parseInt(newY.getText()));
        				meView.mp.pnlMapEd.pnMap.setMapEdited(newMap);
        				meView.model.getEditor().setMap(newMap);
        			}
        		}
        		meView.switchPanel("PanelMapEditor");
            }
        });
		
		//allows saving of a map with a pop-up
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
        
        //allows selection of a map from the maps folder using a selection window
        bPlayMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //open the Game panel with the map that was just created.
            	
            	PanelMapEditorMap currentMapEditor = meView.mp.pnlMapEd.pnMap;
            
            	if (currentMapEditor.tryInitiatingPath()==true){ 
	            	//set up board from the editor
	            	meView.getController().startGame(currentMapEditor.getMapEdited());
	            	//initiate the path
	            	//meView.model.getGame().getBoard().getMap().initPath();
	            	//switch panel
	            	meView.switchPanel("PanelGame");
            	}
            	else
            		System.out.println("Cannot play this map, invalid path");
            }
        });
        
        //returns to the main menu
        bMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                meView.switchPanel("PanelMenu");
            }
        });	
        
        //loads a saved map from the custom_maps folder using a selection window
        bLoadMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //open panel of saved maps
            	//for now, it just opens the test map 
            	File savedMaps = new File("lib/maps/custom_maps");
        		
            	JFileChooser chooser = new JFileChooser();
            	chooser.setCurrentDirectory(savedMaps);
        		
        	    int returnVal = chooser.showOpenDialog(meView.getMainPanel());
        	    
        	    if(returnVal == JFileChooser.APPROVE_OPTION){
        	    	String[] testArrayMap = ReadWriteTxtFile.readTxtFileAsStringArray(chooser.getSelectedFile().getAbsolutePath());
                	Map loadedMap = new Map("testMap", 15, testArrayMap);
                	meView.mp.pnlMapEd.pnMap.setMapEdited(loadedMap);
                	meView.model.getEditor().setMap(loadedMap);
        	    }
            }
        });	
        
        //sets the MapEditor's map to a blank 15x15 map when reset is clicked
        bResetMap.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent event) {
            	Map newMap = new Map();
            	meView.mp.pnlMapEd.pnMap.setMapEdited(newMap);
				meView.model.getEditor().setMap(newMap);
            }
        });
        
        this.add(pnButtonsContainer, BorderLayout.EAST);
		
	}

	public View getView() {
		return meView;
	}
}
