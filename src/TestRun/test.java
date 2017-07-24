package TestRun;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import FixedPanel.FixedPanel;
import MyFrame.MyFrame;
import Player.Player;

public class test {
	public static void main(String args[]){
		
		MyFrame frame = new MyFrame("Main");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(500,750);
		frame.setResizable(false);
		frame.setLayout(null);
				
		Player p1=new Player("aunt");
		
		
		FixedPanel panel = new FixedPanel(frame,p1); 
		frame.add(panel);
		frame.repaint();
		frame.setVisible(true);		
		
	
	}

}
