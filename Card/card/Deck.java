package card;


import java.io.*;
import java.util.*;
import java.lang.*;

public class Deck
{
	public static int numberOfCard;
	private Card card[];
	private Scanner input;
	private String URL;    //2014 /5 /26 update for individual
	
	public Deck(String s)
	{
		this.URL=s;
		readFromFile();
	}
	public void readFromFile()	//建立卡片
	{
		try
		{
			input=new Scanner(new File("res/server/"+URL));//read txt file
			numberOfCard=input.nextInt();
			card=new Card[numberOfCard];	//new Card 
			
			for(int i=0;i<card.length;i++)	//create data for data
			{
				card[i]=new Card(i);
				
				card[i].setName(input.next());
				card[i].setCardStar(input.nextInt());
				
				card[i].setHeadImage("res/server/heads/"+input.next());
				card[i].setCardImage("res/server/cards/"+input.next());
				
								
				card[i].setAttack(input.nextInt());
				card[i].setAttPlus(input.nextInt());
				card[i].setHealth(input.nextInt());
				card[i].setHealPlus(input.nextInt());
				card[i].setRecover(input.nextInt());
				card[i].setRecoverPlus(input.nextInt());
				
				card[i].setCharactistic(input.next());
				card[i].setSpecies(input.next());
				card[i].setPackSpace(input.nextInt());
				
				card[i].setMaxLevel(input.nextInt());
				card[i].setLevel(0);
				card[i].setNextExp(card[i].getLevel());
				card[i].setNowExp(0);
				
				card[i].setSkill(input.next());
				card[i].setSkillMaxLv(input.nextInt());
				card[i].setSkillLv(0);
				card[i].setSkillRound(0);
				card[i].setSkillNextLvRound(card[i].getSkillLv());
				card[i].setCD(input.nextInt());
				card[i].setEffectSkill(input.next());	
			}
		}
		catch(NoSuchElementException no)
		{
			System.err.println("File improper");
			input.close();
			System.exit(1);
		}
		catch(IllegalStateException st)
		{
			System.err.println("error reading");
			System.exit(1);
		}
		catch(FileNotFoundException not)
		{
			System.err.println("File not found");
			System.exit(1);
		}
		finally
		{
			if(input!=null)
				input.close();
		}
	}
	
	public Card selectCard(int index)	//選擇卡片
	{
		return this.card[index];
	}


}