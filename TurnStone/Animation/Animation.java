package Animation;

import java.util.Random;
import java.util.Timer;

import particleSystem.ParticleInterface;
import InterFace.Battle;
import math.vec2;
import stonePanel.CrewPanel;
import stonePanel.TurningStonePanel;
import util.Time;
import gameUtil.ComboMessage;
import control.StateControlManager;

public class Animation {
	//data member
	public static int offset = 400;
	public static int sleepMilli = 300;
	//constructor
	
	//getter & setter
	
	//method
	public static MovingTask fallStone(StateControlManager manager, ComboMessage cMessage, MovingInfo[] mInfo){
		TurningStonePanel stonePanel = manager.stonePanel;
		Timer timer = new Timer();
		MovingTask moveTask = new MovingTask(manager, cMessage, mInfo, timer);
		timer.schedule(moveTask, 0, 10);
		return moveTask;
	}

	public static void makeStoneInAir(StateControlManager manager, ComboMessage comboMessage) {
		for(int i=0; i<comboMessage.totalEliminateNum; i++){
			int x = comboMessage.eliminatePosition[i].getX(); 
			int y = comboMessage.eliminatePosition[i].getY();
			manager.stonePanel.getStone(x, y).setEliminate(true);
			int px = manager.stonePanel.getStone(x,y).getPosition().getX();
			int py = manager.stonePanel.getStone(x,y).getPosition().getY();
			manager.stonePanel.getStone(x, y).setLocation(px, py - Animation.offset);
		}
	}

