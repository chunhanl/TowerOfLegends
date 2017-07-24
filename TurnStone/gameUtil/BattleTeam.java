package gameUtil;

import DirectPower.*;
import card.Card;
import card.SimpleCard;

public class BattleTeam {
	//data member
	private SimpleCard[] card;
	private int totalHP;
	private int TeamMemberNum;
	//constructor
	public BattleTeam(Card[] card){
		this.TeamMemberNum = card.length;
		this.card = new SimpleCard[6];
		this.totalHP = 0; //initial hp
		for(int i=0; i<6; i++){
			if(card[i] != null){
				this.card[i] = new SimpleCard(card[i]);
				totalHP += card[i].getHealth();
			}
			else{
				this.card[i] = null;
			}
		}
	}
	
	//---Test
	/*
	public BattleTeam(String[] strs){
		this.card = new Card[6];
		this.TeamMemberNum = 6;
		this.totalHP = 0;
		for(int i=0; i<6; i++){
			card[i] = new SimpleCard(strs[i]);
			card[i].setHealth(2500);
			card[i].setDirectPower(new SuperLongTurningStoneTime());
			this.totalHP += card[i].getHealth();
		}
	}
	*/
	
	//getter and setter
	public int getTotalHealthPoint(){
		return this.totalHP;
	}
	
	public int getTeamMemberNum(){
		return this.TeamMemberNum;
	}
	
	public int[] getEachCardCD(){
		int[] ret = new int[card.length]; 
		
		for(int i=0; i<ret.length; i++){
			if(card[i] != null)
				ret[i] = card[i].cd;
		}
		
		return ret;
	}
	
	public SimpleCard[] getCard(){
		return this.card;
	}
	//method
}
