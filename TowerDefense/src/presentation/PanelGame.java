package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PanelGame extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final int GAME_SCREEN_TOWER_MANAGER_HEIGHT = 400;
	
	private JPanel pnSide;
	private PanelGameBoard pnBoard;
	private PanelGameTowerManager pnTM;
	private PanelGameOptions pnOptions;
	
	private View view;

	public PanelGame(View view) {

		Dimension dim=new Dimension(View.SCREEN_WIDTH, View.SCREEN_HEIGHT);
		this.view=view;
		
		this.setBackground(Color.BLACK);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setLayout(new BorderLayout());
		
		pnSide= new JPanel(new BorderLayout());

		pnBoard=new PanelGameBoard(view);
		pnTM=new PanelGameTowerManager(view, pnBoard);
		pnOptions=new PanelGameOptions(view);

		
		//add tower manager and options panels to a side container
		pnSide.add(pnTM, BorderLayout.NORTH);
		pnSide.add(pnOptions, BorderLayout.SOUTH);
		
		//add the board and side container to the game panel
		this.add(pnBoard,BorderLayout.WEST);
		this.add(pnSide,BorderLayout.EAST);

	}

	public View getView() {
		return view;
	}
}
