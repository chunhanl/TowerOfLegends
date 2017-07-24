package TeamPanel;

import java.awt.Color;

import javax.swing.JFrame;

import card.Card;
import CardTools.CardListPanel;
import FixedPanel.FixedPanel;
import MyClient.Player;

public class TeamEditPanel extends CardListPanel{

	JFrame frameREF;
	FixedPanel fpanelREF;
	Player player;
	int editTeam =0;
	
	public TeamEditPanel(JFrame frame, FixedPanel fpanel) {
		super(frame, fpanel);
		this.frameREF=frame;
		this.fpanelREF=fpanel;
		this.player=fpanelREF.getPlayer();
		this.setBackground(Color.BLUE);
		this.setAllChoosable();
		this.selectedOldTeam();  //����e�������e�_��
	}

	private void selectedOldTeam() {
		int num=1;
		Card[] team=player.getTeam(this.editTeam);
		
		for(int i=0;i<team.length;i++)
			if(team[i]==null)num++;
			else team[i].setSelected(false);
		
		for(int i=0;i<team.length;i++){
			if(team[i]==null)num++;
			else{
				for(int j=0;j<player.getCardAmount();j++)
				if(super.cardLabels[j].getCard().getId()==team[i].getId()&&team[i]!=null && !team[i].isSelected()){
					super.cardLabels[j].addBlackOut(num);
					System.out.println(cardLabels[j].getCard()+"black");
					team[i].setSelected(true);
					num++;
				}
			}
		}
	}

	public void setVisible(boolean b){
		if(b){
			super.setVisible(true);
			this.runAnimation();
		}
		else{
			super.setVisible(false);
			this.hidePanels();
			fpanelREF.teampanelREF.refreshTeam();
		}
	}
}
