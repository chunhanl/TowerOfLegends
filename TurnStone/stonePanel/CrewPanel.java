package stonePanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import util.EnhanceComfirmDialog;
import math.vec2;
import card.Card;
import card.SimpleCard;

//crewpanel shows the crew of team we used in this level
public class CrewPanel extends JPanel implements Runnable{
	//data member
	private JLabel leader;
	private JLabel firstCrew;
	private JLabel secondCrew;
	private JLabel thirdCrew;
	private JLabel fourthCrew;
	
	private JLabel helper;
	private int[] crewCD; //hold current crew cd
	private int[] crewMAXCD; //hold the cd time of each member
	
	private TurningStonePanel stonePanel;
	private SimpleCard[] card;
	
	private boolean lockClick;
	private EnhanceComfirmDialog optionPane;
	//constructor
	public CrewPanel(TurningStonePanel panel, final SimpleCard[] card){
		super(null);
		this.optionPane = new EnhanceComfirmDialog();
		lockClick = false;
		this.setBackground(new Color(0,0,0,0));
		this.crewCD = new int[6];
		this.crewMAXCD = new int[6];
		//for testing
		for(int i=0; i<6; i++){
			crewCD[i] = 3;
			crewMAXCD[i] = 3;
		}
		
		
		this.stonePanel = panel;
		this.setLocation(0,290);
		this.setSize(panel.getWidth(), 60);
		
		
		final int width = stonePanel.getWidth();
		
		ImageIcon[] imag = new ImageIcon[6];
		
		for(int i=0; i<6; i++){
			if(card[i] != null)
				imag[i] = card[i].headImage;
		}
		
		this.leader  = new JLabel(imag[0]);
		this.firstCrew = new JLabel(imag[1]);
		this.secondCrew = new JLabel(imag[2]);
		this.thirdCrew = new JLabel(imag[3]);
		this.fourthCrew = new JLabel(imag[4]);
		this.helper = new JLabel(imag[5]);
		
		this.add(leader);
		this.add(firstCrew);
		this.add(secondCrew);
		this.add(thirdCrew);
		this.add(fourthCrew);
		this.add(helper);
		
		leader.setLocation(0,10);
		leader.setSize(width/6, 50);
		if(card[0] != null)
		leader.addMouseListener(new MyMouseAdapter(){
			public void mouseClicked(MouseEvent f){			
				if(card[0] != null && crewCD[0] == 0 && !PlayerBloodBar.timerOn && !lockClick){
					System.out.println("don't u trust me?");
					lockClick = true;
					int ans = optionPane.showConfirmDialog(TurningStonePanel.tspREF.getFrame(), card[0].directPower.getSkillName(), "開啟技能", optionPane.YES_NO_OPTION);
					if(ans == optionPane.YES_OPTION){
						card[0].directPower.activatedPower();
						crewCD[0] = crewMAXCD[0];
						leader.setLocation(0,10);
						leader.setSize(width/6, 50);
					}
					lockClick = false;
				}
			}
			
			public void mousePressed(MouseEvent f) {
			}
			
			public void mouseReleased(MouseEvent f){
			}
			
		});
		
		firstCrew.setLocation(width/6, 10);
		firstCrew.setSize(width/6, 50);
		if(card[1] != null)
		firstCrew.addMouseListener(new MyMouseAdapter(){
			public void mouseClicked(MouseEvent f){
				if(card[1] != null && crewCD[1] == 0 && !PlayerBloodBar.timerOn && !lockClick){
					System.out.println("feel my wild");
					lockClick = true;
					int ans = optionPane.showConfirmDialog(TurningStonePanel.tspREF.getFrame(), card[1].directPower.getSkillName(), "開啟技能", optionPane.YES_NO_OPTION);
					if(ans == optionPane.YES_OPTION){
						card[1].directPower.activatedPower();
						crewCD[1] = crewMAXCD[1];
						firstCrew.setLocation(width/6,10);
						firstCrew.setSize(width/6, 50);
					}
					lockClick = false;
				}
			}
			
			public void mousePressed(MouseEvent f) {
			}
			
			public void mouseReleased(MouseEvent f){
			}
			
		});
		
		secondCrew.setLocation(width/6 * 2, 10);
		secondCrew.setSize(width/6, 50);
		if(card[2] != null)
		secondCrew.addMouseListener(new MyMouseAdapter(){
			public void mouseClicked(MouseEvent f){
				if(card[2] != null && crewCD[2] == 0 && !PlayerBloodBar.timerOn && !lockClick){
					System.out.println("I am gg man");
					lockClick = true;
					int ans = optionPane.showConfirmDialog(TurningStonePanel.tspREF.getFrame(), card[2].directPower.getSkillName(), "開啟技能",optionPane.YES_NO_OPTION);
					if(ans == optionPane.YES_OPTION){
						card[2].directPower.activatedPower();
						crewCD[2] = crewMAXCD[2];
						secondCrew.setLocation(width/6 * 2,10);
						secondCrew.setSize(width/6, 50);
					}
					lockClick = false;
				}
			}
			
			public void mousePressed(MouseEvent f) {
			}
			
			public void mouseReleased(MouseEvent f){
			}
			
		});
		
		thirdCrew.setLocation(width/6 * 3, 10);
		thirdCrew.setSize(width/6, 50);
		if(card[3] != null)
		thirdCrew.addMouseListener(new MyMouseAdapter(){
			public void mouseClicked(MouseEvent f){
				if(card[3] != null && crewCD[3] == 0 && !PlayerBloodBar.timerOn && !lockClick){
					System.out.println("I just eat a cow");
					lockClick = true;
					int ans = optionPane.showConfirmDialog(TurningStonePanel.tspREF.getFrame(), card[3].directPower.getSkillName(), "開啟技能", optionPane.YES_NO_OPTION);
					if(ans == optionPane.YES_OPTION){
						card[3].directPower.activatedPower();
						crewCD[3] = crewMAXCD[3];
						thirdCrew.setLocation(width/6 * 3,10);
						thirdCrew.setSize(width/6, 50);
					}
					lockClick = false;
				}
			}
			
			public void mousePressed(MouseEvent f) {
			}
			
			public void mouseReleased(MouseEvent f){
			}
			
		});
		
		fourthCrew.setLocation(width/6 * 4, 10);
		fourthCrew.setSize(width/6, 50);
		if(card[4] != null)
		fourthCrew.addMouseListener(new MyMouseAdapter(){
			public void mouseClicked(MouseEvent f){
				if(card[4] != null && crewCD[4] == 0 && !PlayerBloodBar.timerOn && !lockClick){
					System.out.println("all my mind to do so");
					lockClick = true;
					int ans = optionPane.showConfirmDialog(TurningStonePanel.tspREF.getFrame(), card[4].directPower.getSkillName(), "開啟技能", optionPane.YES_NO_OPTION);
					if(ans == optionPane.YES_OPTION){
						card[4].directPower.activatedPower();
						crewCD[4] = crewMAXCD[4];
						fourthCrew.setLocation(width/6 * 4,10);
						fourthCrew.setSize(width/6, 50);
					}
					lockClick = false;
				}
			}
			
			public void mousePressed(MouseEvent f) {
			}
			
			public void mouseReleased(MouseEvent f){
			}
			
		});
		
		helper.setLocation(width/6 * 5, 10);
		helper.setSize(width/6, 50);
		if(card[5] != null)
		helper.addMouseListener(new MyMouseAdapter(){
			public void mouseClicked(MouseEvent f){
				if(card[5] != null && crewCD[5] == 0 && !PlayerBloodBar.timerOn && !lockClick){
					System.out.println("Try to engage");
					lockClick = true;
					int ans = optionPane.showConfirmDialog(TurningStonePanel.tspREF.getFrame(), card[5].directPower.getSkillName(), "開啟技能", optionPane.YES_NO_OPTION);
					if(ans == optionPane.YES_OPTION){
						card[5].directPower.activatedPower();
						crewCD[5] = crewMAXCD[5];
						helper.setLocation(width/6 * 5,10);
						helper.setSize(width/6, 50);
					}
					lockClick = false;
				}
			}
			
			public void mousePressed(MouseEvent f) {
			}
			
			public void mouseReleased(MouseEvent f){
			}
			
		});
		this.setVisible(true);
	}
	//getter and setter

	
	//method
	public void paintComponent(Graphics g){
		if(TurningStonePanel.canPaint){
			super.paintComponent(g);
		}
	}
	public void decCrewCD(){
		for(int i=0; i<6; i++){
			if(crewCD[i] != 0){
				crewCD[i]--;
			}
			if(crewCD[i] == 0){
				setPowerEnable(i);
			}
		}
	}
	public void setPowerEnable(int id){
		if(id == 0){
			leader.setSize(leader.getSize().width, 60);
			leader.setLocation(leader.getLocation().x, 0);
		}
		else if(id == 1){
			firstCrew.setSize(leader.getSize().width, 60);
			firstCrew.setLocation(firstCrew.getLocation().x, 0);
		}
		else if(id == 2){
			secondCrew.setSize(leader.getSize().width, 60);
			secondCrew.setLocation(secondCrew.getLocation().x, 0);
		}
		else if(id == 3){
			thirdCrew.setSize(leader.getSize().width, 60);
			thirdCrew.setLocation(thirdCrew.getLocation().x, 0);
		}
		else if(id == 4){
			fourthCrew.setSize(leader.getSize().width, 60);
			fourthCrew.setLocation(fourthCrew.getLocation().x, 0);
		}
		else if(id == 5){
			helper.setSize(leader.getSize().width, 60);
			helper.setLocation(helper.getLocation().x, 0);
		}
		else{
		}
	}
	
