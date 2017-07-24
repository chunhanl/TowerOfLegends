package FixedPanel;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class FixedImageLoader {
	//data member

    

	public static ImageIcon playerBackPack;
    public static ImageIcon playerBackPackPressed;
    public static ImageIcon playerSocial;
    public static ImageIcon playerSocialPressed;
    public static ImageIcon playerShop;
    public static ImageIcon playerShopPressed;
    public static ImageIcon playerTeam;
    public static ImageIcon playerTeamPressed;
    
    public static ImageIcon fixedDownBack; 
    
   
    public static ImageIcon playerLeaderSlot;
    public static ImageIcon fixedUpPanelBack;
	public static ImageIcon cardSort;
	
    
    //static constructor
    static{
       //Down 
        playerBackPack		  = new ImageIcon("res/backpack/backpack.png"); 
        playerBackPackPressed = new ImageIcon("res/backpack/backpackP.png"); 
        playerSocial		  = new ImageIcon("res/social/social.png"); 
        playerSocialPressed	  = new ImageIcon("res/social/socialP.png"); 
        playerShop 			  = new ImageIcon("res/shop/shop.png"); 
        playerShopPressed 	  = new ImageIcon("res/shop/shopP.png"); 
        playerTeam 			  = new ImageIcon("res/team/team.png"); 
        playerTeamPressed 	  = new ImageIcon("res/team/teamP.png");
        
        fixedDownBack		  = new ImageIcon("res/fixeddownpanel/back.png");
       //Up 
        playerLeaderSlot	  = new ImageIcon("res/player/slot.png"); 
        fixedUpPanelBack  	  = new ImageIcon("res/fixeduppanel/back.png"); 
        cardSort			  = new ImageIcon("res/team/sort.png"); 
    }
    
    
}

