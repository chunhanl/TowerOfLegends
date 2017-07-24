package FixedPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import CardTools.CardLabel;


public class TestPanel extends JPanel{
	
	int dotX;
	int dotY;
	int width;
	int height;
	int pp=0;
	JPanel sign;
	
	public TestPanel(int x,int y){
		super();
		dotX=0;
		dotY=0;
		width=x-5;
		height=y-15;
		sign=new JPanel();
		sign.setSize(25, 25);
		sign.setLocation(0, 0);
		sign.setVisible(true);
		sign.setBackground(Color.red);
		this.add(sign);
		runAnimation();
	}
	

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		sign.setLocation(dotX,dotY);
		//g.setColor(Color.RED);
		//System.out.println("paint"+(pp++));
		//g.fillRect(dotX,dotY, dotX+5,dotY+5);

	}
	
	private void runAnimation() {
		int dx=1;
		int period=5;
		Timer a=new Timer();
		RunAnimationTask runTask=new RunAnimationTask(dx);
		a.schedule(runTask,0,period);		

	}	
	
	private class RunAnimationTask extends TimerTask{
		private int dx;
		public RunAnimationTask(int dx) {
			this.dx=dx;
		}
		
		public void run() {
				if(TestPanel.this.dotY==0&&TestPanel.this.dotX<TestPanel.this.width)TestPanel.this.dotX++;
				else if(TestPanel.this.dotX==0&&TestPanel.this.dotY>0)TestPanel.this.dotY--;
				else if(TestPanel.this.dotX==TestPanel.this.width&&TestPanel.this.dotY<=TestPanel.this.height)TestPanel.this.dotY++;
				else TestPanel.this.dotX--;
				//System.out.println("hear me"+TestPanel.this.dotX+"/"+TestPanel.this.dotY);
			}
	}

	
}