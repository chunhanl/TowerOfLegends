package stonePanel;

import gameUtil.BattleTeam;

import javax.imageio.ImageIO;
import javax.swing.*;

import particleSystem.ParticleInterface;
import control.ThreadController;
import level.Level;
import math.StoneRandomize;
import math.vec2;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
//turning stone panel is the whole set of all element of turning stone panel
public class TurningStonePanel extends JPanel{
	//data member
	private Stone[] stones;
	public Panel panel;
	private JFrame frame;
	private int[] zOrder;
	public static int col = 6;
	public static int row = 5;
	private int eachGridX;
	private int eachGridY;
	private int showStoneSize = 70;
	private int insetX = 70;   //往內收縮(pixels)
	private int insetY = 430;   //往內收縮(pixels)	
	private int offsetX = 0;
	private int offsetY = 170;
	private PlayerGameStatus playerGameStatus;
	private PlayerBloodBar playerBloodBar;
	private AttackPanel atkPanel;    //for attack sake ( not implement yet
	private CrewPanel crewPanel;   //for crew sake ( not implement yet
	private ComboPanel comboPanel;
	public static boolean gameIsOn = true;
	public static boolean playerAlive = true;
	public static boolean canPaint = true;
	public static TurningStonePanel tspREF;
	
	
	//for test
	public static ParticleInterface pi;
	//----data for moving stone
	public static boolean firstChangeOccur = false;
	public static boolean released = false;
	public static MyMouseAdapter mouseAdapter;
	//----data member for usage(not so important to read
	static int dx;
	static int dy;
	int ii;
	int jj;
	int lastX;  //changed stone's left top position of X
	int lastY;  //changed stond's left top position of Y
	
	//constructor
	public TurningStonePanel(JFrame frame, BattleTeam sentTeam, Level lv){
		
		super(null);  //make it's layout null
		TurningStonePanel.iniPlayerCondition();  //make player condition too be good (Alive, namely)
		//initial our panel
		this.frame = frame;  //save our frame's reference
		this.setSize(frame.getWidth(),frame.getHeight());
		zOrder = new int[col * row];
		this.eachGridX = (frame.getWidth()-insetX) / 6;
		this.eachGridY = (frame.getHeight()-insetY) / 5;
		this.playerGameStatus = new PlayerGameStatus(sentTeam);
		this.playerBloodBar  = new PlayerBloodBar(this, playerGameStatus);
		this.add(playerBloodBar);
		
		Thread bloodBarThread = new Thread(playerBloodBar);
		ThreadController.threadController.add(bloodBarThread);
		bloodBarThread.start();
		//SwingUtilities.invokeLater(playerBloodBar);
		
		crewPanel = new CrewPanel(this, playerGameStatus.getCards());
		this.add(crewPanel);
		atkPanel = new AttackPanel(this, lv);
		
		Thread atkPanelThread = new Thread(atkPanel);
		ThreadController.threadController.add(atkPanelThread);
		atkPanelThread.start();
		this.add(atkPanel);
		comboPanel = new ComboPanel();
		this.add(comboPanel);
		
		//particleInterface --> just for testing
		/*
		pi = new ParticleInterface(500,750,frame);
		Thread piThread = new Thread(pi);
		ThreadController.threadController.add(piThread);
		piThread.start();
		this.add(pi,0);
		*/
		
		//make it self a reference
		tspREF = this;
        //set our stone
		stones = new Stone[row * col];
		panel = new Panel();
		panel.constructPositionPanel(col, row);
		panel.constructGridID(col, row);
		Random rand = new Random();
		
		for(int i=0; i<6; i++){
			for(int j =0; j<5; j++){
				panel.setEachGridPosition(i, j, i * eachGridX + insetX/2 + offsetX, j * eachGridY + insetY/2 + offsetY);
			}
		}
		StoneRandomize.reconstructFallProbability(0, 0.0, 0, 0, 0, 0.0);
		for(int i=0; i<6; i++){
			for(int j =0; j<5; j++){
				int randNum = StoneRandomize.randStoneType();
				ImageIcon tmp = ImageLoader.getStoneImage(randNum);
				stones[j * col + i] = new Stone(tmp,randNum,new vec2(i * eachGridX + insetX/2 + offsetX, j * eachGridY + insetY/2 + offsetY),new vec2(showStoneSize,showStoneSize), i+j*6);
				panel.setGridID(i, j, stones[j*col + i].getid());
			}
		}
		this.repaint();
		//add label to our panel && add mouseListener to every label
		for(int i=0; i<row; i++){
			for(int j =0; j<col; j++){
				//add component & getZ order
				this.add(stones[i * col + j]);
				zOrder[i * col + j] = this.getComponentZOrder(stones[i * col + j]);
				//add listener
				stones[i * col + j].addMouseListener(new MyMouseAdapter(stones[i * col + j], this));

				stones[i * col + j].addMouseMotionListener(new MyMouseMotionAdapter(stones[i * col + j], this));
				
			}
		}

		
	}
	
