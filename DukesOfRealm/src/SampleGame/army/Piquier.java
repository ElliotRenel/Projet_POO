package SampleGame.army;

public class Piquier extends Soldier {
	
	public Piquier(String owner) {
		this.name = "Piquier";
		this.duke_owner = owner;
		this.cost = 100;
		this.time_prod = 5;
		this.speed = 2;
		this.health = 1;
		this.damage = 1;
	}

}
