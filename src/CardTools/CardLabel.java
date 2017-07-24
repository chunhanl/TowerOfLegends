package CardTools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import card.Card;
import BackpackPanel.BackPackImageLoader;
import FixedPanel.FixedPanel;
import MidScrollPanel.MidScrollPanel;


public class CardLabel extends JLabel implements MouseListener,MouseMotionListener{
		
		private Card card;
		private JLabel slot; 
		private JLabel headImage;
		private MidScrollPanel  container; //for scolling control
		private FixedPanel fpanelREF;    //for showing full information
		private int 	 mouseiniY;
		private boolean mousehold;
		private SPanel selected; //for choose
		private boolean isCardExist;
		private boolean isChoosable;
		private int		chooseOrderNum;
		private Timer 	presstimer;  //for information 
	
		
	public CardLabel(MidScrollPanel m ,FixedPanel f,Card c){      
		card=c;
		if(c==null)isCardExist=false;
		else isCardExist=true;
		
		slot=new JLabel(CardToolsImageLoader.cardSlot);
		headImage=new JLabel();
		headImage.setSize(70,70);
		if(isCardExist)	headImage.setIcon(c.getHeadImage());
		container=m;
		fpanelREF=f;
		
		isChoosable=false;
		chooseOrderNum=-1;
		headImage.setLocation(5,-1);
		slot.setSize(80,70);
		this.setSize(80, 80);
		this.add(headImage);
		this.add(slot);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	

	public Card getCard(){
		return this.card;
	}

	public void mouseClicked(MouseEvent e) {
		if(isChoosable&&isCardExist)this.setSelected();  //Card要存在才可selected
		//System.out.println(card.toString());
	}


	public void mousePressed(MouseEvent e) {
		mouseiniY=e.getY();
		mousehold=true;
		presstimer=new Timer();
		presstimer.schedule(new TimerTask(){
			public void run() {
				if(mousehold&&card!=null)  //check full information
				new CardInformation(fpanelREF,card);   //need to change class card
			}
			
		},500);
	}


	public void mouseReleased(MouseEvent e) {
		mousehold=false;
		presstimer.cancel();
		if(container!=null)container.checkPanelY(); //midscrollpanel 矯正 Y軸 
	}



	public void mouseDragged(MouseEvent e) {
		mousehold=false;
		if(container!=null)container.movePanelY(e.getY()-mouseiniY);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void setSelected(){  //以選到的 要取消 位選到的 要選取
		Card[] team=fpanelREF.getPlayer().getTeam(0);   			 //暫時只有 隊伍 0 !! 以後要改多隊時要更改
		if(!card.isSelected()){  //點選未選到的
			for(int i=0;i<team.length;i++){
				if(team[i]==null){   //有位置加入
					card.setSelected(true);
					this.addBlackOut(i+1);
					team[i]=card;
					break;
				}
			}
			
		}
		else {
			for(int i=0;i<team.length;i++){
				if(team[i]==card){   //有位置加入
					card.setSelected(false);
					this.removeBlackOut();
					team[i]=null;
				}
			}
		}

		for(int i=0;i<team.length;i++){
			System.out.println(team[i]);
		}
		System.out.println("--------------");
	}
	
	public void setChoosable(boolean b){
		this.isChoosable=b;
	}
	
	
	
	
	
	// for selected !!
	public void addBlackOut(int num) {
		this.selected=new SPanel(this.getWidth(),this.getHeight(),num);
		this.add(selected,0);
		this.repaint();
	}
	
	public void removeBlackOut() {
		this.remove(selected);
		this.repaint();
	}
	
	
	private class SPanel extends JPanel{
		
		int dotX;
		int dotY;
		int width;
		int height;
		int pp=0;
		JPanel sign;
		int alpha;
		boolean increase;
		int num;
		Timer timer;
		RunAnimationTask runTask;
		public SPanel(int x,int y, int num){
			super();
			setBackground(new Color(0,0,0,100));
			setSize(80,70);
			setLocation(0,0);
			dotX=0;
			dotY=0;
			width=x-5;
			height=y-15;
			alpha=150;
			increase=true;
			this.num=num;
			runAnimation();
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			//sign.setLocation(dotX,dotY);
			drawChoosenBlank(g,new Color(255,255,255,alpha));
			
			
			CardLabel.this.fpanelREF.repaint();
		}
		
		private void drawChoosenBlank(Graphics g,Color c){
			Graphics2D gg=(Graphics2D) g;
			gg.setColor(c);
			gg.setStroke(new BasicStroke(4));
			gg.drawLine(0, 2, 78, 2);
			gg.drawLine(0,68,80, 68);
			
			gg.drawLine(78, 0, 78, 68);
			gg.drawLine(2, 68, 2,0);
			
			gg.setStroke(new BasicStroke(2));
			gg.drawLine(58, 45, 78,45);
			gg.drawLine(58, 45, 58,68);
			gg.setStroke(new BasicStroke(5));
			gg.drawString(String.valueOf(num), 65, 60);
		}
		
		private void runAnimation() {
			int dx=10;  // useless
			int period=5;
			
			timer=new Timer();
			runTask=new RunAnimationTask(dx);
			timer.schedule(runTask,0,period);		

		}	
		
		private class RunAnimationTask extends TimerTask{
			private int dx;
			public RunAnimationTask(int dx) {
				this.dx=dx;
			}
			
			public void run() {
				//System.out.println("run");
					if(increase){
						if(alpha!=255)alpha++;
						else increase=!increase;
					}
					else{
						if(alpha!=100)alpha--;
						else increase=!increase;   //閃爍
					}
				}
		}
		
		public void setVisible(boolean b){
			if(b)super.setVisible(true);
			else{
				this.runTask.cancel();
				this.timer.cancel();
				super.setVisible(false);
			}
		}

		
	}
}
