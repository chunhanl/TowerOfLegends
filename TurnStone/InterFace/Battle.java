package InterFace;


import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.util.Timer;

import javax.swing.JFrame;

import card.Card;
import card.Deck;
import Animation.ParticleAnimation;
import particleSystem.ParticleInterface;
import level.*;
import math.vec2;
import control.StateControlManager;
import control.ThreadController;
import gameUtil.BattleTeam;
import soundUtil.SoundPlayer;
import stonePanel.TurningStonePanel;

public class Battle {
	//data member
	private TurningStonePanel stonePanel;
	private BattleTeam sentTeam;
	private JFrame frame;
	private StateControlManager stateManager;
	private Thread gameControlThread;
	
	public static ParticleInterface particleSystemREF;
	public static ParticleAnimation pa;
	
	public static SoundPlayer soundPlayer;
	public static boolean isNet = false;
	//constructor
	
	//---test
	public Battle(JFrame frame, BattleTeam sentTeam, Level lv, boolean isNet){
		//build up sound player
		this.soundPlayer = new SoundPlayer();
		this.isNet = isNet;
		
		this.sentTeam = sentTeam;
		this.frame = frame;
		
		stonePanel = new TurningStonePanel(frame, this.sentTeam, lv);

		stateManager = new StateControlManager(frame, stonePanel, sentTeam);
		gameControlThread = new Thread(stateManager);
		gameControlThread.start();
		
		
		particleSystemREF = new ParticleInterface(500,750,frame);

		Thread particleThread = new Thread(particleSystemREF);

		stonePanel.add(particleSystemREF,0);  //add ParticleSystemREF to the most front of stonePanel
		particleThread.start();
		particleSystemREF.setVisible(true);
		
		frame.setVisible(true);
		

	}
	
	//getter and setter
	public TurningStonePanel getTurningStonePanel(){
		return this.stonePanel;
	}
	
	public boolean isGameOn(){
		return TurningStonePanel.gameIsOn;
	}
	//method
	
	//main method( for testing
	public static void main(String args[]){
		JFrame frame = new JFrame("Turn Stone");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(500,750);
		frame.setResizable(false);
		
		frame.setLayout(null);
		
		//Testing
		Deck a=new Deck("CardData.txt");
		Card[] c=new Card[6];
		c[0]=a.selectCard(5);
		c[1]=a.selectCard(3);
		c[2]=a.selectCard(7);
		c[3]=a.selectCard(2);
		c[4]=a.selectCard(4);
		c[5]=a.selectCard(6);
		
		Battle bat = new Battle(frame, new BattleTeam(c), new multiPlayer1vs1(), true);
		
		//Thread soundThread = new Thread(new StoneTurningSound("turnStone.au"));
		//soundThread.start();

		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			
		    	public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    		TurningStonePanel.gameIsOn = false;
		    		System.exit(0);
		        }
			}
		);
		
		
	}
}
