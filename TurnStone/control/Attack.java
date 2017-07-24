package control;

import java.util.ArrayList;

import counter.Enemy;
import particleSystem.ParticleInterface;
import math.vec2;
import motionMethod.MotionStrategy;
import motionMethod.ParabolicStrategy;
import InterFace.Battle;
import MyFrame.MyFrame;
import card.Card;
import card.SimpleCard;
import resolverModel.PrePowerResolver;
import stonePanel.CrewPanel;
import stonePanel.TurningStonePanel;
import gameUtil.AttackInfo;
import gameUtil.DataPassenger;
import gameUtil.greatAttack;

public class Attack implements State{
	//data member
	public static boolean attackReach = true;
	//method
	public boolean shouldDo(StateControlManager manager) {

		int passComboMessageNum = DataPassenger.cMessages.size();
		
		int darkEnhance = 0;
		int darkEliminateTime = 0;
		int darkmore = 0;
		
		int brightEnhance = 0;
		int brightEliminateTime = 0;
		int brightmore = 0;
		
		int fireEnhance = 0;
		int fireEliminateTime = 0;
		int firemore = 0;
		
		int woodEnhance = 0;
		int woodEliminateTime = 0;
		int woodmore = 0;
		
		int waterEnhance = 0;
		int waterEliminateTime = 0;
		int watermore = 0;
		
		int heartEnhance = 0;
		int heartEliminateTime = 0;
		int heartmore = 0;
		
		int totCombo = 0;
		
		for(int i=0; i<passComboMessageNum; i++){
			for(int j=0; j<DataPassenger.cMessages.get(i).sMessages.size(); j++){
				if(DataPassenger.cMessages.get(i).sMessages.get(j).eliminateNum>=3){
					totCombo++;
					switch(DataPassenger.cMessages.get(i).sMessages.get(j).stoneType){
						case 0:
							darkEliminateTime++;
							darkmore += DataPassenger.cMessages.get(i).sMessages.get(j).eliminateNum - 3;
							break;
						case 1:
							brightEliminateTime++;
							brightmore += DataPassenger.cMessages.get(i).sMessages.get(j).eliminateNum - 3;
							break;
						case 2:
							fireEliminateTime++;
							firemore += DataPassenger.cMessages.get(i).sMessages.get(j).eliminateNum - 3;
							break;
						case 3:
							waterEliminateTime++;
							watermore += DataPassenger.cMessages.get(i).sMessages.get(j).eliminateNum - 3;
							break;
						case 4:
							woodEliminateTime++;
							woodmore += DataPassenger.cMessages.get(i).sMessages.get(j).eliminateNum - 3;
							break;
						case 5:
							heartEliminateTime++;
							heartmore += DataPassenger.cMessages.get(i).sMessages.get(j).eliminateNum - 3;
							break;
					}
					
					for(int k=0; k<DataPassenger.cMessages.get(i).sMessages.get(j).eliminatePosition.length; k++){
						int x = DataPassenger.cMessages.get(i).sMessages.get(j).eliminatePosition[k].getX(); 
						int y = DataPassenger.cMessages.get(i).sMessages.get(j).eliminatePosition[k].getY();
						if(TurningStonePanel.tspREF.getStone(x, y).isEnhance() == true){
							switch(DataPassenger.cMessages.get(i).sMessages.get(j).stoneType){
							case 0:
								darkEnhance++;
								break;
							case 1:
								brightEnhance++;
								break;
							case 2:
								fireEnhance++;
								break;
							case 3:
								waterEnhance++;
								break;
							case 4:
								woodEnhance++;
								break;
							case 5:
								heartEnhance++;
								break;
							}
						}
					}
				}
			}
		}
		boolean[] isToAll = new boolean[6];
		for(int i=0; i<6; i++){
			isToAll[i] = false;
		}
		
		for(int i=0; i<passComboMessageNum; i++){
			boolean[] tmp = DataPassenger.cMessages.get(i).eachTypeIsWholeorSingle;
			for(int j=0; j<6; j++){
				isToAll[j]  = isToAll[j] || tmp[j];
			}
		}
		
		SimpleCard[] cardREF = TurningStonePanel.tspREF.getPlayerGameStatus().getCards();
		//傷害 =（基礎攻擊）*（combo加成效果+1）*（同色消除次數+多珠數*0.25+強化珠*0.15）*倍率
		/*
		int darkAtt = (int)((8000) * (totCombo*0.25+1) * (darkEliminateTime + darkmore * 0.25 + darkEnhance*0.15) * 6.25);
		
		
		int brightAtt = (int)((8000) * (totCombo*0.25+1) * (brightEliminateTime + brightmore * 0.25 + brightEnhance*0.15) * 6.25);
		
			
		int fireAtt = (int)((8000) * (totCombo*0.25+1) * (fireEliminateTime + firemore * 0.25 + fireEnhance*0.15) * 6.25);
				
				
		int woodAtt = (int)((8000) * (totCombo*0.25+1) * (woodEliminateTime + woodmore * 0.25 + woodEnhance*0.15) * 6.25);
		
						
		int waterAtt = (int)((8000) * (totCombo*0.25+1) * (waterEliminateTime + watermore * 0.25 + waterEnhance*0.15) * 6.25);
		*/	
		/*
		for(int i=0; i<TurningStonePanel.tspREF.getAtkPanel().level.round[TurningStonePanel.tspREF.getAtkPanel().level.currentRound - 1].enemy.length; i++)
		TurningStonePanel.tspREF.getAtkPanel().level.round[TurningStonePanel.tspREF.getAtkPanel().level.currentRound - 1].enemy[i].takeDamage(darkAtt);
		//System.out.println(darkAtt + ", " +TurningStonePanel.tspREF.getAtkPanel().level.round[TurningStonePanel.tspREF.getAtkPanel().level.currentRound - 1].enemy[0].currentBlood);
		*/
		
		//getPrePowerResolver
		PrePowerResolver prePower = TurningStonePanel.tspREF.getPlayerGameStatus().getPrePower();
		
		//cure blood
		int totalRECOVER = 0;
		for(int i=0; i<cardREF.length; i++){
			if(cardREF[i] != null){
				AttackInfo tmp = new AttackInfo(cardREF[i].recover, 5, cardREF[i].attackType, false);
				int value = 0;
				value = prePower.getResolvedPower(tmp);
				totalRECOVER += value;
			}
		}
		
		int heartAtt = (int)((totalRECOVER) * (totCombo*0.25+1) * (heartEliminateTime + heartmore * 0.25 + heartEnhance*0.15));
		if(Battle.isNet == false)
			TurningStonePanel.tspREF.getPlayerGameStatus().cureBlood(heartAtt);
		
		//attack
		Enemy[] enemyREF = TurningStonePanel.tspREF.getAtkPanel().level.round[TurningStonePanel.tspREF.getAtkPanel().level.currentRound - 1].enemy;
		int[] enemyHP = new int[enemyREF.length];
		
		for(int i =0; i < enemyREF.length; i++){
			enemyHP[i] = enemyREF[i].currentBlood;
		}
		
		AttackInfo[] everyCharactorAttackInfo = new AttackInfo[cardREF.length];
		
		//calculate every charactor attack type and attack(unCalculate attack ratio)
		for(int i=0; i<cardREF.length; i++){
			if(cardREF[i] != null){
				int type = cardREF[i].attackType;
				int attack = 0;
				boolean toAll = false;
				//(0 dark, 1 bright, 2 fire, 3 water, 4 wood, 5 heart)
				switch(type){
					case 0:
						attack = (int)((cardREF[i].attack) * (totCombo*0.25+1) * (darkEliminateTime + darkmore * 0.25 + darkEnhance*0.15));
						toAll = isToAll[0];
						break;
					case 1:
						attack = (int)((cardREF[i].attack) * (totCombo*0.25+1) * (brightEliminateTime + brightmore * 0.25 + brightEnhance*0.15));
						toAll = isToAll[1];
						break;
					case 2:
						attack = (int)((cardREF[i].attack) * (totCombo*0.25+1) * (fireEliminateTime + firemore * 0.25 + fireEnhance*0.15));
						toAll = isToAll[2];
						break;
					case 3:
						attack = (int)((cardREF[i].attack) * (totCombo*0.25+1) * (waterEliminateTime + watermore * 0.25 + waterEnhance*0.15));
						toAll = isToAll[3];
						break;	
					case 4:
						attack = (int)((cardREF[i].attack) * (totCombo*0.25+1) * (woodEliminateTime + woodmore * 0.25 + woodEnhance*0.15));
						toAll = isToAll[4];
						break;
					default:
						System.out.println("Error ----- unexpected Attack type, type:" + type);
						System.exit(1);
				}
				everyCharactorAttackInfo[i] = new AttackInfo(attack, type, type, toAll);
			}
			else{
				//happen when the card on i'th place is null(no card namely)
				everyCharactorAttackInfo[i] = null;
			}
		}
		
		if(Battle.isNet == false){       ////////////////////// calculate if not in net mode
		int EnemyAliveNum = 0;
		for(int i=0; i< enemyREF.length; i++){
			if(enemyHP[i] != 0){
				EnemyAliveNum++;
			}
		}
		
		Enemy[] EnemyCanAttack = new Enemy[EnemyAliveNum];
		int count = 0;
		for(int i=0; i<enemyREF.length; i++){
			if(enemyHP[i] != 0){
				EnemyCanAttack[count++] = enemyREF[i];
			}
		}
		
		//------------calculate strategy to attack
		/*
		 * can use variable
		 * 1.Enemy[] enemyREF
		 * 2.int[] enemyHP
		 * 3.Enemy[] EnemyCanAttack
		 * 4.int EnemyAliveNum
		 * 5.AttackInfo[] everyCharactorAttackInfo
		 * 6.PrePowerResolver prePower
		 */
		
		//let attackInfo pass through resolver
		for(int i=0; i<cardREF.length; i++){
			int value = 0;
			if(everyCharactorAttackInfo[i] != null){
				value = prePower.getResolvedPower(everyCharactorAttackInfo[i]);
				everyCharactorAttackInfo[i].attack = value;
			}
		}
		
		
		//choose which can attack
		count = 0;
		for(int i=0; i<cardREF.length; i++){
			if(cardREF[i] != null){
				count++;
			}
		}
		
		int[] attackerID = new int[count];  //可攻擊的隊員欄(有可能那ㄍ隊員欄沒放隊員啊)
		AttackInfo[] canAttackInfo = new AttackInfo[count];//可以攻擊ㄉ隊員欄ㄉ攻擊訊息
		boolean[] attackAll = new boolean[count];
		
		count = 0;
		for(int i=0; i<cardREF.length; i++){
			if(cardREF[i] != null){
				attackerID[count] = i;
				canAttackInfo[count] = everyCharactorAttackInfo[i];
				if(everyCharactorAttackInfo[i].toAll == true){
					attackAll[i] = true;
				}
				else{
					attackAll[i] = false;
				}
				count++;
			}
		}
		

		
		int[] simulateEnemyBlood = new int[enemyHP.length];
		ArrayList<Integer> onGroundEnemyID = new ArrayList<Integer>();
		for(int i=0; i<enemyHP.length; i++){
			simulateEnemyBlood[i] = enemyHP[i];
			if(enemyHP[i]!=0)
				onGroundEnemyID.add(new Integer(i));
		}

		//calculate attack all first
		ArrayList<Integer> beAttackEnemyID = new ArrayList<Integer>();
		for(int i=0; i<attackAll.length; i++){
			int typeTmp = canAttackInfo[i].attackerType;
			int atk = canAttackInfo[i].attack;
			if(attackAll[i] == true){
				for(int j=0; j<enemyHP.length; j++){  //all attackAllEnemy
					int enemyType = enemyREF[j].type;
					int enemyDef = enemyREF[j].defense;
					if(counter(typeTmp, atk) == 0){
						atk = (int)(atk * 1.5);
					}
					else if(counter(typeTmp, atk) == 2){
						atk = (int)(atk / 1.5);
					}
					
					if(simulateEnemyBlood[j] + enemyDef - atk <= 0){
						if(simulateEnemyBlood[j]>0){
							beAttackEnemyID.add(new Integer(j));
						}
						simulateEnemyBlood[j] = 0;
					}
					else{
						beAttackEnemyID.add(new Integer(j));
						simulateEnemyBlood[j] -= atk + enemyDef;
					}
				}
			}
		}
		
		//calculate attack single next
		count = 0;
		for(int i=0; i<simulateEnemyBlood.length; i++){
			if(simulateEnemyBlood[i]>0)
				count++;
		}
		int[] notDeadEnemyID = new int[count];
		count = 0;
		for(int i=0; i<simulateEnemyBlood.length; i++){
			if(simulateEnemyBlood[i]>0){
				notDeadEnemyID[count++] = i;
			}
		}
		
		/*
		 * 1.int[] attackerID
		 * 2.AttackInfo[] canAttackInfo
		 * 3.boolean[] attackAll
		 * 4.int[] simulateEnemyBlood //經過全體攻擊後剩下ㄉ血量
		 * 5.int[] notDeadEnemyID
		*/
		
		count = 0;
		for(int i=0; i<attackerID.length; i++){
			if(attackAll[i] == false)
				count++;
		}
		
		//parse the single attack ID
		int[] singleAttackerID = new int[count];
		AttackInfo[] singleAttackerInfo = new AttackInfo[count];
		
		count = 0;
		for(int i=0; i<attackerID.length; i++){
			if(attackAll[i] == false){
				singleAttackerID[count] = attackerID[i];
				singleAttackerInfo[count] = canAttackInfo[i];
				count++;
			}
		}
		
		count = 0;
		for(int i=0; i<simulateEnemyBlood.length; i++){
			if(simulateEnemyBlood[i]>0){
				count++;
			}
		}
		
		int[] remainEnemyID = new int[count]; ///////////////////////////////////////////
		int[] remainEnemyBlood = new int[count];////////////////////////////////////////
		count = 0;
		for(int i=0; i<simulateEnemyBlood.length; i++){
			if(simulateEnemyBlood[i] > 0){
				remainEnemyID[count] = i;
				remainEnemyBlood[count] = simulateEnemyBlood[i];
				count++;
			}
		}
		//尋找是否可以擊殺敵人(至少一位) 一位都不行的話就尋找最大的攻擊
		//try to find the way to kill(at least one)
		OneCanKillMatch[] everyKillMatch = new OneCanKillMatch[remainEnemyID.length];
		for(int i=0; i<remainEnemyID.length; i++){
			everyKillMatch[i] = new OneCanKillMatch();
		}

		for(int i=0; i<remainEnemyID.length; i++){ //list all possible to kill each enemy
			int enemyBlood = remainEnemyBlood[i];
			int enemyType = enemyREF[remainEnemyID[i]].type;
			int enemyDef = enemyREF[remainEnemyID[i]].defense;
			//用位移法(我自己亂叫ㄉXD)來實現組合
			ArrayList<booleanArrayMatch> arrayMatch = new ArrayList<booleanArrayMatch>();
			for(int pickNum = 1; pickNum<= singleAttackerInfo.length; pickNum++){ //C n 取 pickNum的概念
				boolean[] bArray = new boolean[singleAttackerInfo.length];
				//initialize
				for(int ini=0; ini<singleAttackerInfo.length; ini++){
					if(ini<pickNum){
						bArray[ini]=true;
					}
					else{
						bArray[ini]=false;
					}
				}
				arrayMatch.add(new booleanArrayMatch(bArray));
				//start moving
				boolean reachEnd = true;
				do{
					reachEnd = true;
					for(int pos = 0; pos < bArray.length; pos++){
						if(bArray[pos] == true){  //find choose one then try to move
							if(pos+1 < bArray.length && bArray[pos+1] != true){
								bArray[pos] = false;
								bArray[pos+1] = true;
								//移動完為新的組合，加入arrayMatch中
								arrayMatch.add(new booleanArrayMatch(bArray));
							}
						}
					}
					
					for(int k = 0; k< singleAttackerInfo.length - pickNum; k++){
						if(bArray[k] != false)
							reachEnd = false;
					}
				}while(!reachEnd);
				//跳出後bArray為最後的一個組合,加入!
				arrayMatch.add(new booleanArrayMatch(bArray));	
			}
			
			//calculate if eachMatch can kill
			for(int j=0; j<arrayMatch.size(); j++){
				boolean[] array = arrayMatch.get(j).booleanArray;
				int totDamage = 0;
				int attackerNum = 0;
				for(int k=0; k<singleAttackerInfo.length; k++){
					if(array[k] == true){
						int damage = singleAttackerInfo[k].attack;
						int type = singleAttackerInfo[k].type;
						if(counter(type,enemyType) == 0){
							damage = (int)(damage * 1.5);
						}
						else if(counter(type,enemyType) == 2){
							damage = (int)(damage / 1.5);
						}
					
						totDamage += damage;
						attackerNum++;
					}
				}
				if(totDamage - enemyDef * attackerNum >= enemyBlood){
					CanKillMatch killMatch = new CanKillMatch();
					for(int z=0; z<singleAttackerInfo.length; z++){
						if(array[z] == true){
							/*
							 * killMatch.add(singleAttackerID[z]) 
							 * should be the reason why line722 occurs error sometimes
							 * cuz I add singleAttackerID to it so it looks to the true ID
							 * but in findGreat I suppose to use reference one
							 */
							killMatch.add(singleAttackerID[z]);
						}
					}
					everyKillMatch[i].add(killMatch);
				}
			}
		}

		//above this line we find all posible way to kill every enemy
		//then we try to use what we found
		int leftEnemy = 0;
		
		for(int i =0; i<everyKillMatch.length; i++){
			if(everyKillMatch[i].match.size() != 0){    //獲得理論上沒死的敵人(可能因為攻擊隊員重複而有額外沒殺掉的狀況)
				leftEnemy++;
			}
		}

		int[] appearTimes = new int[singleAttackerInfo.length];
		for(int i=0; i<appearTimes.length; i++){
			appearTimes[i] = 0;
		}
		
		int[] appearAt = new int[singleAttackerInfo.length];
		for(int i=0; i<appearAt.length; i++){
			appearAt[i] = -1;
		}
		
		//運用遞迴關係來找出有沒有能夠擊殺全部的方法

		findGreat(everyKillMatch, 0, appearTimes, appearAt, remainEnemyID, singleAttackerID);
		//最後分配應該的攻擊
		int[] singleAttackToID = new int[singleAttackerID.length];
		for(int i=0; i<singleAttackerID.length; i++){
			singleAttackToID[i] = -1;
		}
		if(DataPassenger.gAttack.size() == 0){   //沒有找到最合適的全部擊殺法, 尋找最大攻擊化的方法(亂打 找剋屬的)
			//find counter
			boolean[] hit = new boolean[singleAttackerID.length];
			for(int i=0; i<singleAttackerID.length; i++){
				hit[i] = false;
			}
			
			for(int i=0; i<hit.length; i++){
				for(int j=0; j<remainEnemyID.length && hit[i] == false; j++){
					if(counter(singleAttackerInfo[i].type, enemyREF[remainEnemyID[j]].type) == 0 && hit[i] != true && remainEnemyBlood[j] != 0){
						if(enemyREF[remainEnemyID[j]].defense < singleAttackerInfo[j].attack * 1.5){
							remainEnemyBlood[j] = (int)(remainEnemyBlood[j] - singleAttackerInfo[i].attack * 1.5 - enemyREF[remainEnemyID[j]].defense);
							if(remainEnemyBlood[j]<0)
								remainEnemyBlood[j] = 0;
							hit[i] = true;
							singleAttackToID[i] = j;
						}
					}
					/*
					if(hit[i] == true){
						break;
					}
					*/
				}
				
				for(int j=0; j<remainEnemyID.length && hit[i] == false; j++){
					if(counter(singleAttackerInfo[i].type, enemyREF[remainEnemyID[j]].type) == 1 && hit[i] != true && remainEnemyBlood[j] != 0){
						if(enemyREF[remainEnemyID[j]].defense < singleAttackerInfo[j].attack * 1){
							remainEnemyBlood[j] = (int)(remainEnemyBlood[j] - singleAttackerInfo[i].attack * 1 - enemyREF[remainEnemyID[j]].defense);
							if(remainEnemyBlood[j]<0)
								remainEnemyBlood[j] = 0;
							hit[i] = true;
							singleAttackToID[i] = j;
						}
					}
					/*
					if(hit[i] == true){
						break;
					}
					*/
				}
				
				for(int j=0; j<remainEnemyID.length && hit[i] == false; j++){
					if(counter(singleAttackerInfo[i].type, enemyREF[remainEnemyID[j]].type) == 2 && hit[i] != true && remainEnemyBlood[j] != 0){
						if(enemyREF[remainEnemyID[j]].defense < singleAttackerInfo[j].attack / 1.5){
							remainEnemyBlood[j] = (int)(remainEnemyBlood[j] - singleAttackerInfo[i].attack / 1.5 - enemyREF[remainEnemyID[j]].defense);
							if(remainEnemyBlood[j]<0)
								remainEnemyBlood[j] = 0;
							hit[i] = true;
							singleAttackToID[i] = j;
						}
					}
					/*
					if(hit[i] == true){
						break;
					}
					*/
				}
			}
			boolean hitAll = true;
			for(int z=0; z<singleAttackerID.length; z++){
				if(hit[z] == false){
					hitAll = false;
					break;
				}
			}
			
			if(hitAll == false){
				int findLastEnemyID = -3;
				for(int k=0; k<remainEnemyID.length; k++){
					if(remainEnemyBlood[k] != 0){
						findLastEnemyID = k;
						break;
					}
				}
				if(findLastEnemyID == -3){
					findLastEnemyID = onGroundEnemyID.get(0);
				}
				for(int k=0; k<hit.length; k++){
					if(hit[k]==false){
						hit[k] = true;
						singleAttackToID[k] = findLastEnemyID;
					}
				}
			}
			
		}
		else{
			System.out.println("use find great");
			int[] id = DataPassenger.gAttack.get(0).attackToEnemyID; //use first strategy(I am lazy! XD)
			boolean[] remainNotAttack = new boolean[singleAttackerID.length];
			for(int i=0; i<remainNotAttack.length; i++){
				remainNotAttack[i] = true;
			}
			int[] atID = DataPassenger.gAttack.get(0).attackerID;
			for(int i=0; i<atID.length; i++){
				for(int j=0; j<singleAttackerID.length; j++){
					if(singleAttackerID[j] == atID[i]){
						remainNotAttack[j] = false;
					}
				}
			}
			
			for(int i=0,k=0; i<singleAttackToID.length; i++){
				if(remainNotAttack[i] == false){
					singleAttackToID[i] = id[k++];
				}
				else{
					singleAttackToID[i] = onGroundEnemyID.get(0);
				}
				
			}
		}
		
		//attacker strategy
		/*
		 * 1.int[] attackerID
		 * 2.AttackInfo[] canAttackInfo
		 * 3.boolean[] attackAll
		 * 4.int[] simulateEnemyBlood //經過全體攻擊後剩下ㄉ血量
		 * 5.int[] notDeadEnemyID
		 * 6.ArrayList<Integer> beAttackEnemyID 
		*/
		int[] attackTo = new int[attackerID.length];
		
		for(int i=0,k=0; i<attackTo.length; i++){
			if(attackAll[i] == true){
				attackTo[i] = -1;
			}
			else{
				attackTo[i] = singleAttackToID[k++];
			}
		}
		/*
		for(int i=0; i<attackTo.length; i++){
			System.out.println(attackTo[i]);
		}
		*/
		
		ArrayList<particleInvokeInfo> invokeInfo = new ArrayList<particleInvokeInfo>();
		for(int i=0,k=0; i<manager.stonePanel.getPlayerGameStatus().getCards().length; i++ ){
			CrewPanel crewPanel = TurningStonePanel.tspREF.getCrewPanel();
			vec2 thisCrewPos = crewPanel.getCrewCenterLocation(i);
			if(thisCrewPos != null && canAttackInfo[k].attack!=0){
				if(attackTo[k] == -1){ //total attack
					for(int z=0; z<onGroundEnemyID.size(); z++){
						MotionStrategy strategy;
						int id = onGroundEnemyID.get(z).intValue();
						vec2 enemyPOS = enemyREF[id].getCenter();
						int enemyType = enemyREF[id].type;
						int type = canAttackInfo[k].type;
						int attack = canAttackInfo[k].attack;
						if(counter(type, enemyType) == 0){
							attack *= 1.5;
						}
						else if(counter(type, enemyType) == 2){
							attack /= 1.5;
						}
						strategy = Battle.particleSystemREF.addConstrainMotion(new vec2(thisCrewPos.getX(), thisCrewPos.getY()), new vec2(enemyPOS.getX(),enemyPOS.getY()), ParticleInterface.parabolicStrategy,30,type);
						invokeInfo.add(new particleInvokeInfo(strategy, type, attack, id));
					}
					k++;
				}
				else{//single Attack
					MotionStrategy strategy;
					int id = attackTo[k];
					int type = canAttackInfo[k].type;
					vec2 enemyPOS = enemyREF[id].getCenter();
					int enemyType = enemyREF[id].type;
					int attack = canAttackInfo[k].attack;
					if(counter(type, enemyType) == 0){
						attack *= 1.5;
					}
					else if(counter(type, enemyType) == 2){
						attack /= 1.5;
					}
					strategy = Battle.particleSystemREF.addConstrainMotion(new vec2(thisCrewPos.getX(), thisCrewPos.getY()), new vec2(enemyPOS.getX(),enemyPOS.getY()), ParticleInterface.parabolicStrategy,30,type);
					invokeInfo.add(new particleInvokeInfo(strategy, type, attack, id));
					k++;
				}

				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(thisCrewPos != null){
				k++;
			}
		}
		boolean[] isHit = new boolean[invokeInfo.size()];
		for(int i=0; i<isHit.length; i++){
			isHit[i] = false;
		}
		
		/*
		 * note that in order to improve the visual satisfy and
		 * make every thing reasonable so I have to make every Visual
		 * things change after every things become reasonable
		 * 1. check if all hit
		 * 2. check if all enemy visual hp bars are sync to theirs math blood
		 */
		boolean notHit = false;
		while(!notHit){
			isHit = invokeAttack(invokeInfo, isHit, enemyREF);
			boolean tmp = true;
			for(int i=0; i<isHit.length; i++){
				if(isHit[i] == false){
					tmp = false;
				}
			}
			notHit = tmp;
			isHit = invokeAttack(invokeInfo, isHit, enemyREF);
			//sleep to release some resource to computer
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

		//finish using DataPassenger then clear it!
		DataPassenger.clearAllData();
		}
		else{
			int attack = 0;
			for(int i=0; i<everyCharactorAttackInfo.length; i++){
				if(everyCharactorAttackInfo[i] != null){
					attack += everyCharactorAttackInfo[i].attack;
				}
			}
			attack /= 10; //small attack
			String name = (MyFrame.refFrame).client.getPlayer().getName();
			//format = @ name % Attack # value
			String buildCommand = "@" + name + "%Attack#" + attack; 	
			(MyFrame.refFrame).client.send(buildCommand);
			try {
			    Thread.sleep(30);
			}
			catch (InterruptedException e) {
			    e.printStackTrace();
			    // handle the exception...        
			    // For example consider calling Thread.currentThread().interrupt(); here.
			}
			if(heartAtt > 0){
				buildCommand = "@" + name + "%Recover#" + heartAtt; 
				(MyFrame.refFrame).client.send(buildCommand);
			}
		}
		
		
		return true;
	}
	
	public boolean[] invokeAttack(ArrayList<particleInvokeInfo> info, boolean[] isHit, Enemy[] enemyREF){
		for(int i=0; i<info.size(); i++){
			if(isHit[i] == false){
				if(((ParabolicStrategy)(info.get(i).strategy)).reach == true){
					enemyREF[info.get(i).enemyID].takeDamage(info.get(i).attack);
					isHit[i] = true;
				}
			}
		}
		return isHit;
	}
	
	
	private void findGreat(OneCanKillMatch[] killMatch, int layer, int[] appearTimes, int[] appearAt, int[] remainEnemyID, int[] singleAttackerID){
		if((layer) < killMatch.length){
			for(int i=0; i<killMatch[layer].match.size(); i++){
					int[] tmpAppearTimes = new int[appearTimes.length];
					int[] tmpAppearAt = new int[appearAt.length];
					for(int j=0; j<appearTimes.length; j++){
						tmpAppearTimes[j] = appearTimes[j];
						tmpAppearAt[j] = appearAt[j];
					}
					/*
					 * sometimes it will error on line 722, maybe it's attackerID builder take the
					 * ID from origin one instead of the sample-remain-singleAttack one, maybe~ 6/2
					 */
					int[] tmp = killMatch[layer].match.get(i).attackerID;   
					int find = 0;
					for(int z=0; z<tmp.length; z++){
						for(int a=0; a<singleAttackerID.length; a++){
							if(singleAttackerID[a] == tmp[z]){
								find = a;
							}
						}
						tmpAppearTimes[find]++;
						appearAt[find] = layer;
					}
					findGreat(killMatch, layer+1, tmpAppearTimes, tmpAppearAt, remainEnemyID, singleAttackerID);
			}
		}
		else{ //bound region
				boolean appearToManyTimes = false;
				for(int a=0; a<appearTimes.length; a++){
					if(appearTimes[a] > 1)
						appearToManyTimes = true;
				}
				if(!appearToManyTimes){
					int[] attackerID = new int[appearTimes.length];
					int[] attackToEnemyID = new int[appearTimes.length];
					for(int b=0; b<appearTimes.length; b++){
						attackerID[b] = b;
						attackToEnemyID[b] = appearAt[b];
					}
					DataPassenger.gAttack.add(new greatAttack(attackerID, attackToEnemyID)); //refernense id
				}
			}
	}

	public void changeState(StateControlManager manager) {
		attackReach = true;
		//should implement EndLevel if we win XD Later will do it
		if(TurningStonePanel.tspREF.getAtkPanel().level.isThisRoundEnd() == true && TurningStonePanel.tspREF.getAtkPanel().level.isFinalLevel() == true){
			System.out.println("Attack to EndLevel");
			EndLevel endLv = new EndLevel("Win");
			manager.setState(endLv);
		}
		else{
			System.out.println("Attack to EndAttack");
			EndAttack endAtt = new EndAttack();
			manager.setState(endAtt);
		}
	}

	public void showState() {
		System.out.println("Attack");
	}
	
	private int counter(int atkType, int takerType){
		//(0 dark, 1 bright, 2 fire, 3 water, 4 wood, 5 heart)
		
		//0 -> counter 1-> normal 2-> be countered
		if((atkType+1) * (takerType+1) == 4){
			return 0;
		}
		else if(atkType == 2 && takerType == 4){
			return 0;
		}
		else if(atkType == 3 && takerType == 2){
			return 0;
		}
		else if(atkType == 4 && takerType == 3){
			return 0;
		}
		else if(atkType == 2 && takerType == 3){
			return 2;
		}
		else if(atkType == 3 && takerType == 4){
			return 2;
		}
		else if(atkType == 4 && takerType == 2){
			return 2;
		}
		else{
			return 1;
		}
	}

}

class particleInvokeInfo{
	//data member
	public MotionStrategy strategy;
	public int attackType;
	public int attack;
	public int enemyID;
	//constructor
	public particleInvokeInfo(MotionStrategy strategy, int type, int atk, int enemyID){
		this.strategy = strategy;
		this.attackType = type;
		this.attack = atk;
		this.enemyID = enemyID;
	}
}

class booleanArrayMatch{
	//data member
	public boolean[] booleanArray;
	//method
	public booleanArrayMatch(boolean[] arr){
		this.booleanArray = new boolean[arr.length];
		for(int i=0; i<arr.length; i++){
			this.booleanArray[i] = arr[i];
		}
	}
}

class OneCanKillMatch{
	//data member
	public ArrayList<CanKillMatch> match;
	//method
	public OneCanKillMatch(){
		this.match = new ArrayList<CanKillMatch>();
	}
	
	public void add(CanKillMatch oneApproach){
		this.match.add(oneApproach);
	}
	
}

class CanKillMatch{
	//data member
	public int[] attackerID;
	
	//method
	public void add(int id){
		if(attackerID == null){
			attackerID = new int[1];
			attackerID[0] = id;
		}
		else{
			int[] tmp = new int[attackerID.length];
			for(int i=0; i<tmp.length; i++){
				tmp[i] = attackerID[i];
			}
			attackerID = new int[tmp.length +1];
			for(int i=0; i<tmp.length; i++){
				attackerID[i] = tmp[i];
			}
			attackerID[tmp.length] = id;
		}
	}
}
