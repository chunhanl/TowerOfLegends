package LeaderPower;

public class BrightRecoverTime2 extends LPower{
	//constructor
	public BrightRecoverTime2(){
		this.describe = "���ݩʦ^�_�O�⭿";
	}
	//method
	public int effect(int type, int attackerType, int value) {
		if(type == 5 && attackerType == 1){
			return value * 2;
		}
		return value;
	}
}
