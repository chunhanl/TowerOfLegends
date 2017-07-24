package LeaderPower;

public class EveryTypeAttackTime3 extends LPower{
	//constructor
	public EveryTypeAttackTime3(){
		this.describe = "全體攻擊力三倍";
	}
	//method
	public int effect(int type, int attackerType, int value) {
		if(type != 5){
			return value *3;
		}
		return value;
	}
}
