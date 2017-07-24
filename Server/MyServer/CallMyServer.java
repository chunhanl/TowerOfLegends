package MyServer;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.* ;
import javax.swing.event.*;

public class CallMyServer extends JFrame 
{
	public static void main(String args[])
	{
		JFrame application = new JFrame();
		//application.setLayout(new FlowLayout());
		MyServer p = new MyServer(application);
		
		application.add(p);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setSize(500,750);
		application.setVisible(true);
		p.go();
	}

}
