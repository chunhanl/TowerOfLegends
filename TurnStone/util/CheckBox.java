package util;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import stonePanel.ImageLoader;
import stonePanel.MyMouseAdapter;

public class CheckBox extends JLabel{
	//data member
	private JLabel yesButton;
	private JLabel noButton;
	private String message;
	private JLabel thisREF;
	public boolean yes;
	public boolean answered;
	//constructor
	public CheckBox(String mes){
		super();
		this.setOpaque(true);
		this.thisREF = this;
		this.yes = false;
		this.answered = false;
		this.setSize(250,200);
		this.setLocation(125,250);
		this.message = mes;
		//set up yesButton
		this.yesButton = new JLabel(ImageLoader.getYesButton());
		this.yesButton.setSize(90,60);
		this.yesButton.setLocation(30,120);
		this.yesButton.addMouseListener(new MyMouseAdapter(){
			public void mousePressed(MouseEvent f){   //first meet of mouse event

			}

			public void mouseClicked(MouseEvent f){  //for blood bar
				answered = true;
				yes = true;
				thisREF.setVisible(false);
			}

			public void mouseReleased(MouseEvent f){   //when user release the mouse then it should be set to a right position

			}
		});
		this.yesButton.setVisible(true);
		//this.add(yesButton);
		//set up noButton
		this.noButton = new JLabel(ImageLoader.getNoButton());
		this.noButton.setSize(90,60);
		this.noButton.setLocation(220,120);
		this.noButton.addMouseListener(new MyMouseAdapter(){
			public void mousePressed(MouseEvent f){   //first meet of mouse event

			}

			public void mouseClicked(MouseEvent f){  //for blood bar
				answered = false;
				yes = false;
				thisREF.setVisible(false);
			}

			public void mouseReleased(MouseEvent f){   //when user release the mouse then it should be set to a right position

			}
		});
		this.noButton.setVisible(true);
		//this.add(noButton);
	}	
	
	
	//method
	public boolean isAnswered(){
		return this.answered;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		System.out.println("move in");
		g.drawImage(ImageLoader.getCheckBox().getImage(), 0, 0, 250, 200, null);
		g.drawString(message, 50, 85);
	}
}
