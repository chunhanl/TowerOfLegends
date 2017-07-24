package stonePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;

import InterFace.Battle;
import math.CalcPair;
import math.vec2;

public class MyMouseMotionAdapter extends MouseMotionAdapter{
	//data member
	private Stone listenLabel;
	private TurningStonePanel operatedPanel;
	private float isChangeTolerance = 0.50f;  //the tolerance to decide to change stone or not
	private int col = 6;
	private int row = 5;
	
	//constructor
	public MyMouseMotionAdapter(Stone lab, TurningStonePanel p){
		this.listenLabel = lab;
		this.operatedPanel = p;
	}
	
	//method
	public void mouseDragged(MouseEvent f) 
	{
		if(TurningStonePanel.released == false){   //release then we can't drag again
			int ddx=f.getX()-TurningStonePanel.dx;
       		int ddy=f.getY()-TurningStonePanel.dy;
       		//old method just make label follow cursor but hard to align center to center
       		//listenLabel.setLocation(listenLabel.getX()+ddx, listenLabel.getY()+ddy);  //the moving method invent by Wakamamama
       		//use new math technique then we can easy make our stone turning experience better even more(2014/6/1)
       		listenLabel.alignStoneCenterTo(new vec2(f.getX(), f.getY()));
       		
       		CalcPair cp = this.calcChangeDirection_Vec(listenLabel.getid());   //calculate which direction is the user may want to change
       		int findi=0;
       		int findj=0;
       		for(int i=0; i<col; i++)
       			for(int j=0; j<row; j++){
       				if(operatedPanel.panel.getGridID(i, j) == cp.id){    //find the position on the panel of stone which user want to change
       					findi = i;
       					findj = j;
       				}
       			}
       		
       		boolean distComp;
       		if(cp.straight == 0){
       			distComp = cp.distance < listenLabel.getPosition().sub(new vec2(operatedPanel.getStone(findi, findj).getPosition())).getLength() * isChangeTolerance;
       		}
       		else{
       			distComp = cp.distance < listenLabel.getPosition().sub(new vec2(operatedPanel.getStone(findi, findj).getPosition())).getLength() * isChangeTolerance * 0.5;
       		}
       		vec2 currentL = new vec2(listenLabel.getLocation().x, listenLabel.getLocation().y);
       		vec2 targetP = new vec2(operatedPanel.getStone(findi, findj).getPosition());
       		vec2 currentP = new vec2(listenLabel.getPosition());
       		boolean vecComp = targetP.sub(currentL).dot(targetP.sub(currentP)) < 0;
       		if(distComp == true || vecComp == true){
       			changeStone(findi, findj);
       		}
       	}
	}
	
	public void changeStone(int findii,int findjj){
		
		//make sound
   		Battle.soundPlayer.addSound("turningStone.wav");
   		//
   		int findi=0;
   		int findj=0;
   		for(int i=0; i<col; i++)
   			for(int j=0; j<row; j++){
   				if(operatedPanel.panel.getGridID(i, j) == listenLabel.getid()){
   					findi = i;
   					findj = j;
   				}
   			}
   		
   		operatedPanel.getStone(findii, findjj).setPositionAndLocation(operatedPanel.panel.getEachGridPosition(findi, findj));
   		operatedPanel.panel.setGridID(findi,findj,operatedPanel.getStone(findii, findjj).getid());
   		
   		listenLabel.setPosition(operatedPanel.panel.getEachGridPosition(findii, findjj));
   		operatedPanel.panel.setGridID(findii,findjj,listenLabel.getid());
   
   		TurningStonePanel.firstChangeOccur = true;   //make User can't select again
	}

