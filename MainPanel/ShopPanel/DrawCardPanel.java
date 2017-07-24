package ShopPanel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

import card.Card;
import AnimatedPanel.ZoomablePanel;
import CardTools.CardInformation;
import FixedPanel.FixedPanel;

public class DrawCardPanel extends JPanel implements Runnable{

	JLabel backGround;
	JLabel backGroundcover;
	JPanel targetcard;
	JLabel cardback;
	JLabel shadow;
	
	Card   target;
	CardDraw targetlistener;

	Timer  timer;
	TimerTask timertask;
	int    time;
	
	JPanel white;
	int whitealpha=0;
	
	CardInformation card;
	
	FixedPanel fixedpanel;
	public DrawCardPanel(FixedPanel f,Card target) {
		super();
		this.setSize(500, 750);
		this.setLocation(0, 0);
		this.setVisible(true);
		this.setLayout(null);
		this.setBackground(Color.RED);

		fixedpanel=f;

		
		buildBackGround();
		placeTargetCard();
		this.add(backGroundcover);
		this.add(targetcard);
		this.add(backGround);
		
		
		f.add(this,0);
		f.repaint();
		
		setTimer();  //for animations
	}

	private void setTimer() {
		this.timer=new Timer();
		timer.schedule(new TimerTask(){
			public void run() {
				DrawCardPanel.this.time++;
			}
		}
		,0,1000);
	}

	private void buildBackGround() {
		backGround=new JLabel();
		backGround.setIcon(DrawCardImageLoader.backGround);
		backGround.setSize(500, 750);
		backGround.setLocation(0,0);
		backGroundcover=new JLabel();
		backGroundcover.setIcon(DrawCardImageLoader.upcover);
		backGroundcover.setSize(500, 451);
		backGroundcover.setLocation(0, 0);


	}
	private void placeTargetCard() {
		int rand=79;
		boolean flag=true;
		
		while(flag){
			System.out.println(fixedpanel.getPlayer().getCard(rand+1).getName());
			flag=false;
			for(int i=0;i<fixedpanel.getPlayer().getCardAmount();i++){
				if(fixedpanel.getPlayer().getCard(rand+1).getName()==fixedpanel.getPlayer().getBagCards()[i].getName())
				{
					flag=true;
					break;
				}
				System.out.println((fixedpanel.getPlayer().getBagCards()[i].getName()));
			}
			rand=(rand+1)%166;
		}
		

		
		fixedpanel.getPlayer().addStone(-5);
		fixedpanel.getClientPanel().send("@"+fixedpanel.getClientPanel().getPlayer().getName()+"%Addcard#"+rand);

		target=fixedpanel.getPlayer().getCard(rand);
		
		targetcard=new JPanel();
		targetcard.setSize(240,360);
		targetcard.setLocation(130,-360);
		targetcard.setLayout(null);
		cardback=new JLabel();
		
		if(target.getCardStar()==4)cardback.setIcon(DrawCardImageLoader.goldCard);
		else if(target.getCardStar()==5)cardback.setIcon(DrawCardImageLoader.silverCard);
		else if(target.getCardStar()==6)cardback.setIcon(DrawCardImageLoader.platCard);
		
		cardback.setSize(240,360);
		cardback.setLocation(0,0);
		shadow=new JLabel();
		shadow.setIcon(DrawCardImageLoader.normal);
		shadow.setSize(240,360);
		shadow.setLocation(0,0);
		targetcard.add(shadow);
		targetcard.add(cardback);

	}

	private void sendCard() {
		Timer t=new Timer();
		int tt=this.time;
		while(this.time<tt+1){System.out.print("");}
		t.schedule(new TimerTask(){
			boolean lock=false;
			public void run() {
				if(targetcard.getY()<-170&&!lock){
					targetcard.setLocation(130,targetcard.getY()+10);
				}
				else{
					lock=true;
					if(targetcard.getY()>-180)
					targetcard.setLocation(130,targetcard.getY()-2);
					else{
						this.cancel();
						DrawCardPanel.this.pullCard();
					}		
				}
				DrawCardPanel.this.repaint();	
			}
		}
		,0,15);
	}

