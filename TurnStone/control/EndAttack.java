package control;

import InterFace.Battle;
import stonePanel.TurningStonePanel;

public class EndAttack implements State{

	public boolean shouldDo(StateControlManager manager) {
		
		TurningStonePanel.tspREF.getAtkPanel().level.round[TurningStonePanel.tspREF.getAtkPanel().level.currentRound -1].decEnemyCD();
		int enemyNum = TurningStonePanel.tspREF.getAtkPanel().level.round[TurningStonePanel.tspREF.getAtkPanel().level.currentRound -1].enemy.length;
		
		if(!TurningStonePanel.tspREF.getAtkPanel().level.round[TurningStonePanel.tspREF.getAtkPanel().level.currentRound -1].enemyAllDead()){
			for(int i=0; i<enemyNum && Battle.isNet == false; i++){
				if(TurningStonePanel.tspREF.getAtkPanel().level.round[TurningStonePanel.tspREF.getAtkPanel().level.currentRound -1].enemy[i].initialCD == 0){
					int atk = TurningStonePanel.tspREF.getAtkPanel().level.round[TurningStonePanel.tspREF.getAtkPanel().level.currentRound -1].enemy[i].attack;
					if(TurningStonePanel.tspREF.getAtkPanel().level.round[TurningStonePanel.tspREF.getAtkPanel().level.currentRound -1].enemy[i].blood != 0){
						TurningStonePanel.tspREF.getPlayerGameStatus().subBlood(atk);
					}
					int cd = TurningStonePanel.tspREF.getAtkPanel().level.round[TurningStonePanel.tspREF.getAtkPanel().level.currentRound -1].enemy[i].attackCD;
					TurningStonePanel.tspREF.getAtkPanel().level.round[TurningStonePanel.tspREF.getAtkPanel().level.currentRound -1].enemy[i].initialCD = cd;
				}
			}
		}
		else{
			/*
			 * need to wait until enemy blood sync
			 */
			while(!TurningStonePanel.tspREF.getAtkPanel().isEveryBloodSync()){
			}
			
			TurningStonePanel.tspREF.getAtkPanel().level.advanceRound();
		}
		return true;
	}

	public void changeState(StateControlManager manager) {
		int health = TurningStonePanel.tspREF.getPlayerGameStatus().getHealthPoint();
		if(!(TurningStonePanel.tspREF.getAtkPanel().level.isFinalLevel() && TurningStonePanel.tspREF.getAtkPanel().level.isThisRoundEnd()) && health != 0){
			System.out.println("EndAttack to Start");
			Start start = new Start();
			manager.setState(start);
		}
		else if(health == 0){
			System.out.println("EndAttack to EndLevel");
			EndLevel ending = new EndLevel("Fail");
			manager.setState(ending);
		}
		else{
			System.out.println("EndAttack to EndLevel");
			EndLevel ending = new EndLevel("Win");
			manager.setState(ending);
		}
	}

	public void showState() {
		System.out.println("EndAttack");
	}

}
