package SampleGame.cases;

import SampleGame.army.Factory;
import SampleGame.army.Order;
import SampleGame.army.Soldier;

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
	
		
	private void addToArmy() {
		int result = fact.update();
		switch(result) {
		
		case 0:
			return;
		case 1:
			army[army_nb] = new Soldier("Piquier", this, 100, 5, 2, 1, 1);
			army_nb ++;
		}
	}
	
	
	public void updateRound(){
		addToArmy();
	}


	public String getDuke_owner() {
		return duke_owner;
	}


	public void setDuke_owner(String duke_owner) {
		this.duke_owner = duke_owner;
	}
	
	
	
}
