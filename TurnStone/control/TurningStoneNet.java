package control;

import stonePanel.PlayerBloodBar;

public class TurningStoneNet implements State{
	
	public TurningStoneNet(StateControlManager manager){
		manager.stonePanel.freeControl();
	}
	
	@Override
	public boolean shouldDo(StateControlManager manager) {
		if(manager.stonePanel.firstChangeOccur == true && manager.stonePanel.released == true){
			return true;
		}
		return false;
	}

	@Override
	public void changeState(StateControlManager manager) {
		System.out.println("TurningStoneNet to CalculateNet");
		manager.stonePanel.freezeControl();
		CalculateNet calcNet = new CalculateNet();
		manager.setState(calcNet);	
	}

	@Override
	public void showState() {
		System.out.println("TurningStoneNet");		
	}

}
