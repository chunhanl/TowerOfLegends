package BackpackPanel;

import javax.swing.ImageIcon;

public class BackPackImageLoader {
	//data member


    
    
    public static ImageIcon backGround;
    public static ImageIcon cardSlot;
    
    //static constructor
    static{
        backGround=new ImageIcon("res/backPack/background.png"); 
        cardSlot  =new ImageIcon("res/server/cards/slot/slot.png");
    }
    
    
}

