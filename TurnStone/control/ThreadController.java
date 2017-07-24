package control;

import java.util.ArrayList;

public class ThreadController {
	//data member
	public static ArrayList<Thread> threadController;
	
	static{
		threadController = new ArrayList<Thread>();
	}
	
	//static method
	public static void interruptAll(){
		for(int i=0; i<threadController.size(); i++){
			threadController.get(i).interrupt();
		}
	}
	
}
