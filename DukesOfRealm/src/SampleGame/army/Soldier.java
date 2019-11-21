package SampleGame.army;

public class Soldier {
	private String name;
	private int cost, time_prod;
	private int speed, health, damage;
	
	public Soldier(String name, int cost, int time_prod, int speed, int health, int damage) {
		this.name = name;
		this.cost = cost;
		this.time_prod = time_prod;
		this.speed = speed;
		this.health = health;
		this.damage = damage;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public void setTime_prod(int time_prod) {
		this.time_prod = time_prod;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	
	
}
