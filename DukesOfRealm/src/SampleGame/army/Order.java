package SampleGame.army;

import SampleGame.army.Soldier.SoldierType;
import SampleGame.tiles.Castle;

public class Order {
	private Castle target;
	private int troops;
	private SoldierType type;
	
	public Order(Castle target, int troops, SoldierType type) {
		this.target = target;
		this.troops = troops;
		this.type = type;
	}
	
	public Castle getTarget() {
		return target;
	}
	
	public int getTroops() {
		return troops;
	}
	
	public void sendTroops() {
		troops--;
	}

	public SoldierType getType() {
		return type;
	}
	
}
