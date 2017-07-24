package FixedPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Player.Player;
import math.vec2;

public class LifeClock extends JLabel{
	//data member
	private ImageIcon circleImage;
	private ImageIcon pressedImage;
	private ImageIcon innerImage;
	private InnerCircle inner;
	private FixedDownPanel container;
	private JLabel lifeboard;
	JLabel circle;
	private Player player;
	private Timer time;
	private int	  timecount=0;
	
	private vec2 position; //generally position mean the position of top-left

	//constructor
	public LifeClock(FixedDownPanel f,Player p){
		super();
		player=p;
		circleImage=new ImageIcon("res/lifeClock/circle.png");
		pressedImage=new ImageIcon("res/lifeClock/circleP.png");
		innerImage=new ImageIcon("res/lifeClock/innercircle.png");
		inner=new InnerCircle();
		this.container=f;
		this.setLocation(172,20);
		this.setSize(160,160);
		this.drawClock();
		this.startLifeTimer();
		
	}
	

	private void drawClock() {
		lifeboard=new JLabel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.setColor(Color.white);
				g.setFont(new Font("微軟正黑體",Font.BOLD,23));
				g.drawString("體力值", 45, 52);
				g.setFont(new Font("Baskerville Old Face",Font.BOLD,34));
				g.drawString(String.valueOf(player.getCurrentLife())+"/"+String.valueOf(player.getLifeMax()), 35, 92);
			}
		};
		circle=new JLabel();
		circle.setIcon(circleImage);
		circle.setLocation(0,0);
		circle.setSize(160,160);

		lifeboard.setSize(160,160);
		lifeboard.setLocation(0, 0);
		this.add(circle);
		this.add(lifeboard);
		this.add(inner);
		
		container.add(this);
	}
	
		
	private void startLifeTimer() {
		time=new Timer();
		time.schedule(new TimerTask(){
			public void run(){
				if(player.getCurrentLife()!=player.getLifeMax()){
					timecount++;
					player.setCurrentLife(player.getCurrentLife()+1);
					inner.setPercentage(((double)player.getCurrentLife()/(double)player.getLifeMax())*100);
				}
			}
		}, 0,1000);
	}



	



	
	private class InnerCircle extends JLabel{
		JLabel circle;
		InnerCircle(){
			super();
			circle=new JLabel();
			this.setLocation(5,-5);
			this.setSize(160,160);
			circle.setIcon(innerImage);
			circle.setLocation(5,-5);
			circle.setSize(160,160);
			this.add(circle);
		}
		public void setPercentage(double d){
			d=Math.sqrt(d)*10;
			this.setSize(160,(int) (1.6*d));
			this.setLocation(5,-5+(int) (20+1.6*(100-d)));
			circle.setLocation(5,-5+(int) (-1.6*(100-d)));
			lifeboard.repaint();
		}
		
	}

	

	//getter and setter
	public vec2 getPosition(){
		return new vec2(position);
	}
	public void setPosition(vec2 p){
		this.position = p;
	}
	public void setPositionAndLocation(vec2 p){
		this.setPosition(p);
		this.setLocation(p.getX(), p.getY());
	}
	public ImageIcon getImageIcon(){
		return circleImage;
	}
	public ImageIcon getPressedImageIcon(){
		return pressedImage;
	}
	
	//method

}