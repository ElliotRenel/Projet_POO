package SampleGame.army;

import SampleGame.tiles.Castle;

public class Piquier extends Soldier {
	
	public Piquier(Castle home) {
		this.name = "Piquier";
		this.duke_owner = home.getDuke_owner();
		this.cost = 100;
		this.time_prod = 5;
		this.speed = 2;
		this.health = 1;
		this.damage = 1;
		this.tile_x = home.getX();
		this.tile_y = home.getY();
	}

}