	private CalcPair calcChangeDirection(int id) {  //calc the direction that player may want to make a exchange(distance approach) 斜轉難以實現
		int findi = 0;
		int findj = 0;
		boolean find = false;
		
		for(int i=0; i<col; i++){
			for(int j=0; j<row; j++){
				if(operatedPanel.panel.getGridID(i, j) == id){
					findi = i;
					findj = j;
					find = true;
					break;
				}
			}
			if(find) 
				break;
		}

		
		//straight line
		boolean validBol[] = new boolean[4];
		if((findi + findj * col) - col <0)
			validBol[0] = false;
		else 
			validBol[0] = true;
		
		if(((findi+1) % 6) > findi%6 && (findi+1 + (findj) * col) < col * row)
			validBol[1] = true;
		else 
			validBol[1] = false;
		
		if((findi + findj * col) + col >= col * row)
			validBol[2] = false;
		else 
			validBol[2] = true;
		
		if((findi-1) >=0 && ((findi-1) % 6) < findi%6 && (findi -1 + (findj) * col)>=0)
			validBol[3] = true;
		else{
			validBol[3] = false;
		}

		int direction[] = {0,1,2,3};
		int validNum = 0;
		for(int i=0; i<validBol.length; i++)
			if(validBol[i] == true)
				validNum++;
		
		int validDirection[] = new int[validNum];
		
		for(int i=0, j=0; i<validBol.length; i++){
			if(validBol[i] == true){
				validDirection[j++] = direction[i];
			}
		}
		
		CalcPair distance[] = new CalcPair[validNum];
		for(int i=0; i<validNum; i++)
			distance[i] = new CalcPair();
		
		for(int i=0; i<validNum; i++){
			int id1 = 0;
			switch(validDirection[i]){
				case 0:
					id1 = operatedPanel.panel.getGridID(findi, findj-1);
					break;
				case 1:
					id1 = operatedPanel.panel.getGridID(findi+1, findj);
					break;
				case 2:
					id1 = operatedPanel.panel.getGridID(findi, findj+1);
					break;
				case 3:
					id1 = operatedPanel.panel.getGridID(findi-1, findj);
					break;
			}
			distance[i].id = id1;
		}
		
		for(int i=0; i< validNum; i++){
			int foundi=0;
			int foundj=0;
			for(int j=0; j<col; j++)
				for(int k=0; k<row; k++)
					if(operatedPanel.panel.getGridID(j, k) == distance[i].id){
						foundi = j;
						foundj = k;
					}
			vec2 tmp = new vec2(listenLabel.getX(), listenLabel.getY());
			distance[i].distance = tmp.sub(new vec2(operatedPanel.getStone(foundi, foundj).getPosition())).getLength();
		}
		
		int smallest = 0;
		for(int i=0; i<validNum; i++){
			if(distance[smallest].distance > distance[i].distance){
				smallest = i;
			}
		}
		/*  for testing ( not used anymore
		System.out.println(validNum);
		for(int i=0; i<validDirection.length; i++){
			if(validDirection[i] ==0)
				System.out.print("TOP ");
			if(validDirection[i] ==1)
				System.out.print("RIT ");
			if(validDirection[i] ==2)
				System.out.print("DOW ");
			if(validDirection[i] ==3)
				System.out.print("LEF ");
		}
		System.out.println("");
		*/
		return distance[smallest];
	}	
	
