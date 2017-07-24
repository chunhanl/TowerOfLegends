package SocialPanel;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import FixedPanel.FixedPanel;
import MidScrollPanel.MidScrollPanel;
import MyClient.Player;


public class SocialPanel extends JPanel{
	
	private JFrame myframeREF;
	private Player player;
	private JLabel backGround;
	private MidScrollPanel midPanel;
	private FixedPanel fixedpanelREF;
	
	
	public SocialPanel(JFrame frame,FixedPanel f)
	{
		super(null);
		this.player=f.getPlayer();
		this.myframeREF=frame;
		this.fixedpanelREF=f;
		this.setSize(frame.getWidth(),frame.getHeight());		
		this.repaint();
		
		buildSocialPanel();
		buildBackGround();
	}
	
	private void buildSocialPanel() {
		midPanel=new MidScrollPanel(myframeREF,fixedpanelREF);
		midPanel.buildPanelRows(5);
		midPanel.setPanelSkin(SocialImageLoader.panelrowimage);
		
		
		
		this.add(midPanel);
	}

	public void buildBackGround(){
		backGround=new JLabel( SocialImageLoader.backGround);
		backGround.setLocation(0,0);
		backGround.setSize(myframeREF.getWidth(),myframeREF.getHeight());
		this.add(backGround);
		this.repaint();
	}

	public void setVisible(boolean b) {
		if(b){
			super.setVisible(true);
			this.fixedpanelREF.showUpBar("ªÀ¸s");
			this.midPanel.runAnimation();

		}
		else{
			this.midPanel.hidePanels();
			super.setVisible(false);
		}
	}
	
	
}
