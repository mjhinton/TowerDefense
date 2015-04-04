package presentation;

//import java.awt.Color;
//import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;

//import javax.swing.JLabel;
//import javax.swing.JPanel;
import javax.swing.*;

public class PanelGameTowerManager extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private View view;
	
	private PanelGameBoard mainboard = new PanelGameBoard(view);
	
	private JLabel PF;

	public PanelGameTowerManager(View view, PanelGameBoard input){

		Dimension dim=new Dimension(View.SCREEN_WIDTH-View.SCREEN_HEIGHT, PanelGame.GAME_SCREEN_TOWER_MANAGER_HEIGHT);
		
		this.view=view;

		mainboard = input;
		
		//will only work if there is a game
		//we will temporarily put '0' here.
		//this.PF = new JLabel("Current funds: " + view.getController().getGame().getCoins());
		this.PF = new JLabel("Current funds: "+0);
		
		//this.setBackground(Color.RED);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
	
		this.add(new JLabel("Tower Manager"));
		
		//add the player funds panel
		addPFPanel();
		
		//add a tabbed pane
        JTabbedPane tabs = new JTabbedPane();
        //add the respective panels
        JPanel buytower = new JPanel();
        JPanel selltower = new JPanel();
        selltower.setLayout(new BoxLayout(selltower, BoxLayout.PAGE_AXIS));
        //labels for the panes
        JLabel buytowerpane = new JLabel();
        buytowerpane.setText("Available Towers");
        JLabel uptowerpane = new JLabel();
        uptowerpane.setText("Sell Towers");
        //add the panes to the tabs
        buytower.add(buytowerpane);
        selltower.add(uptowerpane);
        tabs.setPreferredSize(new Dimension(350, 300));
        //add panes to the overall tab pane
        tabs.addTab("Buy Towers", buytower);
        tabs.addTab("Sell/Upgrade Towers", selltower);
		
		add(tabs);
		tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		//buying panel buttons
		JRadioButton basictower = new JRadioButton("Fire Tower");
        JRadioButton freezingtower = new JRadioButton("Ice Tower");
        JRadioButton monstertower = new JRadioButton("Magic Tower");
        
        JLabel basetower = new JLabel(new ImageIcon("lib/images/tower/BasicTowerx40.png"));
        JLabel freezetower = new JLabel(new ImageIcon("lib/images/tower/FreezingTowerx40.png"));
        JLabel monstower = new JLabel(new ImageIcon("lib/images/tower/MonsterTowerx40.png"));
        
        ButtonGroup group = new ButtonGroup();
        
        group.add(basictower);
        group.add(freezingtower);
        group.add(monstertower);
                
        buytower.add(basictower);
        buytower.add(basetower);
        buytower.add(freezingtower);
        buytower.add(freezetower);
        buytower.add(monstertower);
        buytower.add(monstower);
        
        basictower.addActionListener(this);
        freezingtower.addActionListener(this);
        monstertower.addActionListener(this);
        
        //selling panel        
        JToggleButton sell = new JToggleButton("Sell Tower");
        JToggleButton upgrade = new JToggleButton("Upgrade Tower");
        
        group.add(sell);
        group.add(upgrade);
                
        selltower.add(sell);
        selltower.add(upgrade);
        
        sell.addActionListener(this);
        upgrade.addActionListener(this);
	}
	
	public void addPFPanel(){
		JPanel Playerfunds = new JPanel();
		Playerfunds.add(PF);
		this.add(Playerfunds);
	}
	
	public void updatePF(){
		//will only work if there is a game.
		//temporarily put 1.
		//PF.setText("Current funds: " + view.getController().getGame().getCoins());
		PF.setText("Current funds: " + "1");
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals("Sell Tower")){
			mainboard.sellMode = true;
			mainboard.upgradeMode = false;
			mainboard.buyB = false;
			mainboard.buyF = false;
			mainboard.buyM = false;
		}
		if (e.getActionCommand().equals("Upgrade Tower")){
			mainboard.upgradeMode = true;
			mainboard.sellMode = false;
			mainboard.buyB = false;
			mainboard.buyF = false;
			mainboard.buyM = false;
		}
		if(e.getActionCommand().equals("Fire Tower")){
			mainboard.buyB = true;
			mainboard.sellMode = false;
			mainboard.upgradeMode = false;
			mainboard.buyF = false;
			mainboard.buyM = false;
		}
		if(e.getActionCommand().equals("Ice Tower")){
			mainboard.buyF = true;
			mainboard.sellMode = false;
			mainboard.upgradeMode = false;
			mainboard.buyB = false;
			mainboard.buyM = false;
		}
		if(e.getActionCommand().equals("Magic Tower")){
			mainboard.buyM = true;
			mainboard.sellMode = false;
			mainboard.upgradeMode = false;
			mainboard.buyB = false;
			mainboard.buyF = false;
		}
	}
}
