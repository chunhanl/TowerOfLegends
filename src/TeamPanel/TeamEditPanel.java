package TeamPanel;

import java.awt.Color;

import javax.swing.JFrame;

import card.Card;
import CardTools.CardListPanel;
import FixedPanel.FixedPanel;
import Player.Player;

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
		this.selectedOldTeam();  //把先前的隊員畫起來
	}

	private void selectedOldTeam() {
		int num=1;
		Card[] team=player.getTeam(this.editTeam);
		for(int i=0;i<team.length;i++){
			if(team[i]==null)num++;
			else{
				for(int j=0;j<super.cardLabels.length;j++)
				if(super.cardLabels[j].getCard()==team[i]&&team[i]!=null){
					super.cardLabels[j].addBlackOut(num);
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
