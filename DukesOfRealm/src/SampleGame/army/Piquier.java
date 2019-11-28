package SampleGame.army;

import SampleGame.tiles.Castle;

public class Piquier extends Soldier {
	
	public Piquier(String owner, int x, int y) {
		this.name = "Piquier";
		this.duke_owner = owner;
		this.cost = 100;
		this.time_prod = 5;
		this.speed = 2;
		this.health = 1;
		this.damage = 1;
		this.tile_x = x;
		this.tile_y = y;
	}

}
