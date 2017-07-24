package resolverModel;

import gameUtil.AttackInfo;

import java.util.ArrayList;

import LeaderPower.LPower;

//this is use to resolve the ratio of the Power
public class PrePowerResolver {
	//data member
	public ArrayList<LPower> PrePowerList;
	
	//constructor
	public PrePowerResolver(){
		this.PrePowerList = new ArrayList<LPower>();
	}
	
	//method
	public int getResolvedPower(AttackInfo info){
		int value = 0;
		if(PrePowerList.size() == 0){
			return info.attack;
		}
		for(int i=0; i<PrePowerList.size(); i++){
			if(i == 0)
				value = PrePowerList.get(i).effect(info.type, info.attackerType,info.attack);
			else
				value = PrePowerList.get(i).effect(info.type, info.attackerType, value);
		}
		
		return value;
	}
	
}