	public void setAllInvisible(){
		leader.setVisible(false);
		firstCrew.setVisible(false);
		secondCrew.setVisible(false);
		thirdCrew.setVisible(false);
		fourthCrew.setVisible(false);
		helper.setVisible(false);
	}
	
	public vec2 getCrewCenterLocation(int index){
		vec2 tmp = null;
		switch(index){
			case 0:
				if(this.leader != null)
					tmp = new vec2(leader.getWidth()/2 + leader.getLocation().x, leader.getHeight()/2 + leader.getLocation().y);
				break;
			case 1:
				if(this.firstCrew != null)
					tmp = new vec2(firstCrew.getWidth()/2 + firstCrew.getLocation().x, firstCrew.getHeight()/2 + firstCrew.getLocation().y);
				break;
			case 2:
				if(this.secondCrew != null)
					tmp = new vec2(secondCrew.getWidth()/2 + secondCrew.getLocation().x, secondCrew.getHeight()/2 + secondCrew.getLocation().y);
				break;
			case 3:
				if(this.thirdCrew != null)
					tmp = new vec2(thirdCrew .getWidth()/2 + thirdCrew .getLocation().x, thirdCrew .getHeight()/2 + thirdCrew .getLocation().y);
				break;
			case 4:
				if(this.fourthCrew != null)
					tmp = new vec2(fourthCrew.getWidth()/2 + fourthCrew.getLocation().x, fourthCrew.getHeight()/2 + fourthCrew.getLocation().y);
				break;
			case 5:
				if(this.helper != null)
					tmp = new vec2(helper.getWidth()/2 + helper.getLocation().x, helper.getHeight()/2 + helper.getLocation().y);
				break;
		}
		//make this position to absolute position to this frame
		if(tmp	!= null)
			tmp = tmp.add(new vec2(this.getLocation().x, this.getLocation().y));
		
		return tmp;
	}
	//Thread method
	public void run() {
		while(TurningStonePanel.gameIsOn == true){
			while(PlayerBloodBar.inGameAndAlive == true){
			}
		}	
	}
}
