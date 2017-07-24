package MainPanel;
import javax.swing.*;

import java.util.Timer;
import java.util.TimerTask;

import FixedPanel.FixedPanel;
import MyFrame.MyFrame;
import MyClient.Player;
import math.vec2;

import java.awt.*;


public class MainPanel extends JPanel{
	//data member

	private Player player;
	private FixedPanel fixedpanelREF;
	
	private Tower darkTower ;
	private Tower brightTower ;
	private Tower fireTower ;
	private Tower waterTower ;
	private Tower woodTower ;
	private Tower mainTower ;
	private Tower shipTower;
	private JLabel backGround;
	
	private double time;
	private MyFrame frame;

	
	//constructor
	public MainPanel(MyFrame frame,FixedPanel f){
		
		super(null); 
		this.player=f.getPlayer();
		this.fixedpanelREF=f;
		this.frame=frame;
		this.setSize(frame.getWidth(),frame.getHeight());		
		this.repaint();


		buildMainTower();
		buildDarkTower();
		buildBrightTower();
		buildFireTower();
		buildWaterTower();
		buildWoodTower();
		buildShipTower();
		buildBackGround();
	}


	
	


	public void buildBackGround(){
		backGround=new JLabel( MainImageLoader.backGround);
		backGround.setSize(getMyFrame().getWidth(),getMyFrame().getHeight());
		backGround.setLocation(0, 21);
		this.add(backGround);
	}
	
	
	private void buildShipTower() {
		shipTower=new Tower( MainImageLoader.shipTower,MainImageLoader.shipTowerPressed,new vec2(30,100),new vec2(107,101));
		this.add(shipTower);
		shipTower.addMouseListener(new MainMouseAdapter(shipTower, this));
		Timer t=new Timer();
		
		t.schedule(new TimerTask(){
			boolean rise=true;
			public void run() {
				if(shipTower.getY()<120&&rise){
					shipTower.setLocation(30, shipTower.getY()+1);
					if(shipTower.getY()==120)rise=false;
				}
				else if(shipTower.getY()>100){
					shipTower.setLocation(30, shipTower.getY()-1);
					if(shipTower.getY()==100)rise=true;
				}
			}
			
		},0,60);
	}

	public void buildMainTower(){
		mainTower=new Tower( MainImageLoader.mainTower,MainImageLoader.mainTowerPressed,new vec2(119,121),new vec2(252,341));
		this.add(mainTower);
		mainTower.addMouseListener(new MainMouseAdapter(mainTower, this));
	}
	
	public void buildFireTower(){
		fireTower=new Tower(  MainImageLoader.fireTower,MainImageLoader.fireTowerPressed,new vec2(300,199),new vec2(199,148));
		this.add(fireTower);
		fireTower.addMouseListener(new MainMouseAdapter(fireTower, this));
	}
	
	public void buildWoodTower(){
		woodTower=new Tower(  MainImageLoader.woodTower, MainImageLoader.woodTowerPressed,new vec2(250,460),new vec2(250,160));
		this.add(woodTower);
		woodTower.addMouseListener(new MainMouseAdapter(woodTower, this));
	}
	
	public void buildBrightTower(){
		brightTower=new Tower(  MainImageLoader.brightTower, MainImageLoader.brightTowerPressed,new vec2(0,369),new vec2(204,228));
		this.add(brightTower);
		brightTower.addMouseListener(new MainMouseAdapter(brightTower, this));
	}
	
	public void buildDarkTower(){
		darkTower=new Tower(  MainImageLoader.darkTower,MainImageLoader.darkTowerPressed,new vec2(345,361),new vec2(155,125));
		this.add(darkTower);
		darkTower.addMouseListener(new MainMouseAdapter(darkTower, this));
	}

	public void buildWaterTower(){
		waterTower=new Tower(  MainImageLoader.waterTower,MainImageLoader.waterTowerPressed,new vec2(0,188),new vec2(188,164));
		this.add(waterTower);
		waterTower.addMouseListener(new MainMouseAdapter(waterTower, this));
	}
	
	public Tower getMainTower(){
		return this.mainTower;
	}
	public Tower getFireTower(){
		return this.fireTower;
	}
	public Tower getWaterTower(){
		return this.waterTower;
	}
	public Tower getDarkTower(){
		return this.darkTower;
	}
	public Tower getWoodTower(){
		return this.woodTower;
	}
	public Tower getShipTower(){
		return this.shipTower;
	}
	public Tower getBrightTower() {
		return this.brightTower;
	}
	
	
	//method 
	public void paintComponent(Graphics g){ 
		super.paintComponent(g);
		getMyFrame().repaint();
	}

	public Player getPlayer() {
		return player;
	}
	
	public FixedPanel getFixedPanel(){
		return this.fixedpanelREF;
	}

	public MyFrame getMyFrame() {
		return frame;
	}

}