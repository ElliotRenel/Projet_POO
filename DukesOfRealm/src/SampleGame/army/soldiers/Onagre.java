package SampleGame.army.soldiers;

import SampleGame.Settings;
import SampleGame.army.Soldier;
import SampleGame.tiles.Castle;
import javafx.scene.layout.Pane;

public class Onagre extends Soldier {

	public Onagre(Pane layer, int x, int y, String duke_owner) {
		super(Settings.PiquierImage, x, y);
		
		this.duke_owner = duke_owner;
		this.speed = 1;
		this.health = 5;
		this.damage = 10;
		this.cost = 1000;
		this.time_prod = 50;
		this.name = "Onagre";
	}
	
	public Onagre(Pane layer, Castle home) {
		super(Settings.PiquierImage, home.getX(), home.getY());
		
		this.duke_owner = home.getDuke_owner();
		this.speed = 1;
		this.health = 5;
		this.damage = 10;
		this.cost = 1000;
		this.time_prod = 50;
		this.name = "Onagre";
	}
	
	@Override
	public Soldier trainNew() {
		return new Onagre(Settings.field, this.getX(),this.getY(), this.duke_owner);
	}
}
