package gameUtil;

import java.util.ArrayList;

import javax.swing.JTextArea;

import stonePanel.Stone;
import stonePanel.TurningStonePanel;
import math.vec2;

public class ComboMessage {
	//data member
	public int combo;
	public int[] eachTypeEliminate;
	public boolean[] eachTypeIsWholeorSingle; //每種屬性是全體還是單體攻擊 true = 全體
	public vec2[] eliminatePosition;
	public int totalEliminateNum;
	public ArrayList<ShapeMessage> sMessages;
	
	//constructor
	public ComboMessage(){
		this.sMessages = new ArrayList<ShapeMessage>();
		this.totalEliminateNum = 0;
		this.eliminatePosition = null;
		this.combo = 0;
		this.eachTypeEliminate = new int[6];
		
		for(int i=0; i<6 ;i++)
			this.eachTypeEliminate[i]=0;
		
		this.eachTypeIsWholeorSingle = new boolean[6];
		
		for(int i=0; i<6; i++)
			this.eachTypeIsWholeorSingle[i] = false;
	}
	//method
	public void resolveShapeMesssage(ShapeMessage message){
		this.combo += message.combo;
		
		if(message.eliminatePosition != null){
			for(int i=0; i<message.eliminatePosition.length; i++){
				int x = message.eliminatePosition[i].getX();
				int y = message.eliminatePosition[i].getY();
				if(TurningStonePanel.tspREF.getStone(x, y).isEnhance() == true){
					message.enhanceNum++;
				}
			}
		}
		
		if(message.combo != 0){
			this.sMessages.add(message);
		}
		
		this.eachTypeEliminate[message.stoneType] += message.eliminateNum;
		
		if((eachTypeIsWholeorSingle[message.stoneType] == true) || (message.eliminateNum >= 5)){
			eachTypeIsWholeorSingle[message.stoneType] = true;
		}
		
		if(combo != 0){  //mean has eliminate
			if(this.eliminatePosition == null){  //mean firstTime use
				this.eliminatePosition = new vec2[message.eliminateNum];
				for(int i=0; i<message.eliminateNum; i++)
					this.eliminatePosition[i] = message.eliminatePosition[i];
				this.totalEliminateNum += message.eliminateNum;
			}
			else{
				vec2[] tmp = new vec2[totalEliminateNum];
				for(int i=0; i<this.totalEliminateNum; i++){
					tmp[i] = this.eliminatePosition[i];
				}
				this.eliminatePosition = new vec2[this.totalEliminateNum + message.eliminateNum];
				
				for(int i=0; i<this.totalEliminateNum; i++){
					this.eliminatePosition[i] = tmp[i];
				}
				
				for(int i=totalEliminateNum; i<totalEliminateNum + message.eliminateNum; i++){
					this.eliminatePosition[i] = message.eliminatePosition[i-totalEliminateNum];
				}
				this.totalEliminateNum += message.eliminateNum;				
			}
		}
	}
	/*
	public int combo;
	public int eliminateNum;
	public int stoneType;
	public int totalNum;
	public vec2[] eliminatePosition;
	public vec2[] remainPosition;
	*/
	
	public void showComboMessage(){
		System.out.println("Combo is: " + this.combo);
		for(int i=0; i<6; i++){
			System.out.println("Type: " + Stone.getStringType(i) + "  Eliminate num: " +this.eachTypeEliminate[i] + " isWhole or single? " + eachTypeIsWholeorSingle[i]);
		}
	}
	
}
