package SampleGame.tiles;

import SampleGame.army.*;
import java.util.LinkedList;
import java.util.Queue;

public class Castle extends Tile{
	
	public enum Orientation{
		N,
		E,
		S,
		W;
	}
	
	private String duke_owner;
	private int treasure;
	private Queue<Soldier> army;
	private int army_nb;
	private Order order; 
	private final Orientation door;						/* Not used*/
	private Factory fact;
	
	
	public Castle(String duke_owner, int treasure, Soldier[] initial_army, int army_nb, Order target, Orientation door, Factory fact) {
		this.duke_owner = duke_owner;
		this.treasure = treasure;
		for(Soldier s : initial_army) this.army.add(s);
		this.army_nb = army_nb;
		this.order = target;
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
			army.add(new Piquier(this));
			army_nb ++;
		}
	}
	
	public void addToArmy(Soldier s) {
		army.add(s);
	}
	
	public void getAttacked(Soldier s) {
		if(army.isEmpty()) {
			this.duke_owner = s.getDuke_owner();
			army.add(s);
		}else {
			army.remove();
			present_army.remove(s);
		}
	}
	
	
	//executeOrder sends maximum 3 soldiers through the gates to attack the castle pointed by order
	private void executeOrder() {
		if (order.getTroops()!=0) {
			for(int i=0; i<3 && order.getTroops()>0;i++, order.sendTroops()) {
				Soldier s = army.remove();
				s.executeOrder66(order.getTarget());
			}
		}		
	}	
	
	//updateRound is called at each turn to update the differents mechanics
	
	@Override
	public void updateRound(){
		super.updateRound();
		
		addToArmy();
		
		executeOrder();
		
	}
	
	


	public String getDuke_owner() {
		return duke_owner;
	}


	public void setDuke_owner(String duke_owner) {
		this.duke_owner = duke_owner;
	}
	
	
	
}
