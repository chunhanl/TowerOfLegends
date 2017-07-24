package util;

import soundUtil.Sound;
import stonePanel.TurningStonePanel;

public class StoneTurningSound extends Sound implements Runnable{
	//data member
	public static int remainPlayTime = 0;
	//constructor
	public StoneTurningSound(String url){
		super(url);
	}

	//Thread Method
	public void run() {
		while(TurningStonePanel.gameIsOn){
			System.out.println("");
			if(remainPlayTime>0){
				this.play();
				while(this.getCurrentLength()/10000 < this.getSoundLength()/10000);
				long delay = 980000000l;
				
				while(delay>0)delay--;
				this.stop();
				this.rebuildSound("turnStone.au");
				remainPlayTime--;
			}
		}
	}

}
