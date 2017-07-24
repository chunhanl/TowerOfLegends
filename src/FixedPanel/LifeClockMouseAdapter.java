package FixedPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BackpackPanel.BackPackPanel;
import FixedPanel.FixedPanel;
import MainPanel.MainPanel;
import Player.Player;


public class LifeClockMouseAdapter extends MouseAdapter{
	//data member
	private boolean enable;
	
	private LifeClock listenLabel;
	private FixedDownPanel fpanelREF;
	private JFrame frameREF;
	
	private ImageIcon unpressed;
	private ImageIcon pressed;
	private Player player;
	
	//constructor
	public LifeClockMouseAdapter(LifeClock lab,FixedDownPanel panel,Player p,JFrame frame){
		enable=true;
		listenLabel = lab;
		fpanelREF = panel;
		unpressed=listenLabel.getImageIcon();
		pressed =listenLabel.getPressedImageIcon();
		player=p;
		frameREF =frame;
	}
	
	//method
	public void mousePressed(MouseEvent f) 
	{
		if(f.getSource()==listenLabel &&enable){
			listenLabel.circle.setIcon(pressed);
			fpanelREF.repaint();
		}

	}
	
	public void mouseReleased(MouseEvent f) 
	{
		if(f.getSource()==listenLabel &&enable){
			
		
			listenLabel.circle.setIcon(unpressed);

		//Screen Changed
			fpanelREF.getFixedPanel().setAllInvisible();
			fpanelREF.getFixedPanel().mainpanelREF.setVisible(true); //animation adds here
			
		}
	
	}
	
	public void setDisable(){
		enable=false;
	}
	public void setEnable(){
		enable=true;
	}
}