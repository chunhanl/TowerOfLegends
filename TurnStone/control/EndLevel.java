package control;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Animation.FadingTask;
import InterFace.Battle;
import MyFrame.MyFrame;
import stonePanel.ImageLoader;
import stonePanel.MyMouseAdapter;
import stonePanel.PlayerBloodBar;
import stonePanel.PlayerGameStatus;
import stonePanel.TurningStonePanel;

public class EndLevel implements State{
	//data member
	private boolean win;
	//constructor
	public EndLevel(String condition){
		if(condition == "Win"){
			this.win = true;
		}
		else{
			this.win = false;
		}
	}
	
	//implements methods
	public boolean shouldDo(StateControlManager manager) {

		

		//stop every control Thread
		PlayerBloodBar.inGameAndAlive = false;
		TurningStonePanel.gameIsOn = false;
		ThreadController.interruptAll();
		//-------clear particle
		Battle.particleSystemREF.clearGameParticle();
		this.shutDownAll();
		
		//playing win or defeat animation
		TurningStonePanel stonePanelREF = TurningStonePanel.tspREF;
		JLabel fadingLabel = new JLabel();
		fadingLabel.setSize(500,750);
		fadingLabel.setLocation(0,0);
		Timer timer = new Timer();
		fadingLabel.setBackground(new Color(0,0,0,0));

		
		JLabel conditionLabel = new JLabel(ImageLoader.getConditionImage(win));
		JLabel conditionPanel = new JLabel();
		conditionPanel.setSize(400,200);
		conditionPanel.setLocation(50,50);
		conditionLabel.setSize(400,200);
		conditionLabel.setLocation(0,0);
		conditionPanel.setBackground(new Color(0,0,0,0));
		
		conditionPanel.add(conditionLabel);

		FadingTask ft = new FadingTask(timer, fadingLabel, 250);
		
		stonePanelREF.add(conditionPanel);
		stonePanelREF.add(fadingLabel,0);
		conditionLabel.setVisible(true);
		conditionPanel.setVisible(true);
		fadingLabel.setVisible(true);
		timer.schedule(ft, 0, 10);
		

		/*
		 * lose it's work but still don't know why now
		 */
		while(!ft.thisFinishPercent(95)){
		}
		
		JLabel returnButton = new JLabel(ImageLoader.getContinueButton());
		returnButton.addMouseListener(new MyMouseAdapter(){
				public void mouseClicked(MouseEvent f){
					System.out.println("Yeah i'm clicked");
					TurningStonePanel.tspREF.setVisible(false);
					MyFrame m = (MyFrame) TurningStonePanel.tspREF.getFrame();
					m.remove(TurningStonePanel.tspREF);
					m.revalidate();
					Battle.soundPlayer.stopBGMusic();
					MyFrame.refFrame.main.setVisible(true);
					(m).client.getPlayer().initialHp();
					(m).client.getOtherPlayer(0).initialHp();
				}
				
				public void mouseReleased(MouseEvent f){
				}
				
				public void mousePressed(MouseEvent f){
					
				}
			}
		);
		
		returnButton.setSize(200, 70);
		returnButton.setLocation(150, 450);
		returnButton.setVisible(true);
		stonePanelREF.add(returnButton,0);
		
		
		/*
		JFrame frame = stonePanelREF.getFrame();
		frame.remove(stonePanelREF);
		frame.revalidate();
		*/

		System.out.println("Ending \"" + TurningStonePanel.tspREF.getAtkPanel().level.getClass().getName() + "\"......");		

		
		return true;
	}

	public void changeState(StateControlManager manager) {
		while(true){
			
		}
	}

	public void showState() {
		
	}
	
	//method
	public void shutDownAll(){
		TurningStonePanel spREF = TurningStonePanel.tspREF;
		spREF.setAllInvisible();
	}
	
	
}
