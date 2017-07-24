package CardTools;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import card.Card;
import FixedPanel.FixedPanel;

public class CardInformation extends JPanel implements MouseListener{
	
	private Card card;
	
	private JLabel blank;
	private JLabel fullImage;
	
	private JLabel space;
	private JLabel life;
	private JLabel attack;
	private JLabel heal;
	private JLabel species;
	private JLabel lv;
	private JLabel skill;			//�D�ʧ�
	private JLabel effectSkill;		//�Q�ʧޯ�
	private JLabel cd;
	private JLabel skillLevel;
	
	private FixedPanel fpanelREF;
	
	
	public CardInformation(FixedPanel f,Card cardID){
		super();
		this.fpanelREF=f;
		this.card=cardID;
		
		
		fillinData();
		buildBlank();   //
		buildFullImage();
		this.addMouseListener(this);
		this.setSize(500, 750);
		this.setOpaque(false);
		this.setVisible(true);
		f.add(this,0);
		
		this.zoomIn();
		f.repaint();
	}


	private void fillinData() {
		space=new JLabel();
		space.setLocation(440,72);
		space.setSize(200, 50);
		space.setForeground(Color.white);
		space.setFont(new Font("Times New Roman", Font.BOLD, 25));
		space.setText(String.valueOf(card.getPackSpace()));
		this.add(space);
		
		lv=new JLabel();
		lv.setLocation(70,400);
		lv.setSize(100, 50);
		lv.setForeground(Color.white);
		lv.setFont(new Font("Georgia", Font.BOLD , 22));
		lv.setText(String.valueOf(card.getLevel())+"/"+card.getMaxLevel());
		this.add(lv);
		
		
		life=new JLabel(String.valueOf(card.getHealth()),SwingConstants.RIGHT);
		life.setLocation(140,442);
		life.setSize(100, 50);
		life.setForeground(Color.white);
		life.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.add(life);
		
		attack=new JLabel(String.valueOf(card.getAttack()),SwingConstants.RIGHT);
		attack.setLocation(350,442);
		attack.setSize(100, 50);
		attack.setForeground(Color.white);
		attack.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.add(attack);
		
		heal=new JLabel(String.valueOf(card.getRecover()),SwingConstants.RIGHT);
		heal.setLocation(140,475);
		heal.setSize(100, 50);
		heal.setForeground(Color.white);
		heal.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.add(heal);
		
		species=new JLabel(String.valueOf(card.getSpecies()),SwingConstants.RIGHT);
		species.setLocation(350,475);
		species.setSize(100, 50);
		species.setForeground(Color.white);
		species.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.add(species);
		
		skill=new JLabel(String.valueOf(card.getSkillLevel()),SwingConstants.RIGHT);
		skill.setLocation(50,507);
		skill.setSize(100, 50);
		skill.setForeground(Color.white);
		skill.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.add(skill);
		
		effectSkill=new JLabel(String.valueOf(card.getEffectSkill()),SwingConstants.RIGHT);
		effectSkill.setLocation(70,605);
		effectSkill.setSize(100, 50);
		effectSkill.setForeground(Color.white);
		effectSkill.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.add(effectSkill);
		
		
		skillLevel=new JLabel(String.valueOf(card.getCD()),SwingConstants.RIGHT);
		skillLevel.setLocation(370,510);
		skillLevel.setSize(100, 50);
		skillLevel.setForeground(Color.white);
		skillLevel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.add(skillLevel);
		
		cd=new JLabel(String.valueOf(card.getCD()),SwingConstants.RIGHT);
		cd.setLocation(300,510);
		cd.setSize(100, 50);
		cd.setForeground(Color.white);
		cd.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.add(cd);
	}


	private void buildFullImage() {
		this.fullImage=new JLabel(card.getCardImage());
		fullImage.setSize(254,400);
		fullImage.setLocation(160,100);
		this.add(fullImage);
	}


	private void zoomIn() {
		final Timer time=new Timer();
		time.schedule(new TimerTask(){
			public void run(){
				blank.setSize(blank.getWidth()+10,blank.getHeight()+15);
				blank.setLocation((500-blank.getWidth())/2, (750-blank.getHeight())/2);

				if(blank.getWidth()==500){
					time.cancel(); 
					this.cancel();
					fullImage.setVisible(true);
				}
				blank.repaint();
			}
				
		}, 0, 1);
		
	}
	
	private void zoomOut() {
		final Timer time=new Timer();
		time.schedule(new TimerTask(){
			public void run(){
				blank.setSize(blank.getWidth()-10,blank.getHeight()-15);
				blank.setLocation((500-blank.getWidth())/2, (750-blank.getHeight())/2);

				if(blank.getWidth()==0){
					fpanelREF.remove(0);
					fpanelREF.repaint();
					time.cancel(); 
					this.cancel();
				}
				fpanelREF.repaint();
				System.out.println(blank.getWidth());
			}
				
		}, 0, 1);
		
	}


	private void buildBlank() {
		blank=new JLabel(new ImageIcon("res/cardtools/blank.png"));
		blank.setSize(0,0);
		blank.setLocation(0, 0);
		this.add(blank);
	}


	public void mouseClicked(MouseEvent e) {
		this.zoomOut();
	}


	public void mousePressed(MouseEvent e) {}


	public void mouseReleased(MouseEvent e) {}

	
	public void mouseEntered(MouseEvent e) {}


	public void mouseExited(MouseEvent e) {}
}
