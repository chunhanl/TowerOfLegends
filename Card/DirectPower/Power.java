package DirectPower;

import stonePanel.TurningStonePanel;

public abstract class Power {
	//data member
	public String skillName;
	//constructor
	public Power(){
	}
	
	//abstract method
	public abstract void activatedPower();
	public abstract String getSkillName();
}
