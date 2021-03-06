package presentation;

//import java.awt.Color;
//import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;

//import javax.swing.JLabel;
//import javax.swing.JPanel;
import javax.swing.*;
import tower.*;

/**
 * this class is the panel which holds the Tower Manager (and its respective
 * panels and buttons)
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */

public class PanelGameTowerManager extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private View view;

	private PanelGameBoard mainboard;

	private static JLabel PF = new JLabel("Current funds: "
			+ Character.toString((char) 8353) + 1000);
	private static JLabel WaveNo = new JLabel("Wave: 0");
	private static JLabel PHealth = new JLabel("Health: 0");

	public PanelGameTowerManager(View iview) {

		Dimension dim = new Dimension(View.SCREEN_WIDTH - View.SCREEN_HEIGHT,
				PanelGame.GAME_SCREEN_TOWER_MANAGER_HEIGHT);

		view = iview;

		// this.setBackground(Color.RED);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);

		// add the player funds panel
		JPanel datacontainer = new JPanel();
		JPanel Playerfunds = new JPanel();
		Playerfunds.add(PF);
		Playerfunds.setOpaque(true);
		Playerfunds.setBackground(Color.white);
		datacontainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
		datacontainer.setPreferredSize(new Dimension(350, 30));
		datacontainer.add(WaveNo);
		datacontainer.add(PHealth);
		datacontainer.add(Playerfunds);
		this.add(datacontainer);

		this.add(new JLabel("Tower Manager"));

		// add a tabbed pane
		JTabbedPane tabs = new JTabbedPane();
		// add the respective panels
		JPanel buytower = new JPanel();
		JPanel selltower = new JPanel();
		JPanel target = new JPanel();
		target.setLayout(new GridLayout(4, 1));
		// selltower.setLayout(new GridLayout(0,2));

		// titles for the panes
		JLabel buytowerpane = new JLabel("Available Towers");
		buytowerpane.setPreferredSize(new Dimension(200, 15));
		JLabel buydesc = new JLabel("Hover for more information");
		buydesc.setPreferredSize(new Dimension(200, 10));
		buydesc.setForeground(Color.GRAY);
		JLabel uptowerpane = new JLabel("Select to Sell/Upgrade Towers");
		JLabel targetpane = new JLabel("Choose and click a tower");

		// add the panes to the tabs
		buytower.add(buytowerpane);
		buytower.add(buydesc);

		selltower.add(uptowerpane, BorderLayout.CENTER);
		target.add(targetpane);
		tabs.setPreferredSize(new Dimension(350, 335));

		// add panes to the overall tab pane
		tabs.add("Buy Towers", buytower);
		tabs.addTab("Sell/Upgrade Towers", selltower);
		tabs.addTab("Targeting", target);

		add(tabs);
		tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		// buying panel buttons
		JRadioButton basictower = new JRadioButton("Fire Tower");
		JRadioButton freezingtower = new JRadioButton("Ice Tower");
		JRadioButton monstertower = new JRadioButton("Magic Tower");

		// towers images
		JLabel basetower = new JLabel(new ImageIcon(
				"lib/images/tower/BasicTowerx40.png"));
		JLabel freezetower = new JLabel(new ImageIcon(
				"lib/images/tower/FreezingTowerx40.png"));
		JLabel monstower = new JLabel(new ImageIcon(
				"lib/images/tower/MonsterTowerx40.png"));

		// group will allow only one button to be active at a time.
		ButtonGroup group = new ButtonGroup();

		group.add(basictower);
		group.add(freezingtower);
		group.add(monstertower);

		// new panels for hoverboxes
		JPanel norm = new JPanel();
		JPanel ice = new JPanel();
		JPanel magic = new JPanel();

		norm.add(basictower);
		norm.add(basetower);
		ice.add(freezingtower);
		ice.add(freezetower);
		magic.add(monstertower);
		magic.add(monstower);

		// add display panels (radio buttons) and their cost panels
		buytower.setLayout(new FlowLayout(FlowLayout.LEFT));
		buytower.add(norm);
		buytower.add(initCL(NormalTower.COST));
		buytower.add(ice);
		buytower.add(initCL(FreezingTower.COST));
		buytower.add(magic);
		buytower.add(initCL(MonsterTower.COST));

		basictower.addActionListener(this);
		freezingtower.addActionListener(this);
		monstertower.addActionListener(this);

		// selling panel
		JToggleButton sell = new JToggleButton("Sell Tower");
		JToggleButton upgrade = new JToggleButton("Upgrade Tower");

		group.add(sell);
		group.add(upgrade);

		JPanel buttons = new JPanel();
		buttons.setPreferredSize(new Dimension(350, 50));
		buttons.add(sell);
		buttons.add(upgrade);
		selltower.add(buttons);

		// set hoverboxes for more info (uses HTML)
		norm.setToolTipText("<html><b>Damage: </b>"
				+ (int) (100 * NormalTower.DAMAGE) + "<br><b>Range:</b> "
				+ NormalTower.RANGE + "<br><b>Splash Radius:</b> "
				+ NormalTower.BLAST_RADIUS + "<br><b>Fire Rate: </b>"
				+ NormalTower.FIRE_RATE + "</html>");
		ice.setToolTipText("<html><b>Damage:</b> "
				+ (int) (100 * FreezingTower.DAMAGE) + "<br><b>Range:</b> "
				+ FreezingTower.RANGE + "<br><b>Splash Radius:</b> "
				+ FreezingTower.BLAST_RADIUS + "<br><b>Fire Rate: </b>1"
				+ FreezingTower.FIRE_RATE + "<br><b>Rate of Slowing: </b>"
				+ 100 * FreezingTower.SPECIAL_MOD + "%</html>");
		magic.setToolTipText("<html><b>Damage:</b> "
				+ (int) (100 * MonsterTower.DAMAGE) + "<br><b>Range:</b> "
				+ MonsterTower.RANGE + "<br><b>Splash Radius:</b> "
				+ MonsterTower.BLAST_RADIUS + "<br><b>Fire Rate: </b>"
				+ MonsterTower.FIRE_RATE + "</html>");

		sell.addActionListener(this);
		upgrade.addActionListener(this);

		// targeting behavior panel
		JToggleButton lowest = new JToggleButton("Lowest health critter");
		JToggleButton highest = new JToggleButton("Highest health critter");
		JToggleButton closest = new JToggleButton("Closest critter");
		JToggleButton farthest = new JToggleButton("Farthest critter");

		group.add(lowest);
		group.add(highest);
		group.add(closest);
		group.add(farthest);

		target.add(Box.createRigidArea(new Dimension(0, 5)));
		target.add(lowest);
		target.add(highest);
		target.add(closest);
		target.add(farthest);

		lowest.addActionListener(this);
		highest.addActionListener(this);
		closest.addActionListener(this);
		farthest.addActionListener(this);
		;
	}

	// initializes the funds panel
	public void addPFPanel() {
		// initPF();
		JPanel Playerfunds = new JPanel();
		Playerfunds.add(PF);
		this.add(Playerfunds);
	}

	// gets and updates with the values of funds, health, and wave.
	public void updatePF() {
		PF.setText("Current funds: " + Character.toString((char) 8353)
				+ view.getController().getGame().getCoins());
		WaveNo.setText("Current Wave: "
				+ view.getController().getGame().getWaveNo());
		if (view.getController().getGame().getHealth() <= 20) {
			PHealth.setText("<html>Health: <font color=red>"
					+ view.getController().getGame().getHealth()
					+ "</font></html>");
		} else if (view.getController().getGame().getHealth() <= 50) {
			PHealth.setText("<html>Health: <font color=#FFD700>"
					+ view.getController().getGame().getHealth()
					+ "</font></html>");
		} else {
			PHealth.setText("<html>Health: <font color=green>"
					+ view.getController().getGame().getHealth()
					+ "</font></html>");
		}
	}

	/*
	 * //fake 'padding' public static JLabel initPadding(){ JLabel padding = new
	 * JLabel("<html><br></html>"); //padding.setOpaque(true);
	 * //padding.setBackground(Color.white); //padding.setPreferredSize(new
	 * Dimension(i,15)); return padding; }
	 */

	// creates cost panels
	public static JLabel initCL(int i) {
		JLabel label = new JLabel("Cost: " + Character.toString((char) 8353)
				+ i);
		label.setPreferredSize(new Dimension(100, 30));
		return label;
	}

	// finds which button is activated
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Sell Tower")) {
			mainboard.sellMode = true;
			mainboard.upgradeMode = false;
			mainboard.buyB = false;
			mainboard.buyF = false;
			mainboard.buyM = false;
			mainboard.lowest = false;
			mainboard.highest = false;
			mainboard.closest = false;
			mainboard.farthest = false;
		}
		if (e.getActionCommand().equals("Upgrade Tower")) {
			mainboard.upgradeMode = true;
			mainboard.sellMode = false;
			mainboard.buyB = false;
			mainboard.buyF = false;
			mainboard.buyM = false;
			mainboard.lowest = false;
			mainboard.highest = false;
			mainboard.closest = false;
			mainboard.farthest = false;
		}
		if (e.getActionCommand().equals("Fire Tower")) {
			mainboard.buyB = true;
			mainboard.sellMode = false;
			mainboard.upgradeMode = false;
			mainboard.buyF = false;
			mainboard.buyM = false;
			mainboard.lowest = false;
			mainboard.highest = false;
			mainboard.closest = false;
			mainboard.farthest = false;
		}
		if (e.getActionCommand().equals("Ice Tower")) {
			mainboard.buyF = true;
			mainboard.sellMode = false;
			mainboard.upgradeMode = false;
			mainboard.buyB = false;
			mainboard.buyM = false;
			mainboard.lowest = false;
			mainboard.highest = false;
			mainboard.closest = false;
			mainboard.farthest = false;
		}
		if (e.getActionCommand().equals("Magic Tower")) {
			mainboard.buyM = true;
			mainboard.sellMode = false;
			mainboard.upgradeMode = false;
			mainboard.buyB = false;
			mainboard.buyF = false;
			mainboard.lowest = false;
			mainboard.highest = false;
			mainboard.closest = false;
			mainboard.farthest = false;
		}
		if (e.getActionCommand().equals("Lowest health critter")) {
			mainboard.buyM = false;
			mainboard.sellMode = false;
			mainboard.upgradeMode = false;
			mainboard.buyB = false;
			mainboard.buyF = false;
			mainboard.lowest = true;
			mainboard.highest = false;
			mainboard.closest = false;
			mainboard.farthest = false;
		}
		if (e.getActionCommand().equals("Highest health critter")) {
			mainboard.buyM = false;
			mainboard.sellMode = false;
			mainboard.upgradeMode = false;
			mainboard.buyB = false;
			mainboard.buyF = false;
			mainboard.lowest = false;
			mainboard.highest = true;
			mainboard.closest = false;
			mainboard.farthest = false;
		}
		if (e.getActionCommand().equals("Closest critter")) {
			mainboard.buyM = false;
			mainboard.sellMode = false;
			mainboard.upgradeMode = false;
			mainboard.buyB = false;
			mainboard.buyF = false;
			mainboard.lowest = false;
			mainboard.highest = false;
			mainboard.closest = true;
			mainboard.farthest = false;
		}
		if (e.getActionCommand().equals("Farthest critter")) {
			mainboard.buyM = false;
			mainboard.sellMode = false;
			mainboard.upgradeMode = false;
			mainboard.buyB = false;
			mainboard.buyF = false;
			mainboard.lowest = false;
			mainboard.highest = false;
			mainboard.closest = false;
			mainboard.farthest = true;
		}
	}

	public void update() {

		if (mainboard == null) {
			mainboard = view.getMainPanel().getPanelGame().getPanelGameBoard();
		}
		this.updatePF();
	}
}
