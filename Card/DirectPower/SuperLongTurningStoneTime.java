package DirectPower;

import stonePanel.PlayerBloodBar;

public class SuperLongTurningStoneTime extends Power{
	//constructor
	public SuperLongTurningStoneTime(){
		super();
		this.skillName = "20¬íÂà¯]";
	}
	
	//method
	public void activatedPower() {
		PlayerBloodBar.timerOn = true;
		PlayerBloodBar.superLongTime = true;
	}
	
	public String getSkillName(){
		return this.skillName;
	}

}
