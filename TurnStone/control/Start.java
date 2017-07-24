package control;

public class Start implements State{

	public boolean shouldDo(StateControlManager manager) {
		//load enemy or some how
		return true;
	}

	public void changeState(StateControlManager manager) {
		System.out.println("Start to TurningStone");
		TurningStone turningStone = new TurningStone(manager);
		manager.stonePanel.freeControl();
		manager.setState(turningStone);
	}

	public void showState() {
		System.out.println("Start");	
	}

}
