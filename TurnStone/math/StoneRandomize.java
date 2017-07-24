package math;

import java.util.Random;
//a class management the whole falling stone probability
public class StoneRandomize {
	//data member
	private static double heart;
	private static double dark;
	private static double bright;
	private static double fire;
	private static double water;
	private static double wood;
	private static Random rand = new Random();
	//constructor	
	public StoneRandomize(){
		
	}
	
	static{
		StoneRandomize.constructGeneralFallProbability();
	}
	//getter and setter 
	public static double getHeartFallProb(){
		return heart;
	}
	
	public static void setHeartFallProb(double p){
		heart = p;
	}
	
	public static double getDarkFallProb(){
		return dark;
	}
	
	public static void setDarkFallProb(double p){
		dark = p;
	}
	
	public static double getBrightFallProb(){
		return bright;
	}
	
	public static void setBrightFallProb(double p){
		bright = p;
	}
	
	public static double getFireFallProb(){
		return fire;
	}
	
	public static void setFireFallProb(double p){
		fire = p;
	}
	
	public static double getWaterFallProb(){
		return water;
	}
	
	public static void setWaterFallProb(double p){
		water = p;
	}
	
	public static double getWoodFallProb(){
		return wood;
	}
	
	public static void setWoodFallProb(double p){
		wood = p;
	}
	
	//static method
	public static void reconstructFallProbability(double h, double d, double b, double f, double wa, double wo){
		double conserve = h + d + b + f + wa + wo;
		if(conserve > 1.0){
			
		}
		else{
			double remain = 1.0 - conserve;
			remain /=6.0;
			
			heart = remain + h;
			dark = remain + d;
			bright = remain + b;
			fire = remain + f;
			water = remain + wa;
			wood = remain + wo;
		}
	}
	
	public static void constructGeneralFallProbability(){
		heart = 1.0/6.0;
		dark = 1.0/6.0;
		bright = 1.0/6.0;
		fire = 1.0/6.0;
		water = 1.0/6.0;
		wood = 1.0/6.0;
	}
	
	public static int randStoneType(double fallOn){  //passive random (need outer random value
		vec2 range = new vec2(0,0);
		int enlargeRatio = 100000000;
		int type = 0;
		for(int i=0; i<6; i++){
			switch(i){
				case 0:
					range = range.add(new vec2(0, (int)(dark*enlargeRatio)));
					break;
				case 1:
					range = range.add(new vec2(range.getY(), (int)(bright*enlargeRatio)));
					break;
				case 2:
					range = range.add(new vec2(range.getY(), (int)(fire*enlargeRatio)));
					break;
				case 3:
					range = range.add(new vec2(range.getY(), (int)(water*enlargeRatio)));
					break;
				case 4:
					range = range.add(new vec2(range.getY(), (int)(wood*enlargeRatio)));
					break;
				case 5:
					range = range.add(new vec2(range.getY(), (int)(heart*enlargeRatio)));
					break;
			}

			if(range.inRange((int)fallOn * enlargeRatio)){
				type = i;
			}
		}
		return type;
	}
	
	public static int randStoneType(){  //auto random
		double fallOn = rand.nextDouble();
		vec2 range = new vec2(0,0);
		int enlargeRatio = 10000000;
		int type = 0;
		for(int i=0; i<6; i++){
			int j = range.getY();
			switch(i){
				case 0:
					range = range.add(new vec2(0, (int)(dark*enlargeRatio)));
					break;
				case 1:
					range = range.add(new vec2(0, (int)(bright*enlargeRatio)));				
					break;
				case 2:
					range = range.add(new vec2(0, (int)(fire*enlargeRatio)));
					break;
				case 3:
					range = range.add(new vec2(0, (int)(water*enlargeRatio)));
					break;
				case 4:
					range = range.add(new vec2(0, (int)(wood*enlargeRatio)));
					break;
				case 5:
					range = range.add(new vec2(0, (int)(heart*enlargeRatio)));
					break;
			}
			range.setX(j);
			
			if(range.inRange((int)(fallOn * enlargeRatio))){
				type = i;
				break;
			}
		}
		return type;
	}
	
}
