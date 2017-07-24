package stonePanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Timer;

import javax.swing.JLabel;

import Animation.ScalingTask;
import InterFace.Battle;
import math.vec2;

public class PlayerBloodBar extends JLabel implements Runnable{
	//data member
	public static boolean inGameAndAlive = true;
	private PlayerGameStatus status;
	private TurningStonePanel panel;
	public boolean bloodDisplayMode = true; //choose to display currentBlood / totalBlood or the ratio of it
	private JLabel remainBlood;
	public static boolean timerOn;
	public static boolean timerStart;
	public static int timeCount;
	public static int totTime;
	public static boolean superLongTime = false;
	public static Timer timerREF;
	public static PlayerBloodBar pbb;
	public static ScalingTask st;
	public JLabel decorate;
	private static int rightInset = 60;
	//---for animation test
	private double radd;
	private static int gg = 0;
	//constructor
	public PlayerBloodBar(TurningStonePanel panel, PlayerGameStatus status){
		super(ImageLoader.getBloodImage());
		pbb = this;
		this.timerOn = false;
		inGameAndAlive = true;
		this.timerStart = false;
		this.remainBlood = new JLabel();
		this.remainBlood.setVerticalTextPosition(CENTER);
		this.remainBlood.setHorizontalAlignment(CENTER);
		this.remainBlood.setSize(panel.getWidth(),20);
		this.remainBlood.setLocation(0,360);
		this.remainBlood.setVisible(true);
		this.remainBlood.addMouseListener(new MyMouseAdapter(){
			public void mouseClicked(MouseEvent f){
				changeBloodDisplayMode();
			}
			
			public void mousePressed(MouseEvent f) {
			}
			
			public void mouseReleased(MouseEvent f){
			}
			
		});
		
		this.decorate = new JLabel(ImageLoader.getBloodDecorateImage());
		this.decorate.setSize(panel.getWidth(),40);
		this.decorate.setLocation(0,349);
		this.setVisible(true);
		
		panel.add(decorate);
		
		this.panel = panel;
		this.status = status;
		this.setSize(panel.getWidth()-rightInset,20);
		this.setLocation(40,360);
		this.setVisible(true);
		
		panel.add(remainBlood);
	}
	
	
	//getter and setter
	public static void setInGameAndAlive(boolean bol){
		inGameAndAlive = bol;
	}
	
	//method
	public void changeBloodDisplayMode(){
		this.bloodDisplayMode = !this.bloodDisplayMode;
	}
	
	public static void reduceTimeCount(){
		timeCount -= 1;
	}
	
	public static void cancelTimer(){
		if(timerREF != null){
			timerREF.cancel();
		}
		if(st != null){
			st.cancel();
		}
		superLongTime = false;
		timerStart = false;
		timerOn = false;
		pbb.setIcon(ImageLoader.getBloodImage());
	}
	
	public void setAllInvisible(){
		this.remainBlood.setVisible(false);
		this.decorate.setVisible(false);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//System.out.println("BloodBar");
	}
	
	//Thread method
	public void run() {  //for auto detecting how long should blood bar display(detecting "PlayerGameStatus")
		while(TurningStonePanel.gameIsOn == true){
			Thread.yield();
			while(inGameAndAlive == true){
				//this.repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					System.out.println("Ending Thread : PlayerBloodBar, while sleeping");
				}
				if(!timerOn || Battle.isNet == true){
					if(pbb.timerREF != null)
						pbb.timerREF.cancel();
					this.setIcon(ImageLoader.getBloodImage());
					double w = (double)panel.getWidth()-rightInset;
					int totBlood = status.getTotalHealthPoint();
					int currentBlood = status.getHealthPoint();
					double ratio = (double)currentBlood / totBlood;
					w *= ratio;
					this.setSize((int)w, 20);
					this.remainBlood.setHorizontalAlignment(CENTER);
					this.remainBlood.setForeground(Color.YELLOW);
					//two mode to display
					if(this.bloodDisplayMode == true)
						this.remainBlood.setText(currentBlood + "/" + totBlood);
					else{
						DecimalFormat df = new DecimalFormat("#");
						this.remainBlood.setText(df.format(ratio*100) + "%");
					}
				
					if(status.getHealthPoint() == 0 && totBlood == 0){
						PlayerBloodBar.inGameAndAlive = false;
					}
				
					/* Test
					double rad = Math.toRadians(radd);
					radd+=0.001;
					panel.setLocation((int)(Math.sin(rad)*50),0);
					 */
				}
				else {
					//need to beware that Thread will occur some unexpected result -> boolean control
					if(this.timerStart == false && (TurningStonePanel.firstChangeOccur == true || superLongTime == true)){
						this.setIcon(ImageLoader.getTimeImage());
						this.timerStart = true;
						Timer timer = new Timer();
						//System.out.println("in");
						if(superLongTime == true){
							totTime = 20000;
							timeCount = 20000;
						}
						else{
							totTime = 8000;
							timeCount = 8000;
						}
						
						ScalingTask sTask = new ScalingTask(timer);
						st = sTask;
						timer.schedule(sTask, 0, 1);
						this.remainBlood.setText("");
					}
					else{
						if(totTime == 0 || (totTime == 8000 && superLongTime == true) || (totTime == 20000 && superLongTime == false)){
							this.timerStart = false;
						}
						else{
							double w = (double)panel.getWidth()-rightInset;
							int totBlood = totTime;
							int currentBlood = timeCount;
							double ratio = (double)currentBlood / totBlood;
							w *= ratio;
							this.setSize((int)w, 20);
							if(timeCount == 0){
								panel.freezeControl();
								if(TurningStonePanel.mouseAdapter != null){
									TurningStonePanel.mouseAdapter.setToPosition();
								}
								TurningStonePanel.firstChangeOccur = false;
								cancelTimer();
								superLongTime = false;
								timerStart = false;
								timerOn = false;
								this.setIcon(ImageLoader.getBloodImage());
							}
						}
					}
				}
			}
		}
	}

}
