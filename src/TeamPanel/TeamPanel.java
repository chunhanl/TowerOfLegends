package TeamPanel;
import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import card.Card;
import CardTools.CardLabel;
import FixedPanel.FixedPanel;
import MidScrollPanel.MidScrollPanel;
import Player.Player;
import SocialPanel.SocialImageLoader;


public class TeamPanel extends JPanel{
	
	private JFrame myframeREF;
	private Player player;
	private JLabel backGround;
	private JLabel cardblank;
	private JLabel editteam;
	private MidScrollPanel midPanel;
	private TeamChoosePanel teamPanel;
	private TeamEditPanel editPanel;
	protected boolean	isChoosing;

	
	private FixedPanel fixedpanelREF;
		
	public TeamPanel(JFrame frame,FixedPanel f)
	{
		super(null);
		player=f.getPlayer(); // delete when finish define player
		myframeREF=frame;
		fixedpanelREF=f;
		isChoosing=false;
		
		this.setSize(frame.getWidth(),frame.getHeight());		
		this.repaint();
		this.setOpaque(false);
		
		buildTeamPanel();
		//buildBackGround();
	}
	
	private void buildTeamPanel() {
		teamPanel=new TeamChoosePanel();
		midPanel=new MidScrollPanel(myframeREF,fixedpanelREF);
		buildEditTeamPanel();
		this.add(teamPanel);
	}


	
	public boolean isChoosingTeam() {
		return isChoosing;
	}

	public void exitEditTeamPanel(){   // for back to main button
		if(isChoosing){
			this.refreshTeam();
			this.remove(editPanel);
			editPanel=null;
			isChoosing=false;
		}
	}
	
	private void buildEditTeamPanel() {
		editteam=new JLabel();
		editteam.setIcon(TeamImageLoader.editteam);
		editteam.addMouseListener(new EditPanelListener(fixedpanelREF,editteam));
		editteam.setSize(212,104);
		editteam.setLocation(288,450);
		this.add(editteam);

	}



	public void showEditPanel() {
		editPanel=new TeamEditPanel(myframeREF,fixedpanelREF);
		this.add(editPanel,0);
		editPanel.setVisible(true);
		isChoosing=true;	
	}
	
	public void setVisible(boolean b) {
		if(b){
			super.setVisible(true);
			this.teamPanel.setVisible(true);
			this.fixedpanelREF.showUpBar("¶¤¥î");
		}
		else{
			this.teamPanel.setVisible(false);
			
			if(isChoosing){
				this.remove(editPanel);
				isChoosing=false;
			}
			super.setVisible(false);
		}	
	}

	public void refreshTeam() {
		this.teamPanel.refreshTeam();
	}
	
	private class TeamChoosePanel extends JPanel{
		private int 				time;
		private boolean 			isInPosition;
		private Timer 				animationTimer;
		private RunAnimationTask	runTask;
		
		private JPanel				teamContainer;
		private Card[]				cardList;
		private CardLabel[]			cardLabel;
		
		private JLabel[]			teamdata;			
	
		
		
		public TeamChoosePanel(){
			super();
			this.setLayout(null);
			this.setSize(500, 600);
			this.setLocation(0, 90);
			this.setBackground(Color.gray);
			this.isInPosition=false;
			this.setOpaque(false);
	
			this.buildCardList();
			this.buildBackGround();
			
		}
		
		private void buildCardList() {
			teamContainer=new JPanel();
			teamContainer.setOpaque(false);
			teamContainer.setLayout(null);
			cardblank=new JLabel(TeamImageLoader.cardblank);
			cardblank.setSize(478, 243);
			cardblank.setLocation(10,100);
			
			pluginMembers();
			fillinDatas();
			teamContainer.setSize(500, 510);
			teamContainer.add(cardblank);
			this.add(teamContainer);
		}
		
