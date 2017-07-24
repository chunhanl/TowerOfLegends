package MyClient;

import card.Card;
import card.Deck;

import java.util.*;

import javax.swing.*;

import java.util.StringTokenizer;


public class Player
{
		//private String	 account;
		private String 	 name;
		
		private int		 stone;
		private int      money;
		private int 	 level;
		private int		 hp=0;
		private int		 currenthp=0;
		private int 	 life; // 5/21
		private int 	 currentlife;  //5/21
		
		private int		 nowBagCardPos; //目前背包卡片最後一張的陣列位置 
		private int 	 bagCapacity;
		private Card[]	 bagCards;  	// change to class card
		private Card	 cardLeader;
		
		private Card[][] playerteam;
		
		public  Deck     cardFullList;
		public 	Deck	 teamFullList;
		
		private boolean Online = false;
		
		public Player(){
			
			//this.account=account;
			/*this.name="123";
			this.bagCapacity=50;
			this.stone=10;
			this.money=1000;
			this.life=90;
			this.currentlife=50;
			
			
			this.cardFullList=new Deck("CardData.txt");
			this.cardLeader=cardFullList.selectCard(7);
			this.playerteam=new Card[1][];
			this.playerteam[0]=new Card[6];
			this.playerteam[0][0]=cardFullList.selectCard(1);
			this.playerteam[0][1]=cardFullList.selectCard(2);
			this.playerteam[0][2]=cardFullList.selectCard(3);
			this.playerteam[0][3]=cardFullList.selectCard(4);
			this.playerteam[0][4]=cardFullList.selectCard(5);
			this.playerteam[0][5]=cardFullList.selectCard(6);
			
			this.setBagCards();*/
		}
		
		public Player(String Data){			//read file
			this.Online=true;
			
			String[] data=Data.split("#");
			System.out.println("data.length:"+data.length);
			System.out.println(data[0]);
			this.name=data[0];
			this.bagCapacity=(Integer.parseInt(data[1]));
			this.stone=Integer.parseInt(data[2]);
			this.money=Integer.parseInt(data[3]);
			this.life=Integer.parseInt(data[4]);
			this.currentlife=Integer.parseInt(data[5]);
	
			this.cardFullList=new Deck("CardData.txt");
			this.teamFullList=new Deck("CardData.txt");
			//this.setBagCards();		//隨機assign
			
			this.cardLeader=cardFullList.selectCard(Integer.parseInt(data[6]));
			
			this.bagCards=new Card[300];
			this.bagCards[0]=cardFullList.selectCard(Integer.parseInt(data[7]));
			this.bagCards[1]=cardFullList.selectCard(Integer.parseInt(data[9]));
			this.bagCards[2]=cardFullList.selectCard(Integer.parseInt(data[11]));
			this.bagCards[3]=cardFullList.selectCard(Integer.parseInt(data[13]));
			this.bagCards[4]=cardFullList.selectCard(Integer.parseInt(data[15]));
			this.bagCards[5]=cardFullList.selectCard(Integer.parseInt(data[17]));
			for(int i=6;i<data.length-19;i++)
				this.bagCards[i]=cardFullList.selectCard(Integer.parseInt(data[i+19]));
			this.nowBagCardPos=data.length-20; 
			
			this.playerteam=new Card[1][];
			this.playerteam[0]=new Card[6];
			this.playerteam[0][0]=teamFullList.selectCard(Integer.parseInt(data[7]));
			this.playerteam[0][1]=teamFullList.selectCard(Integer.parseInt(data[9]));
			this.playerteam[0][2]=teamFullList.selectCard(Integer.parseInt(data[11]));
			this.playerteam[0][3]=teamFullList.selectCard(Integer.parseInt(data[13]));
			this.playerteam[0][4]=teamFullList.selectCard(Integer.parseInt(data[15]));
			this.playerteam[0][5]=teamFullList.selectCard(Integer.parseInt(data[17]));	
			this.playerteam[0][0].setLevel(Integer.parseInt(data[8]));
			this.playerteam[0][1].setLevel(Integer.parseInt(data[10]));
			this.playerteam[0][2].setLevel(Integer.parseInt(data[12]));
			this.playerteam[0][3].setLevel(Integer.parseInt(data[14]));
			this.playerteam[0][4].setLevel(Integer.parseInt(data[16]));
			this.playerteam[0][5].setLevel(Integer.parseInt(data[18]));
			this.bagCards[0].setSelected(true);
			this.bagCards[1].setSelected(true);
			this.bagCards[2].setSelected(true);
			this.bagCards[3].setSelected(true);
			this.bagCards[4].setSelected(true);
			this.bagCards[5].setSelected(true);
			
			this.setTotalHp();
		}
		
