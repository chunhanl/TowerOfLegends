package Animation;

import gameUtil.ComboMessage;

import java.util.Timer;
import java.util.TimerTask;

import control.StateControlManager;

public class MovingTask extends TimerTask{
	//data member
	private StateControlManager manager;
	private ComboMessage cMessage; 
	private MovingInfo[] mInfo;
	private Timer timer;
	//constructor
	public MovingTask(StateControlManager manager, ComboMessage cMessage, MovingInfo[] mInfo, Timer timer) { //for falling stone task
		this.manager = manager;
		this.cMessage = cMessage;
		this.mInfo = mInfo;
		this.timer = timer;
	}
	
	public MovingTask(MovingInfo mInfo, Timer timer){ //for single moving task
		this.mInfo = new MovingInfo[1];
		this.mInfo[0] = mInfo;
		this.timer = timer;
	}
	//getter & setter
	
	//method
	public boolean isEveryStoneReachPosition(){
		for(int i=0; i<mInfo.length; i++){
			if(mInfo[i].reachPosition == false){
				return false;
			}
		}
		return true;
	}
	
	//Thread method
	public void run() {
		if(!isEveryStoneReachPosition()){
			for(int i=0; i<mInfo.length; i++){
				mInfo[i].calculateCurrentPosition(10);
			}
		}
		else{
			this.timer.cancel();
		}
	}

}
