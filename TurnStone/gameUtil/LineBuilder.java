package gameUtil;

import math.vec2;

public class LineBuilder {
	//data member
	public vec2[] linePoint;
	public int lineLength;
	
	//constructor
	public LineBuilder(){
		this.linePoint = null;
		this.lineLength = 0;
	}
	
	//method
	public void addToLine(int x, int y){
		if(this.lineLength == 0){
			this.linePoint = new vec2[1];
			this.linePoint[0] = new vec2(x,y);
		}
		else{
			vec2[] tmp = new vec2[this.lineLength];
			for(int i=0; i<lineLength; i++)
				tmp[i] = this.linePoint[i];
			
			linePoint = new vec2[this.lineLength+1];
			
			for(int i=0; i<lineLength; i++)
				this.linePoint[i] = tmp[i];
			
			this.linePoint[lineLength] = new vec2(x,y);
		}
		this.lineLength++;
	}
	
	public boolean compare(LineBuilder lineB){
		if(this.lineLength != lineB.lineLength){
			return false;
		}
		else{
			boolean[] comp = new boolean[lineLength];
			for(int i=0; i<lineLength; i++){   //initial boolean
				comp[i] = false;
			}
			
			for(int i=0; i<lineLength; i++){
				for(int j=0; j<lineLength; j++){
					int thisX = this.linePoint[i].getX();
					int thisY = this.linePoint[i].getY();
					int thatX = lineB.linePoint[j].getX();
					int thatY = lineB.linePoint[j].getY();
					if((thisX == thatX) && (thisY == thatY)){
						comp[i] = true;
					}
				}
			}
			
			for(int i=0; i<lineLength; i++){
				if(comp[i] == false)
					return false;
			}
			return true;
		}
	}
}
