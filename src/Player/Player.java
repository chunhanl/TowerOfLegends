package Player;

import card.Card;
import card.Deck;


public class Player {
		private String	 account;				//�b��
		private String 	 name;					//�W��
		
		private int		 stone;					//�]�k��
		private int      money;					//����
		private int 	 level;					//����
		private int		 life;   				//��O��
		private int	     currentlife;			//�{����O
		
		private int 	 bagCapacity;			//�I�]�e�q
		private int      bagCardsAmount;		//�I�]�d���ƶq
		private Card[] 	 bagCards;  			//�I�]�d��

		private Card	 cardLeader;			//����
		private Card[][] playerteam;			//���a����
		public  Deck     cardFullList;			//�`�d���w
		
		
		public Player(String account){
			
		
			//Server �n������--------------------------------------
			this.cardFullList=new Deck();       //�o�̭n��"���a"�� Deck�d���w
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
			//Server �n������--------------------------------------
			
			
			this.playerteam=new Card[1][];
			this.setBagCards();
			this.selectTeam();

		
		}
		
		public void setBagCards(){
			 bagCards=new Card[bagCapacity];  				//�]�t null Cards!!
			 for(int i=0;i<getBagCardsAmount();i++){
				 bagCards[i]=cardFullList.selectCard(i);	//�o�����ӭnŪ�J�Ȼs�ƪ��d���C�� ,���O�S���u�n��Ū�JDeck
				 bagCards[i].setInBagId(i);					//�]�w�d���b�I�]�̪��s��
				 bagCards[i].setSelected(false);			//�]�w�d���O�_�Q��J����
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
