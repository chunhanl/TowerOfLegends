package particleSystem;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import math.vec2;
import motionMethod.*;
import particle.Particle;
import stonePanel.ImageLoader;
import stonePanel.TurningStonePanel;

public class ParticleInterface extends JLabel implements Runnable{
	//data member
	private final int sleepMilliTime = 20;
	
	private ArrayList<Particle> particles;  //free obj-------------> particle is not yet finish
	
	private ArrayList<MotionStrategy> strategy;       //constrain obj by it's orbit
	private ArrayList<Integer> index;
	private ArrayList<vec2> position;
	private ArrayList<Integer> size;
	private ArrayList<JLabel> constrainParticle;
	private JFrame frame;
	
	private boolean garbageCollect = false;
	
	/*
	private int xx = 0;
	private int yy = 70;
	private JLabel labb  = new JLabel(ImageLoader.getStoneImage(2));
	private TurningStonePanel pan;
	*/
	
	//const
	public static int parabolicStrategy = 0;
	public static int CustomizeStrategy = -1;
	
	
	//constructor
	public ParticleInterface(int width, int height, JFrame frame){
		super();
		this.setBackground(new Color(0,0,0,0));
		this.setSize(width, height);
		this.setLocation(0,0);
		this.particles = new ArrayList<Particle>();
		this.strategy = new ArrayList<MotionStrategy>();
		this.index = new ArrayList<Integer>();
		this.position = new ArrayList<vec2>();
		this.size = new ArrayList<Integer>();
		this.constrainParticle = new ArrayList<JLabel>();
		this.frame = frame;
		
		/*
		this.add(labb,0);
		labb.setVisible(true);
		labb.setSize(60,60);
		pan = panel;
		*/
	}
	
