package MainPanel;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import FixedPanel.FixedPanel;
import MidScrollPanel.MidScrollPanel;
import MyClient.Player;



public class LevelChoosePanel extends JPanel{


	private JFrame myframeREF;
	private Player player;
	private JLabel backGround;
	private MidScrollPanel midPanel;
	public  JPanel[] midPanelRows;
	private FixedPanel fixedpanelREF;
	private int filledNum;
	
	
	public LevelChoosePanel(JFrame frame,FixedPanel f)
	{
		super(null);
		this.player=f.getPlayer();
		this.myframeREF=frame;
		this.fixedpanelREF=f;
		this.filledNum=0;
		this.setSize(frame.getWidth(),frame.getHeight());		
		this.repaint();
		
		buildLevelPanel();
		buildBackGround();
	}
	
	private void buildLevelPanel() {
		midPanel=new MidScrollPanel(myframeREF,fixedpanelREF);
		midPanel.buildPanelRows(3);

		this.midPanelRows=midPanel.panelRows;
		System.out.println(midPanelRows.length);
		this.add(midPanel);
	}

	private void buildBackGround(){
		backGround=new JLabel( MainImageLoader.backGround);
		backGround.setLocation(0,0);
		backGround.setSize(myframeREF.getWidth(),myframeREF.getHeight());
		this.add(backGround);
		this.repaint();
	}

	public void buildLevel(String stage){
		if(filledNum>midPanelRows.length)System.out.println("error");
		else{
			JLabel j=new JLabel(stage);
			j.setBackground(Color.red);
			j.setSize(500, 80);
			j.setLocation(0, 0);
			this.midPanelRows[2].add(j);
			filledNum++;
		}
	}
	
	public void setVisible(boolean b) {
		if(b){
			super.setVisible(true);
			this.fixedpanelREF.showUpBar("Level");
			this.midPanel.runAnimation();

		}
		else{
			this.midPanel.hidePanels();
			super.setVisible(false);
		}
	}
	
	

}
