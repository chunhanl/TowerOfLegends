package FixedPanel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import math.vec2;

public class SwitchableLabel extends JLabel{
	//data member
	private ImageIcon unpressedImage;
	private ImageIcon pressedImage;
	
	private vec2 position; //generally position mean the position of top-left

	//constructor
	public SwitchableLabel(ImageIcon imag,ImageIcon pimag, vec2 pos, vec2 size){
		super(imag);

		this.position = pos;
		this.setLocation(pos.getX(), pos.getY());
		this.setSize(size.getX(), size.getY());
		unpressedImage=imag;
		pressedImage=pimag;
		}
	
	
	//getter and setter

	public ImageIcon getImageIcon(){
		return unpressedImage;
	}
	public ImageIcon getPressedImageIcon(){
		return pressedImage;
	}
	
	//method

}
