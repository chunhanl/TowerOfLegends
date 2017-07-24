package ShopPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import soundUtil.Sound;
import FixedPanel.FixedPanel;
import MidScrollPanel.MidScrollPanel;
import MyClient.Player;


public class ShopPanel extends JPanel implements MouseListener{
	
	private JFrame myframeREF;
	private Player player;
	private JLabel backGround;
	private MidScrollPanel midPanel;
	private FixedPanel fixedpanelREF;
	public Thread t;
	public boolean isDrawing;
	
	public ShopPanel(JFrame frame,FixedPanel f)
	{
		super(null);
		this.player=f.getPlayer();
		this.myframeREF=frame;
		this.fixedpanelREF=f;
		this.setSize(500,750);		
		this.isDrawing=false;
		this.repaint();
		
		buildShopPanel();
		buildBackGround();
	}
	
	private void buildShopPanel() {
		midPanel=new MidScrollPanel(myframeREF,fixedpanelREF);
		midPanel.buildPanelRows(5);
		midPanel.setPanelSkin(ShopImageLoader.panelrowimage);    //panel rows' skin
		midPanel.panelRows[0].addMouseListener(this);
		midPanel.setRowsText(0, "Draw Card (5 stones)");       //set Text
	
		this.add(midPanel);
	}

	public void buildBackGround(){
		backGround=new JLabel( ShopImageLoader.backGround);
		backGround.setLocation(0,0);
		backGround.setSize(myframeREF.getWidth(),myframeREF.getHeight());
		this.add(backGround);
		this.repaint();
	}

	public void setVisible(boolean b) {
		if(b){
			super.setVisible(true);
			this.fixedpanelREF.showUpBar("°Ó©±");
			this.midPanel.runAnimation();

		}
		else{
			this.midPanel.hidePanels();
			super.setVisible(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==midPanel.panelRows[0]&&!isDrawing){
			System.out.println("Activate DrawCard");
			GoDrawCard();
			new Sound("main/click.wav").play();
		}
	}

	private void GoDrawCard() {
		
		if(player.getStone()<5){
			JOptionPane j=new JOptionPane();
			j.showMessageDialog(null,"Out of stone");
		}
		else{
			isDrawing=true;
			DrawCardPanel d=new DrawCardPanel(fixedpanelREF,player.getCardLeader());
			t=new Thread(d);
			t.start();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	
}
