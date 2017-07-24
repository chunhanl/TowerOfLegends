package stonePanel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import util.StoneTurningSound;
import math.StoneRandomize;
import math.vec2;
//class for every stone
public class Stone extends JLabel{
	//data member
	private ImageIcon stoneImage;
	private int type;
	private vec2 position; //generally position mean the position of top-left coner
	private vec2 positionCenter; //the center of the Stone
	private int stoneSizeX;
	private int stoneSizeY;
	private int ID;
	private boolean enhanced;
	private boolean isEliminate;
	
	//some constant
	public static int DARK = 0;
	public static int BRIGHT = 1;
	public static int FIRE = 2;
	public static int WATER = 3;
	public static int WOOD = 4;
	public static int HEART = 5;

	//constructor
	public Stone(ImageIcon imag, int type, vec2 pos, vec2 size, int id){
		super(imag);
		this.enhanced = false;
		this.isEliminate = false;
		this.type = type;
		this.position = pos;
		this.setLocation(pos.getX(), pos.getY());
		this.setSize(size.getX(), size.getY());
		this.stoneSizeX = size.getX();
		this.stoneSizeY = size.getY();
		this.positionCenter = new vec2(pos.getX() + size.getX()/2 , pos.getY() + size.getY()/2);
		this.ID = id;
		this.stoneImage = imag;
	}
	
	
	//getter and setter
	public vec2 getPosition(){
		return new vec2(position);
	}
	
	public void setPosition(vec2 p){
		this.position = p;
		reCalcCenter();
	}
	
	public void setPositionAndLocation(vec2 p){   //position is the 2D vector that remember the position when we don't use it
		this.setPosition(p);                      //location is the 2D vector that represent the position the stone is right now
		this.setLocation(p.getX(), p.getY());
		reCalcCenter();
	}
	
	public vec2 getPositionCenter(){
		return new vec2(positionCenter);
	}
	
	private void reCalcCenter(){
		this.positionCenter = new vec2(position.getX() + stoneSizeX/2 , position.getY() + stoneSizeY/2);
	}
	
	public int getid(){
		return ID;
	}
	
	public void setid(int id){
		this.ID = id;
	}
	
	public ImageIcon getImageIcon(){
		return stoneImage;
	}
	
	public void changeType(int type){
		this.type = type;
		this.stoneImage = ImageLoader.getStoneImage(type);
	}
	
	public void setEliminate(boolean b){
		this.isEliminate = b;
	}
	
	public boolean isEliminateBool(){
		return this.isEliminate;
	}
	
	public int getType(){
		return this.type;
	}
	
	public static String getStringType(int choose){
    	if(choose == 0){
    		return "dark";
    	}
    	else if(choose == 1){
    		return "bright";
    	}
    	else if(choose == 2){
    		return "fire";
    	}
    	else if(choose == 3){
    		return "water";
    	}
    	else if(choose == 4){
    		return "wood";
    	}
    	else{
    		return "heart";
    	}
	}
	
	public void setEnhance(boolean b){
		this.enhanced = b;
		if(enhanced == true){
			this.stoneImage = ImageLoader.getStoneImage(this.type, true);
			this.setIcon(this.stoneImage);
		}
	}
	
	public boolean isEnhance(){
		return this.enhanced;
	}
	
	
	
	//method
	public void randomItSelf(){
		this.type = StoneRandomize.randStoneType();
		this.stoneImage = ImageLoader.getStoneImage(this.type);
		this.setIcon(this.stoneImage);
	}
	
	public void setTypeItSelf(int type){
		this.type = type;
		this.stoneImage = ImageLoader.getStoneImage(this.type);
		this.setIcon(this.stoneImage);
		this.enhanced = false;
	}
	
	public void setTypeItSelf(int type, boolean enhance){
		this.type = type;
		this.setEnhance(enhance);
	}
	
	public void alignStoneCenterTo(vec2 pos){
		/*
		System.out.println("Location X, Y:" + this.getLocation().x + " " + this.getLocation().y);
		System.out.println("Pos : " +pos.getX() +" " + pos.getY());
		*/
		
		int stoneSIZEX = this.stoneSizeX/2;
		int stoneSIZEY = this.stoneSizeY/2;
		int stoneXPos = pos.getX() - stoneSIZEX;
		int stoneYPos = pos.getY() - stoneSIZEY;
		this.setLocation(this.getLocation().x + stoneXPos, this.getLocation().y + stoneYPos);
		this.setVisible(true);
		
	}
	//static method
	public void playStoneChange(){
		StoneTurningSound.remainPlayTime++;
	}
}
