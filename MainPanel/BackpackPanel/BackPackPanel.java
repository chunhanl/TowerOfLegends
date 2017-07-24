package BackpackPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import card.Card;
import CardTools.CardLabel;
import CardTools.CardListPanel;
import FixedPanel.FixedPanel;
import FixedPanel.UpScrollPanel;
import MidScrollPanel.MidScrollPanel;
import MyClient.Player;




public class BackPackPanel extends JPanel/* implements MouseListener*/{
	
	private JFrame myframeREF;
	private FixedPanel fixedpanelREF;
	private Player player;

	private JLabel backGround;
	public  CardListPanel cardContainer;

	
	
	private BackPackPanel thisPanel;

	//for animation
	private Timer animationTimer;

	//for animation
	
	public BackPackPanel(JFrame frame,FixedPanel f)
	{
		super(null);
		player=f.getPlayer(); // delete when finish define player
		myframeREF=frame;
		fixedpanelREF=f;
		thisPanel=this;

		
		this.setSize(frame.getWidth(),frame.getHeight());	
		this.setLocation(0, 0);
		this.repaint();
		

		buildCardPanel();
		buildBackGround();
		
	}
	


	public void buildBackGround(){
		backGround=new JLabel( BackPackImageLoader.backGround);
		backGround.setSize(myframeREF.getWidth(),myframeREF.getHeight());
		backGround.setLocation(0,0);
		this.add(backGround);
		this.repaint();
	}

	public void buildCardPanel(){
		cardContainer=new CardListPanel(this.myframeREF,this.fixedpanelREF);
		this.add(cardContainer);

	}


	public void setVisible(boolean b) {
		if(b){
			super.setVisible(true);
			this.fixedpanelREF.showUpBar("­I¥]");
			this.cardContainer.setVisible(true);
			this.cardContainer.refresh();
			this.cardContainer.runAnimation();

		}
		else{
			this.cardContainer.hidePanels();
			this.cardContainer.setVisible(false);
			super.setVisible(false);
		}
	}


}
