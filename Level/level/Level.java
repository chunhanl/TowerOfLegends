package level;

import Round.Round;

public abstract class Level {
	//data member
	public Round[] round;
	public int roundNumber;
	public int currentRound;
	private String levelTitle;
	public String bgURL;
	//constructor
	public Level(String name){
		setLevelTitle(name);
	}
	
	//getter and setter
	public void setLevelTitle(String name){
		levelTitle = name;
	}
	//method
	public abstract void levelBuilder();
	
	public boolean isFinalLevel(){
		if(currentRound == roundNumber){
			return true;
		}
		return false;
	}
	
	public boolean isThisRoundEnd(){
		if(round[currentRound-1].enemyAllDead()){
			return true;
		}
		return false;
	}
	
	public void advanceRound(){
		if(currentRound < roundNumber)
			currentRound++;
	}
}
