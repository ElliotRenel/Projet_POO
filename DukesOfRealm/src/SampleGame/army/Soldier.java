package SampleGame.army;

import SampleGame.cases.Castle;

public class Soldier {
	private String name, duke_owner;
	private int cost, time_prod;
	private int speed, health, damage;
	int tile_x,tile_y;
	
	public Soldier(String name, Castle home, int cost, int time_prod, int speed, int health, int damage) {
		this.name = name;
		this.duke_owner = home.getDuke_owner();
		this.cost = cost;
		this.time_prod = time_prod;
		this.speed = speed;
		this.health = health;
		this.damage = damage;
		this.tile_x = home.getX();
		this.tile_y = home.getY();
	}
	
	public void executeOrder(Castle target) {
		
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
