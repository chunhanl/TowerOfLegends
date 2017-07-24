package MainPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import math.vec2;

public class Tower extends JLabel{
	//data member
	private ImageIcon towerImage;
	public  ImageIcon pressedImage;
	private vec2 position; //generally position mean the position of top-left

	//constructor
	public Tower(ImageIcon imag,ImageIcon pimag, vec2 pos, vec2 size){

		super(imag);
		this.position = pos;
		this.setLocation(pos.getX(), pos.getY());
		this.setSize(size.getX(), size.getY());
		towerImage=imag;
		pressedImage=pimag;
	}
	
	
	//getter and setter
	public vec2 getPosition(){
		return new vec2(position);
	}
	
	public void setPosition(vec2 p){
		this.position = p;
	}
	
	public void setPositionAndLocation(vec2 p){
		this.setPosition(p);
		this.setLocation(p.getX(), p.getY());
	}
	

	public ImageIcon getImageIcon(){
		return towerImage;
	}
	public ImageIcon getPressedImageIcon(){
		return pressedImage;
	}
	
	//method

}
