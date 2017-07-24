package control;

import gameUtil.BattleTeam;

import javax.swing.JFrame;

import Animation.ShakeTask;
import InterFace.Battle;
import MyFrame.MyFrame;
import stonePanel.TurningStonePanel;

public class StateControlManager implements Runnable{
	//data member
	private State _current;
	public boolean canChange = false;;
	private JFrame frame;
	public TurningStonePanel stonePanel;
	
	//for net
	public ShakeTask tmpTask = null;
	//constructor
	public StateControlManager(JFrame frame, TurningStonePanel stonePanel, BattleTeam battleTeam){
		this.frame = frame;
		this.stonePanel = stonePanel;
		this._current = new StartLevel(frame, stonePanel);
	}
	
	//method
	public void changeState(){
		_current.changeState(this);
	}
	
	public void setState(State s){
		_current = s;
	}
	
	public State getState(){
		return _current;
	}

	//Thread method
	public void run() {
		Thread.currentThread().setPriority(10);
		while(stonePanel.gameIsOn == true){
			
			if(Battle.isNet == true){ //then recieve data
				int lastTimeHP = stonePanel.tspREF.getAtkPanel().getCurrentEnemyBlood();
				stonePanel.getPlayerGameStatus().setHealthPoint(((MyFrame)frame).client.getPlayer().getHp());	
				/*
				if(lastTimeHP != ((MyFrame)frame).client.getOtherPlayer(0).getHp()){
					if(this.tmpTask == null || this.tmpTask.dead == true){
						this.tmpTask = new ShakeTask(stonePanel.tspREF.getAtkPanel().getCurrentEnemy(), 50, 0.5);
						Thread t = new Thread(this.tmpTask);
						t.start();
					}
					else{
						this.tmpTask.resetTime();
					}
				}
				*/
				if(((MyFrame)frame).client.getPlayer().getHp() == 0){
					this._current = new EndLevel("fail");
				}
				if(((MyFrame)frame).client.getOtherPlayer(0).getHp() == 0){
					this._current = new EndLevel("Win");
				}
			}
			
			
			if(canChange == true){
				this._current.changeState(this);
				this.canChange = false;
			}
			else{
				this.canChange = this._current.shouldDo(this);
			}				
		}
	}
}
