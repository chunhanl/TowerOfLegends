package MainPanel;

import javax.swing.ImageIcon;

public class MainImageLoader {
	//data member
	public static ImageIcon darkTower ;
	public static ImageIcon darkTowerPressed ;
    public static ImageIcon brightTower ;
    public static ImageIcon brightTowerPressed ;
    public static ImageIcon fireTower ;
    public static ImageIcon fireTowerPressed ;
    public static ImageIcon waterTower ;
    public static ImageIcon waterTowerPressed ;
    public static ImageIcon woodTower ;
    public static ImageIcon woodTowerPressed ;
    public static ImageIcon mainTower ;
    public static ImageIcon mainTowerPressed ;
	public static ImageIcon shipTower;
	public static ImageIcon shipTowerPressed;
	
    

    
    
    public static ImageIcon backGround;

    
    //static constructor
    static{
    	darkTower 			= new ImageIcon("res/tower/dark.png");
    	darkTowerPressed 	= new ImageIcon("res/tower/darkP.png");
        brightTower 		= new ImageIcon("res/tower/bright.png");
        brightTowerPressed	= new ImageIcon("res/tower/brightP.png");
        fireTower 			= new ImageIcon("res/tower/fire.png");
        fireTowerPressed	= new ImageIcon("res/tower/fireP.png");
        waterTower 			= new ImageIcon("res/tower/water.png");
        waterTowerPressed	= new ImageIcon("res/tower/waterP.png");
        woodTower 			= new ImageIcon("res/tower/wood.png");
        woodTowerPressed	= new ImageIcon("res/tower/woodP.png");
        mainTower 			= new ImageIcon("res/tower/main.png"); 
        mainTowerPressed 	= new ImageIcon("res/tower/mainP.png");   
        shipTower 			= new ImageIcon("res/tower/ship.png"); 
        shipTowerPressed 	= new ImageIcon("res/tower/shipP.png");  
        
        
        
        backGround=new ImageIcon("res/tower/back.png"); 
    }
    
    
}