		public void setBagCards(){
			 for(int i=0;i<bagCapacity;i++){
				 bagCards[i]=cardFullList.selectCard(i+1);
			 }
		}
		
		public Card[] getTeam(int i){
			return playerteam[i];
		}
		
		public Card[] getBagCards(){
			return bagCards;
		}
		
		public int getBagCapacity(){
			return bagCapacity;
		}
		
		public void setCardLeader(int ID){
			cardLeader=cardFullList.selectCard(ID);
		}
		
		public Card getCardLeader(){
			return cardLeader;
		}

		public void setStone(int i) {
			stone=i;
		}

		public void initialHp()
		{
			currenthp = this.hp;
		}
		
		public void resetHp()
		{
			currenthp = 0;
		}
		
		public void setMoney(int i) {
			money=i;
		}

		public void setTotalHp(){
			for(int i=0; i<6; i++)
				this.hp += playerteam[0][i].getHealth();
			
			this.currenthp = this.hp;
			System.out.println(this.hp+'\n');
		}
		
		public Card getCard(int index){
			return cardFullList.selectCard(index);
		}
		
		public int getTotalHp(){
			return hp;
		}
		
		public void setHp(int i){
			if(i != 0){
				System.out.println("hp change");
			}
			
			currenthp += i;
			if(currenthp >= hp)
				currenthp = hp;
			else if(currenthp <=0)
				currenthp = 0;
		}
		
		public int getHp(){
			return this.currenthp;
		}
		
		public void setOnline(boolean online){
			Online=online;
		}
		
		public boolean getOnline(){
			return Online;
		}
		
		public int getMoney() {
			return money;
		}

		public int getStone() {
			return stone;
		}

		public String getName() {
			return name;
		}
		
		public int getCurrentLife() {
			return currentlife;
		}

		public int getLifeMax() {
			return life;
		}

		public void setCurrentLife(int i) {
			this.currentlife=i;
			
		}
		
		public boolean addNewCard(int index){
			if(nowBagCardPos >= bagCapacity)
				return false;
			else
			{
				bagCards[this.nowBagCardPos+1]=cardFullList.selectCard(index);
				this.nowBagCardPos++;
			}
			return true;
		}
		
		public int getCardAmount(){
			return nowBagCardPos+1;
		}
		
		public ImageIcon getHeadImage(){
			return cardLeader.getHeadImage();
		}
		
		
		public void addStone(int index){
			this.stone += index ;
		}
		
		public void addLife(int index){
			this.life += index ;
		}
		
		public void addCurrentlife(int index){
			this.currentlife += index ;
			if(currentlife >= life)
				currentlife = life;
			if(currentlife <= 0)
				currentlife = 0;
		}
		
		public void addBagCapacity(int index){
			this.bagCapacity += index ;
		}
		
		public void addMoney(int index){
			this.money += index;
		}
		
		public String getSaveData(){
			String totalData = "@Leave%" + name + "#" + 
								name + "#" + bagCapacity + "#" + stone + "#" + 
								money + "#" + life + "#" + currentlife + "#";
			
			String teamData = cardLeader.getId() + "";
			System.out.println(cardLeader.getId());
			for(int i=0; i< 6; i++)
				teamData += ("#" + playerteam[0][i].getId() + "#" + playerteam[0][i].getLevel() );
			for(int i = 0; i < getCardAmount(); i++)
				teamData += ("#" + bagCards[i].getId() );
			
			totalData += teamData;
			
			return totalData;
		}
		
}
