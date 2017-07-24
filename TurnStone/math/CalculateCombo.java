package math;

import java.util.ArrayList;

import stonePanel.Panel;
import stonePanel.TurningStonePanel;
import control.StateControlManager;
import gameUtil.ComboMessage;
import gameUtil.LineBuilder;
import gameUtil.ShapeMessage;
import gameUtil.StoneShape;

public class CalculateCombo {
	//data member
	public static boolean[][] inGroup;
	
	static{
		inGroup = new boolean[6][5];
		
		for(int i=0; i<6; i++){
			for(int j=0; j<5; j++){
				inGroup[i][j] = false;
			}
		}
	}
	
	//getter and setter
	public static void resetInGroup(){
		for(int i=0; i<6; i++){
			for(int j=0; j<5; j++){
				inGroup[i][j] = false;
			}
		}
	}
	
	//method
	public static ComboMessage calculateCombo(StateControlManager manager){
		ComboMessage message = new ComboMessage();
		
		//-----divide stone into group and resolve the group that has more than three stones
		StoneShape[] shape;
		resetInGroup();
		shape = findEachShape(manager.stonePanel);

		int moreThanTwoStone = 0;
		
		for(int i=0; i<shape.length; i++)
			if(shape[i].eachStonePosition.length > 2)
				moreThanTwoStone++;
		
		int[] moreThanTwoStoneIndex = new int[moreThanTwoStone];
		
		for(int i=0, j=0; i<shape.length; i++){
			if(shape[i].eachStonePosition.length>2){
				moreThanTwoStoneIndex[j++] = i;
			}
		}
		
		StoneShape[] moreThanTwoShape = new StoneShape[moreThanTwoStone];
		
		for(int i=0; i<moreThanTwoStone; i++){
			moreThanTwoShape[i] = shape[moreThanTwoStoneIndex[i]];
		}
		
		//-----resolve each shape and getCombo
		
		ShapeMessage[] sMessage = new ShapeMessage[moreThanTwoStone];
		
		for(int i=0; i<moreThanTwoStone; i++){
			sMessage[i] = getThisShapeComboMessage(moreThanTwoShape[i]);
		}
		
		for(int i=0; i<moreThanTwoStone; i++){
			message.resolveShapeMesssage(sMessage[i]);
		}
		
		return message;
	}
	
