package ShopPanel;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class DrawCardImageLoader {
	//data member


    
    

    
    public static ImageIcon backGround;
	public static ImageIcon goldCard;
	public static ImageIcon silverCard;
	public static ImageIcon platCard;
	public static ImageIcon biggoldCard;
	public static ImageIcon bigsilverCard;
	public static ImageIcon bigplatCard;
	
	
	public static ImageIcon upcover;
	public static ImageIcon normal;
	

	//static constructor
    static{
    	backGround=new ImageIcon("res/shop/drawcard/background.png");
    	
    	goldCard=new ImageIcon("res/shop/drawcard/gold.png");
    	platCard=new ImageIcon("res/shop/drawcard/platinum.png");
    	silverCard=new ImageIcon("res/shop/drawcard/silver.png");
    	biggoldCard=new ImageIcon("res/shop/drawcard/Bgold.png");
    	bigsilverCard=new ImageIcon("res/shop/drawcard/Bsilver.png");
    	bigplatCard=new ImageIcon("res/shop/drawcard/Bplatinum.png");
    	
    	upcover=new ImageIcon("res/shop/drawcard/upcover.png");
    	normal=new ImageIcon("res/shop/drawcard/normal.png");;
    	
    }
    
    
}

