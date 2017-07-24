package control;

import stonePanel.PlayerBloodBar;
import stonePanel.TurningStonePanel;
import Animation.Animation;
import Animation.MovingInfo;
import Animation.MovingTask;
import gameUtil.ComboMessage;
import gameUtil.DataPassenger;
import math.CalculateCombo;

public class Calculate implements State{
	//data member
	public static boolean hasEliminateAny;
	//implements abstract method
	public boolean shouldDo(StateControlManager manager) {
		//Calculate and make stone and attack decided
		hasEliminateAny = false;
		while(true){
			MovingInfo[] mInfo = null;
			ComboMessage comboMessage = CalculateCombo.calculateCombo(manager);
			
			/*ComboMessage
			comboMessage.showComboMessage();
			*/
			
			
			if(comboMessage.totalEliminateNum != 0){
				hasEliminateAny = true;
				DataPassenger.cMessages.add(comboMessage);
				mInfo = Animation.calculateStoneFallingStrategy(manager, comboMessage);
			}
			
			
			if(comboMessage.eliminatePosition == null || comboMessage.totalEliminateNum == 0){  //if this condition has no stone to be eliminated than dreak
				try {
					Thread.sleep(1200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				manager.stonePanel.getComboPanel().setCanSee(false);
				manager.stonePanel.getComboPanel().setNumber(0);
				break;
			}
			
			MovingTask mt = null;
			
			if(mInfo != null)
				mt = Animation.fallStone(manager, comboMessage, mInfo);
			
			if(mInfo != null){
				while(mt.isEveryStoneReachPosition() == false){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			else{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}


	public void changeState(StateControlManager manager) {
		System.out.println("Calculate to Attack");
		//when ending we decrease every crew's cd
		if(hasEliminateAny == true){
			TurningStonePanel.tspREF.getCrewPanel().decCrewCD();
		}
		hasEliminateAny = false;
		Attack att = new Attack();
		manager.setState(att);
	}

	public void showState() {
		System.out.println("Calculate");
	}

}
