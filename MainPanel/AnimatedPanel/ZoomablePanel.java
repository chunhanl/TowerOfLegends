package AnimatedPanel;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

import FixedPanel.FixedPanel;



public class ZoomablePanel extends JPanel{
	

	private JLabel blank;
	//private JLabel fullImage;
	
	private int height;
	private int width;

	
	private FixedPanel fpanelREF;
	
	
	public ZoomablePanel(FixedPanel f,int width,int height){
		super();
		this.fpanelREF=f;
		this.height=height;
		this.width=width;
		

		buildBlank(); 
		this.setSize(width,height);
		this.setLocation(0, 0);
		this.setOpaque(false);
		this.setVisible(true);
		f.add(this,0);
		f.repaint();
	}
	
	
	
	protected void zoomIn() {
		final Timer time=new Timer();
		time.schedule(new TimerTask(){
			public void run(){
				blank.setSize(blank.getWidth()+10,blank.getHeight()+15);
				blank.setLocation((500-blank.getWidth())/2, (750-blank.getHeight())/2);
				System.out.println("run");
				if(blank.getWidth()==500){
					time.cancel(); 
					this.cancel();
				}
				blank.repaint();
				fpanelREF.repaint();
			}
				
		}, 0, 1);
		
	}
	
	private void zoomOut() {
		final Timer time=new Timer();
		time.schedule(new TimerTask(){
			public void run(){
				blank.setSize(blank.getWidth()-10,blank.getHeight()-15);
				blank.setLocation((500-blank.getWidth())/2, (750-blank.getHeight())/2);

				if(blank.getWidth()==0){
					fpanelREF.remove(0);
					fpanelREF.repaint();
					time.cancel(); 
					this.cancel();
				}
				fpanelREF.repaint();
				System.out.println(blank.getWidth());
			}
				
		}, 0, 1);
		
	}

	private void buildBlank() {
		blank=new JLabel();
		blank.setSize(0,0);
		blank.setLocation(0, 0);
		blank.setVisible(true);
		blank.setBackground(Color.black);
		this.add(blank,0);
	}


	
}

