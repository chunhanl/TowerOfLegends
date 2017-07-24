package counter;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import math.vec2;

public class Enemy extends JLabel{
	//data member
	public ImageIcon imag;
	public vec2 position;        //unlike normal gui component the y axis calc from buttom
	public vec2 size;
	public int blood;
	public int currentBlood;
	public int attackCD;
	public int attack;
	public int initialCD;
	public int defense;
	public int type;
	
	
	//constructor
	public Enemy(){
	}
	//getter and setter
	
	//method
	public void takeDamage(int damage){
		if(damage <= defense && damage > 0){
			damage = 1;
		}
		if(currentBlood - damage < 0){
			currentBlood = 0;
		}
		else{
			currentBlood -= damage;
		}
	}
	
	public vec2 getCenter(){
		vec2 tmp = new vec2(position.getX() + size.getX()/2, position.getY() + size.getY()/2);
		return tmp;
	}
}
