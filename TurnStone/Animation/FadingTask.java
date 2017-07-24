package Animation;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FadingTask extends TimerTask{
	//data member
	private Timer timer;
	private JLabel target;
	private int targetAlpha;
	private int currentAlpha;
	//constructor
	public FadingTask(Timer t, JLabel target, int alpha){
		this.timer = t;
		this.target = target;
		this.targetAlpha = alpha;
		this.currentAlpha = 0;
	}
	
	//getter and setter
	
	//method
	public boolean thisFinishPercent(int percent){
		int thisPercent = (int)(100.0 * (double)currentAlpha / targetAlpha);
		if(percent >= thisPercent){
			return true;
		}
		return false;
	}
	//thread method
	public void run() {
		if(currentAlpha <= targetAlpha){
			this.target.setBackground(new Color(0,0,0, currentAlpha));
			currentAlpha++;
		}
		else{
			this.cancel();
			timer.cancel();
		}
	}
}
