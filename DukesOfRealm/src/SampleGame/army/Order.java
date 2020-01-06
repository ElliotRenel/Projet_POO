package SampleGame.army;

import SampleGame.army.Soldier.SoldierType;
import SampleGame.tiles.Castle;

/**
 * Order class allows a castle to send a number of troops of a SoldierType to a castle
 * @author theod
 *
 */
public class Order {
	private Castle target;
	private int troops;
	private SoldierType type;
	
	
	/**
	 * Allows to build an order
	 * @param target The castle targeted by the order
	 * @param troops The number of troops to send
	 * @param type The type of soldier sent
	 */
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
