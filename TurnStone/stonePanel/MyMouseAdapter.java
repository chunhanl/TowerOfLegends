package stonePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import math.vec2;

public class MyMouseAdapter extends MouseAdapter{
	//data member
	private Stone listenLabel;
	private TurningStonePanel mypanelREF;
	private int col = 6;
	private int row = 5;
	//constructor
	public MyMouseAdapter(Stone s, TurningStonePanel panel){  //for stones on panel
		listenLabel = s;
		mypanelREF = panel;
	}
	
	public MyMouseAdapter(){  //for blood bar change display
		
	}
	
	//method
	public void setToPosition(){
		listenLabel.setPositionAndLocation(listenLabel.getPosition()); 
	}
	
	public void mousePressed(MouseEvent f){   //first meet of mouse event
		if(TurningStonePanel.firstChangeOccur == false || PlayerBloodBar.superLongTime == true){           //make use can only select one
			TurningStonePanel.dx=f.getX();
			TurningStonePanel.dy=f.getY();
			listenLabel.alignStoneCenterTo(new vec2(f.getX(), f.getY()));
			/*
       		int findi=0;
       		int findj=0;
       		for(int i=0; i<col; i++)
       			for(int j=0; j<row; j++){
       				if(mypanelREF.panel.getGridID(i, j) == listenLabel.getid()){
       					findi = i;
       					findj = j;
       				}
       			}
       		*/
			mypanelREF.setToFirstZOrder(listenLabel.getid());   //getID
			TurningStonePanel.mouseAdapter = this;
		}
	}
	
	public void mouseClicked(MouseEvent f){  //for blood bar
		
	}
	
	public void mouseReleased(MouseEvent f){   //when user release the mouse then it should be set to a right position
		listenLabel.setPositionAndLocation(listenLabel.getPosition()); 

		
		if(TurningStonePanel.firstChangeOccur == true && PlayerBloodBar.superLongTime == false){
			TurningStonePanel.released = true;   //make user can't drag again
			PlayerBloodBar.timerOn = false;
			PlayerBloodBar.timerStart = false;
			
			PlayerBloodBar.timerOn = false;
			PlayerBloodBar.cancelTimer();
		}
	}
}
