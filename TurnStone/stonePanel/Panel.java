package stonePanel;

import math.vec2;
//the class for remembering the position and each position id of the stonePanel
public class Panel {
	//data member 
	private int[][] idOnThisGrid;
	private vec2[][] eachGridPosition;

	//constructor
	
	//method	
	public void constructPositionPanel(int col, int row) {  //to build PositionPanel
		eachGridPosition = new vec2[col][row];
		for(int i=0; i<col; i++)
			for(int j=0; j<row; j++)
				eachGridPosition[i][j] = new vec2(0,0);
	}
	
	public void setEachGridPosition(int i, int j, int x, int y){
		eachGridPosition[i][j] = new vec2(x,y);
	}
	
	public vec2 getEachGridPosition(int i, int j){
		return new vec2(eachGridPosition[i][j]);
	}
	
	public vec2 getEachGridPosition(int id){
		for(int i=0; i<idOnThisGrid.length; i++){
			for(int j=0; j<idOnThisGrid[i].length; j++)
				if(idOnThisGrid[i][j] == id)
					return new vec2(eachGridPosition[i][j]);
		}
		return null;
	}
	
	public void constructGridID(int col, int row){  //to build GridID
		idOnThisGrid = new int[col][row];
	}
	
	public void setGridID(int i, int j, int value){
		idOnThisGrid[i][j] = value;
	}
	
	public int getGridID(int i, int j){
		return idOnThisGrid[i][j];
	}
	
	public int[][] getAllGridID(){
		return idOnThisGrid;
	}
}
