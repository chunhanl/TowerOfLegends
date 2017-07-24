package card;


import javax.swing.ImageIcon;

import DirectPower.DirectPowerGetter;
import DirectPower.Power;
import enumeration.attackCharactistic;
import enumeration.species;

//重點更新 加了  inBagId isSelected;
public class Card {
	//data member
	private int id; 		//*******
	private int nextid;		//*******
	
	private String name;    //**
	private ImageIcon headImage;//*
	private ImageIcon cardImage;//**
	
	private int attack;    //攻擊力
	private int health;    //生命值
	private int recover;   //回復力
	private int attackGrow=0;	//攻擊成長曲線
	private int healthGrow=0;	//生命成長曲線
	private int recoverGrow=0;	//回復成長曲線
	
	private String charactistic;//攻擊屬性
	private String species;   	//種族
	private int packSpace=0;  	//佔的背包空間
	private int cardStar=0;    	//卡片星興
	
	
	private int attackType;  //攻擊屬性(0 dark, 1 bright, 2 fire, 3 water, 4 wood, 5 heart)
	private int cd;  //技能冷卻時間
	private String spec;   //種族
	private Power directPower;    //能力， 主動技
	private Power effectPower;    //能力，被動技能

	private upgradeComponent[] upcomponent;  //array for 卡片升級的素材
	
	private String skill;			//主動技
	private String effectSkill;		//被動技能
	private int skillLevelMaxNum=0; //技能等級上限
	private int skillLevel=0;   	//當前的技能等級
	private int skillRound=0;		//當前的已經過戰鬥的回合
	private int skillNextRound=0;	//技能升等的需要戰鬥回合

	private int levelMaxNum;  //最高等級上限(到達上限就可以準備升級*******************************
	private int level;        //當前卡片等級
	private int nextExp=0;   	//到達下一級要的經驗
	private int nowExp=0; 		//當前此即擁有的經驗
	
	private int exp4nextLV;   //到達下一級要的經驗
	private int currentLVexp; //當前此即擁有的經驗
	private int wholeEXP;  //總經驗


	private int inBagId;			  //在背包裡的編號
	private boolean isSelected=false; //是否被選入隊伍
	//成長曲線

	//讀檔案的路徑(相對路徑 由 root)
	private String rootPath = "./res/";
	
	//constructor

	public Card(){
		//TODO
		//need to build up 1. data member 2.upgradeComponent

	}
	
	
	//Deep Copy  !警告  部分Data尚未完全複製過去
	public Card( Card c){
		id = c.id; 		//*******
		nextid = c.nextid;		//*******
		
		name = c.name;    //**
		headImage =c.headImage;//*
		cardImage =c.cardImage;//**
		
		attack =c.attack;    //攻擊力
		health =c.health;    //生命值
		recover=c.recover;   //回復力
		attackGrow= c.attackGrow;	//攻擊成長曲線
		healthGrow= c.healthGrow;	//生命成長曲線
		recoverGrow=c.recoverGrow;	//回復成長曲線
		
		charactistic =c.charactistic;//攻擊屬性
		species = c.species;   	//種族
		packSpace= c.packSpace;  	//佔的背包空間
		cardStar= c.cardStar;    	//卡片星興
		
		
		attackType =c.attackType;  //攻擊屬性(0 dark, 1 bright, 2 fire, 3 water, 4 wood, 5 heart)
		cd = c.cd;  //技能冷卻時間
		spec =c.spec;   //種族
		
		//private Power directPower;    //能力， 主動技
		//private Power effectPower;    //能力，被動技能

		//private upgradeComponent[] upcomponent;  //array for 卡片升級的素材
		
		skill = c.skill;			//主動技
		effectSkill = c.effectSkill;		//被動技能
		skillLevelMaxNum= c.skillLevelMaxNum; //技能等級上限
		skillLevel= c.skillLevel;   	//當前的技能等級
		skillRound= c.skillRound;		//當前的已經過戰鬥的回合
		skillNextRound= c.skillNextRound;	//技能升等的需要戰鬥回合

		levelMaxNum = c.levelMaxNum;  //最高等級上限(到達上限就可以準備升級*******************************
		level = c.level;        //當前卡片等級
		nextExp= c.nextExp;   	//到達下一級要的經驗
		nowExp= c.nowExp; 		//當前此即擁有的經驗
		
		exp4nextLV = c.exp4nextLV;   //到達下一級要的經驗
		currentLVexp = c.currentLVexp; //當前此即擁有的經驗
		wholeEXP = c.wholeEXP;  //總經驗


		inBagId = c.inBagId;			  //在背包裡的編號
		isSelected = c.isSelected; //是否被選入隊伍
		//成長曲線

		//讀檔案的路徑(相對路徑 由 root)
		rootPath = c.rootPath;
		
	}
	
	
	
	
	public Card(String str){
		this.headImage = new ImageIcon("res/server/heads/" + str);
	}

	public Card(int ID)	//constructor
	{
		this.id=ID;
		//need to build up 1. data member 2.upgradeComponent
	}
	//getter and setter
	
