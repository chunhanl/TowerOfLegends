package CardTools;

import javax.swing.JFrame;
import javax.swing.JLabel;

import card.Card;
import FixedPanel.FixedPanel;
import MidScrollPanel.MidScrollPanel;
import Player.Player;

public class CardListPanel extends MidScrollPanel{

	private Player player;
	protected Card[]	playerCardList;
	protected CardLabel[] cardLabels;
	private int playerBagCapacity;

	public CardListPanel(JFrame frame, FixedPanel fpanelREF) {
		super(frame, fpanelREF);
		player=fpanelREF.getPlayer();
		playerBagCapacity =player.getBagCapacity();
		playerCardList  =player.getBagCards();
		cardLabels=new CardLabel[playerBagCapacity];
		
		int BagRow =playerBagCapacity/5;
		if(playerBagCapacity%5!=0){BagRow++;}
		
		this.buildPanelRows( BagRow);
				
		for(int i=0;i< playerBagCapacity;i++)
		{
			cardLabels[i]=new CardLabel(this,this.fixedpanelREF,playerCardList[i]);  //for rolling on heads
			cardLabels[i].setLocation(80*(i%5),0);
			this.panelRows[i/5].add(cardLabels[i]);
			
		}
		this.adjustSizeY(80*this.panelRows.length);

	}
	


	public void setAllChoosable(){
		for(int i=0;i<playerCardList.length;i++){
			cardLabels[i].setChoosable(true);
		}
	}

}
