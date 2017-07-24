package Player;

import card.Card;
import card.Deck;


public class Player {
		private String	 account;				//帳號
		private String 	 name;					//名稱
		
		private int		 stone;					//魔法石
		private int      money;					//金錢
		private int 	 level;					//等級
		private int		 life;   				//體力值
		private int	     currentlife;			//現有體力
		
		private int 	 bagCapacity;			//背包容量
		private int      bagCardsAmount;		//背包卡片數量
		private Card[] 	 bagCards;  			//背包卡片

		private Card	 cardLeader;			//隊長
		private Card[][] playerteam;			//玩家隊伍
		public  Deck     cardFullList;			//總卡片庫
		
		
		public Player(String account){
			
		
			//Server 要給的值--------------------------------------
			this.cardFullList=new Deck();       //這裡要給"玩家"的 Deck卡片庫
			this.account=account;
			this.name="WakaMama";
			this.cardLeader=cardFullList.selectCard(1);
			this.bagCapacity=50;
			this.bagCardsAmount=Deck.numberOfCard;
			this.stone=5;
			this.money=1000;
			this.level=5;
			this.life=80+(level/2)*5;
			this.currentlife=10;
			//Server 要給的值--------------------------------------
			
			
			this.playerteam=new Card[1][];
			this.setBagCards();
			this.selectTeam();

		
		}
		
		public void setBagCards(){
			 bagCards=new Card[bagCapacity];  				//包含 null Cards!!
			 for(int i=0;i<getBagCardsAmount();i++){
				 bagCards[i]=cardFullList.selectCard(i);	//這裡應該要讀入客製化的卡片列表 ,但是沒有只好先讀入Deck
				 bagCards[i].setInBagId(i);					//設定卡片在背包裡的編號
				 bagCards[i].setSelected(false);			//設定卡片是否被選入隊伍
			 }
			 
			 
		}

		private void selectTeam(){
			this.playerteam[0]=new Card[6];
			this.playerteam[0][0]=bagCards[0];
			this.playerteam[0][1]=bagCards[1];
			this.playerteam[0][2]=bagCards[2];
			this.playerteam[0][3]=bagCards[3];
			this.playerteam[0][4]=bagCards[4];
			this.playerteam[0][5]=bagCards[5];
			for(int i=0;i<6;i++){
				bagCards[i].setSelected(true);
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

		public void setMoney(int i) {
			money=i;
		}

		public int getMoney() {
			return money;
		}

		public int getStone() {
			// TODO Auto-generated method stub
			return stone;
		}

		public String getName() {
			return name;
		}

		public int getBagCardsAmount() {
			return bagCardsAmount;
		}

		public int getLifeMax() {
			return this.life;
		}

		public int getCurrentLife() {
			return this.currentlife;
		}

		public void setCurrentLife(int l) {
			this.currentlife=l;			
		}
		


}