	private CalcPair calcChangeDirection_Vec(int id) {  //calc the direction that player may want to make a exchange(vector approach)
		int findi = 0;
		int findj = 0;
		boolean find = false;
		
		for(int i=0; i<col; i++){
			for(int j=0; j<row; j++){
				if(operatedPanel.panel.getGridID(i, j) == id){
					findi = i;
					findj = j;
					find = true;
					break;
				}
			}
			if(find) 
				break;
		}

		
		//straight line
		boolean validBol[] = new boolean[8];
		if((findi + findj * col) - col <0) //top
			validBol[0] = false;
		else 
			validBol[0] = true;
		
		if((findi + 1) > 5 || (findj -1)<0)  //top right
			validBol[1] = false;
		else 
			validBol[1] = true;
		
		if(((findi+1) % 6) > findi%6 && (findi+1 + (findj) * col) < col * row) //right
			validBol[2] = true;
		else 
			validBol[2] = false;
		
		if((findi +1 )>5 || (findj + 1)>4) //down right
			validBol[3] = false;
		else 
			validBol[3] = true;
		
		if((findi + findj * col) + col >= col * row)  //down
			validBol[4] = false;
		else 
			validBol[4] = true;
		
		if((findi-1)<0 || (findj+1)>4)  //down left
			validBol[5] = false;
		else 
			validBol[5] = true;
		
		if((findi-1) >=0 && ((findi-1) % 6) < findi%6 && (findi -1 + (findj) * col)>=0) //left
			validBol[6] = true;
		else
			validBol[6] = false;
		
		if((findi -1 )<0 || (findj -1) < 0) //top left
			validBol[7] = false;
		else 
			validBol[7] = true;
		
		int direction[] = {0,1,2,3,4,5,6,7};
		int validNum = 0;
		for(int i=0; i<validBol.length; i++)
			if(validBol[i] == true)
				validNum++;
		
		int validDirection[] = new int[validNum];
		
		for(int i=0, j=0; i<validBol.length; i++){
			if(validBol[i] == true){
				validDirection[j++] = direction[i];
			}
		}
		
		CalcPair distance[] = new CalcPair[validNum];
		for(int i=0; i<validNum; i++)
			distance[i] = new CalcPair();
		
		for(int i=0; i<validNum; i++){
			int id1 = 0;
			switch(validDirection[i]){
				case 0:
					id1 = operatedPanel.panel.getGridID(findi, findj-1);
					break;
				case 1:
					id1 = operatedPanel.panel.getGridID(findi+1, findj-1);
					break;
				case 2:
					id1 = operatedPanel.panel.getGridID(findi+1, findj);
					break;
				case 3:
					id1 = operatedPanel.panel.getGridID(findi+1, findj+1);
					break;
				case 4:
					id1 = operatedPanel.panel.getGridID(findi, findj+1);
					break;
				case 5:
					id1 = operatedPanel.panel.getGridID(findi-1, findj+1);
					break;
				case 6:
					id1 = operatedPanel.panel.getGridID(findi-1, findj);
					break;
				case 7:
					id1 = operatedPanel.panel.getGridID(findi-1, findj-1);
					break;
					
			}
			distance[i].id = id1;
		}
		
		for(int i=0; i< validNum; i++){
			int foundi=0;
			int foundj=0;
			for(int j=0; j<col; j++)
				for(int k=0; k<row; k++)
					if(operatedPanel.panel.getGridID(j, k) == distance[i].id){
						foundi = j;
						foundj = k;
					}
			vec2 tmp = new vec2(listenLabel.getX(), listenLabel.getY());
			distance[i].distance = tmp.sub(new vec2(operatedPanel.getStone(foundi, foundj).getPosition())).getLength();
		}

		int smallest = 0;
		for(int i=0; i<validNum; i++){
			if(i%2 == 0){
				if(smallest%2 == 0){
					if(distance[smallest].distance > distance[i].distance){
						smallest = i;
					}
				}
				else{
					if((distance[smallest].distance/Math.sqrt(2.0)) > distance[i].distance){
						smallest = i;
					}
				}
			}
			else{
				if(smallest%2 == 0){
					if(distance[smallest].distance > (distance[i].distance/Math.sqrt(2.0))){
						smallest = i;
					}
				}
				else{
					if((distance[smallest].distance/Math.sqrt(2.0)) > (distance[i].distance/Math.sqrt(2.0))){
						smallest = i;
					}
				}
			}
		}
		/*  for testing ( not used anymore
		System.out.println(validNum);
		for(int i=0; i<validDirection.length; i++){
			if(validDirection[i] ==0)
				System.out.print("TOP ");
			if(validDirection[i] ==1)
				System.out.print("TRs "); //top right side
			if(validDirection[i] ==2)
				System.out.print("RIT ");
			if(validDirection[i] ==3)
				System.out.print("DRs "); //down right side
			if(validDirection[i] ==4)
				System.out.print("DOW ");
			if(validDirection[i] ==5)
				System.out.print("DLs "); //down left side
			if(validDirection[i] ==6)
				System.out.print("LEF ");
			if(validDirection[i] ==7)
				System.out.print("TLs "); //top left side
		}
		System.out.println("");
		*/
		distance[smallest].straight = smallest%2;
		return distance[smallest];
	}	
}


