package SampleGame.army;

import SampleGame.tiles.Castle;

public class Order {
	private Castle target;
	private int troops;
	
	public Order(Castle target, int troops) {
		this.target = target;
		this.troops = troops;
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
	
}
