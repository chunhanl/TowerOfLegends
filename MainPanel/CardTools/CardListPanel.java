package CardTools;

import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import card.Card;
import FixedPanel.FixedPanel;
import FixedPanel.UpScrollPanelImageLoader;
import MidScrollPanel.MidScrollPanel;
import MyClient.Player;

public class CardListPanel extends MidScrollPanel{
	
	protected FixedPanel fpanelREF;
	private Player player;
	protected Card[] playerCardList;
	protected CardLabel[] cardLabels;
	private int playerBagCapacity;
	private JLabel sort;
	private JLabel sorttext;
	private SortPanel sortpanel;

	public CardListPanel(JFrame frame, FixedPanel fpanel) {
		super(frame, fpanel);
		fpanelREF=fpanel;
		player=fpanelREF.getPlayer();
		playerBagCapacity =player.getBagCapacity();
		playerCardList  =player.getBagCards();
		cardLabels=new CardLabel[playerBagCapacity];
		
		int BagRow =playerBagCapacity/5;
		if(playerBagCapacity%5!=0){BagRow++;}
		
		this.buildPanelRows( BagRow);
				
		for(int i=0;i< playerBagCapacity;i++)
		{
			cardLabels[i]=new CardLabel(this,this.fixedpanelREF,playerCardList[i]);  //for rolling on heads
			cardLabels[i].setLocation(80*(i%5),0);
			this.panelRows[i/5].add(cardLabels[i]);
			
		}
		this.adjustSizeY(80*this.panelRows.length);
		this.buildsortbutton();
	}
	


	public void setAllChoosable(){
		for(int i=0;i<player.getCardAmount();i++){
			cardLabels[i].setChoosable(true);
		}
	}
	
	//sort
	
	private void buildsortbutton() {
		sort=new JLabel();
		sort.setSize(100, 35);
		sort.setLocation(400, 45);
		sort.setIcon(CardToolsImageLoader.sortButton);
		sortpanel=new SortPanel();
		sort.addMouseListener(sortpanel);
		sorttext=new JLabel("≡排序",SwingConstants.RIGHT);
		sorttext.setSize(80, 35);
		sorttext.setLocation(0,0);
		sorttext.setForeground(Color.white);
		sorttext.setFont(new Font("粗黑體", Font.BOLD, 18));
		sort.add(sorttext,0);
		this.add(sort);
		
		sort.setVisible(true);
	}
	
	public void sortByAttack(){
		//quick sort
		for(int i=0;i<cardLabels.length;i++)
			for(int j=i;j<cardLabels.length;j++)
				if(cardLabels[i].getCard()!=null&&cardLabels[j].getCard()!=null)
					if(cardLabels[i].getCard().getAttack()<cardLabels[j].getCard().getAttack()){
						CardLabel temp=cardLabels[i];
						cardLabels[i]=cardLabels[j];
						cardLabels[j]=temp;
					}
		refillPanelRows();
	}
	
	public void sortBySpecies(){
		//quick sort
		for(int i=0;i<cardLabels.length;i++)
			for(int j=i;j<cardLabels.length;j++)
				if(cardLabels[i].getCard()!=null&&cardLabels[j].getCard()!=null)
					if(cardLabels[i].getCard().getSpecies().codePointAt(0)
								<cardLabels[j].getCard().getSpecies().codePointAt(0)){
						CardLabel temp=cardLabels[i];
						cardLabels[i]=cardLabels[j];
						cardLabels[j]=temp;
					}
		refillPanelRows();
	}
	

	public void sortByHealth() {
		//quick sort
		for(int i=0;i<cardLabels.length;i++)
			for(int j=i;j<cardLabels.length;j++)
				if(cardLabels[i].getCard()!=null&&cardLabels[j].getCard()!=null)
					if(cardLabels[i].getCard().getHealth()
								<cardLabels[j].getCard().getHealth()){
						CardLabel temp=cardLabels[i];
						cardLabels[i]=cardLabels[j];
						cardLabels[j]=temp;
					}
		refillPanelRows();
		
	}
	private void refillPanelRows(){
		for(int i=0;i<playerBagCapacity/5;i++)
			panelRows[i].removeAll();

		for(int i=0;i< playerBagCapacity;i++)
		{
			cardLabels[i].setLocation(80*(i%5),0);
			this.panelRows[i/5].add(cardLabels[i]);
		}
	}

