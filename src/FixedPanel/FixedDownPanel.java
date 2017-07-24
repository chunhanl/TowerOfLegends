package FixedPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import math.vec2;
import MainPanel.MainImageLoader;
import MainPanel.MainPanel;
import Player.Player;


public class FixedDownPanel extends JPanel{
	
	private JFrame myframeREF;
	private Player player;
	
	private SwitchableLabel backpack;
	private SwitchMouseAdapter backpackAdapter;
	private SwitchableLabel socialicon;
	private SwitchMouseAdapter socialAdapter;
	private SwitchableLabel shopicon;
	private SwitchMouseAdapter shopAdapter;
	private SwitchableLabel teamicon;
	private SwitchMouseAdapter teamAdapter;
	
	private LifeClock lifeclock;
	private LifeClockMouseAdapter lifeclockAdapter;
	private JLabel backGround;
	
	private FixedPanel fixedpanelREF; 

	

	
	public FixedDownPanel(JFrame frame,FixedPanel f){		
		super(null); 
		
		player=f.getPlayer();
		fixedpanelREF=f;
		myframeREF = frame;  
		this.setSize(500,250);		
		this.setLocation(0,myframeREF.getHeight()-250);
		this.repaint();
		this.setOpaque(false);    //³z©ú¤Æ
		
		
		this.addLifeClock();
		this.addTeamIcon();
		this.addBackPack();
		this.addSocialIcon();
		this.addShopIcon();
		this.addBackGround();

	
		//this.addStatusBar();

		
	}

	
	
	private void addBackGround() {
		backGround=new JLabel();
		backGround.setIcon(FixedImageLoader.fixedDownBack);
		backGround.setSize(500, 238);
		backGround.setLocation(0,0);
		this.add(backGround);		
	}


	private void addTeamIcon() {
		teamicon=new SwitchableLabel(FixedImageLoader.playerTeam,FixedImageLoader.playerTeamPressed,new vec2(2,79),new vec2(85,82));
		this.add(teamicon);
		teamAdapter=new SwitchMouseAdapter(teamicon,this,myframeREF,"team"); 
		teamicon.addMouseListener(teamAdapter);
	}

	private void addSocialIcon() {
		socialicon=new SwitchableLabel(FixedImageLoader.playerSocial,FixedImageLoader.playerSocialPressed,new vec2(413,81),new vec2(85,82));
		this.add(socialicon);
		socialAdapter=new SwitchMouseAdapter(socialicon,this,myframeREF,"social"); 
		socialicon.addMouseListener(socialAdapter);
	}

	private void addShopIcon() {
		shopicon=new SwitchableLabel(FixedImageLoader.playerShop,FixedImageLoader.playerShopPressed,new vec2(329,78),new vec2(85,82));
		this.add(shopicon);
		shopAdapter=new SwitchMouseAdapter(shopicon,this,myframeREF,"shop"); 
		shopicon.addMouseListener(shopAdapter);
	}


	public void addBackPack(){
		backpack=new SwitchableLabel(FixedImageLoader.playerBackPack,FixedImageLoader.playerBackPackPressed,new vec2(90,80),new vec2(79,79));
		this.add(backpack);
		backpackAdapter=new SwitchMouseAdapter(backpack,this,myframeREF,"backpack"); 
		backpack.addMouseListener(backpackAdapter);
	}
	
	public void addLifeClock(){
		lifeclock=new LifeClock(this,player);
		this.add(lifeclock);
		lifeclockAdapter=new LifeClockMouseAdapter(lifeclock,this,player,myframeREF); 
		lifeclock.addMouseListener(lifeclockAdapter);
	}
	
	//button off
	public void setButtonDisable(){
		backpackAdapter.setDisable();
		socialAdapter.setDisable();
		lifeclockAdapter.setDisable();
		shopAdapter.setDisable();
		teamAdapter.setDisable();
		//team
	}
	public void setButtonEnable(){
		backpackAdapter.setEnable();
		socialAdapter.setEnable();
		lifeclockAdapter.setEnable();
		shopAdapter.setEnable();
		teamAdapter.setEnable();
		//team
	}
	
	
	public FixedPanel getFixedPanel(){
		return fixedpanelREF;
	}
}