		private void pluginMembers(){
			cardList=player.getTeam(0);
			if(cardLabel==null)cardLabel=new CardLabel[6];
			else teamContainer.remove(0); //for refresh
			
			for(int i=0;i<6;i++){
				cardLabel[i]=new CardLabel(null,fixedpanelREF,cardList[i]);
				cardLabel[i].setLocation(80*i+10,120);
				teamContainer.add(cardLabel[i],0);
				teamContainer.repaint();
			}
		}
		public void buildBackGround(){
			backGround=new JLabel( TeamImageLoader.backGround);
			backGround.setLocation(0,0);
			backGround.setSize(500,600);
			this.add(backGround);
			this.repaint();
		}
		private void fillinDatas() {	
			cardList=player.getTeam(0);
			if(teamdata==null)teamdata=new JLabel[8];
			else for(int i=0;i<8;i++)cardblank.remove(teamdata[i]);   //for refresh		
			
			teamdata=new JLabel[8];
			for(int i=0;i<8;i++){
				teamdata[i]=new JLabel("",SwingConstants.RIGHT);
				teamdata[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
				teamdata[i].setForeground(Color.white);
				teamdata[i].setSize(120,50);
			}
			
			int space=0;
			int health=0;
			int recover=0;
			int fattack=0;
			int wattack=0;
			int dattack=0;
			int tattack=0;
			int lattack=0;
					
			for(int i=0;i<6;i++){
				if(cardList[i]!=null){
					space+=cardList[i].getPackSpace();
					health+=cardList[i].getHealth();
					recover+=cardList[i].getRecover();
					System.out.println(cardList[i].getCharactistic());
					switch(cardList[i].getCharactistic()){
						case "M":{
							tattack+=cardList[i].getAttack();
							break;
						}
						case "W":{
							wattack+=cardList[i].getAttack();
							break;
						}
						case "F":{
							fattack+=cardList[i].getAttack();
							break;
						}
						case "D":{
							dattack+=cardList[i].getAttack();
							break;
						}
						case "L":{
							lattack+=cardList[i].getAttack();
							break;
						}
					}
				}
			}
			teamdata[0].setText(String.valueOf(space));
			teamdata[0].setLocation(110,120);
			teamdata[1].setText(String.valueOf(recover));
			teamdata[1].setLocation(110,145);
			teamdata[2].setText(String.valueOf(fattack));
			teamdata[2].setLocation(110,170);
			teamdata[3].setText(String.valueOf(wattack));
			teamdata[3].setLocation(110,195);
			
			teamdata[4].setText(String.valueOf(health));
			teamdata[4].setLocation(330,120);
			teamdata[5].setText(String.valueOf(tattack));
			teamdata[5].setLocation(330,145);			
			teamdata[6].setText(String.valueOf(lattack));
			teamdata[6].setLocation(330,170);
			teamdata[7].setText(String.valueOf(dattack));
			teamdata[7].setLocation(330,195);
						
			for(int i=0;i<8;i++)cardblank.add(teamdata[i],0);
			
			cardblank.repaint();
		}
		
		public void refreshTeam() {
			this.pluginMembers();
			this.fillinDatas();
		}
		

		
		
		public void setVisible(boolean b) {
			if(b) {
				if(!isInPosition)this.runAnimation();
				midPanel.setVisible(false);
			}
			else this.hidePanels();
		}
		


		private void hidePanels() {
			teamContainer.setLocation(-500, 0);
			this.isInPosition=false;
		}

		private void runAnimation() {
			fixedpanelREF.setButtonDisable();
			int dx=10;
			int period=3;
			animationTimer=new Timer();
			runTask=new RunAnimationTask(dx);
			animationTimer.schedule(runTask,0,period);		

		}	
		
		private class RunAnimationTask extends TimerTask{
			private int dx;
			public RunAnimationTask(int dx) {
				this.dx=dx;
			}
			
			public void run() {
				if(isInPosition==false){
						if(teamContainer.getX()<0){
							teamContainer.setLocation(-500+time*dx, 0 );
							time++;
							fixedpanelREF.repaint();
						}
						

						else{
							isInPosition = true;	
							time=0;
							fixedpanelREF.setButtonEnable();
							animationTimer.cancel();
							runTask.cancel();
						}
				}
				//isInPosition=true
				else{
					isInPosition = true;	
					time=0;
					fixedpanelREF.setButtonEnable();
					animationTimer.cancel();
					runTask.cancel();
				}
			}
			
		}
	}









	
	
}
