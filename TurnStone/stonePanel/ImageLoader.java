package stonePanel;

import javax.swing.ImageIcon;

//class for Loading our image
public class ImageLoader {
	//data member
	//---stone image
	public static ImageIcon dark ;
    public static ImageIcon bright ;
    public static ImageIcon fire ;
    public static ImageIcon water ;
    public static ImageIcon wood ;
    public static ImageIcon heart ;
    //---hp bar
    public static ImageIcon bloodBar;
    
    //static constructor
    static{
    	dark = new ImageIcon("res/stone/dark.png");
        bright = new ImageIcon("res/stone/bright.png");
        fire = new ImageIcon("res/stone/fire.png");
        water = new ImageIcon("res/stone/water.png");
        wood = new ImageIcon("res/stone/wood.png");
        heart = new ImageIcon("res/stone/heart.png"); 	
    }
    
    //static getter and setter
    public static ImageIcon getStoneImage(int choose){
    	ImageIcon imageTmp = null;
    	if(choose == 0){
    		imageTmp = new ImageIcon("res/stone/dark.png");
    	}
    	else if(choose == 1){
    		imageTmp = new ImageIcon("res/stone/bright.png");
    	}
    	else if(choose == 2){
    		imageTmp = new ImageIcon("res/stone/fire.png");
    	}
    	else if(choose == 3){
    		imageTmp = new ImageIcon("res/stone/water.png");
    	}
    	else if(choose == 4){
    		imageTmp = new ImageIcon("res/stone/wood.png");
    	}
    	else{
    		imageTmp = new ImageIcon("res/stone/heart.png"); 
    	}
    	return imageTmp;
    }
    
    public static ImageIcon getStoneImage(int choose, boolean enhanced){
    	ImageIcon imageTmp = null;
    	if(choose == 0){
    		imageTmp = new ImageIcon("res/stone/darkEnhance.png");
    	}
    	else if(choose == 1){
    		imageTmp = new ImageIcon("res/stone/brightEnhance.png");
    	}
    	else if(choose == 2){
    		imageTmp = new ImageIcon("res/stone/fireEnhance.png");
    	}
    	else if(choose == 3){
    		imageTmp = new ImageIcon("res/stone/waterEnhance.png");
    	}
    	else if(choose == 4){
    		imageTmp = new ImageIcon("res/stone/woodEnhance.png");
    	}
    	else{
    		imageTmp = new ImageIcon("res/stone/heartEnhance.png"); 
    	}
    	if(enhanced == true)
    		return imageTmp;
    	else
    		return getStoneImage(choose);
    }
    
    public static ImageIcon getBloodImage(){
    	ImageIcon imageTmp = new ImageIcon("res/util/bloodBar2.gif");
    	return imageTmp;
    }
    
    public static ImageIcon getBloodDecorateImage(){
    	ImageIcon imageTmp = new ImageIcon("res/util/BloodBar_decorate.png");
    	return imageTmp;
    }
    
    public static ImageIcon getEnemyBloodDecorateImage(){
    	ImageIcon imageTmp = new ImageIcon("res/util/EnemyBloodBar_decorate.png");
    	return imageTmp;
    }
    
    public static ImageIcon getTimeImage(){
    	ImageIcon imageTmp = new ImageIcon("res/util/greenBar.jpg");
    	return imageTmp;
    }
    
    public static ImageIcon getDarkNumberImage(int num){
    	ImageIcon image = new ImageIcon("res/Number/Dark/Num" + num + ".png");
    	return image;
    }
    
    public static ImageIcon getBrightNumberImage(int num){
    	ImageIcon image = new ImageIcon("res/Number/Bright/Num" + num + ".png");
    	return image;
    }
    
    public static ImageIcon getConditionImage(boolean cond){
    	ImageIcon image;
    	if(cond == true){ //win
    		image = new ImageIcon("res/level/condition/Victory.png");
    	}
    	else{
       		image = new ImageIcon("res/level/condition/Defeat.png");
    	}
    	return image;
    }
    
    public static ImageIcon getContinueButton(){
    	return new ImageIcon("res/util/Continue.png");
    }
    
    public static ImageIcon getCheckBox(){
    	return new ImageIcon("res/util/CheckBoxPic.jpeg");
    }
    
    public static ImageIcon getYesButton(){
    	return new ImageIcon("res/util/yesButton.png");
    }
    
    public static ImageIcon getNoButton(){
    	return new ImageIcon("res/util/noButton.png");
    }
    //static method
}
/*note
	loading "GIF" format of file could make pseudo animation
*/