package control;

import javax.swing.JFrame;

import stonePanel.TurningStonePanel;

public class StartLevel implements State{
	//constructor
	public StartLevel(JFrame frame, TurningStonePanel stonePanel){
		frame.getLayeredPane().add(stonePanel);
		System.out.println("GameStarts");
	}
	
	public boolean shouldDo(StateControlManager manager) {
		if(manager.stonePanel != null){
			manager.stonePanel.freezeControl();
			return true;
		}
		System.out.println("The stone panel doesn't initial well(null pointer exception)");
		return false;
	}

	public void changeState(StateControlManager manager) {
		System.out.println("StartLevel to Start");
		Start start = new Start();
		manager.setState(start);
	}

	public void showState() {
		System.out.println("StartLevel");
	}
	

}
