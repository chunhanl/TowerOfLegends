package TeamPanel;

import javax.swing.ImageIcon;

public class TeamImageLoader {
	//data member


    
    
    public static ImageIcon backGround;
    public static ImageIcon cardblank;
    public static ImageIcon editteam;
    public static ImageIcon editteamPressed;
    
    //static constructor
    static{
        backGround=new ImageIcon("res/team/background.png"); 
        cardblank=new ImageIcon("res/team/cardblank.png"); 
        editteam=new ImageIcon("res/team/edit.png"); 
        editteamPressed=new ImageIcon("res/team/editP.png"); 
    }
    
    
}