	//getter and setter
	public Stone getStone(int i, int j){
		int find = panel.getGridID(i, j);
		int findi = 0;
		for(int k=0; k<stones.length; k++)
			if(find == stones[k].getid()){
				findi = k;
			}
		return stones[findi];
	}
	
	//method 
	public static void iniPlayerCondition(){
		TurningStonePanel.playerAlive = true;
		TurningStonePanel.gameIsOn = true;
	}
	
	public void freeControl(){
		firstChangeOccur = false;
		released = false;
	}
	
	public void freezeControl(){
		firstChangeOccur = true;
		released = true;
	}
	
	
	public void paintComponent(Graphics g){
		if(canPaint){
			super.paintComponent(g);
			this.eachGridX = frame.getWidth() / 6;
			this.eachGridY = frame.getHeight() / 5;
			BufferedImage image = null;
		
			try {
				image = ImageIO.read(new File("./res/stone/bgTest.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			g.drawImage(image,0,0,null);
			//System.out.println("StonePanel");
		}
	}
	
	public void setToFirstZOrder(int ii, int jj){  //set the Z Order for every stone(not well to use)
		int smalli = 0;
		int smallj = 0;
		for(int i = 0; i<col;i++){
			for(int j = 0; j<row; j++)
				if(zOrder[smallj * col + smalli] > zOrder[j * col + i]){
					smalli = i;
					smallj = j;
				}
		}	
		//swap
		int tmp = zOrder[jj * 6 + ii];
		zOrder[jj * 6 + ii] = zOrder[smallj * col + smalli];
		zOrder[smallj * col + smalli] = tmp;
		
		setOrder();
	}
	
	public void setToFirstZOrder(int id){  //more easy to use
		int find = 0;
		for(int i=0; i<stones.length; i++){
			if(stones[i].getid() == id){
				find = i;
				//System.out.println("found");
			}
		}
		int zorder=0;
		for(int i=0; i<stones.length; i++){
			if(zOrder[zorder] > zOrder[i])
				zorder = i;
		}
		
		int tmp = zOrder[find];
		zOrder[find] = zOrder[zorder];
		zOrder[zorder] = tmp;
		//System.out.println(find + "," + zorder);
		setOrder();
	}
	
	public void setOrder(){   //set order is to let the select one to be paint at the front
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				this.setComponentZOrder(stones[i * 6 + j], zOrder[i * 6 + j]);
			}
		}
	}
	
	public void showOrder(){   //set order is to let the select one to be paint at the front
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				System.out.print(zOrder[i * 6 + j] + " ");
			}
			System.out.printf("%n");
		}
	}
	
	
	public int getStoneLength(){
		return stones.length;
	}
	
	public int getWidth(){
		return frame.getWidth();
	}
	
	public Panel getPanel(){
		return this.panel;
	}
	
	public void setAllStoneIsNotEliminated(){
		for(int i=0; i<stones.length; i++){
			stones[i].setEliminate(false);
		}
	}
	
	public ComboPanel getComboPanel(){
		return this.comboPanel;
	}
	
	public CrewPanel getCrewPanel(){
		return this.crewPanel;
	}
	
	public PlayerGameStatus getPlayerGameStatus(){
		return this.playerGameStatus;
	}
	
	public AttackPanel getAtkPanel(){
		return this.atkPanel;
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public void setAllInvisible(){
		this.playerBloodBar.setAllInvisible();
		this.playerBloodBar.setVisible(false);
		this.atkPanel.setAllInvisible();
		this.comboPanel.setVisible(false);
		this.crewPanel.setVisible(false);
		for(int i=0; i<this.stones.length; i++){
			this.stones[i].setVisible(false);
		}
		this.atkPanel.setVisible(false);
	}
	
	public void setElseDisable(int exceptID){
		if(this.stones.length <= exceptID){
			System.out.println("error ---- out of stones length");
			return ;
	}
		for(int i=0; i<this.stones.length; i++){
			if(i != exceptID)
				this.stones[i].setEnabled(false);
		}
	}
	
	public void enableAll(){
		for(int i=0; i<this.stones.length; i++){
				this.stones[i].setEnabled(true);
		}
	}

}