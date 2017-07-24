package gameUtil;

public class AttackInfo {
	//data member
	public int attack;
	public int type;
	public boolean toAll;
	public int attackerType;
	
	//constructor
	public AttackInfo(int attack, int type, int attackerType, boolean toAll){
		this.attack = attack;
		this.type = type;
		this.attackerType = attackerType;
		this.toAll = toAll;
	}
}
