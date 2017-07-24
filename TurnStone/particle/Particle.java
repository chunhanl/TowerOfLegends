package particle;

import math.vec2;

public class Particle {
	//data member
	public static final int horizontal = 0;
	public static final int verticle = 1;
	public static final double gravity = 3;
	private int mode = 0;
	
	private double mass;
	private int lifeTime; 
	//---position
	private int x;
	private int y;
	private int sizeX;
	private int sizeY;
	//---velocity
	private double dx;
	private double dy;
	//---force
	private double fx;
	private double fy;
	
	//constructor
	public Particle(){  //the particle will stay still
		
	}
	//getter and setter
	public void setAll(double mass, int lifeTime, int x, int y, double dx, double dy, double fx, double fy, int sizex, int sizey, int mode){
		this.mass = mass;
		this.lifeTime = lifeTime;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.fx = fx;
		this.fy = fy;
		this.sizeX = sizex;
		this.sizeY = sizey;
		this.mode = mode;
	}
	
	public void setMass(double m){
		this.mass = m;
	}
	
	public void setLifeTime(int time){
		this.lifeTime = time;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setVx(double v){
		this.dx = v;
	}
	
	public void setVy(double y){
		this.dy = y;
	}
	
	public void setFx(double fx){
		this.fx = fx;
	}
	
	public void setFy(double fy){
		this.fy = fy;
	}
	
	public void setSizeX(int x){
		this.sizeX = x;
	}
	
	public void setSizeY(int y){
		this.sizeY = y;
	}
	
	public vec2 getPos(){
		return new vec2(this.x, this.y);
	}
	
	public double getFx(){
		return this.fx;
	}
	
	public double getFy(){
		return this.fy;
	}
	
	public double getdx(){
		return this.dx;
	}
	
	public double getdy(){
		return this.dy;
	}
	
	public int getLifeTime(){
		return this.lifeTime;
	}
	//method
	public void update(int milliTime){  //to updata this particle's position
		//f = ma(more short the update time more precise the animation is)
		if(mode == Particle.verticle){
			this.fy -= this.mass * gravity;
		}
		
		double accX = this.fx / this.mass;
		double accY = this.fy / this.mass;
		
		this.fx = 0;
		this.fy = 0;
		
		this.dx += accX * milliTime;
		this.dy += accY * milliTime;
		
		this.x += this.dx * milliTime;
		this.y += this.dy * milliTime;
		
	}	
}
