package LeaderPower;

public abstract class LPower {
	//data member
	public String describe;
	
	//abstract method
	public abstract int effect(int type, int attackerType, int value);
	
	//method
	public String showEffect(){
		return this.describe;
	}
}
