package motionMethod;

import math.vec2;

public interface MotionStrategy {
	//data member
	
	//abstract method
	public vec2 getWay(int x);
	public boolean reachPosition(vec2 in, int size);
}
