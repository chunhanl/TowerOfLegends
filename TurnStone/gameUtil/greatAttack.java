package gameUtil;

public class greatAttack {
	public int[] attackerID;
	public int[] attackToEnemyID;
	
	public greatAttack(int[] attackerID, int[] attackToEnemyID){
		this.attackerID = new int[attackerID.length];
		this.attackToEnemyID = new int[attackToEnemyID.length];
		
		if(attackerID.length != attackToEnemyID.length){
			System.out.println("Lose infomation about attacker or enemy's");
			System.exit(0);
		}
		
		for(int i=0; i<attackerID.length; i++){
			this.attackerID[i] = attackerID[i];
			this.attackToEnemyID[i] = attackToEnemyID[i];
		}
	}
	
}
