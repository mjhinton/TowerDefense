package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import common.ReadWriteTxtFile;
import map.Map;
import map.PathEndCell;
import map.PathStartCell;
import map.SceneryCell;
import model.MapEditor;
import model.Model;

//import model.*;

public class PanelMapEditorMap extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	
	private View view;
	private Map mapBeingEdited;

	public PanelMapEditorMap(View view){

		Dimension dim=new Dimension(View.SCREEN_HEIGHT, View.SCREEN_HEIGHT);
		this.view=view;
		
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		addMouseListener(this);
		
	}
	
	public void paint(Graphics g){
		g.fillRect(0,0,Map.CELL_PIXEL_SIZE*Map.MAX_WIDTH,Map.CELL_PIXEL_SIZE*Map.MAX_HEIGHT);
		view.getController().paintComponent(g);

		
	}

	public View getView() {
		return view;
	}
	
	public Map getMapEdited(){
		return mapBeingEdited;
	}

	public Map setMapEdited(Map map){
		mapBeingEdited = map;
		return mapBeingEdited;
	}
	
	public boolean tryInitiatingPath(){
		try{
			if (mapBeingEdited.initPath())
				return true;
		} catch (NullPointerException e){
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mapBeingEdited = view.model.getEditor().getMap();
		int x = e.getX();
		int y = e.getY(); 
		view.model.getEditor().editMap(x, y);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
