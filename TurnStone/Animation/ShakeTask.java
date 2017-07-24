package Animation;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import math.vec2;

public class ShakeTask implements Runnable{
	//data member
	private JLabel target;
	private vec2 OriginPlace;
	private int shakeRadius;
	private double decay;
	public boolean dead;
	private double time;
	//constructor
	public ShakeTask(JLabel target, int shakeRadius, double decay){
		this.target = target;
		this.OriginPlace = new vec2(target.getLocation().x, target.getLocation().y);
		this.shakeRadius = shakeRadius;
		this.decay = decay;
		this.dead = false;
		this.time = 0;
	}
	
	//getter and setter
	
	//method
	public void resetTime(){
		this.time = 0;
	}
	//thread method
	public void run() {
		while(true){
			double rad = Math.toRadians(time);
			time+=0.001;
			int delX = (int)(shakeRadius * Math.sin(rad));//*Math.exp(-time * decay)
			this.target.setLocation(OriginPlace.getX() + delX, OriginPlace.getY());
			//System.out.println("in" + delX);
			if(time >= 1){
				break;
			}
		}
		this.target.setLocation(OriginPlace.getX(), OriginPlace.getY());
		this.dead = true;
	}
}
