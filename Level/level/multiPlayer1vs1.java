package level;

import javax.swing.ImageIcon;

import stonePanel.TurningStonePanel;
import math.vec2;
import counter.Enemy;
import MyFrame.MyFrame;
import Round.Round;

public class multiPlayer1vs1 extends Level{
	//data member
	//constructor
	public multiPlayer1vs1(){
		//------------
		super("¤¤³æ¾Ô°«");		
		this.bgURL = "./res/level/whitegoat/bk.PNG";  //.res/level/(levelName)/XXXXX.jpg
		this.round = new Round[1]; //this.round = new Round[how many rounds];
		this.roundNumber = 1;
		//--------------
		this.currentRound = 1;
		for(int i=0; i<roundNumber; i++){
			this.round[i] = new Round();
		}
		
		this.levelBuilder();
	}
	//getter and setter


	
	//method
	public void levelBuilder() {
		//round 1
		Enemy[] round1Enemy = new Enemy[1];
		round1Enemy[0] = new Enemy();
			round1Enemy[0].type = 0;
			round1Enemy[0].imag = (MyFrame.refFrame).client.getOtherPlayer(0).getCardLeader().getCardImage();
			round1Enemy[0].position = new vec2(157, 0);     
			round1Enemy[0].size = new vec2(185, 201);   //X: 0~500  Y:250 lower
			round1Enemy[0].blood = (MyFrame.refFrame).client.getOtherPlayer(0).getHp();
			round1Enemy[0].currentBlood = (MyFrame.refFrame).client.getOtherPlayer(0).getHp();
			round1Enemy[0].attackCD = 0;
			round1Enemy[0].attack = 0;
			round1Enemy[0].initialCD = 0;
			round1Enemy[0].defense = 0;
			
		round[0].addEnemy(round1Enemy);
	}
}

