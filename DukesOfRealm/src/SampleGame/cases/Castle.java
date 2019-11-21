package SampleGame.cases;

import SampleGame.army.*;

public class Castle extends Tile{
	
	public enum Orientation{
		N,
		E,
		S,
		W;
	}
	
	private String duke_owner;
	private int treasure;
	private Soldier[] army;
	private int army_nb;
	private Order target; 
	private final Orientation door;						/* Not used*/
	private Factory fact;
	
	
	public Castle(String duke_owner, int treasure, Soldier[] army, int army_nb, Order target, Orientation door, Factory fact) {
		this.duke_owner = duke_owner;
		this.treasure = treasure;
		this.army = army;
		this.army_nb = army_nb;
		this.target = target;
		this.door = door;
		this.fact = fact;
	}
	
		
	//	addToArmy checks if there is a new soldier to add from production and then adds or not the right soldier to the army
	
	private void addToArmy() {
		int result = fact.update();
		switch(result) {
		
		case 0:
			return;
		case 1:
			army[army_nb] = new Piquier(this);
			army_nb ++;
		}
	}
	
	
	//sendArmy sends maximum 3 soldiers through the gates
	private void sendArmy() {
		
	}
	
	
	//updateRound is called at each turn to update the differents mechanics
	
	public void updateRound(){
		addToArmy();
		
		sendArmy();
		
	}
	
	


	public String getDuke_owner() {
		return duke_owner;
	}


	public void setDuke_owner(String duke_owner) {
		this.duke_owner = duke_owner;
	}
	
	
	
}
