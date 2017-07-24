package stonePanel;


import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import InterFace.Battle;
import MyFrame.MyFrame;
import control.Attack;
import counter.Enemy;
import level.Level;

//the panel that show enemy we met
public class AttackPanel extends JPanel implements Runnable{
	//data member
	private TurningStonePanel stonePanel;
	public Level level;
	private JLabel[] enemyBloodBar;
	private JLabel[] enemyBloodBarDecorate;
	private Enemy[] currentEnemy;
	private int currentLV;
	/*
	private JLabel labb = new JLabel(ImageLoader.getStoneImage(2));
	int labbx = 0;
	int labby = 70;
	*/
	//constructor
	public AttackPanel(TurningStonePanel sPanel, Level lv){
		super(null);
		this.stonePanel = sPanel;
		this.level = lv;
		this.setLocation(0,0);
		this.setSize(stonePanel.getWidth(),380);
		this.setVisible(true);
		
		/*
		this.add(labb,0);
		labb.setSize(60,60);
		*/
	}
	//getter and setter

	
	//method
	public void paintComponent(Graphics g){
		if(TurningStonePanel.canPaint){
			super.paintComponent(g);
	    	Image bg = new ImageIcon(this.level.bgURL).getImage();
			g.drawImage(bg,0,0,this.getWidth(), getHeight(), this);
		}
	}
	
	public void setAllInvisible(){
		if(enemyBloodBar != null){
			for(int i=0; i<enemyBloodBar.length; i++){
				enemyBloodBar[i].setVisible(false);
			}
		}
		if(enemyBloodBarDecorate != null){
			for(int i=0; i<enemyBloodBarDecorate.length; i++){
				enemyBloodBarDecorate[i].setVisible(false);
			}
		}
	}
	
	public boolean isEveryBloodSync(){
		boolean decide = true;
		for(int i=0; i<level.round[level.currentRound-1].enemy.length; i++){
			int tot = level.round[level.currentRound-1].enemy[i].blood;
			int now = level.round[level.currentRound-1].enemy[i].currentBlood;
			int enemyWidth = level.round[level.currentRound-1].enemy[i].getSize().width;
			if(Math.abs((this.enemyBloodBar[i].getSize().width/enemyWidth * 0.89) - (now / tot)) >= 0.01){
				decide = false;
				break;
			}
		}
		return decide;
	}
	
	//thread method
	public void run() {
		Thread.currentThread().setPriority((Thread.MAX_PRIORITY + Thread.NORM_PRIORITY)/2);
		while(!level.isFinalLevel() || (level.isFinalLevel() && !level.isThisRoundEnd()) || (!looseAllBlood())){
			if(level.round[level.currentRound-1].isConstruct){
				int enemyNum = level.round[level.currentRound-1].enemy.length;

				for(int i=0; i<enemyNum; i++){
					if(currentLV != level.currentRound)
						break;
					int tot = level.round[level.currentRound-1].enemy[i].blood;
					int now = level.round[level.currentRound-1].enemy[i].currentBlood;
					if(Battle.isNet == true){
						now = ((MyFrame)stonePanel.getFrame()).client.getOtherPlayer(0).getHp();
					}
					int enemyWidth = level.round[level.currentRound-1].enemy[i].getSize().width;
					this.enemyBloodBar[i].setSize((int)((enemyWidth * 0.89) * now / ((double)tot)), 5);

				}
				try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("Ending Thread : AttackPanel, while sleeping");
				}
				/*
				labb.setLocation(++labbx/10000, labby);
				System.out.println(labbx);
				*/
			}
			else{  //construct this level
				if(level.currentRound == 1 && !level.isFinalLevel()){
					Battle.soundPlayer.setBGMusic("normalMusic.wav");
				}
				else if(level.isFinalLevel()){
					Battle.soundPlayer.setBGMusic("finalMusic.wav");
				}
				
				if(Attack.attackReach == true){
					if(level.currentRound != 1){
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					level.round[level.currentRound-1].isConstruct = true;
					this.currentLV = level.currentRound;
					if(enemyBloodBar != null){
						for(int i=0; i<enemyBloodBar.length; i++){
							this.enemyBloodBar[i].setVisible(false);
							this.enemyBloodBarDecorate[i].setVisible(false);
							this.remove(enemyBloodBar[i]);
							this.remove(enemyBloodBarDecorate[i]);
							this.revalidate();
						}
					}
				
					if(currentEnemy != null){
						for(int i=0; i<currentEnemy.length; i++){
							this.currentEnemy[i].setVisible(false);
							this.remove(currentEnemy[i]);
							this.revalidate();
						}
					}
					this.currentEnemy = null;
					this.enemyBloodBar = null;
					this.enemyBloodBarDecorate = null;
				
				
					Enemy[] tmpEnemy = level.round[level.currentRound-1].enemy;
					this.currentEnemy = tmpEnemy;
					this.enemyBloodBar = new JLabel[tmpEnemy.length];
					this.enemyBloodBarDecorate = new JLabel[tmpEnemy.length];
				
					for(int i=0; i<tmpEnemy.length; i++){
						this.add(tmpEnemy[i]);
						//add enemy to specify position
						tmpEnemy[i].setLocation(tmpEnemy[i].position.getX(), 265 - tmpEnemy[i].position.getY() - tmpEnemy[i].size.getY());
						tmpEnemy[i].setSize(tmpEnemy[i].size.getX(), tmpEnemy[i].size.getY());
						tmpEnemy[i].setIcon(tmpEnemy[i].imag);
						tmpEnemy[i].setVisible(true);
						
						//add enemy blood bar decorate under it
						Image newimg = ImageLoader.getEnemyBloodDecorateImage().getImage().getScaledInstance(tmpEnemy[i].size.getX(), 10,  java.awt.Image.SCALE_AREA_AVERAGING);
						ImageIcon newIcon = new ImageIcon(newimg);
						this.enemyBloodBarDecorate[i] = new JLabel(newIcon);
						this.enemyBloodBarDecorate[i].setLocation(tmpEnemy[i].position.getX(), 275 - tmpEnemy[i].position.getY()-4);
						this.enemyBloodBarDecorate[i].setSize(tmpEnemy[i].size.getX(), 10);
						this.enemyBloodBarDecorate[i].setVisible(true);
						this.add(this.enemyBloodBarDecorate[i],0);
					
						//add the blood bar to enemy 
						this.enemyBloodBar[i] = new JLabel(ImageLoader.getBloodImage());
						this.enemyBloodBar[i].setLocation(tmpEnemy[i].position.getX()+(int)(tmpEnemy[i].size.getX() * 0.06), 275 - tmpEnemy[i].position.getY());
						this.enemyBloodBar[i].setSize((int)(tmpEnemy[i].size.getX() * 0.89), 2);
						this.enemyBloodBar[i].setVisible(true);
						this.add(this.enemyBloodBar[i]);
					}
				}
			}
		}
	}
	// for net
	public Enemy getCurrentEnemy(){
		return level.round[level.currentRound-1].enemy[0];
	}
	
	public int getCurrentEnemyBlood(){
		return level.round[level.currentRound-1].enemy[0].currentBlood;
	}
	//
	public boolean looseAllBlood(){
		if(Battle.isNet == true)
			return false;
		if(level.isThisRoundEnd()){
			for(int i=0; i<this.enemyBloodBar.length; i++)
				if(this.enemyBloodBar[i].getSize().width != 0)
					return false;
			return true;
		}
		else{
			return false;
		}
	}
}
