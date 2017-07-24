package Animation;

import stonePanel.Stone;
import math.vec2;

public class MovingInfo {
	//data member
	private static double gravity = 0.0030;
	private static double tolerance = 0.01;
	public int milliProcessTime;
	public vec2 from;
	public vec2 direction;
	public vec2 currentPosition;
	public vec2 to;	
	public boolean reachPosition;
	public Stone refStone;
	//constructor
	public MovingInfo(vec2 from, vec2 to, Stone rock){
		this.milliProcessTime = 0;
		this.from = new vec2(from);
		this.to = new vec2(to);
		this.direction = ((new vec2(to)).sub(from)).normalize();
		this.currentPosition = new vec2(from);
		this.reachPosition = false;
		this.refStone = rock;
	}
	//getter and setter
	
	//method -> support linear moving for now
	public void calculateCurrentPosition(int milliTime){
		if(this.reachPosition == false){
			refStone.setVisible(true);
			double ratio = 0.5 * gravity * (2.0 * (double)this.milliProcessTime + (double)milliTime) * milliTime;
			vec2 tmpPos = new vec2(currentPosition);
			tmpPos = tmpPos.add(direction.mul((int) ratio));
			currentPosition = new vec2(tmpPos);
			if(direction.dot(to.sub(tmpPos))<=0){
				currentPosition = new vec2(to);
				reachPosition = true;
				/*
				System.out.println("moving task finished");
				*/
			}
			this.milliProcessTime += milliTime;
			refStone.setLocation(currentPosition.getX(), currentPosition.getY());
		}
	}
	
}
