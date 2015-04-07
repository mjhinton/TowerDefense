package presentation;

import java.util.Hashtable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import map.Map;
import model.Game;

/**
 * This is the options panel while a game is being played. allows for pausing,
 * restarting, adjustment of sound, exiting--anything unrelated to the core game
 * playing.
 * 
 * @authors Saahil Hamayun, Michael Hinton, Solvie Lee, Jenna Mar
 */

public class PanelGameOptions extends JPanel implements ChangeListener {

	private static final long serialVersionUID = 1L;

	private JButton bPlayWave;
	private JButton bResumePause;
	private JButton bSave;
	private JButton bRestart;
	private JButton bMenu;
	private JComboBox cbSpeed;
	private JSlider sSound;

	private boolean isPaused;

	private JPanel pnButtonsContainer;
	private JPanel pnSound;
	// edited temporarily
	private View goView;

	public PanelGameOptions(final View view) {

		Dimension dim = new Dimension(View.SCREEN_WIDTH - View.SCREEN_HEIGHT,
				View.SCREEN_HEIGHT - PanelGame.GAME_SCREEN_TOWER_MANAGER_HEIGHT);

		this.goView = view;

		this.setLayout(new BorderLayout());
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.isPaused = false;

		// initiate buttons and such
		bPlayWave = new JButton("Play Wave");
		bResumePause = new JButton("Pause");
		bSave = new JButton("Save");
		bRestart = new JButton("Restart");
		bMenu = new JButton("Main Menu");
		String[] speedOptions = { "Speed X1", "Speed X2", "Speed X4",
				"Speed X6", "Speed X10" };
		cbSpeed = new JComboBox(speedOptions);
		sSound = new JSlider(0, 10);
		sSound.setValue(5);

		// add them to the panel
		pnSound = new JPanel();
		pnSound.add(new JLabel("Sound"));
		pnSound.add(sSound);
		pnButtonsContainer = new JPanel(new GridLayout(3, 2));
		pnButtonsContainer.setPreferredSize(new Dimension((int) (this.getSize()
				.getWidth() * 2 / 3), (int) (this.getSize().getHeight())));
		pnButtonsContainer.add(bResumePause);
		pnButtonsContainer.add(bSave);
		pnButtonsContainer.add(cbSpeed);
		pnButtonsContainer.add(pnSound);
		pnButtonsContainer.add(bRestart);
		pnButtonsContainer.add(bMenu);

		this.add(pnButtonsContainer, BorderLayout.CENTER);
		this.add(bPlayWave, BorderLayout.WEST);

		bMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int dialogChosen = JOptionPane
						.showConfirmDialog(null,
								"Return to main menu? Remember to save before exiting.");
				if (dialogChosen == JOptionPane.YES_OPTION) {
					goView.switchPanel("PanelMenu");
				}
			}
		});

		bPlayWave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				Game agame = goView.getController().getGame();
				final Map amap = agame.getBoard().getMap();
				Thread athread = new Thread() {
					public void run() {
						// do something
						goView.getController().playGame(amap);
					}
				};
				athread.start();

				/*
				 * //playGame is a method in Controller that starts a game and
				 * generates a wave //if the game hasn't already been started,
				 * and if the game has already been started //it generates a
				 * wave.
				 * 
				 * //goView.getController().playGame(amap);
				 */

			}
		});

		cbSpeed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				JComboBox cb = (JComboBox) event.getSource();
				String speed = (String) cb.getSelectedItem();
				if (speed.equals("Speed X1")) {
					goView.getController().setGameSpeed(1);
				} else if (speed.equals("Speed X2")) {
					goView.getController().setGameSpeed(2);
				} else if (speed.equals("Speed X4")) {
					goView.getController().setGameSpeed(4);
				} else if (speed.equals("Speed X6")) {
					goView.getController().setGameSpeed(6);
				} else if (speed.equals("Speed X10")) {
					goView.getController().setGameSpeed(10);
				}
			}
		});

		bRestart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int dialogChosen = JOptionPane.showConfirmDialog(null,
						"Confirm game restart?");
				if (dialogChosen == JOptionPane.YES_OPTION) {
					Map map = goView.getController().getGame().getBoard()
							.getMap();
					goView.getController().startGame(map);
				}
			}
		});

		bResumePause.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent event) {
				goView.getController().pausePlay();
				if (isPaused) {
					isPaused = false;
					bResumePause.setLabel("Pause");
				} else {
					isPaused = true;
					bResumePause.setLabel("Resume");
				}
			}
		});

		bSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out
						.println("This functionality has not yet been added.");
				// goView.getController().getGame().saveGame();
			}
		});

		// the slider is too long. adjust dimension
		sSound.setPreferredSize(new Dimension(160, 30));
		// make labels
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(0), new JLabel("-"));
		labelTable.put(new Integer(10), new JLabel("+"));
		sSound.setLabelTable(labelTable);
		sSound.setPaintLabels(true);

		sSound.addChangeListener(this);

	}

	public View getView() {
		return goView;
	}

	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if (!source.getValueIsAdjusting()) {
			int vol = source.getValue();
			double z = vol;
			if (vol == 0) {
				SoundPlayer.setPaused(true);
			} else {
				SoundPlayer.setPaused(false);
				SoundPlayer.setVolume(z);
			}
		}
	}
}
