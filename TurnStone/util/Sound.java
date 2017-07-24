package util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound{
	//data member
	private String soundURL = null;
	private AudioInputStream music = null;
	private int musicTime = 0;
	private Time time;
	
	private DataLine.Info info = null;
	private Clip player = null;
	
	private int loopTime = -1;   //loop again and again
	private boolean loopEnable = false;
	
	private long frameLength;
	
	//constructor
	public Sound(String URL){
		this.soundURL = URL;
		this.buildSound(URL);
		this.frameLength = music.getFrameLength();
	}
	
	//audio method
	public void play(){
		player.start();
	}
	
	public void stop(){
		player.stop();
	}
	
	public void loop(){
		loopEnable = !loopEnable;
		
		if(loopEnable){
			player.loop(loopTime);
		}
		else
			player.loop(0);
	}
	
	public void loop(boolean b){
		loopEnable = b;
		
		if(loopEnable){
			player.loop(loopTime);
		}
		else
			player.loop(0);
	}
	
	//getter and setter
	public void setLoopTimes(int times){
		this.loopTime = times;
	}
	
	public boolean getLoopEnable(){
		return this.loopEnable;
	}
	
	public Time getTime(){
		return new Time(this.time);
	}
	
	public long getSoundLength(){
		return this.frameLength;
	}
	
	public long getCurrentLength(){
		return player.getLongFramePosition();
	}
	
	//file method
	public void buildSound(String url){
		File f = new File("./res/sound/" + soundURL);
		
		try {
			music = AudioSystem.getAudioInputStream(f);
		} 
		catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
			System.exit(1);
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		info = new DataLine.Info(Clip.class, music.getFormat());
		
		try {
			player = (Clip)AudioSystem.getLine(info);
		} 
		catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		try {
			player.open(music);
		} 
		catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.musicTime = (int)player.getMicrosecondLength()/1000;   //convert it to milli-seconds
		time = new Time(musicTime);
	}
	
	public void rebuildSound(String URL){
		buildSound(URL);
	}


}
