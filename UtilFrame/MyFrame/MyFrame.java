package MyFrame;

import gameUtil.BattleTeam;

import javax.swing.JFrame;

import card.Card;
import level.WhiteGoat;
import level.multiPlayer1vs1;
import FixedPanel.FixedPanel;
import InterFace.Battle;
import MyClient.*;

import java.awt.event.*;

import javax.swing.*;

public class MyFrame extends JFrame{
	
	public FixedPanel main;
	Battle     battle; 
	Card[]	   team;
	public MyPanel    client;
	public static MyFrame refFrame;
	
	public MyFrame(String string) {
		super(string);	

		refFrame = this;
		System.out.print("");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		//important key code
		this.addWindowListener(new WindowAdapter() 
		{
	    	public void windowClosing(WindowEvent windowEvent) 
			{
	    		int result;
				result = JOptionPane.showConfirmDialog(null, "離開遊戲?", "Leave", JOptionPane.YES_NO_OPTION);
				if(result == 0)
				{
					System.out.println("EXIT");
		    		Thread t=new Thread(new closeFrame());
		    		t.start();
		    		client.leave();
				}
				else
					System.out.println("CONTINUE!!!");
	        }
		}
	);
		
	}

	public void CallMainPanel(){   //Frame建立主畫面
		client=new MyPanel(this);
		while(true)
		{
			System.out.println("");
			if(client.getPlayer().getOnline()==true)
			{
				main = new FixedPanel(client,this);
				this.add(main);
				this.repaint();
				break;
			}
		}
		//main = new FixedPanel(client,this);
		//this.add(main);
		//this.repaint();
	}	

	public void CallBattlePanel(){ //Frame呼叫戰鬥畫面
		team=new Card[6];
		team=main.getPlayer().getTeam(0);
		main.setVisible(false);
		//System.gc();
		this.revalidate();
		
		battle= new Battle(this,new BattleTeam(team), new multiPlayer1vs1(), true);
		this.repaint();
	}
	
	
	public void CallSinglePanel(){ //Frame呼叫戰鬥畫面
		team=new Card[6];
		team=main.getPlayer().getTeam(0);
		main.setVisible(false);
		//System.gc();
		this.revalidate();
		
		battle= new Battle(this,new BattleTeam(team), new WhiteGoat(), false);
		this.repaint();
	}
	
	
	private class closeFrame implements Runnable
	{
		public void run()
		{
			try{
				while(true)
				{
					Thread.sleep(10);
					if(client.getPlayer().getOnline()==false)
					{
						System.out.println("Success close the game!!!");
						System.exit(1);
					}
				}
			}
			catch(InterruptedException ex)
    		{
    			ex.printStackTrace();
    		}
		}
	}
}
