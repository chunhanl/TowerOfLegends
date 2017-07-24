package Animation;

import java.util.TimerTask;

import InterFace.Battle;
import motionMethod.MotionStrategy;
import particle.Particle;

public class ParticleAnimation extends TimerTask{

	public void run() {
		try {
			Battle.particleSystemREF.render();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
