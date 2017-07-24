package soundUtil;

import java.util.ArrayList;

public class SoundPlayer {
	//data member
	private ArrayList<SoundThread> soundThread;
	private SoundThread bgMusic;
	private int soundNum;
	
	//constructor
	public SoundPlayer(){
		this.soundThread = new ArrayList<SoundThread>();
		this.soundThread.clear();
		this.soundNum = 0;
	}
	
	//method
	public void addSound(String URL){
		this.soundThread.add(new SoundThread(URL, this));
		this.soundNum++;
	}
	
	public void removeSound(SoundThread s){
		this.soundThread.remove(s);
		this.soundNum--;
	}
	
	public int getSoundNum(){
		return soundNum;
	}
	
	public void setBGMusic(String URL){
		if(this.bgMusic != null){
			this.bgMusic.stop();
			this.bgMusic = null;
		}
		
		this.bgMusic = new SoundThread(URL, this);
	}
	
	public void stopBGMusic(){
		this.bgMusic.stop();
		this.bgMusic = null;
	}
}
