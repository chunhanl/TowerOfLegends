package card;

import javax.swing.ImageIcon;

import DirectPower.Power;
import enumeration.species;

public class SimpleCard {
	//data member
	public String name;    //**
	public ImageIcon headImage;//*
	public ImageIcon cardImage;//**
	
	public int attack;    //�����O
	public int health;    //�ͩR��
	public int recover;   //�^�_�O
	public int attackType;  //�����ݩ�(0 dark, 1 bright, 2 fire, 3 water, 4 wood, 5 heart)
	public int cd;  //�ޯ�N�o�ɶ�
	public String spec;   //�ر�
	public Power directPower;    //��O�A �D�ʧ�
	public Power effectPower;    //��O�A�Q�ʧޯ�
	
	//constructor
	public SimpleCard(Card card){
		this.name = card.getName();
		this.headImage = card.getHeadImage();
		this.cardImage = card.getCardImage();
		this.attack = card.getAttack();
		this.health = card.getHealth();
		this.recover = card.getRecover();
		int attackType = 0;
		//(0 dark, 1 bright, 2 fire, 3 water, 4 wood, 5 heart)
		if(card.getCharactistic().equals("D")){
			attackType = 0;
		}
		else if(card.getCharactistic().equals("L")){
			attackType = 1;
		}
		else if(card.getCharactistic().equals("W")){
			attackType = 3;
		}
		else if(card.getCharactistic().equals("F")){
			attackType = 2;
		}
		else{
			attackType = 4;
		}
		this.attackType = attackType;
		this.spec = card.getSpecies();
		this.directPower = card.getDirectPower();
		this.effectPower = card.getEffectPower();
	}
}
