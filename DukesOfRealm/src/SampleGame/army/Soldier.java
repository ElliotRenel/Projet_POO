package SampleGame.army;

import SampleGame.tiles.Castle;

public class Soldier {
	protected String name, duke_owner;
	protected int cost, time_prod;
	protected int speed, health, damage;
	protected int tile_x,tile_y;
	
	public Soldier() {}
	
	public void executeOrder66(Castle target) {
		
	}

	public String getName() {
		return name;
	}

	public String getDuke_owner() {
		return duke_owner;
	}	
	
	public int getCost() {
		return cost;
	}

	public int getTime_prod() {
		return time_prod;
	}

	public int getSpeed() {
		return speed;
	}

	public int getHealth() {
		return health;
	}

	public int getDamage() {
		return damage;
	}

}
