package FixedPanel;

import javax.swing.ImageIcon;

public class UpScrollPanelImageLoader {
	//data member


    
    
    public static ImageIcon backToMainPoint;
    public static ImageIcon backToMainPointPressed;
    public static ImageIcon informationBar;
    public static ImageIcon sortButton;
    
    //static constructor
    static{
    	backToMainPoint=new ImageIcon("res/upscrollpanel/backtomainpoint.png"); 
    	backToMainPointPressed=new ImageIcon("res/upscrollpanel/backtomainpointP.png"); 
    	informationBar =new ImageIcon("res/upscrollpanel/bar.png");
    	sortButton =new ImageIcon("res/upscrollpanel/sort.png");
    }
    
    
}