	public static ShapeMessage getThisShapeComboMessage(StoneShape shape){
		int[][] shapeGeometry = new int[6][5];
		int type = shape.searchingType;
		for(int i=0; i<6; i++){           //initail shape Geometry
			for(int j=0; j<5; j++){
				shapeGeometry[i][j]=0;    //0 -> noShape 1 -> general Point 2 -> joint 3 -> Eliminated
			}
		}
		
		for(int i=0; i<shape.eachStonePosition.length; i++){     //build general Point(not find joint yet)
			int x = shape.eachStonePosition[i].getX();
			int y = shape.eachStonePosition[i].getY();
			shapeGeometry[x][y] = 1;
		}
		
		for(int i=0; i<6; i++){
			for(int j=0; j<5; j++){
				if(shapeGeometry[i][j] == 1){  //if it is general point it maybe a joint
					shapeGeometry[i][j] = checkJoint(shapeGeometry, i, j);
				}
			}
		}
		ShapeMessage sMessage = new ShapeMessage();
		if(hasNoJoint(shapeGeometry) && shape.eachStonePosition.length > 2){   //line
			sMessage.combo = 1;
			sMessage.eliminateNum = shape.eachStonePosition.length;
			sMessage.eliminatePosition = new vec2[shape.eachStonePosition.length];
			sMessage.remainPosition = null;
			sMessage.totalNum = shape.eachStonePosition.length;
			for(int i=0; i<shape.eachStonePosition.length; i++){
				int x = shape.eachStonePosition[i].getX();
				int y = shape.eachStonePosition[i].getY();
				sMessage.eliminatePosition[i] = new vec2(x,y);
			}
			sMessage.stoneType = type;
		}
		else{
			findJointLine(shapeGeometry);
			int hasEliminate = 0;
			for(int i=0; i<6; i++){
				for(int j=0; j<5; j++){
					if(shapeGeometry[i][j] == 3){
						hasEliminate++;
					}
				}
			}
			
			if(hasEliminate>0){  //this shape has eliminate
				sMessage.combo = 1;
				sMessage.eliminateNum = hasEliminate;
				sMessage.eliminatePosition = new vec2[hasEliminate];
				sMessage.stoneType = shape.searchingType;
				sMessage.totalNum = shape.eachStonePosition.length;
				sMessage.remainPosition = new vec2[sMessage.totalNum - hasEliminate];
				
				int remain = 0;
				for(int i=0; i<6; i++){
					for(int j=0; j<5; j++){
						if(shapeGeometry[i][j] == 1 || shapeGeometry[i][j] == 2)
							sMessage.remainPosition[remain++] = new vec2(i,j);
					}
				}
				
				int isDeleted = 0;
				for(int i=0; i<6; i++){
					for(int j=0; j<5; j++){
						if(shapeGeometry[i][j] == 3){
							sMessage.eliminatePosition[isDeleted++] = new vec2(i,j);
						}
					}
				}
			}
			else{  //this shape has no eliminate
				sMessage.combo = 0;
				sMessage.eliminateNum = hasEliminate;
				sMessage.eliminatePosition = null;
				sMessage.stoneType = shape.searchingType;
				sMessage.totalNum = shape.eachStonePosition.length;
				sMessage.remainPosition = new vec2[sMessage.totalNum];
				for(int i=0; i<sMessage.totalNum; i++){
					int x = shape.eachStonePosition[i].getX();
					int y = shape.eachStonePosition[i].getY();
					sMessage.remainPosition[i] = new vec2(x,y);
				}
			}
		}
		
		return sMessage;
	}
	
	public static void findJointLine(int[][] shapeGeometry){
		int jointNum = 0;
		for(int i=0; i<6; i++){
			for(int j=0; j<5; j++){
				if(shapeGeometry[i][j] == 2){
					jointNum++;
				}
			}
		}
		
		LineBuilder[][] totLine = new LineBuilder[jointNum][2];
		for(int i=0; i<jointNum; i++){
			totLine[i][0] = new LineBuilder();
			totLine[i][1] = new LineBuilder();
		}
		
		int lineID = 0;
		for(int i=0; i<6; i++){
			for(int j=0; j<5; j++){
				if(shapeGeometry[i][j] == 2){  //if this point is joint then release lineBuilder
					totLine[lineID][0].addToLine(i,j);
					totLine[lineID][1].addToLine(i,j);
					{ //discover top-down direction
						discover(totLine, lineID, 0, 0, shapeGeometry, i, j); //find top
						discover(totLine, lineID, 0, 2, shapeGeometry, i, j); //find down
					}
					{ //discove left-right direction
						discover(totLine, lineID, 1, 3, shapeGeometry, i, j); //find left
						discover(totLine, lineID, 1, 1, shapeGeometry, i, j); //find right
					}
					lineID++;
				}
			}
		}
		
		int invalidLine = 0;
		for(int i=0; i<2; i++){    //need more than three stone to eliminate
			for(int j=0; j<lineID; j++){
				if(totLine[j][i].linePoint.length < 3){
					totLine[j][i] = null;
					invalidLine++;
				}
			}
		}
		
		int validLineNum = lineID * 2 - invalidLine;
		LineBuilder[] validLine = new LineBuilder[validLineNum];
		int count = 0;
		
		for(int i=0; i<2; i++){
			for(int j=0; j<lineID; j++){
				if(totLine[j][i] != null){
					validLine[count++] = totLine[j][i];
				}
			}
		}
		
		if(validLine == null)//to ensure that the following code should have at least one validLine
			return;
		
		for(int i=0; i<validLine.length; i++){
			for(int j=i-1; j>=0; j--){
				//make the same line to null(make the same line appear once only)
				if(validLine[j] != null && validLine[i].compare(validLine[j])){  
					validLine[i] = null;
					break;
				}
			}
		}
		
		int canEliminateNum = 0;
		for(int i=0; i<validLine.length; i++){
			if(validLine[i] != null)
				canEliminateNum++;
		}
		
		LineBuilder[] canEliminateLine = new LineBuilder[canEliminateNum];
		int eCount = 0;
		for(int i=0; i<validLine.length; i++){
			if(validLine[i] != null)
				canEliminateLine[eCount++] = validLine[i];
		}
		
		for(int i=0; i<canEliminateLine.length; i++){
			for(int j=0; j<canEliminateLine[i].linePoint.length; j++){
				int x = canEliminateLine[i].linePoint[j].getX();				
				int y = canEliminateLine[i].linePoint[j].getY();
				shapeGeometry[x][y] = 3;
			}
		}
	}
	
