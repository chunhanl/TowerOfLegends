package util;

import java.util.Random;

public class Time {
	//data member
	private int hr;
	private int min;
	private int sec;
	private long startTimeMilli;
	public static Random rand = new Random();
	
	//constructor
	public Time(){
		this(0);
	}
	
	public Time(long timeMilli){
		int trans = (int) timeMilli / 1000 % (60*60*24);
		setSec(trans % 60);
		setMin(trans % 3600 / 60);
		setHr(trans % (3600 * 24) / 3600);
	}
	
	public Time(Time t){
		hr = t.getHr();
		min = t.getMin();
		sec = t.getSec();
		startTimeMilli = t.getStartTimeMilli();
	}
	
	//getter & setter
	public int getHr() {
		return hr;
	}

	public void setHr(int hr) {
		this.hr = hr;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public long getStartTimeMilli() {
		return startTimeMilli;
	}

	public void setStartTimeMilli(long startTimeMilli) {
		this.startTimeMilli = startTimeMilli;
	}
	
	//time method
	public Time buildTime(long milli){
		int trans = (int) milli / 1000 % (60*60*24);
		Time tt = new Time();
		tt.setSec(trans % 60);
		tt.setMin(trans % 3600 / 60);
		tt.setHr(trans % (3600 * 24) / 3600);
		return tt;
	}
	
	public void buildThisTime(long milli){
		int trans = (int) milli / 1000 % (60*60*24);
		this.setSec(trans % 60);
		this.setMin(trans % 3600 / 60);
		this.setHr(trans % (3600 * 24) / 3600);
	}
	
	//print method
	public String toString(){
		String str = "";
		if(hr < 10)
			str += "0" + hr + ":";
		else
			str += hr + ":";
		
		if(min < 10)
			str += "0" + min + ":";
		else
			str += min + ":";
		
		if(sec < 10)
			str += "0" + sec;
		else
			str += sec;
		return str;
	}
}