	public void setSkill(String skill){
		this.skill=skill;
		this.directPower = DirectPowerGetter.getDirectPower(this.skill);
	}
	
	public String getSkill(){
		return this.skill;
	}
	public void setSkillMaxLv(int skmax){
		this.skillLevelMaxNum=skmax;
	}
	
	public int getSkillMaxLv(){
		return this.skillLevelMaxNum;
	}
	
	public void setSkillLv(int sklv){
		this.skillLevel=sklv;
	}
	
	public int getSkillLv(){
		return this.skillLevel;
	}
	
	public void setSkillRound(int round){
		this.skillRound+=round;
	}
	
	public int getSkillRound(){
		return this.skillRound;
	}
	
	public void setSkillNextLvRound(int next){
		this.skillNextRound=(int)Math.pow(2,(double)next-1.0);
	}
	
	public int getSkillNextLvRound(){
		return this.skillNextRound;
	}
	
	public void setAttack(int att){
		this.attack = att;
	}
	
	public int getAttack(){
		return this.attack;
	}
	
	public void setHealth(int heal){
		this.health = heal;
	}
	
	public int getHealth(){
		return this.health;
	}
	
	public void setRecover(int re){
		this.recover = re;
	}
	
	public int getRecover(){
		return this.recover;
	}
	
	public void setCharactistic(String ac){
		this.charactistic = ac;
	}
	
	public String getCharactistic(){
		return this.charactistic;
	}
	
	public int getAttackType(){
		return this.attackType;
	}
	
	public void setPackSpace(int ps){
		this.packSpace = ps;
	}
	
	public int getPackSpace(){
		return this.packSpace;
	}
	
	public void setLevel(int lv){
		this.level = lv;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public void setCD(int i){
		this.cd = i;
	}
	
	public int getCD(){
		return this.cd;
	}
	
	public void setEffectSkill(String pass){
		this.effectSkill=pass;
	}
	
	public String getEffectSkill(){
		return this.effectSkill;
	}
	public void setDirectPower(Power pow){
		this.directPower = pow;
	}
	
	public Power getDirectPower(){
		return this.directPower;
	}
	
	public void setEffectPower(Power pow){
		this.effectPower = pow;
	}
	
	public Power getEffectPower(){
		return this.effectPower;
	}
	
	public void setCardStar(int star){
		this.cardStar = star;
	}
	
	public int getCardStar(){
		return this.cardStar;
	}
	
	public void setSkillLevel(int sk){
		this.skillLevel = sk;
	}
	
	public int getSkillLevel(){
		return this.skillLevel;
	}
	public void setNextExp(int nowlv){
		nextExp=nowlv*1000;
	}
	
	public int getNextExp(){
		return this.nextExp;
	}
	
	public void setNowExp(int nowExp){
		this.nowExp+=nowExp;
	}

	public void setAttPlus(int att){
		this.attackGrow = att;
	};
	
	public int getAttPlus(){
		return this.attackGrow;
	}
	
	public void setHealPlus(int heal){
		this.healthGrow = heal;
	}
	
	public int getHealPlus(){
		return this.healthGrow;
	}
	
	public void setRecoverPlus(int r){
		this.recoverGrow = r;
	}
	
	public int getRecoverPlus(){
		return this.recoverGrow;
	}
	
	public void setId(int i){
		this.id = i;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setNextId(int i){
		this.nextid = i;
	}
	
	public int getNextId(){
		return this.nextid;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	

	public void setHeadImage(String URL){
		this.headImage = new ImageIcon(URL);
	}
	
	public ImageIcon getHeadImage(){
		return this.headImage;
	}
	
	public void setCardImage(String URL){
		this.cardImage = new ImageIcon(URL);
	}
	
	public ImageIcon getCardImage(){
		return this.cardImage;
	}
	
	//method
	public void reCalculateMaxLV(){   //因不同等級而有不同的LV MAX
		//TODO
	}
	
	public void reCalculateEXP4nextLV(){  //不同LV會有不同的經驗值需求
		//TODO
	}
	
	public void setCard(Card card){  //設定卡片
		//TODO
	}
	public void setSpecies(String spec){
		this.species = spec;
	}
	
	public String getSpecies(){
		return this.species;
	}
	public void setMaxLevel(int maxlv){
		this.levelMaxNum=maxlv;
	}
	
	public int getMaxLevel(){
		return this.levelMaxNum;
	}
	
	public int getInBagId() {
		return inBagId;
	}

	public void setInBagId(int inBagId) {
		this.inBagId = inBagId;
	}
	
	public void setSelected(boolean a){
		isSelected=a;
	}
	public boolean isSelected(){
		return isSelected;
	}
	public String toString(){
		return this.name;
	}


}

final class upgradeComponent{
	//data member
	private Card[] upgradeCards;
	public static final int cardMaxNum = 5;
	//constructor
	public upgradeComponent(){
		
	}
	
	//method
	public void init(){
		upgradeCards = new Card[5];
	}
	
	public void setComponent(int index, Card card){
		this.upgradeCards[index].setCard(card); 
	}
	
	public Card getComponent(int index){
		return this.upgradeCards[index];
	}
	

}