	public static MovingInfo[] calculateStoneFallingStrategy(StateControlManager manager, ComboMessage comboMessage) {
		
		for(int i=0; i<comboMessage.sMessages.size(); i++){
			try{
				Thread.sleep(sleepMilli);
			} 
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
			//play destroying sound
			int comboNum = manager.stonePanel.getComboPanel().number + 1;
			if(comboNum < 9){
				Battle.soundPlayer.addSound("turningStoneCombo/" + comboNum + ".wav");
			}
			else{
				Battle.soundPlayer.addSound("turningStoneCombo/9.wav");
			}
			
			//make this shape of stone eliminate
			for(int j=0; j<comboMessage.sMessages.get(i).eliminatePosition.length; j++){
				int x = comboMessage.sMessages.get(i).eliminatePosition[j].getX();
				int y = comboMessage.sMessages.get(i).eliminatePosition[j].getY();
				manager.stonePanel.getStone(x, y).setVisible(false);
				int eliminateType = comboMessage.sMessages.get(i).stoneType;
				//try to emit the particle to the charactor
				if(j == (int)Math.floor(comboMessage.sMessages.size()/2)){
					vec2 v2 = new vec2(manager.stonePanel.getPanel().getEachGridPosition(x, y));
					
					for(int k=0; k<manager.stonePanel.getPlayerGameStatus().getCards().length; k++){
						if(manager.stonePanel.getPlayerGameStatus().getCards()[k] != null){
							int cardType = manager.stonePanel.getPlayerGameStatus().getCards()[k].attackType;
							if(cardType == eliminateType){
								CrewPanel crewPanel = TurningStonePanel.tspREF.getCrewPanel();
								vec2 thisCrewPos = crewPanel.getCrewCenterLocation(k);
								Battle.particleSystemREF.addConstrainMotion(v2, thisCrewPos, ParticleInterface.parabolicStrategy, 20, cardType);
							}
						}
					
					}
				}
				//
				
			}
			manager.stonePanel.getComboPanel().setNumber(manager.stonePanel.getComboPanel().number + 1);
			manager.stonePanel.getComboPanel().setCanSee(true);
		}
		
		for(int i=0; i<comboMessage.totalEliminateNum; i++){ //make stonePanel know which is eliminate
			int x = comboMessage.eliminatePosition[i].getX(); 
			int y = comboMessage.eliminatePosition[i].getY();
			manager.stonePanel.getStone(x, y).setEliminate(true);
		}
		//Test
		makeStoneInAir(manager, comboMessage);
		//===========================================
		int[][] remainStoneFrom = new int[6][5];
		int[] eliminateStoneID = new int[comboMessage.eliminatePosition.length];
		
		for(int i=0; i<6; i++)
			for(int j=0; j<5; j++)
				remainStoneFrom[i][j] = -1;
		
		int count = 0;
		for(int i=0; i<6; i++){
			for(int j=0; j<5; j++){
				if(manager.stonePanel.getStone(i,j).isEliminateBool() == false){
					remainStoneFrom[i][j] = manager.stonePanel.getStone(i, j).getid();
				}
				else{
					remainStoneFrom[i][j] = -1;  //-1 mean is eliminated
					eliminateStoneID[count++] = manager.stonePanel.getStone(i, j).getid();
				}
			}
		}
		
		//check if it's right
		/*
		System.out.println("remainStoneFrom: ");
		show2DIntArray(remainStoneFrom);
		System.out.println("eliminateStoneID: ");
		show1DIntArray(eliminateStoneID);
		*/
		//====================================
		
		int[][] remainStoneTo = new int[6][5];
		
		for(int i=0; i<6; i++){
			for(int j=0; j<5; j++){
				remainStoneTo[i][j] = remainStoneFrom[i][j];
			}
		}
		
		//start to fall
		for(int j=3; j>=0; j--){
			for(int i=0; i<6; i++){
				if(remainStoneTo[i][j] != -1){
					fall(remainStoneTo, i, j);
				}
			}
		}
		
		//check if it's right
		/*
		System.out.println("remainStoneTo: ");
		show2DIntArray(remainStoneTo);
		*/
		//====================================
		
		int[][] eliminateStonesGeometry = new int[6][5];
		
		for(int i=0; i<6; i++){
			for(int j=0; j<5; j++){
				eliminateStonesGeometry[i][j] = -1;  //-1 mean none eliminate
			}
		}
		
		count = 0;
		for(int i=0; i<6; i++){
			for(int j=0; j<5; j++){
				if(remainStoneTo[i][j] == -1){
					eliminateStonesGeometry[i][j] = eliminateStoneID[count++];
				}
			}
		}
		
		//check if it's right
		/*
		System.out.println("EliminateStonesGeometry: ");
		show2DIntArray(eliminateStonesGeometry);
		*/
		//=========================================
		
		//determine what type and how many enhance stone should fall
		int enhanceNum = 0;
		for(int i=0; i<comboMessage.sMessages.size(); i++){
			if(comboMessage.sMessages.get(i).eliminateNum >= 5)
				enhanceNum++;
		}
		int[] enhanceType = new int[enhanceNum];
		for(int i=0, j=0; i<comboMessage.sMessages.size(); i++){
			if(comboMessage.sMessages.get(i).eliminateNum >= 5){
				enhanceType[j++] = comboMessage.sMessages.get(i).stoneType;
			}
		}
		
		int countEnhance = 0;
		boolean[] enhancePlace = new boolean[comboMessage.eliminatePosition.length];
		for(int i=0; i<enhancePlace.length; i++)
			enhancePlace[i] = false;
		Random randEnhance = Time.rand;
		while(countEnhance < enhanceNum){
			int eNum = randEnhance.nextInt(enhancePlace.length);
			if(enhancePlace[eNum] == false){
				enhancePlace[eNum] = true;
				countEnhance++;
			}
		}
		
		//randomize all stone
		for(int i=0, j=0; i<comboMessage.eliminatePosition.length; i++){
			int x = comboMessage.eliminatePosition[i].getX();
			int y = comboMessage.eliminatePosition[i].getY();
			if(enhancePlace[i] == false)
				manager.stonePanel.getStone(x, y).randomItSelf();
			else 
				manager.stonePanel.getStone(x, y).setTypeItSelf(enhanceType[j++], true);
		}
		//all above is correct

		//calculate moving info
		int movingInfoNum = comboMessage.totalEliminateNum; //at least
		
		for(int i=0; i<6; i++){
			for(int j=0; j<5; j++){
				int id = remainStoneFrom[i][j];
				int x = getToID(id, remainStoneTo).getX();
				int y = getToID(id, remainStoneTo).getY();
				if(((i != x) || (j != y)) && id!=-1)
					movingInfoNum++;
			}
		}
		
		MovingInfo[] mInfo = new MovingInfo[movingInfoNum];
		/*
		System.out.println("Moving Info Num: " + movingInfoNum);
		*/

		
		//set position first
		count = 0;
		for(int i=0; i<6; i++){
			for(int j=0; j<5; j++){
				if(remainStoneFrom[i][j]!=-1){  //stone remain
					int x = getToID(remainStoneFrom[i][j], remainStoneTo).getX();
					int y = getToID(remainStoneFrom[i][j], remainStoneTo).getY();
					//
					manager.stonePanel.getStone(i, j).setPosition(manager.stonePanel.getPanel().getEachGridPosition(x, y));
					//
					if(i!=x || y!= j){
						vec2 from = new vec2(manager.stonePanel.getPanel().getEachGridPosition(i, j));
						vec2 to =  new vec2(manager.stonePanel.getPanel().getEachGridPosition(x, y));
						mInfo[count++] = new MovingInfo(from, to, manager.stonePanel.getStone(i, j));
					}
				}
				else{ //stone is eliminated
					int x = getToID(manager.stonePanel.getPanel().getGridID(i, j), eliminateStonesGeometry).getX();
					int y = getToID(manager.stonePanel.getPanel().getGridID(i, j), eliminateStonesGeometry).getY();
					//
					manager.stonePanel.getStone(i, j).setPosition(manager.stonePanel.getPanel().getEachGridPosition(x, y));
					//
					vec2 to = new vec2(manager.stonePanel.getPanel().getEachGridPosition(x, y));
					vec2 from =  new vec2(manager.stonePanel.getPanel().getEachGridPosition(x, y)).sub(new vec2(0,400));
					mInfo[count++] = new MovingInfo(from, to, manager.stonePanel.getStone(i, j));
				}
			}
		}
		
		//then register ID second
		
		for(int i=0; i<6; i++){
			for(int j=0; j<5; j++){
				if(remainStoneTo[i][j]!=-1){
					manager.stonePanel.getPanel().setGridID(i, j, remainStoneTo[i][j]);
				}
				else{
					manager.stonePanel.getPanel().setGridID(i, j, eliminateStonesGeometry[i][j]);
				}
			}
		}
		
		manager.stonePanel.setAllStoneIsNotEliminated();
		return mInfo;
		
	}
	
	
	
	public static vec2 getToID(int id, int[][] findin){
		for(int i=0; i<6; i++){
			for(int j=0; j<5; j++){
				if(id == findin[i][j]){
					return new vec2(i, j);
				}
			}
		}
		System.out.println("fail to find");
		return null;
	}
	
	public static void fall(int[][] remainStoneTo,int x, int y){
		if(y+1<=4 && remainStoneTo[x][y+1] == -1){
			remainStoneTo[x][y+1] = remainStoneTo[x][y];
			remainStoneTo[x][y] = -1;
			fall(remainStoneTo, x, y+1);
		}
	}
	
	public static void show2DIntArray(int[][] twoDArray){
		for(int i=0; i<5; i++){
			for(int j=0; j<6; j++){
				System.out.printf("%3d ", twoDArray[j][i]);
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	public static void show1DIntArray(int[] oneDArray){
		for(int i=0; i<oneDArray.length; i++)
			System.out.printf("%3d ", oneDArray[i]);
		System.out.println("");
		System.out.println("");
	}
}
