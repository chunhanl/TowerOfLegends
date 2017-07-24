package stonePanel;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComboPanel extends JPanel{
	//data member
	private JLabel tenNum;
	private JLabel oneNum;
	public int number;
	//constructor
	public ComboPanel(){
		super(null);
		this.setLayout(null);
		tenNum = new JLabel(ImageLoader.getDarkNumberImage(0));
		oneNum = new JLabel(ImageLoader.getDarkNumberImage(0));
		
		this.add(tenNum);
		this.add(oneNum);
		this.setLocation(250,580);
		this.setSize(220,90);
		
		tenNum.setLocation(0,0);
		tenNum.setSize(110,90);
		
		oneNum.setLocation(110, 0);
		oneNum.setSize(110, 90);
		
		tenNum.setVisible(false);
		oneNum.setVisible(false);
		
		this.setOpaque(false);
		this.setVisible(false);
	}
	//getter and setter
	
	//method
	public void paintComponent(Graphics g){
		if(TurningStonePanel.canPaint){
			super.paintComponent(g);
		}
	}
	public void setNumber(int num){
		int decimal = (num%100)/10;
		int single = num%10;
		this.number = num;
		if(num < 10){
			oneNum.setLocation(70,0);
			tenNum.setVisible(false);
			oneNum.setIcon(ImageLoader.getDarkNumberImage(single));
		}
		else{
			oneNum.setLocation(110,0);
			tenNum.setVisible(true);
			tenNum.setIcon(ImageLoader.getBrightNumberImage(decimal));
			oneNum.setIcon(ImageLoader.getBrightNumberImage(single));
		}
		TurningStonePanel.tspREF.setComponentZOrder(this, 0);
	}
	
	public void setCanSee(boolean b){
		this.setVisible(b);
		
		if(b == true && this.number > 9)
			tenNum.setVisible(b);
		oneNum.setVisible(b);
	}
}
