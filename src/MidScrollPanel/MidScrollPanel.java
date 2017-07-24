package MidScrollPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import math.vec2;
import FixedPanel.FixedPanel;
import MainPanel.MainImageLoader;
import MainPanel.MainPanel;

//5000MAX
//提供一個可動的目錄底層
public class MidScrollPanel extends JPanel{
	
	private JFrame myframeREF;
	protected FixedPanel fixedpanelREF; 
	private MidScrollPanel thispanelREF;
	

	public  JPanel[] panelRows;
	private int      panelRowsNum;
	private int 	 mouseiniY;
	
	private int 				time;
	private boolean 			isInPosition;
	private Timer 				animationTimer;
	private RunAnimationTask	runTask;
	private SortButton			sortButton;
	
	
	public MidScrollPanel(JFrame frame,FixedPanel fpanelREF){		
		super(null); 
		thispanelREF =this;
		fixedpanelREF=fpanelREF;
		myframeREF = frame;  
		isInPosition=false;

		
		this.setSize(500,5000);		
		this.setLocation(0,180);
		this.repaint();
		this.setOpaque(false);    //透明化
		this.setBackground(Color.BLUE);
		this.addMouseListener(new ScrollMouseAdapter());
		this.addMouseMotionListener(new ScrollMouseAdapter()); //!!!

		addSortButton();
	}
	

	public  void adjustSizeY(int Y){   //解決拉過頭的問題
		this.setSize(500,Y);
	}
	
	public void addSortButton(){
		sortButton=new SortButton();

	}
	
	private class ScrollMouseAdapter extends MouseAdapter{
		public void mousePressed(MouseEvent m){
			mouseiniY=m.getY();
		}
		public void mouseDragged(MouseEvent m){
			thispanelREF.movePanelY((m.getY()-mouseiniY));
			
		}
		
		public void mouseReleased(MouseEvent m){
			thispanelREF.checkPanelY();
		}
	}
	
	public void movePanelY(int dy){   //move panel y in y-axis
		this.setLocation(0,this.getY()+dy);
		myframeREF.repaint(); ///保留FIXEDPANEL
	}
	
	public void checkPanelY(){ 		//check if out of bound
		if(thispanelREF.getY()>180)
			thispanelREF.setLocation(0,180);
		else if(thispanelREF.getY()+thispanelREF.getHeight()-400<180)
			thispanelREF.setLocation(0,550-thispanelREF.getHeight());
		myframeREF.repaint();
	}
	
	
	public void buildPanelRows(int num){
		this.panelRowsNum=num;
		this.panelRows=new JPanel[num];
		for(int i=0;i< num;i++)
		{
			panelRows[i]=new JPanel();
			panelRows[i].setLayout(null);
			panelRows[i].setSize(400,70);
			panelRows[i].setLocation(-500,80*i );
			panelRows[i].setBackground(Color.yellow);
			
			this.add(panelRows[i]);
			panelRows[i].setVisible(true);
			//panelRows[i].setOpaque(false);    //透明化
		}
		adjustSizeY((num+1)*80);
	}
	
	
	public void setPanelSkin(ImageIcon rowimage){
		for(int i=0;i<panelRows.length;i++){
			JLabel rowskin=new JLabel();
			rowskin.setSize(panelRows[i].getWidth(), panelRows[i].getHeight());
			rowskin.setIcon(rowimage);
			panelRows[i].add(rowskin);
		}
	}
	
	
	
	public void runAnimation() {
		this.fixedpanelREF.setButtonDisable();
		int dx=10;
		int period=3;
		animationTimer=new Timer();
		runTask=new RunAnimationTask(dx);
		animationTimer.schedule(runTask,0,period);		

	}	
	
	private class RunAnimationTask extends TimerTask{
		private int dx;
		public RunAnimationTask(int dx) {
			this.dx=dx;
		}
		
		public void run() {
			if(isInPosition==false){
				for(int i=0;i<panelRows.length;i++)
				{
					if(panelRows[i].getX()<(int) ((myframeREF.getSize().getWidth()-400)/2)){
						panelRows[i].setLocation(-500+(time-20*i)*dx, 80*i );
					}
					myframeREF.repaint();
				}
			
				if(-500+(time-panelRows.length*20)*dx<(int) ((myframeREF.getSize().getWidth()-400)/2)){
					time++;
				}
				else{
					isInPosition = true;	
					time=0;
					fixedpanelREF.setButtonEnable();
					animationTimer.cancel();
					runTask.cancel();
				}
			}
			//isInPosition=true
			else{
				isInPosition = true;	
				time=0;
				fixedpanelREF.setButtonEnable();
				animationTimer.cancel();
				runTask.cancel();
			}
		}
	}
	
	public boolean isInPosition(){
		return this.isInPosition;
	}

	//------------------------------------------------------

	

	
	public void hidePanels(){
		this.fixedpanelREF.setButtonDisable();
		for(int i=0;i<panelRows.length;i++)
			panelRows[i].setLocation(-500,80*i);
		this.setLocation(0,180);
		this.isInPosition=false;
		this.fixedpanelREF.setButtonEnable();
	}
	
	
	
	//-----------SortButton Inner class
	
	private class SortButton extends JLabel{
		SortButton(){
			super();
			this.setSize(110, 61);
			this.setLocation(390,0);
			this.setIcon(MidScrollImageLoader.sortButton);
			
		}
	}
}

	