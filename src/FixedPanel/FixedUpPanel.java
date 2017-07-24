package FixedPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import math.vec2;
import CardTools.CardLabel;
import CardTools.CardToolsImageLoader;
import MainPanel.MainImageLoader;
import MainPanel.MainPanel;
import Player.Player;


public class FixedUpPanel extends JPanel{
	
	private JFrame myframeREF;
	private Player player;
	private FixedPanel fixedpanelREF; 
	
	private JLabel playerLeaderSlot;
	private CardLabel playerLeaderHead;
	
	private JLabel name;
	private JLabel stone;//max not test yet ??
	private JLabel money;

	
	private JLabel backGround;

	
	
	public FixedUpPanel(JFrame frame,FixedPanel f){		
		super(null); 
		
		player=f.getPlayer(); //~~~~~~~~~~~~~~~~~~
		fixedpanelREF=f;
		myframeREF = frame;  
		this.setSize(500,130);		
		this.setLocation(0,0);
		this.repaint();
		this.setOpaque(false);    //透明化

		writePlayerDatas();		//name stone money
		buildPlayerLeader();
		buildPlayerLeaderSlot();
		buildBackGround();

		//test for Server
		setPlayerLeader(1);   //設定隊長
		setPlayerMoney(10000);
		setPlayerStone(5);


	}

	private void writePlayerDatas() {
		this.name=new JLabel();
		this.stone=new JLabel();
		this.money=new JLabel();
		
		this.name.setForeground(Color.white);
		this.name.setFont(new Font("Times New Roman", Font.BOLD, 18));
		this.name.setText(player.getName());
		this.name.setSize(200, 30);						//max not test yet ??
		this.name.setLocation(25,19);
		this.add(name);
		
		
		this.stone.setForeground(new Color(0,154,218));
		this.stone.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		this.stone.setText(String.valueOf(player.getStone()));
		this.stone.setSize(100, 30);						//max not test yet ??
		this.stone.setLocation(355,19);
		this.add(stone);
		
		
		this.money.setForeground(new Color(255,222,0));
		this.money.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		this.money.setText(String.valueOf(player.getMoney()));
		this.money.setSize(200, 30);
		this.money.setLocation(355,54);
		this.add(money);
		
	}

	//server's control
	private void setPlayerStone(int i) {
		player.setStone(i);
	}


	private void setPlayerMoney(int i) {
		player.setMoney(i);
		
	}
	
	private void setPlayerLeader(int ID){
		player.setCardLeader(ID);
		playerLeaderHead=new CardLabel(null, fixedpanelREF,player.getCardLeader());
			this.repaint();
	}


	private void buildBackGround() {
		backGround=new JLabel();
		backGround.setIcon(FixedImageLoader.fixedUpPanelBack);
		backGround.setSize(500,180);
		backGround.setLocation(0,-60);
		this.add(backGround);
	}

	public void buildPlayerLeaderSlot(){
		playerLeaderSlot=new JLabel();
		playerLeaderSlot.setIcon(FixedImageLoader.playerLeaderSlot);
		playerLeaderSlot.setSize(100, 100);
		playerLeaderSlot.setLocation(this.getWidth()/2-50, this.getHeight()/2-60);
		this.add(playerLeaderSlot);

	}

	
	public void buildPlayerLeader() {
		playerLeaderHead=new CardLabel(null, fixedpanelREF,player.getCardLeader());
		playerLeaderHead.setSize(80, 70);
		playerLeaderHead.setLocation(210,20);			//head  80 *70
		this.add(playerLeaderHead);
	}
	




}
