package MainPanel;
import gameUtil.BattleTeam;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import level.AngerOfTheKing;
import level.WhiteGoat;
import InterFace.Battle;
import MyFrame.MyFrame;

public class MainMouseAdapter extends MouseAdapter{
	//data member
	private Tower listenLabel;
	private MainPanel mypanelREF;
	private ImageIcon unpressed;
	private ImageIcon pressed;
	private MyFrame myframe;
	
	//constructor
	public MainMouseAdapter(Tower lab,MainPanel panel){
		listenLabel = lab;
		mypanelREF = panel;
		myframe=mypanelREF.getMyFrame();
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
			int result;
			result = JOptionPane.showConfirmDialog(null, "進入連線遊戲模式?(消耗20體力)", "Game", JOptionPane.YES_NO_OPTION);
			if(result == 0 ){
				if(mypanelREF.getFixedPanel().getPlayer().getCurrentLife() >= 20){
					mypanelREF.getFixedPanel().getPlayer().addCurrentlife(-20);
					mypanelREF.getFixedPanel().setVisible(false);
					myframe.CallBattlePanel();
				}
				else{
					JOptionPane.showMessageDialog(null, "體力不足");
				}
			}
			else{
				System.out.println("CONTINUE!!!");
			}
		}
		else if(listenLabel==mypanelREF.getFireTower()){
			mypanelREF.getFixedPanel().getClientPanel().send("@"+mypanelREF.getFixedPanel().getClientPanel().getPlayer().getName()+"%Addmoney#1000");
		}
		else if(listenLabel==mypanelREF.getDarkTower()){
			mypanelREF.getFixedPanel().getClientPanel().send("@"+mypanelREF.getFixedPanel().getClientPanel().getPlayer().getName()+"%Addstone#5");
		}
		else if(listenLabel==mypanelREF.getWaterTower()){
			mypanelREF.getFixedPanel().getClientPanel().send("@"+mypanelREF.getFixedPanel().getClientPanel().getPlayer().getName()+"%Addcurrentlife#20");
			
		}
		else if(listenLabel==mypanelREF.getWoodTower()){
			mypanelREF.getFixedPanel().getClientPanel().send("@"+mypanelREF.getFixedPanel().getClientPanel().getPlayer().getName()+"%Addlife#20");
		}
		else if(listenLabel==mypanelREF.getBrightTower()){
			int result;
			result = JOptionPane.showConfirmDialog(null, "進入單人遊戲模式?(消耗20體力)", "Game", JOptionPane.YES_NO_OPTION);
			if(result == 0){
				if(mypanelREF.getFixedPanel().getPlayer().getCurrentLife() >= 20){
					mypanelREF.getFixedPanel().getPlayer().addCurrentlife(-20);
					mypanelREF.getFixedPanel().setVisible(false);
					myframe.CallSinglePanel();
				}
				else{
					JOptionPane.showMessageDialog(null, "體力不足");
				}
			}
			else{
				System.out.println("CONTINUE!!!");
			}
		}
		
		System.out.println("GGGGGGG//"+ mypanelREF.getPlayer().getCurrentLife());
	}
	
	
}