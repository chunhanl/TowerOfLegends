package soundUtil;

public class SoundThread implements Runnable{
	//data member
	private Sound sound;
	private SoundPlayer player;
	private Thread thread;
	//constructor
	public SoundThread(String soundURL, SoundPlayer player){
		sound = new Sound(soundURL);
		this.player = player;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop(){
		thread.interrupt();
		sound.stop();
		sound = null;
	}
	
	public void play(){
		sound.play();
	}
	
	//Thread method
	public void run() {
		sound.play();
		Thread.yield();
		while(sound.getCurrentLength() < sound.getSoundLength()/1.5){
		}
		
		sound.stop();
		sound = null;
		player.removeSound(this);
	}
}
