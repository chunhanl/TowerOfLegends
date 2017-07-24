package level;

import javax.swing.ImageIcon;

import math.vec2;
import counter.Enemy;
import Round.Round;

public class WhiteGoat extends Level{
	//data member
	//constructor
	public WhiteGoat(){
		//------------
		super("¨ª¤§¥Õ¦Ï®c");		
		this.bgURL = "./res/level/whitegoat/bk.PNG";  //.res/level/(levelName)/XXXXX.jpg
		this.round = new Round[15]; //this.round = new Round[how many rounds];
		this.roundNumber = 15;
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
			round1Enemy[0].type = 2;
			round1Enemy[0].imag = new ImageIcon("./res/level/whitegoat/round1.png");
			round1Enemy[0].position = new vec2(157, 0);     
			round1Enemy[0].size = new vec2(185, 201);   //X: 0~500  Y:250 lower
			round1Enemy[0].blood = 5000;
			round1Enemy[0].currentBlood = 5000;
			round1Enemy[0].attackCD = 2;
			round1Enemy[0].attack = 100;
			round1Enemy[0].initialCD = 2;
			round1Enemy[0].defense = 5;
			
		round[0].addEnemy(round1Enemy);
		
		
		//round 2
		Enemy[] round2Enemy = new Enemy[2];
		round2Enemy[0] = new Enemy();
			round2Enemy[0].type = 2;
			round2Enemy[0].imag = new ImageIcon("./res/level/whitegoat/round1.png");
			round2Enemy[0].position = new vec2(65, 0);     
			round2Enemy[0].size = new vec2(185, 201);   //X: 0~500  Y:250 lower
			round2Enemy[0].blood = 5000;
			round2Enemy[0].currentBlood = 5000;
			round2Enemy[0].attackCD = 2;
			round2Enemy[0].attack = 100;
			round2Enemy[0].initialCD = 2;
			round2Enemy[0].defense = 5;
			
		round2Enemy[1] = new Enemy();
			round2Enemy[1].type = 2;
			round2Enemy[1].imag = new ImageIcon("./res/level/whitegoat/round1.png");
			round2Enemy[1].position = new vec2(250, 0);     
			round2Enemy[1].size = new vec2(185, 201);   //X: 0~500  Y:250 lower
			round2Enemy[1].blood = 5000;
			round2Enemy[1].currentBlood = 5000;
			round2Enemy[1].attackCD = 2;
			round2Enemy[1].attack = 100;
			round2Enemy[1].initialCD = 2;
			round2Enemy[1].defense = 5;
					
		round[1].addEnemy(round2Enemy);
		
		
		//round 3
		Enemy[] round3Enemy = new Enemy[1];
		round3Enemy[0] = new Enemy();
			round3Enemy[0].type = 2;
			round3Enemy[0].imag = new ImageIcon("./res/level/whitegoat/round3.png");
			round3Enemy[0].position = new vec2(100, 0);     
			round3Enemy[0].size = new vec2(300, 259);   //X: 0~500  Y:250 lower
			round3Enemy[0].blood = 68700;
			round3Enemy[0].currentBlood = 68700;
			round3Enemy[0].attackCD = 3;
			round3Enemy[0].attack = 4204;
			round3Enemy[0].initialCD = 3;
			round3Enemy[0].defense = 100;
							
		round[2].addEnemy(round3Enemy);
		
		
		//round 4
		Enemy[] round4Enemy = new Enemy[2];
		round4Enemy[0] = new Enemy();
			round4Enemy[0].type = 2;
			round4Enemy[0].imag = new ImageIcon("./res/level/whitegoat/round4_and_7.png");
			round4Enemy[0].position = new vec2(98, 0);     
			round4Enemy[0].size = new vec2(152, 200);   //X: 0~500  Y:250 lower
			round4Enemy[0].blood = 28750;
			round4Enemy[0].currentBlood = 28750;
			round4Enemy[0].attackCD = 2;
			round4Enemy[0].attack = 2879;
			round4Enemy[0].initialCD = 2;
			round4Enemy[0].defense = 200;
					
		round4Enemy[1] = new Enemy();
			round4Enemy[1].type = 2;
			round4Enemy[1].imag = new ImageIcon("./res/level/whitegoat/round4_and_7.png");
			round4Enemy[1].position = new vec2(250, 0);     
			round4Enemy[1].size = new vec2(152, 200);   //X: 0~500  Y:250 lower
			round4Enemy[1].blood = 28750;
			round4Enemy[1].currentBlood = 28750;
			round4Enemy[1].attackCD = 2;
			round4Enemy[1].attack = 2879;
			round4Enemy[1].initialCD = 2;
			round4Enemy[1].defense = 200;
						
		round[3].addEnemy(round4Enemy);

		
		//round 5
		Enemy[] round5Enemy = new Enemy[3];
		round5Enemy[0] = new Enemy();
			round5Enemy[0].type = 2;
			round5Enemy[0].imag = new ImageIcon("./res/level/whitegoat/round5_1_and_10.png");
			round5Enemy[0].position = new vec2(13, 0);     
			round5Enemy[0].size = new vec2(162, 201);   //X: 0~500  Y:250 lower
			round5Enemy[0].blood = 28750;
			round5Enemy[0].currentBlood = 28750;
			round5Enemy[0].attackCD = 2;
			round5Enemy[0].attack = 2879;
			round5Enemy[0].initialCD = 2;
			round5Enemy[0].defense = 200;
							
		round5Enemy[1] = new Enemy();
			round5Enemy[1].type = 2;
			round5Enemy[1].imag = new ImageIcon("./res/level/whitegoat/round5_1_and_10.png");
			round5Enemy[1].position = new vec2(175, 0);     
			round5Enemy[1].size = new vec2(162, 201);   //X: 0~500  Y:250 lower
			round5Enemy[1].blood = 28750;
			round5Enemy[1].currentBlood = 28750;
			round5Enemy[1].attackCD = 2;
			round5Enemy[1].attack = 2879;
			round5Enemy[1].initialCD = 2;
			round5Enemy[1].defense = 200;
			
		round5Enemy[2] = new Enemy();
			round5Enemy[2].type = 2;
			round5Enemy[2].imag = new ImageIcon("./res/level/whitegoat/round5_2_and_13.png");
			round5Enemy[2].position = new vec2(337, 0);     
			round5Enemy[2].size = new vec2(150, 137);   //X: 0~500  Y:250 lower
			round5Enemy[2].blood = 56091;
			round5Enemy[2].currentBlood = 56091;
			round5Enemy[2].attackCD = 4;
			round5Enemy[2].attack = 10385;
			round5Enemy[2].initialCD = 4;
			round5Enemy[2].defense = 100;
									
		round[4].addEnemy(round5Enemy);
		
		
		//round 6
		Enemy[] round6Enemy = new Enemy[1];
		round6Enemy[0] = new Enemy();
			round6Enemy[0].type = 2;
			round6Enemy[0].imag = new ImageIcon("./res/level/whitegoat/round6.png");
			round6Enemy[0].position = new vec2(125, 0);     
			round6Enemy[0].size = new vec2(250, 210);   //X: 0~500  Y:250 lower
			round6Enemy[0].blood = 360000;
			round6Enemy[0].currentBlood = 360000;
			round6Enemy[0].attackCD = 1;
			round6Enemy[0].attack = 7500;
			round6Enemy[0].initialCD = 1;
			round6Enemy[0].defense = 50;
											
		round[5].addEnemy(round6Enemy);
		
		
		//round 7
		Enemy[] round7Enemy = new Enemy[3];
		round7Enemy[0] = new Enemy();
			round7Enemy[0].type = 2;
			round7Enemy[0].imag = new ImageIcon("./res/level/whitegoat/round4_and_7.png");
			round7Enemy[0].position = new vec2(22, 0);     
			round7Enemy[0].size = new vec2(152, 200);   //X: 0~500  Y:250 lower
			round7Enemy[0].blood = 28750;
			round7Enemy[0].currentBlood = 28750;
			round7Enemy[0].attackCD = 2;
			round7Enemy[0].attack = 2879;
			round7Enemy[0].initialCD = 2;
			round7Enemy[0].defense = 200;
							
		round7Enemy[1] = new Enemy();
			round7Enemy[1].type = 2;
			round7Enemy[1].imag = new ImageIcon("./res/level/whitegoat/round4_and_7.png");
			round7Enemy[1].position = new vec2(174, 0);     
			round7Enemy[1].size = new vec2(152, 200);   //X: 0~500  Y:250 lower
			round7Enemy[1].blood = 28750;
			round7Enemy[1].currentBlood = 28750;
			round7Enemy[1].attackCD = 2;
			round7Enemy[1].attack = 2879;
			round7Enemy[1].initialCD = 2;
			round7Enemy[1].defense = 200;
			
		round7Enemy[2] = new Enemy();
			round7Enemy[2].type = 2;
			round7Enemy[2].imag = new ImageIcon("./res/level/whitegoat/round4_and_7.png");
			round7Enemy[2].position = new vec2(326, 0);     
			round7Enemy[2].size = new vec2(152, 200);   //X: 0~500  Y:250 lower
			round7Enemy[2].blood = 28750;
			round7Enemy[2].currentBlood = 28750;
			round7Enemy[2].attackCD = 2;
			round7Enemy[2].attack = 2879;
			round7Enemy[2].initialCD = 2;
			round7Enemy[2].defense = 200;
									
		round[6].addEnemy(round7Enemy);
		
		
		//round 8
		Enemy[] round8Enemy = new Enemy[1];
		round8Enemy[0] = new Enemy();
			round8Enemy[0].type = 2;
			round8Enemy[0].imag = new ImageIcon("./res/level/whitegoat/round8.png");
			round8Enemy[0].position = new vec2(124, 0);     
			round8Enemy[0].size = new vec2(252, 275);   //X: 0~500  Y:250 lower
			round8Enemy[0].blood = 89425;
			round8Enemy[0].currentBlood = 89425;
			round8Enemy[0].attackCD = 1;
			round8Enemy[0].attack = 3610;
			round8Enemy[0].initialCD = 1;
			round8Enemy[0].defense = 100;
														
		round[7].addEnemy(round8Enemy);
		
		
		//round 9
		Enemy[] round9Enemy = new Enemy[1];
		round9Enemy[0] = new Enemy();
			round9Enemy[0].type = 2;
			round9Enemy[0].imag = new ImageIcon("./res/level/whitegoat/round9.png");
			round9Enemy[0].position = new vec2(50, 0);     
			round9Enemy[0].size = new vec2(400, 234);   //X: 0~500  Y:250 lower
			round9Enemy[0].blood = 500000;
			round9Enemy[0].currentBlood = 500000;
			round9Enemy[0].attackCD = 3;
			round9Enemy[0].attack = 9724;
			round9Enemy[0].initialCD = 3;
			round9Enemy[0].defense = 150;
														
		round[8].addEnemy(round9Enemy);
		
		
		//round 10
		Enemy[] round10Enemy = new Enemy[3];
		round10Enemy[0] = new Enemy();
			round10Enemy[0].type = 2;
			round10Enemy[0].imag = new ImageIcon("./res/level/whitegoat/round5_1_and_10.png");
			round10Enemy[0].position = new vec2(7, 0);     
			round10Enemy[0].size = new vec2(162, 201);   //X: 0~500  Y:250 lower
			round10Enemy[0].blood = 28750;
			round10Enemy[0].currentBlood = 28750;
			round10Enemy[0].attackCD = 2;
			round10Enemy[0].attack = 2879;
			round10Enemy[0].initialCD = 2;
			round10Enemy[0].defense = 200;
							
		round10Enemy[1] = new Enemy();
			round10Enemy[1].type = 2;
			round10Enemy[1].imag = new ImageIcon("./res/level/whitegoat/round5_1_and_10.png");
			round10Enemy[1].position = new vec2(169, 0);     
			round10Enemy[1].size = new vec2(162, 201);   //X: 0~500  Y:250 lower
			round10Enemy[1].blood = 28750;
			round10Enemy[1].currentBlood = 28750;
			round10Enemy[1].attackCD = 2;
			round10Enemy[1].attack = 2879;
			round10Enemy[1].initialCD = 2;
			round10Enemy[1].defense = 200;
			
		round10Enemy[2] = new Enemy();
			round10Enemy[2].type = 2;
			round10Enemy[2].imag = new ImageIcon("./res/level/whitegoat/round5_1_and_10.png");
			round10Enemy[2].position = new vec2(331, 0);     
			round10Enemy[2].size = new vec2(162, 201);   //X: 0~500  Y:250 lower
			round10Enemy[2].blood = 28750;
			round10Enemy[2].currentBlood = 28750;
			round10Enemy[2].attackCD = 2;
			round10Enemy[2].attack = 2879;
			round10Enemy[2].initialCD = 2;
			round10Enemy[2].defense = 200;
									
		round[9].addEnemy(round10Enemy);
		
		
		//round 11
		Enemy[] round11Enemy = new Enemy[2];
		round11Enemy[0] = new Enemy();
			round11Enemy[0].type = 2;
			round11Enemy[0].imag = new ImageIcon("./res/level/whitegoat/round11_and_13.png");
			round11Enemy[0].position = new vec2(50, 0);     
			round11Enemy[0].size = new vec2(200, 166);   //X: 0~500  Y:250 lower
			round11Enemy[0].blood = 101683;
			round11Enemy[0].currentBlood = 101683;
			round11Enemy[0].attackCD = 1;
			round11Enemy[0].attack = 4203;
			round11Enemy[0].initialCD = 1;
			round11Enemy[0].defense = 490;
					
		round11Enemy[1] = new Enemy();
			round11Enemy[1].type = 2;
			round11Enemy[1].imag = new ImageIcon("./res/level/whitegoat/round11_and_13.png");
			round11Enemy[1].position = new vec2(250, 0);     
			round11Enemy[1].size = new vec2(200, 166);   //X: 0~500  Y:250 lower
			round11Enemy[1].blood = 101683;
			round11Enemy[1].currentBlood = 101683;
			round11Enemy[1].attackCD = 1;
			round11Enemy[1].attack = 4203;
			round11Enemy[1].initialCD = 1;
			round11Enemy[1].defense = 490;
							
		round[10].addEnemy(round11Enemy);
		
		
		//round 12
		Enemy[] round12Enemy = new Enemy[2];
		round12Enemy[0] = new Enemy();
			round12Enemy[0].type = 2;
			round12Enemy[0].imag = new ImageIcon("./res/level/whitegoat/round12.png");
			round12Enemy[0].position = new vec2(16, 0);     
			round12Enemy[0].size = new vec2(234, 250);   //X: 0~500  Y:250 lower
			round12Enemy[0].blood = 543500;
			round12Enemy[0].currentBlood = 543500;
			round12Enemy[0].attackCD = 3;
			round12Enemy[0].attack = 8800;
			round12Enemy[0].initialCD = 3;
			round12Enemy[0].defense = 200;
					
		round12Enemy[1] = new Enemy();
			round12Enemy[1].type = 2;
			round12Enemy[1].imag = new ImageIcon("./res/level/whitegoat/round12.png");
			round12Enemy[1].position = new vec2(250, 0);     
			round12Enemy[1].size = new vec2(234, 250);   //X: 0~500  Y:250 lower
			round12Enemy[1].blood = 543500;
			round12Enemy[1].currentBlood = 543500;
			round12Enemy[1].attackCD = 3;
			round12Enemy[1].attack = 8800;
			round12Enemy[1].initialCD = 3;
			round12Enemy[1].defense = 200;
							
		round[11].addEnemy(round12Enemy);
		
		
		//round 13
		Enemy[] round13Enemy = new Enemy[3];
		round13Enemy[0] = new Enemy();
			round13Enemy[0].type = 2;
			round13Enemy[0].imag = new ImageIcon("./res/level/whitegoat/round13_1.png");
			round13Enemy[0].position = new vec2(2, 0);     
			round13Enemy[0].size = new vec2(146, 200);   //X: 0~500  Y:250 lower
			round13Enemy[0].blood = 28967;
			round13Enemy[0].currentBlood = 28967;
			round13Enemy[0].attackCD = 3;
			round13Enemy[0].attack = 6766;
			round13Enemy[0].initialCD = 3;
			round13Enemy[0].defense = 50;
							
		round13Enemy[1] = new Enemy();
			round13Enemy[1].type = 2;
			round13Enemy[1].imag = new ImageIcon("./res/level/whitegoat/round11_and_13.png");
			round13Enemy[1].position = new vec2(148, 0);     
			round13Enemy[1].size = new vec2(200, 166);   //X: 0~500  Y:250 lower
			round13Enemy[1].blood = 101683;
			round13Enemy[1].currentBlood = 101683;
			round13Enemy[1].attackCD = 1;
			round13Enemy[1].attack = 4203;
			round13Enemy[1].initialCD = 1;
			round13Enemy[1].defense = 490;
			
		round13Enemy[2] = new Enemy();
			round13Enemy[2].type = 2;
			round13Enemy[2].imag = new ImageIcon("./res/level/whitegoat/round5_2_and_13.png");
			round13Enemy[2].position = new vec2(348, 0);     
			round13Enemy[2].size = new vec2(150, 137);   //X: 0~500  Y:250 lower
			round13Enemy[2].blood = 56091;
			round13Enemy[2].currentBlood = 56091;
			round13Enemy[2].attackCD = 4;
			round13Enemy[2].attack = 10385;
			round13Enemy[2].initialCD = 4;
			round13Enemy[2].defense = 100;
									
		round[12].addEnemy(round13Enemy);
		
		
		//round 14
		Enemy[] round14Enemy = new Enemy[1];
		round14Enemy[0] = new Enemy();
			round14Enemy[0].type = 2;
			round14Enemy[0].imag = new ImageIcon("./res/level/whitegoat/round14.png");
			round14Enemy[0].position = new vec2(100, 0);     
			round14Enemy[0].size = new vec2(300, 231);   //X: 0~500  Y:250 lower
			round14Enemy[0].blood = 150000;
			round14Enemy[0].currentBlood = 150000;
			round14Enemy[0].attackCD = 3;
			round14Enemy[0].attack = 11057;
			round14Enemy[0].initialCD = 3;
			round14Enemy[0].defense = 300;
														
		round[13].addEnemy(round14Enemy);
		
		
		//round 15
		Enemy[] round15Enemy = new Enemy[1];
		round15Enemy[0] = new Enemy();
			round15Enemy[0].type = 2;
			round15Enemy[0].imag = new ImageIcon("./res/level/whitegoat/round15.png");
			round15Enemy[0].position = new vec2(100, 0);     
			round15Enemy[0].size = new vec2(300, 221);   //X: 0~500  Y:250 lower
			round15Enemy[0].blood = 1200000;
			round15Enemy[0].currentBlood = 1200000;
			round15Enemy[0].attackCD = 2;
			round15Enemy[0].attack = 4500;
			round15Enemy[0].initialCD = 2;
			round15Enemy[0].defense = 300;
														
		round[14].addEnemy(round15Enemy);
	}
}
