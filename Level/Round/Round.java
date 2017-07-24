package Round;

import counter.Enemy;

public class Round {
	//data member
	public Enemy[] enemy;
	public boolean isConstruct;
	//constructor
	public Round(){
		this.isConstruct = false;
	}
	
	//getter and setter
	public void addEnemy(Enemy[] enemy){
		this.enemy = enemy;
	}
	//method
	public boolean enemyAllDead(){
		boolean allDead = true;

		
		for(int i=0; i<enemy.length; i++){
			if(enemy[i].currentBlood != 0)
				allDead = false;
		}
		
		return allDead;
	}
	
	public void decEnemyCD(){
		for(int i=0; i<enemy.length; i++){
			enemy[i].initialCD--;
		}
	}
	
	public void reINIEnemyCD(){
		for(int i=0; i<enemy.length; i++){
			if(enemy[i].attackCD == 0)
				enemy[i].initialCD = enemy[i].attackCD;
		}
	}
}
