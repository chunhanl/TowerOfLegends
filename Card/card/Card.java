package card;


import javax.swing.ImageIcon;

import DirectPower.DirectPowerGetter;
import DirectPower.Power;
import enumeration.attackCharactistic;
import enumeration.species;

//���I��s �[�F  inBagId isSelected;
public class Card {
	//data member
	private int id; 		//*******
	private int nextid;		//*******
	
	private String name;    //**
	private ImageIcon headImage;//*
	private ImageIcon cardImage;//**
	
	private int attack;    //�����O
	private int health;    //�ͩR��
	private int recover;   //�^�_�O
	private int attackGrow=0;	//�����������u
	private int healthGrow=0;	//�ͩR�������u
	private int recoverGrow=0;	//�^�_�������u
	
	private String charactistic;//�����ݩ�
	private String species;   	//�ر�
	private int packSpace=0;  	//�����I�]�Ŷ�
	private int cardStar=0;    	//�d���P��
	
	
	private int attackType;  //�����ݩ�(0 dark, 1 bright, 2 fire, 3 water, 4 wood, 5 heart)
	private int cd;  //�ޯ�N�o�ɶ�
	private String spec;   //�ر�
	private Power directPower;    //��O�A �D�ʧ�
	private Power effectPower;    //��O�A�Q�ʧޯ�

	private upgradeComponent[] upcomponent;  //array for �d���ɯŪ�����
	
	private String skill;			//�D�ʧ�
	private String effectSkill;		//�Q�ʧޯ�
	private int skillLevelMaxNum=0; //�ޯ൥�ŤW��
	private int skillLevel=0;   	//��e���ޯ൥��
	private int skillRound=0;		//��e���w�g�L�԰����^�X
	private int skillNextRound=0;	//�ޯ�ɵ����ݭn�԰��^�X

	private int levelMaxNum;  //�̰����ŤW��(��F�W���N�i�H�ǳƤɯ�*******************************
	private int level;        //��e�d������
	private int nextExp=0;   	//��F�U�@�ŭn���g��
	private int nowExp=0; 		//��e���Y�֦����g��
	
	private int exp4nextLV;   //��F�U�@�ŭn���g��
	private int currentLVexp; //��e���Y�֦����g��
	private int wholeEXP;  //�`�g��


	private int inBagId;			  //�b�I�]�̪��s��
	private boolean isSelected=false; //�O�_�Q��J����
	//�������u

	//Ū�ɮת����|(�۹���| �� root)
	private String rootPath = "./res/";
	
	//constructor

	public Card(){
		//TODO
		//need to build up 1. data member 2.upgradeComponent

	}
	
	
	//Deep Copy  !ĵ�i  ����Data�|�������ƻs�L�h
	public Card( Card c){
		id = c.id; 		//*******
		nextid = c.nextid;		//*******
		
		name = c.name;    //**
		headImage =c.headImage;//*
		cardImage =c.cardImage;//**
		
		attack =c.attack;    //�����O
		health =c.health;    //�ͩR��
		recover=c.recover;   //�^�_�O
		attackGrow= c.attackGrow;	//�����������u
		healthGrow= c.healthGrow;	//�ͩR�������u
		recoverGrow=c.recoverGrow;	//�^�_�������u
		
		charactistic =c.charactistic;//�����ݩ�
		species = c.species;   	//�ر�
		packSpace= c.packSpace;  	//�����I�]�Ŷ�
		cardStar= c.cardStar;    	//�d���P��
		
		
		attackType =c.attackType;  //�����ݩ�(0 dark, 1 bright, 2 fire, 3 water, 4 wood, 5 heart)
		cd = c.cd;  //�ޯ�N�o�ɶ�
		spec =c.spec;   //�ر�
		
		//private Power directPower;    //��O�A �D�ʧ�
		//private Power effectPower;    //��O�A�Q�ʧޯ�

		//private upgradeComponent[] upcomponent;  //array for �d���ɯŪ�����
		
		skill = c.skill;			//�D�ʧ�
		effectSkill = c.effectSkill;		//�Q�ʧޯ�
		skillLevelMaxNum= c.skillLevelMaxNum; //�ޯ൥�ŤW��
		skillLevel= c.skillLevel;   	//��e���ޯ൥��
		skillRound= c.skillRound;		//��e���w�g�L�԰����^�X
		skillNextRound= c.skillNextRound;	//�ޯ�ɵ����ݭn�԰��^�X

		levelMaxNum = c.levelMaxNum;  //�̰����ŤW��(��F�W���N�i�H�ǳƤɯ�*******************************
		level = c.level;        //��e�d������
		nextExp= c.nextExp;   	//��F�U�@�ŭn���g��
		nowExp= c.nowExp; 		//��e���Y�֦����g��
		
		exp4nextLV = c.exp4nextLV;   //��F�U�@�ŭn���g��
		currentLVexp = c.currentLVexp; //��e���Y�֦����g��
		wholeEXP = c.wholeEXP;  //�`�g��


		inBagId = c.inBagId;			  //�b�I�]�̪��s��
		isSelected = c.isSelected; //�O�_�Q��J����
		//�������u

		//Ū�ɮת����|(�۹���| �� root)
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
	public void reCalculateMaxLV(){   //�]���P���ŦӦ����P��LV MAX
		//TODO
	}
	
	public void reCalculateEXP4nextLV(){  //���PLV�|�����P���g��ȻݨD
		//TODO
	}
	
	public void setCard(Card card){  //�]�w�d��
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