	public void refresh(){
		for(int i=0;i< playerBagCapacity;i++)
		{
			cardLabels[i].setCard(playerCardList[i]);  //for 抽卡
		}
	}
	
	public void sortRefresh(){
		for(int i=0;i< playerBagCapacity;i++)
		{
			if(cardLabels[i].getCard()==null)cardLabels[i].setCard(playerCardList[i]);  //for 抽卡
		}
	}
	
	@Override
	public void setVisible(boolean a){
		if(a){
			fpanelREF.upBarREF.add(sort,0);
			super.setVisible(true);
		}
		else{
			fpanelREF.upBarREF.remove(sort);
			if(fpanelREF.getComponent(4)==sortpanel)fpanelREF.remove(sortpanel);
			super.setVisible(false);
		}
	}
	
	
	
	
	
	private class SortPanel extends JPanel implements MouseListener{

		private JLabel[] sortlist;
		private JLabel[] sortlisttext;
		
		public SortPanel(){
			super();
			this.setSize(500, 700);
			this.setLocation(0,120);
			this.setBackground(new Color(0,0,0,100));
			this.setLayout(null);
			buildsortlist();
			this.addMouseListener(this);
			this.setVisible(false);
		}
		
	


		private void buildsortlist() {
			sortlist=new JLabel[3];
			sortlisttext=new JLabel[3];
			
			sortlist[0]=new JLabel();
			sortlist[0].setSize(130, 35);
			sortlist[0].setLocation(370,40);
			sortlist[0].setIcon(CardToolsImageLoader.sortList);
			sortlist[0].addMouseListener(this);
			sortlisttext[0]=new JLabel("攻擊力",SwingConstants.RIGHT);
			sortlisttext[0].setSize(100, 35);
			sortlisttext[0].setLocation(10,0);
			sortlisttext[0].setForeground(Color.white);
			sortlisttext[0].setFont(new Font("粗黑體", Font.BOLD, 18));
			sortlist[0].add(sortlisttext[0],0);
			this.add(sortlist[0]);
			
			sortlist[1]=new JLabel();
			sortlist[1].setSize(130, 35);
			sortlist[1].setLocation(370,80);
			sortlist[1].setIcon(CardToolsImageLoader.sortList);
			sortlist[1].addMouseListener(this);
			sortlisttext[1]=new JLabel("生命力",SwingConstants.RIGHT);
			sortlisttext[1].setSize(100, 35);
			sortlisttext[1].setLocation(10,0);
			sortlisttext[1].setForeground(Color.white);
			sortlisttext[1].setFont(new Font("粗黑體", Font.BOLD, 18));
			sortlist[1].add(sortlisttext[1],0);
			this.add(sortlist[1]);
			
			
			sortlist[2]=new JLabel();
			sortlist[2].setSize(130, 35);
			sortlist[2].setLocation(370,120);
			sortlist[2].setIcon(CardToolsImageLoader.sortList);
			sortlist[2].addMouseListener(this);
			sortlisttext[2]=new JLabel("種族",SwingConstants.RIGHT);
			sortlisttext[2].setSize(100, 35);
			sortlisttext[2].setLocation(10,0);
			sortlisttext[2].setForeground(Color.white);
			sortlisttext[2].setFont(new Font("粗黑體", Font.BOLD, 18));
			sortlist[2].add(sortlisttext[2],0);
			this.add(sortlist[2]);
		}

	

		public void mousePressed(MouseEvent e) {
			CardListPanel.this.sortRefresh();
			if(e.getSource()==sort){
				if(this.isVisible()){ 
					this.setVisible(false); 
					fpanelREF.remove(this);
				}
				else {
					fpanelREF.add(this,4);
					this.setVisible(true);
				}
				sort.setIcon(CardToolsImageLoader.sortButtonP);
			}
			else if(e.getSource()==sortlist[0]){
				CardListPanel.this.sortByAttack();
				this.setVisible(false); 
				fpanelREF.remove(this);
			}
			else if(e.getSource()==sortlist[1]){
				CardListPanel.this.sortByHealth();
				this.setVisible(false); 
				fpanelREF.remove(this);
			}
			else if(e.getSource()==sortlist[2]){
				CardListPanel.this.sortBySpecies();
				this.setVisible(false); 
				fpanelREF.remove(this);
			}
		}
		
		public void mouseReleased(MouseEvent e) {
			sort.setIcon(CardToolsImageLoader.sortButton);
		}

	
		
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
	}






}
