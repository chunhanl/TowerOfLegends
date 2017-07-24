package stonePanel;

import resolverModel.AfterPowerResolver;
import resolverModel.PrePowerResolver;
import card.Card;
import card.SimpleCard;
import gameUtil.BattleTeam;
import LeaderPower.*;
//PlayerGameStatus is used to record player in-Level condition
public class PlayerGameStatus {
	//data member
	private int healthPoint;
	private int totalHealthPoint;
	private BattleTeam team;;  //record the team that user sent to this level
	private boolean invincible;
	private int invincibleRound;  //invincible set to -1 means infinite invincible
	private int[] eachCardCD;
	private PrePowerResolver prePower;
	private AfterPowerResolver afterPower;
	
	
	//constructor
	public PlayerGameStatus(BattleTeam sendTeam){
		this.team = sendTeam;
		eachCardCD = this.team.getEachCardCD();
		this.constructTotalHealthPoint(team);
		this.invincible = false;
		this.invincibleRound = 0;
		this.prePower = new PrePowerResolver();
		this.afterPower = new AfterPowerResolver();
		//****Test*****
		//Leader power
		this.prePower.PrePowerList.add(new EveryTypeAttackTime3());
		//Helper power
		this.prePower.PrePowerList.add(new EveryTypeAttackTime3());
	}
	
	//method
	public PrePowerResolver getPrePower(){
		return this.prePower;
	}
	
	public AfterPowerResolver getAfterPower(){
		return this.afterPower;
	}
	
	private void constructTotalHealthPoint(BattleTeam team){
		this.totalHealthPoint = 0;
		this.totalHealthPoint = team.getTotalHealthPoint();
		this.healthPoint = this.totalHealthPoint;
	}
	
	public boolean subBlood(int blood){
		if(blood<0){
			blood *=-1;
		}

		if((this.healthPoint - blood) > 0){
			this.healthPoint -= blood;
			return true;
		}
		else{
			this.healthPoint = 0;
			return false; //means "DIE"
		}
	}
	
	public boolean cureBlood(int blood){
		if(blood<0){
			blood *=-1;
		}
		
		if((this.healthPoint + blood) > this.totalHealthPoint){
			this.healthPoint = this.totalHealthPoint;
			return true;  //over cure
		}
		else{
			this.healthPoint += blood;
		}
		return false;
	}
	
	//getter and setter
	public int getHealthPoint(){
		return this.healthPoint;
	}
	
	public void setHealthPoint(int blood){
		this.healthPoint = blood;
	}
	
	public SimpleCard[] getCards(){
		return team.getCard();
	}
	
	public int getTotalHealthPoint(){
		return this.totalHealthPoint;
	}
	
	public void setTotalHealthPoint(int blood){
		this.totalHealthPoint = blood;
	}
	
	public void setInvincible(boolean bol){
		this.invincible = bol;
	}
	
	public void setInvincibleRound(int round){
		this.invincibleRound = round;
	}
	
	public int getInvincibleRound(){
		return this.invincibleRound;
	}
	
	public boolean subInvincibleRound(){
		if((this.invincibleRound - 1) > 0){
			this.invincibleRound -= 1;
			return true;
		}
		else{
			this.invincibleRound = 0;
		}
		return false;
	}
	
	public boolean isInvincible(){
		return this.invincible;
	}
	
}
