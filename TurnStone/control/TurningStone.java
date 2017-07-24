package control;

import InterFace.Battle;
import stonePanel.PlayerBloodBar;

public class TurningStone implements State{
	
	public TurningStone(StateControlManager manager){
		manager.stonePanel.freeControl();
	}
	public boolean shouldDo(StateControlManager manager) {
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(manager.stonePanel.firstChangeOccur == true && PlayerBloodBar.timerOn == false && Battle.isNet == false){
			PlayerBloodBar.timerOn = true;
		}
		if(Battle.isNet == true){
			if(manager.stonePanel.released == true && manager.stonePanel.firstChangeOccur == true){
				return true;
			}
		}
		else{
			if(manager.stonePanel.released == true && (manager.stonePanel.firstChangeOccur == true || PlayerBloodBar.superLongTime == false)){
				return true;
			}
		}
		return false;
	}

	public void changeState(StateControlManager manager) {
		System.out.println("TurningStone to Calculate");
		manager.stonePanel.freezeControl();
		
		//the attack doesn't even emit yet so it's false
		Attack.attackReach = false;
		
		PlayerBloodBar.timerOn = false;
		PlayerBloodBar.cancelTimer();
		Calculate calc = new Calculate();
		manager.setState(calc);	
	}

	public void showState() {
		System.out.println("TurningStone");
	}
}
