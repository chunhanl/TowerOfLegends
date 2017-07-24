package gameUtil;

import java.util.ArrayList;

public class DataPassenger {
	//data member
	public static ArrayList<ComboMessage> cMessages;
	public static ArrayList<greatAttack> gAttack;
	
	//static initial
	static{
		cMessages = new ArrayList<ComboMessage>();
		gAttack = new ArrayList<greatAttack>();
	}
	
	public static void clearAllData(){
		cMessages.clear();
		gAttack.clear();
	}
}