	private void pullCard() {
		this.targetlistener=new CardDraw();
		targetcard.addMouseListener(targetlistener);
		targetcard.addMouseMotionListener(targetlistener);
	}

	@Override
	public void run() {
		this.sendCard();
	}

	private class CardDraw extends MouseAdapter{
		int y;
		int by;
		@Override
		public void mousePressed(MouseEvent e){
			y=e.getY();
		}
		public void mouseDragged(MouseEvent e){
			by=e.getY();
			if(targetcard.getY()+by-y>=-180){
				targetcard.setLocation(130, targetcard.getY()+by-y);
			}
		}
		public void mouseReleased(MouseEvent e){
			if(targetcard.getY()<=295)targetcard.setLocation(130,-180);
			else{
				DrawCardPanel.this.cardDrawn();
			}
		}
	}

	private void cardDrawn() {
		targetcard.removeMouseListener(targetlistener);
		targetcard.removeMouseMotionListener(targetlistener);
		Timer t=new Timer();
		t.schedule(new TimerTask(){
			public void run() {
				if(targetcard.getY()<750){
					targetcard.setLocation(130,targetcard.getY()+10);
					DrawCardPanel.this.repaint();
					//wait for next second
				}
				else{
					this.cancel();
					DrawCardPanel.this.cardRise();
				}
					DrawCardPanel.this.repaint();
			}
		}
		,0,5);
	}

	private void cardRise() {
		this.remove(targetcard);
		targetcard.removeAll();
		targetcard.setSize(300,450);
		targetcard.setLocation(100,750);
		targetcard.setLayout(null);

		
		if(target.getCardStar()==4)cardback.setIcon(DrawCardImageLoader.biggoldCard);
		else if(target.getCardStar()==5)cardback.setIcon(DrawCardImageLoader.bigsilverCard);
		else if(target.getCardStar()==6)cardback.setIcon(DrawCardImageLoader.bigplatCard);
		cardback.setSize(300,450);
		cardback.setLocation(0,0);
		shadow.setIcon(DrawCardImageLoader.normal);
		shadow.setSize(300,450);
		shadow.setLocation(0,0);
		//targetcard.add(shadow);
		targetcard.add(cardback);
		this.add(targetcard,0);
		
		Timer t=new Timer();
		t.schedule(new TimerTask(){
			public void run() {
				if(targetcard.getY()>120){
					targetcard.setLocation(100,targetcard.getY()-10);
					DrawCardPanel.this.repaint();
					//wait for next second
				}
				else{
					this.cancel();
					WhiteOut();
				}
					DrawCardPanel.this.repaint();
			}
		}
		,0,3);
		
		
	}

	private void WhiteOut() {
		white=new JPanel();
		white.setBackground(new Color(255,255,255,whitealpha));
		white.setSize(500, 750);
		white.setLocation(0, 0);
		this.remove(targetcard);
		this.add(white,0);
		this.add(targetcard,1);
		
		Timer t=new Timer();
		t.schedule(new TimerTask(){
			public void run() {
				if(DrawCardPanel.this.whitealpha<255){
					DrawCardPanel.this.whitealpha++;
					white.setBackground(new Color(255,255,255,whitealpha));
					//wait for next second
				}
				else{
					this.cancel();
					ShowCard();
				}
					DrawCardPanel.this.repaint();
			}

		}
		,0,2);
		
		
	}
	

	private void ShowCard() {
		card=new CardInformation(this.fixedpanel,target);
		this.remove(white);
		
		fixedpanel.add(card,0);
		fixedpanel.remove(this);
		fixedpanel.shoppanelREF.isDrawing=false;
	
	}
}
