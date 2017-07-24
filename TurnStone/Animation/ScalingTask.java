package Animation;

import java.util.Timer;
import java.util.TimerTask;

import stonePanel.PlayerBloodBar;

public class ScalingTask extends TimerTask{
	//data member
	private Timer timer;
	
	//constructor
	public ScalingTask(Timer timer){
		this.timer = timer;
	}
	
	//Thread method
	public void run() {
		if(PlayerBloodBar.timeCount > 0){
			PlayerBloodBar.timeCount--;
		}
		else{
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			timer.cancel();
		}
	}

}