	//method
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//System.out.println("particle");
	}
	
	public void addFreeParticle(){
		this.particles.add(new Particle());
	}
	
	public MotionStrategy addConstrainMotion(vec2 startPoint, vec2 endPoint, int choose, int size){
		if(choose == parabolicStrategy){
			this.strategy.add(new ParabolicStrategy(startPoint, endPoint));
			index.add(new Integer(0));
			position.add(new vec2(startPoint));
			this.size.add(new Integer(size));
			this.constrainParticle.add(new JLabel(ImageLoader.getStoneImage(2)));
			this.constrainParticle.get(constrainParticle.size() - 1).setSize(size, size);
			this.constrainParticle.get(constrainParticle.size() - 1).setLocation(startPoint.getX(), startPoint.getY());
			this.constrainParticle.get(constrainParticle.size() - 1).setVisible(true);
			this.add(this.constrainParticle.get(constrainParticle.size() - 1));
		}
		return this.strategy.get(this.strategy.size() - 1);
	}
	
	public MotionStrategy addConstrainMotion(vec2 startPoint, vec2 endPoint, int choose, int size, int type){
		if(choose == parabolicStrategy){
			this.strategy.add(new ParabolicStrategy(startPoint, endPoint));
			index.add(new Integer(0));
			position.add(new vec2(startPoint));
			this.size.add(new Integer(size));
			this.constrainParticle.add(new JLabel(ImageLoader.getStoneImage(type)));
			this.constrainParticle.get(constrainParticle.size() - 1).setSize(size, size);
			this.constrainParticle.get(constrainParticle.size() - 1).setLocation(startPoint.getX(), startPoint.getY());
			this.constrainParticle.get(constrainParticle.size() - 1).setVisible(true);
			this.add(this.constrainParticle.get(constrainParticle.size() - 1));
		}
		return this.strategy.get(this.strategy.size() - 1);
	}
	
	public void addConstrainMotion(vec2 startPoint, vec2 endPoint, String name){
		this.strategy.add(new CustomizeStrategy(startPoint, endPoint, name));
		index.add(new Integer(0));
		position.add(new vec2(startPoint));
	}
	
	/*
	 * paintConstrainParticle is to paint the particle which moving path is constrain by specific path
	 * and without effected by physic path(eg. gravity)
	 */
	
	public void paintConstrainParticle(){
		//draw constrain only		
		for(int i=0; i<strategy.size(); i++){
			int SIZE = this.size.get(i)/2;
			int x = this.position.get(i).getX() - SIZE;
			int y = this.position.get(i).getY() - SIZE;
			if(this.constrainParticle.size() > i && this.constrainParticle.get(i) != null)
				this.constrainParticle.get(i).setLocation(x, y);
			//this.constrainParticle.get(i).repaint();
		}
				
	}
	
	/*
	 * paintFreeParticle is invent to do some physics thing the particle it paint is following the physic
	 * rule and has killZone setting to lift up the code efficientcy of the particle System(Prevent from lagging)
	 */
	public void paintFreeParticle(){
		
	}
	
	public synchronized boolean everyAttackReach(){
		
		if(garbageCollect){
			return false;
		}
		for(int i=0; i<strategy.size() && !garbageCollect; i++){
			if(this.strategy != null && this.strategy.get(i) != null && !this.strategy.get(i).reachPosition(this.position.get(i), this.size.get(i))){
				return false;
			}
			
			if(this.strategy.get(i) != null && !this.strategy.get(i).reachPosition(this.position.get(i), this.size.get(i))){
				return false;
			}
		}
		return true;
	}
	
	private synchronized void garbageDelete(){
		garbageCollect = true;
		for(int i=0; i<strategy.size(); i++){
			if(this.position.size() > i && this.size.size() > i){
				if(this.strategy.get(i).reachPosition(this.position.get(i), this.size.get(i))){
					this.position.remove(i);
					this.index.remove(i);
					this.size.remove(i);
					this.strategy.remove(i);
					this.constrainParticle.get(i).setVisible(false);
					this.remove(this.constrainParticle.get(i));
					this.constrainParticle.remove(i);
				}
			}
			else{
				//deal with special situation
				int smallNum = this.size.size();
				if(smallNum > this.position.size()){
					smallNum = this.position.size();
				}
				for(int err = strategy.size()-1; err>(smallNum-1); err--){
					this.strategy.remove(err);
					if(this.index.size() >= (err+1))
						this.index.remove(err);
					if(this.size.size() >= (err+1))
						this.size.remove(err);
					if(this.position.size() >= (err+1))
						this.position.remove(err);
					if(this.constrainParticle.size() >= (err+1)){
						this.constrainParticle.get(err).setVisible(false);
						this.remove(this.constrainParticle.get(err));
						this.constrainParticle.remove(err);
					}
				}
			}
		}
		garbageCollect = false;
	}
	
	public void render() throws InterruptedException {		
			//paint
			this.paintConstrainParticle();

			//garbage collect
			garbageDelete();

			//update
			for(int i=0; i<strategy.size(); i++){
				this.position.get(i).setX(this.strategy.get(i).getWay(this.index.get(i)).getX());
				this.position.get(i).setY(this.strategy.get(i).getWay(this.index.get(i)).getY());	
				int value = this.index.get(i).intValue();
				this.index.remove(i);
				this.index.add(i, new Integer(value+3));
			}
	}
	

	
	public void run() {		
		//paint
		Thread.currentThread().setPriority(Thread.NORM_PRIORITY + 2);
		while(TurningStonePanel.gameIsOn){
			//paint the particle whose path is on the constrain path
			this.paintConstrainParticle();
			
			//paint the particle whose path is depends on gravity
			this.paintFreeParticle();


			try {
				Thread.sleep(this.sleepMilliTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			//update
			boolean hasUpdate = false;
			for(int i=0; i<strategy.size() && !this.garbageCollect; i++){
				if(strategy.size() != position.size() && strategy.size() != index.size()){
					garbageDelete();
					continue;
				}
				if(this.position.get(i) != null && this.index.get(i) != null){
					this.position.get(i).setX(this.strategy.get(i).getWay(this.index.get(i)).getX());
					this.position.get(i).setY(this.strategy.get(i).getWay(this.index.get(i)).getY());	
					int value = this.index.get(i).intValue();
					this.index.remove(i);
					this.index.add(i, new Integer(value+9));
					hasUpdate = true;
				}
			}
			if(hasUpdate == true){
				//garbage collect
				garbageDelete();
			}
		}

		
	}
	
	public void clearGameParticle(){
		//end all particle
		//----clear constrain particle
		while(this.garbageCollect != false){;}
		for(int i=0; i<this.constrainParticle.size(); i++){
			this.constrainParticle.get(i).setVisible(false);	
		}
		this.constrainParticle.clear();
		System.out.println("Constrain Particle is clear");
		//---------------------------------------------------------
		
		
	}
}
