package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class PanelGameOptions extends JPanel{

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
	
	private View view;

	public PanelGameOptions(final View view){


		Dimension dim=new Dimension(View.SCREEN_WIDTH-View.SCREEN_HEIGHT, View.SCREEN_HEIGHT-PanelGame.GAME_SCREEN_TOWER_MANAGER_HEIGHT);
		
		this.view=view;
		
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
		

		//Add button functionality
		bPlayWave.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            try {
					view.getController().getGame().generateWave();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	         }          
	      });

	}

	public View getView() {
		return view;
	}
}
