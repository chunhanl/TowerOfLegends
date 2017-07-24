package ShopPanel;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import FixedPanel.FixedPanel;
import MidScrollPanel.MidScrollPanel;
import Player.Player;


public class ShopPanel extends JPanel{
	
	private JFrame myframeREF;
	private Player player;
	private JLabel backGround;
	private MidScrollPanel midPanel;
	private FixedPanel fixedpanelREF;
	
	
	public ShopPanel(JFrame frame,FixedPanel f)
	{
		super(null);
		this.player=f.getPlayer();
		this.myframeREF=frame;
		this.fixedpanelREF=f;
		this.setSize(frame.getWidth(),frame.getHeight());		
		this.repaint();
		
		buildShopPanel();
		buildBackGround();
	}
	
	private void buildShopPanel() {
		midPanel=new MidScrollPanel(myframeREF,fixedpanelREF);
		midPanel.buildPanelRows(5);
		midPanel.setPanelSkin(ShopImageLoader.panelrowimage);
		this.add(midPanel);
	}

	public void buildBackGround(){
		backGround=new JLabel( ShopImageLoader.backGround);
		backGround.setLocation(0,0);
		backGround.setSize(myframeREF.getWidth(),myframeREF.getHeight());
		this.add(backGround);
		this.repaint();
	}

	public void setVisible(boolean b) {
		if(b){
			super.setVisible(true);
			this.fixedpanelREF.showUpBar("�ө�");
			this.midPanel.runAnimation();

		}
		else{
			this.midPanel.hidePanels();
			super.setVisible(false);
		}
	}
	
	
}
