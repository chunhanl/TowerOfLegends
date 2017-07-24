package FixedPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Sound;
import math.vec2;
import FixedPanel.SwitchMouseAdapter;
import FixedPanel.SwitchableLabel;

public class UpScrollPanel extends JPanel implements MouseListener{
	
	private SwitchableLabel backToMain;
	private JLabel informationBar;
	private JLabel information;

	
	private FixedPanel fpanelREF;
	private JFrame myframeREF;
	
	public UpScrollPanel(JFrame frame,FixedPanel container){
		this.myframeREF=frame;
		this.fpanelREF=container;
		this.setLayout(null);
		this.setSize(500,100);
		this.setLocation(0,70);
		this.setOpaque(false);

		buildInformation();
		buildBackToMain();
		buildInformationBar();

			
	}
	

	private void buildBackToMain() {
		this.backToMain=new SwitchableLabel(UpScrollPanelImageLoader.backToMainPoint,UpScrollPanelImageLoader.backToMainPointPressed,new vec2(5,30),new vec2(70,70));
		backToMain.addMouseListener(this);
		this.add(backToMain);
	}

	public void buildInformationBar(){
		this.informationBar=new JLabel();
		informationBar.setIcon(UpScrollPanelImageLoader.informationBar);
		informationBar.setSize(500,67);
		informationBar.setLocation(10, 20);
		this.add(informationBar);
	}
	
	public void buildInformation(){
		this.information=new JLabel("");	
		this.information.setForeground(Color.white);
		this.information.setFont(new Font("·L³n¥¿¶ÂÅé", Font.BOLD, 30));
		this.information.setSize(500, 80);
		this.information.setLocation(220,20);
		this.add(information);
	}
	
	public void setInformationBar(String word){
		this.information.setText(word);
	}


	public void mousePressed(MouseEvent e) {
		if(e.getSource()==backToMain){
			backToMain.setIcon(UpScrollPanelImageLoader.backToMainPointPressed);
			new Sound("main/return.wav").play();
		}
		myframeREF.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==backToMain){
			backToMain.setIcon(UpScrollPanelImageLoader.backToMainPoint);
			fpanelREF.backButtonPressed();
		}
		myframeREF.repaint();
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e){}

	

	
	
}	
