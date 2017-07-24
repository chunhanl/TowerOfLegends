package MainPanel;
import gameUtil.BattleTeam;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import level.AngerOfTheKing;
import InterFace.Battle;

public class MainMouseAdapter extends MouseAdapter{
	//data member
	private Tower listenLabel;
	private MainPanel mypanelREF;
	private ImageIcon unpressed;
	private ImageIcon pressed;
	private JFrame myframe;
	
	//constructor
	public MainMouseAdapter(Tower lab,MainPanel panel){
		listenLabel = lab;
		mypanelREF = panel;
		myframe=mypanelREF.getFrame();
		unpressed=listenLabel.getImageIcon();
		pressed =listenLabel.getPressedImageIcon();
	}
	
	//method
	public void mousePressed(MouseEvent f) 
	{
		listenLabel.setIcon(pressed);
		mypanelREF.repaint();

	}
	
	public void mouseReleased(MouseEvent f) 
	{
		listenLabel.setIcon(unpressed);
		mypanelREF.repaint();
		
		if(listenLabel==mypanelREF.getMainTower()){
			mypanelREF.getFixedPanel().setVisible(false);
			Battle bat = new Battle(mypanelREF.getFrame(),new BattleTeam(mypanelREF.getPlayer().getTeam(0)), new AngerOfTheKing());

		}
	}
	
	
}