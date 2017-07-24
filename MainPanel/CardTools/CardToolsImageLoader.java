package CardTools;

import javax.swing.ImageIcon;

public class CardToolsImageLoader {
	
	public static ImageIcon cardSlot;
    public static ImageIcon sortButton;
	public static ImageIcon sortButtonP;
	public static ImageIcon sortList;
	public static ImageIcon star;

	static{
		cardSlot  =new ImageIcon("res/server/cards/slot/slot.png");
		star=new ImageIcon("res/cardtools/star.png");
		sortButton =new ImageIcon("res/upscrollpanel/sort.png");
    	sortButtonP=new ImageIcon("res/upscrollpanel/sortP.png");
    	sortList=new ImageIcon("res/upscrollpanel/sortlist.png");
    	
	}
}