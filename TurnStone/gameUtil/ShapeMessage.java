package gameUtil;

import math.vec2;
//support ComboMessage and CalculateCombo classes
public class ShapeMessage {
	//data member
	public int combo;
	public int eliminateNum;
	public int stoneType;
	public int totalNum;
	public int enhanceNum = 0;
	public vec2[] eliminatePosition;
	public vec2[] remainPosition;
	
	//constructor
	public ShapeMessage(){
		combo = 0;
		eliminateNum = 0;
	}
}
