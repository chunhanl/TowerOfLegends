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
import MyClient.Player;

public class test {
	public static void main(String args[]){
		
		MyFrame frame = new MyFrame("Main");
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(500,750);
		frame.setResizable(false);
		frame.setLayout(null);

		frame.setVisible(true);		
		frame.CallMainPanel();
	
	}

}
