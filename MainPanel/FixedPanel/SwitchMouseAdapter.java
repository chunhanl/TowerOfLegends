package FixedPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Sound;
import BackpackPanel.BackPackPanel;
import FixedPanel.FixedPanel;
import MyClient.Player;


public class SwitchMouseAdapter extends MouseAdapter{
	//data member
	private boolean enable;
	
	private SwitchableLabel listenLabel;
	private FixedDownPanel fpanelREF;
	private JFrame frameREF;
	
	private ImageIcon unpressed;
	private ImageIcon pressed;

	private String 	destinationPanel;
	//constructor
	public SwitchMouseAdapter(SwitchableLabel lab,FixedDownPanel panel,JFrame frame ,String destination){
		destinationPanel=destination;
		listenLabel = lab;
		fpanelREF = panel;
		unpressed=listenLabel.getImageIcon();
		pressed =listenLabel.getPressedImageIcon();
		enable=true;
		frameREF =frame;
	}
	
	//method
	public void mousePressed(MouseEvent f) 
	{
		if(f.getSource()==listenLabel &&enable){
			listenLabel.setIcon(pressed);
			fpanelREF.repaint();
		}
		new Sound("main/click.wav").play();

	}
	
	public void mouseReleased(MouseEvent f) 
	{
		System.out.println(enable);
		if(f.getSource()==listenLabel &&enable){
			listenLabel.setIcon(unpressed);
			frameREF.repaint();
			//Screen Changed
			fpanelREF.getFixedPanel().setAllInvisible();
					
			switch (destinationPanel){
				case "backpack" : {
					if(!fpanelREF.getFixedPanel().backpackpanelREF.isVisible()){
						fpanelREF.getFixedPanel().backpackpanelREF.setVisible(true);  
						break; 
					}
				}
				case "shop" : {
					if(!fpanelREF.getFixedPanel().shoppanelREF.isVisible()){
						fpanelREF.getFixedPanel().shoppanelREF.setVisible(true);
						break;
					}
				}
				case "social" : {
					if(!fpanelREF.getFixedPanel().socialpanelREF.isVisible()){
						fpanelREF.getFixedPanel().socialpanelREF.setVisible(true);
						break;
					}
				}
				
				case "team" : {
					if(!fpanelREF.getFixedPanel().teampanelREF.isVisible()){
						fpanelREF.getFixedPanel().teampanelREF.setVisible(true);
						break;
					}
				}
	

			}
		
		}			
			
		
	}
	
	public void setDisable(){
		enable=false;
	}
	public void setEnable(){
		enable=true;
	}
}
