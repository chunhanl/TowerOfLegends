package DirectPower;

public class DirectPowerGetter {
	//static method
	public static Power getDirectPower(String url){
		Power ref  = null;
		
		if(url.equals("LtoD")){
			ref = new TurnBright2Dark();
		}
		
		else
			
		if(url.equals("LtoH")){
			ref = new TurnBright2Heart();
		}
		
		else
			
		if(url.equals("DtoL")){
			ref = new TurnDark2Bright();
		}
		
		else
			
		if(url.equals("DtoH")){
			ref = new TurnDark2Heart();
		}
		
		else
			
		if(url.equals("FtoH")){
			ref = new TurnFire2Heart();
		}
		
		else
			
		if(url.equals("HtoL")){
			ref = new TurnHeart2Bright();
		}
		
		else
			
		if(url.equals("HtoD")){
			ref = new TurnHeart2Dark();
		}
		
		else
			
		if(url.equals("HtoF")){
			ref = new TurnHeart2Fire();
		}
		
		else
			
		if(url.equals("HtoW")){
			ref = new TurnHeart2Water();
		}
		
		else
			
		if(url.equals("HtoM")){
			ref = new TurnHeart2Wood();
		}
		
		else
			
		if(url.equals("WtoH")){
			ref = new TurnWater2Heart();
		}
		
		else
			
		if(url.equals("MtoH")){
			ref = new TurnWood2Heart();
		}
		
		else
			
		if(url.equals("LongTurnStone")){
			ref = new SuperLongTurningStoneTime();
		}
		
		else
		
		if(url.equals("MtoF")){
			ref = new TurnWood2Fire();
		}
		
		else
			
		if(url.equals("FtoM")){
			ref = new TurnFire2Wood();
		}
		
		else
			
		if(url.equals("WtoF")){
			ref = new TurnWood2Fire();
		}	
		
		else
			
		if(url.equals("FtoD")){
			ref = new TurnFire2Dark();
		}
		
		else
			
		if(url.equals("MtoD")){
			ref = new TurnWood2Dark();
		}
		
		if(ref == null){
			System.out.println("error---Fail to get DirectPower, System crash");
			System.exit(1);
		}
		
		return ref;
	}

}
