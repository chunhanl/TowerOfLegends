package level;

import javax.swing.ImageIcon;

import math.vec2;
import counter.Enemy;
import Round.Round;

public class AngerOfTheKing extends Level{
	//data member
	//constructor
	public AngerOfTheKing(){
		//------------
		super("Å]¤ýªº¼««ã");		
		this.bgURL = "./res/level/wotan/bg.JPG";  //.res/level/(levelName)/XXXXX.jpg
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
			round1Enemy[0].imag = new ImageIcon("./res/level/wotan/wotan.png");
			round1Enemy[0].position = new vec2(60, 0);     
			round1Enemy[0].size = new vec2(380, 250);   //X: 0~500  Y:250 lower
			round1Enemy[0].blood = 1960000;
			round1Enemy[0].currentBlood = 1960000;
			round1Enemy[0].attackCD = 1;
			round1Enemy[0].attack = 1500;
			round1Enemy[0].initialCD = 1;
			round1Enemy[0].defense = 600;
			round1Enemy[0].type = 0;
		round[0].addEnemy(round1Enemy);
	}
}