	public static void showShapeGeometry(int[][] shapeMap){
		for(int i=0; i<5; i++){
			for(int j=0; j<6; j++){
				System.out.print(shapeMap[j][i] + " ");
			}
			System.out.printf("%n");
		}
		System.out.println(" ");
	}
	
	public static void discover(LineBuilder[][] lbuilder, int lineID, int id, int direction, int[][] shapeGeometry, int x, int y){
		if(direction == 0){ //find top
			if(((y+1) <= 4) && shapeGeometry[x][y+1] != 0){
				lbuilder[lineID][id].addToLine(x, y+1);
				if(y+1<4){
					discover(lbuilder, lineID, id, direction, shapeGeometry, x, y+1);
				}
			}
		}
		else if(direction == 1){ //find right
			if(((x+1) <= 5) && shapeGeometry[x+1][y] != 0){
				lbuilder[lineID][id].addToLine(x+1,y);
				if(x+1 < 5){
					discover(lbuilder, lineID, id, direction, shapeGeometry, x+1, y);
				}
			}
		}
		else if(direction == 2){ //find down
			if(((y-1) >= 0) && shapeGeometry[x][y-1] != 0){
				lbuilder[lineID][id].addToLine(x, y-1);
				if(y-1 > 0){
					discover(lbuilder, lineID, id, direction, shapeGeometry, x, y-1);
				}
			}
		}
		else if(direction == 3){  //find left
			if(((x-1) >= 0) && shapeGeometry[x-1][y] != 0){
				lbuilder[lineID][id].addToLine(x-1,y);
				if(x-1 > 0){
					discover(lbuilder, lineID, id, direction, shapeGeometry, x-1, y);
				}
			}
		}
		else{
			System.out.println("error! none this direction");
			System.exit(1);
		}
	}
	
	public static boolean hasNoJoint(int[][] k){
		for(int i=0; i<6; i++){
			for(int j=0; j<5; j++){
				if(k[i][j] == 2){
					return false;
				}
			}
		}	
		return true;
	}
	
	public static int checkJoint(int[][] shapeGeometry, int x, int y){
		boolean hasTop = false;
		boolean hasDown = false;
		boolean hasRight = false;
		boolean hasLeft = false;
		
		if(y+1<=4){
			if(shapeGeometry[x][y+1] != 0){
				hasTop = true;
			}
		}
		
		if(y-1>=0){
			if(shapeGeometry[x][y-1] != 0){
				hasDown = true;
			}
		}
		
		if(x+1<=5){
			if(shapeGeometry[x+1][y] != 0){
				hasRight = true;
			}
		}
		
		if(x-1>=0){
			if(shapeGeometry[x-1][y] != 0){
				hasLeft = true;
			}
		}
		
		if((hasLeft || hasRight) && (hasTop || hasDown)){
			return 2;
		}
		return 1;	
	}
	
