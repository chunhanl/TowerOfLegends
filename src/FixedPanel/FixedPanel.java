package FixedPanel;
import java.util.Timer;
import java.util.TimerTask;

import math.vec2;

import javax.swing.JFrame;
import javax.swing.JPanel;

import BackpackPanel.BackPackPanel;
import ChatRoom.MyPanel;
import MainPanel.MainImageLoader;
import MainPanel.MainPanel;
import MainPanel.MainPanel;
import Player.Player;
import ShopPanel.ShopPanel;
import SocialPanel.SocialPanel;
import TeamPanel.TeamPanel;


//最底層

public class FixedPanel extends JPanel{
	
	public JFrame myframeREF;
	private Player player;
	
	private MyPanel chatroomREF;
	public MainPanel mainpanelREF;
	public BackPackPanel backpackpanelREF;
	public ShopPanel shoppanelREF;
	public SocialPanel socialpanelREF;
	public TeamPanel teampanelREF;
	
	private FixedDownPanel fixeddownpanelREF;
	private FixedUpPanel fixeduppanelREF;
	private UpScrollPanel upBarREF;

	
	private String currentPanel;
	
	public FixedPanel(JFrame frame,Player p){		
		super(null); 
		
		player=p;
		myframeREF = frame;  
		this.setSize(frame.getWidth(),frame.getHeight());		
		this.repaint();
		
		//chatroomREF			=new MyPanel(frame,this);
		shoppanelREF		=new ShopPanel(frame,this);
		socialpanelREF		=new SocialPanel(frame,this);
		mainpanelREF		=new MainPanel(frame,this);
		teampanelREF		=new TeamPanel(frame,this);
		backpackpanelREF	=new BackPackPanel(frame,this);
		fixeddownpanelREF	=new FixedDownPanel(frame,this); //給予主控權
		fixeduppanelREF		=new FixedUpPanel(frame,this);
		upBarREF 			=new UpScrollPanel(frame,this);
		

		
		this.add(new JPanel(),0);
		//this.add(chatroomREF,0);
		this.add(upBarREF,1);
		this.add(fixeduppanelREF,2);
		this.add(fixeddownpanelREF,3);
		this.add(mainpanelREF,4);
		this.add(backpackpanelREF,5);
		this.add(teampanelREF,6);
		this.add(socialpanelREF,7);
		this.add(shoppanelREF,8);
		
		this.setAllInvisible();
		mainpanelREF.setVisible(true);
	}


	
	public void setAllInvisible() {
		socialpanelREF.setVisible(false);
		upBarREF.setVisible(false);	
		backpackpanelREF.setVisible(false);
		mainpanelREF.setVisible(false);
		teampanelREF.setVisible(false);
		shoppanelREF.setVisible(false);
	}

	
	
	public void showUpBar(String word){
		upBarREF.setInformationBar(word);
		upBarREF.setVisible(true);
	}
	public Player getPlayer() {
		return player;
	}
	
	public void setButtonDisable(){
		fixeddownpanelREF.setButtonDisable();
	}
	
	public void setButtonEnable(){
		fixeddownpanelREF.setButtonEnable();
	}

	public JFrame getFrameREF() {
		return this.myframeREF;
	}

	public void backButtonPressed() {		//返回見按下
		this.showVisibleStatus();
		if(teampanelREF.isChoosingTeam()){ //如果在選擇  返回鍵只會消掉選擇盤
			teampanelREF.exitEditTeamPanel();
		}
		else{
			this.setAllInvisible();
			this.mainpanelREF.setVisible(true);
		}
	}


	public void showVisibleStatus(){
		System.out.println("shoppanelREF:"+shoppanelREF.isVisible());
		System.out.println("socialpanelREF:"+socialpanelREF.isVisible());
		System.out.println("mainpanelREF:"+mainpanelREF.isVisible());
		System.out.println("teampanelREF:"+teampanelREF.isVisible());
		System.out.println("backpackpanelREF:"+backpackpanelREF.isVisible());
		System.out.println("fixeddownpanelREF:"+fixeddownpanelREF.isVisible());
		System.out.println("fixeduppanelREF	:"+fixeduppanelREF.isVisible());
		System.out.println("upBarREF :"+upBarREF.isVisible());
	}
	




	

}
