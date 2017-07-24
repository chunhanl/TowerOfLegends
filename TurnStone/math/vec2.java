package math;
//vec2 mean "2D Vector" that record cartesian coordinate of the object on this panel
public class vec2 {
	//data member
	private int X;
	private int Y;
	private double Length; //length will re-calculate when other variables has been changed(cannot change directly)
	
	//constructor
	public vec2(){
		this(0,0);
	}
	
	public vec2(int X, int Y){
		this.setX(X);
		this.setY(Y);
		Length = Math.sqrt(X * X + Y * Y);
	}
	
	public vec2(vec2 v){
		this.X = v.getX();
		this.Y = v.getY();
		Length = Math.sqrt(X * X + Y * Y);
	}

	//getter and setter
	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
		Length = Math.sqrt(X * X + Y * Y);
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
		Length = Math.sqrt(X * X + Y * Y);
	}
	
	public void setThisVec2(vec2 v2){
		this.X = v2.getX();
		this.Y = v2.getY();
		Length = Math.sqrt(X * X + Y * Y);
	}
	
	public void setThisVec2(int x, int y){
		this.X = x;
		this.Y = y;
		Length = Math.sqrt(X * X + Y * Y);
	}
	
	public vec2 getThisVec2(){
		return new vec2(X, Y);
	}
	
	public double getLength(){
		return Math.sqrt(X * X + Y * Y);
	}
	
	//math
	public vec2 add(vec2 v){
		return new vec2(v.getX() + this.X, v.getY() + this.Y);
	}
	
	public vec2 add(int i){
		return new vec2(i + this.X, i + this.Y);
	}
	
	public vec2 sub(vec2 v){
		return new vec2(-v.getX() + this.X, -v.getY() + this.Y);
	}
	
	public vec2 sub(int i){
		return new vec2(-i + this.X, -i + this.Y);
	}
	
	public vec2 mul(int i){
		return new vec2(i * this.X, i * this.Y);
	}
	
	public vec2 div(int i){
		return new vec2(this.X / i, this.Y / i);
	}
	
	public int dot(vec2 v){
		return v.getX() * this.X + v.getY() * this.Y;
	}
	
	public vec2 normalize(){
		return new vec2((int)(X / Length), (int)(Y / Length));
	}
	
	//for calculate range
	public boolean inRange(int value){
		if((X<value && Y>value) || (X>value && Y<value))
			return true;
		return false;
	}
	
	public boolean goNegativeX(vec2 other){
		if(this.X < other.getX()){
			return true;
		}
		return false;
	}
	
	public boolean goNegativeY(vec2 other){
		if(this.Y < other.getY()){
			return true;
		}
		return false;
	}
	//toString method
	public String toString(){
		return "(" + this.X + "," + this.Y + ")";
	}
	
}
