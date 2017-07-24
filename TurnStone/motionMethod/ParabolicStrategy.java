package motionMethod;

import math.vec2;

public class ParabolicStrategy implements MotionStrategy{
	//data member  y = a(x-b)^2 + k
	private double a;
	private int b;
	private int k;
	private vec2 endPoint;
	private vec2 startPoint;
	public boolean reach = false;
	
	//constructor
	public ParabolicStrategy(vec2 startPoint, vec2 endPoint){
		
		this.endPoint = endPoint;
		this.startPoint = startPoint;
		/*
		this.b = (endPoint.getX() - startPoint.getX());
		this.k = endPoint.getY() - startPoint.getY();
		this.a = (-1.0) * k / (b * b);
		*/
		
		this.k = startPoint.getX();
		this.b = startPoint.getY();
		this.a = (double)(endPoint.getX() - startPoint.getX()) / ((endPoint.getY() - startPoint.getY()) * (endPoint.getY() - startPoint.getY()));
	}
	
	//method
	public vec2 getEndPoint(){
		return new vec2(this.endPoint);
	}
	public vec2 getStartPoint(){
		return new vec2(this.startPoint);
	}
	
	public vec2 getWay(int y) {
		if(endPoint.goNegativeY(startPoint) == true)
			y *= -1;
		double x = a * (y + startPoint.getY() - b) * (y + startPoint.getY() - b) + k;
		
		//System.out.println(startPoint.toString() + "," + endPoint.toString() + "," +"y = " + a + "(x - " + b +")^2 + " + k);
		//return new vec2(x + startPoint.getX(), (int) y + startPoint.getY());
		
		return new vec2((int)x, (int)y + startPoint.getY());
	}

	public boolean reachPosition(vec2 in, int size) {
		double length = new vec2(endPoint).sub(startPoint).getLength();
		if(size * 0.7 > (in.sub(new vec2(endPoint))).getLength() || length < (in.sub(new vec2(startPoint))).getLength()){
			this.reach = true;
			return true;
		}
		return false;
	}
}
