package gameUtil;

import math.vec2;

public class StoneShape {
	//data member
	public vec2[] eachStonePosition;
	public int searchingType;
	
	//method
	public void addToVec(int x, int y){
		if(eachStonePosition == null){
			eachStonePosition = new vec2[1];
			eachStonePosition[0] = new vec2(x,y);
		}
		else{
			int len = eachStonePosition.length;
			vec2[] tmp = eachStonePosition;
			
			eachStonePosition = new vec2[len+1];
			
			for(int i=0; i<len; i++){
				eachStonePosition[i] = tmp[i];
			}
			
			eachStonePosition[len] = new vec2(x,y);		
		}
	}
}
