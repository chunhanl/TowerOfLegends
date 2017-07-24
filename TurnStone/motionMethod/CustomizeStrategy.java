package motionMethod;

import math.vec2;

public class CustomizeStrategy implements MotionStrategy{
	//data member
	private String strategyName;
	private vec2[] strategy;
	private vec2 startPoint;
	private vec2 endPoint;
	
	//constructor
	public CustomizeStrategy(vec2 startPoint, vec2 endPoint, String strategyName){
		this.strategyName = strategyName;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.buildStrategy();
	}
	
	//method
	public vec2 getWay(int index) {
		return new vec2(strategy[index].getX() + startPoint.getX(), strategy[index].getY() + startPoint.getY());
	}
	
	private void buildStrategy(){
		//read from file and build vec2[] strategy
	}
	
	private boolean onEndPoint(vec2 currentPosition){
		if(currentPosition.getX() == endPoint.getX() && currentPosition.getY() == endPoint.getY())
			return true;
		return false;
	}

	@Override
	public boolean reachPosition(vec2 in, int size) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
