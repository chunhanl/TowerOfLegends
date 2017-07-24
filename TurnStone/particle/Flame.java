package particle;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class Flame extends JLabel{
	//data member
	private int[][] heatMap;
	private int width;
	private int height;
	private int flameSource;
	//constructor
	
	public Flame(int width, int height, int flameSource){
		this.width = width;
		this.height = height;
		this.flameSource = flameSource;
		this.heatMap = new int[height][width];
		
		BuildFlame();
	}
	
	//getter and setter
	
	//method
	public void BuildFlame(){
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++)
				heatMap[i][j] = 0;
		
		int interval = width / flameSource;
		
		for(int i=0; i<width; i++){
			if((i+1) % interval == 0){
				heatMap[height-1][i] = 99;
			}
		}
	}
	//Thread method
}
