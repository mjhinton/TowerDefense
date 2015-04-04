package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import map.Map;
import model.Game;

public class PanelGameOptions extends JPanel implements ChangeListener{

	private static final long serialVersionUID = 1L;
	
	private JButton bPlayWave;
	private JButton bResumePause;
	private JButton bSave;
	private JButton bRestart;
	private JButton bExit;
	private JComboBox cbSpeed;
	private JSlider sSound;
	
	private JPanel pnButtonsContainer;
	private JPanel pnSound;
	//edited temporarily
	private View goView;

	public PanelGameOptions(View view){


		Dimension dim=new Dimension(View.SCREEN_WIDTH-View.SCREEN_HEIGHT, View.SCREEN_HEIGHT-PanelGame.GAME_SCREEN_TOWER_MANAGER_HEIGHT);
		
		this.goView=view;
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		//initiate buttons and such
		bPlayWave=new JButton("Play Wave");
		bResumePause=new JButton("Pause");
		bSave=new JButton("Save");
		bRestart=new JButton("Restart");
		bExit=new JButton("Exit");
		String [] speedOptions={"Speed X1","Speed X2","Speed X4"};
		cbSpeed=new JComboBox(speedOptions);
		sSound=new JSlider();
		
		//add them to the panel
		pnSound=new JPanel();
		pnSound.add(new JLabel("Sound"));
		pnSound.add(sSound);
		pnButtonsContainer=new JPanel(new GridLayout(3,2));
		pnButtonsContainer.setPreferredSize(new Dimension((int)(this.getSize().getWidth()*2/3),(int)(this.getSize().getHeight())));
		pnButtonsContainer.add(bResumePause);
		pnButtonsContainer.add(bSave);
		pnButtonsContainer.add(cbSpeed);
		pnButtonsContainer.add(pnSound);
		pnButtonsContainer.add(bRestart);
		pnButtonsContainer.add(bExit);
		
		this.add(pnButtonsContainer, BorderLayout.CENTER);
		this.add(bPlayWave,BorderLayout.WEST);
		
		bExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });	
		
		bPlayWave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				// generate a wave.
				Game agame = goView.getController().getGame();
				Map amap = agame.getBoard().getMap();
				
				goView.getController().playGame(amap);

			}
		});
		
		sSound.addChangeListener(this);
	}

	public View getView() {
		return goView;
	}
	
	public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
    }
}
