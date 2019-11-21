package SampleGame.cases;

import SampleGame.Order;
import SampleGame.Soldier;

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
	private Order target; 
	private Orientation door;						/* Not used*/
	
	public Castle(String duke_owner, int treasure, Soldier[] army, Order target, Orientation door) {
		this.duke_owner = duke_owner;
		this.treasure = treasure;
		this.army = army;
		this.target = target;
		this.door = door;
	}
	
	
}