	public static StoneShape[] findEachShape(TurningStonePanel sPanel){
		int[][] id = sPanel.getPanel().getAllGridID();
		ArrayList<StoneShape> shapes = new ArrayList<StoneShape>();
		int len=0;
		for(int x=0; x<6; x++){
			for(int y=0; y<5; y++){
				if(inGroup[x][y] == false){
					StoneShape stoneShape= new StoneShape();
					int type = sPanel.getStone(x,y).getType();
					stoneShape.searchingType = type;
					inGroup[x][y] = true;
					stoneShape.addToVec(x,y);
					goFindNearBy(stoneShape, type, x, y, sPanel);	
					shapes.add(stoneShape);
					len++;
				}
			}
		}
		StoneShape[] stmp = new StoneShape[len];
		for(int i=0; i<len; i++)
			stmp[i] = shapes.get(i);
		
		return stmp;
	}
	
	public static void goFindNearBy(StoneShape stoneShape, int type, int startx, int starty, TurningStonePanel sPanel){
		if(starty+1 <= 4){  //to Down
			findUp(stoneShape, type, startx, starty+1, sPanel);
		}
		if(startx+1 <= 5){  //to Right
			findRight(stoneShape, type, startx+1, starty, sPanel);
		}
		if(starty-1 >= 0){  //to Up(top and down is inversed because of the index of array)
			findDown(stoneShape, type, startx, starty-1, sPanel);
		}
		if(startx-1 >=0){   //to Left
			findLeft(stoneShape, type, startx-1, starty, sPanel);
		}
	}
	
	public static void findUp(StoneShape stoneShape, int type, int startx, int starty, TurningStonePanel sPanel){
		if(type == sPanel.getStone(startx,starty).getType() && inGroup[startx][starty] == false){
			inGroup[startx][starty] = true;
			stoneShape.addToVec(startx, starty);
			if(startx+1 <= 5){ 
				findRight(stoneShape, type, startx+1, starty, sPanel);
			}
			if(starty+1 <= 4){  
				findUp(stoneShape, type, startx, starty+1, sPanel);
			}
			if(startx-1 >=0){   
				findLeft(stoneShape, type, startx-1, starty, sPanel);
			}
		}
	}
	
	public static void findLeft(StoneShape stoneShape, int type, int startx, int starty, TurningStonePanel sPanel){
		if(type == sPanel.getStone(startx,starty).getType() && inGroup[startx][starty] == false){
			inGroup[startx][starty] = true;
			stoneShape.addToVec(startx, starty);
			if(starty+1 <= 4){  
				findUp(stoneShape, type, startx, starty+1, sPanel);
			}
			if(startx-1 >= 0){  
				findLeft(stoneShape, type, startx-1, starty, sPanel);
			}
			if(starty-1 >= 0){  
				findDown(stoneShape, type, startx, starty-1, sPanel);
			}
		}
	}
	
	public static void findRight(StoneShape stoneShape, int type, int startx, int starty, TurningStonePanel sPanel){
		if(type == sPanel.getStone(startx,starty).getType() && inGroup[startx][starty] == false){
			inGroup[startx][starty] = true;
			stoneShape.addToVec(startx, starty);	
			if(starty+1 <= 4){  
				findUp(stoneShape, type, startx, starty+1, sPanel);
			}
			if(starty-1 >= 0){  
				findDown(stoneShape, type, startx, starty-1, sPanel);
			}
			if(startx+1 <= 5){  
				findRight(stoneShape, type, startx+1, starty, sPanel);
			}
		}
	}
	
	public static void findDown(StoneShape stoneShape, int type, int startx, int starty, TurningStonePanel sPanel){
		if(type == sPanel.getStone(startx,starty).getType() && inGroup[startx][starty] == false){
			inGroup[startx][starty] = true;
			stoneShape.addToVec(startx, starty);	
			if(starty-1 >= 0){  
				findDown(stoneShape, type, startx, starty-1, sPanel);
			}
			if(startx+1 <= 5){ 
				findRight(stoneShape, type, startx+1, starty, sPanel);
			}
			if(startx-1 >=0){  
					findLeft(stoneShape, type, startx-1, starty, sPanel);
			}
		}
	}
}
