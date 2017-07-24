package ChatRoom;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import FixedPanel.FixedPanel;

import java.util.*;

public class MyPanel extends JPanel implements MouseListener, MouseMotionListener
{
	private JLabel chat;			//chat picture
	private JFrame application;
	private MyClient mc;
	
	private int dx, dy;
	private int ddx, ddy;
	private boolean isFirst;

	public FixedPanel fpanelREF;
	
	public MyPanel(JFrame f,FixedPanel fp)
	{
		isFirst = true;
		dx = 10;
		dy = 100;
		
		fpanelREF=fp;
		application = f;
		this.setLayout(null);
		mc = new MyClient(application, this);
			
		chat = new JLabel();
		chat.setIcon(new ImageIcon("res/chatroom/chat.png"));
		chat.addMouseListener(this);
		chat.addMouseMotionListener(this);
		chat.setSize(60,60);
		chat.setLocation(dx,dy);
		
		this.setOpaque(false);
		this.add(chat);
		this.setSize(500, 750);
		this.setVisible(true);
	}
	
	
	public void mousePressed(MouseEvent event)
	{
		dx = event.getX();
		dy = event.getY();
	}
	public void mouseReleased(MouseEvent event){
		//border check
		for(int i=0;i<3;i++){
		if(chat.getX()>450)chat.setLocation(420, chat.getY());
		if(chat.getX()<0)chat.setLocation(10, chat.getY());
		
		if(chat.getY()>650)chat.setLocation(chat.getX(),650);
		if(chat.getY()<0)chat.setLocation(chat.getX(),20);
		}
	}
    public void mouseClicked(MouseEvent event)
	{
			dx = chat.getX()+ddx;
			dy = chat.getY()+ddy;
			this.setVisible(false);		//close current panel
			
			if(isFirst == true)			//if not click yet
			{
				application.add(mc,0);	//add MyClient panel to Frame
				isFirst = false;
			}
			else
				mc.setVisible(true);	//show MyClient panel
	}
	public void mouseEntered(MouseEvent event){}
    public void mouseExited(MouseEvent event){}
	public void mouseDragged( MouseEvent event )
	{
		ddx = event.getX()-dx;
        ddy = event.getY()-dy;
		

        chat.setLocation(chat.getX()+ddx,chat.getY()+ddy);
	}
	public void mouseMoved(MouseEvent event){}
	
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
	}

}