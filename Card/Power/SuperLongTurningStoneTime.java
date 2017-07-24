package Power;

import stonePanel.PlayerBloodBar;

public class SuperLongTurningStoneTime extends Power{
	public void activatedPower() {
		PlayerBloodBar.timerOn = true;
		PlayerBloodBar.superLongTime = true;
	}

}
