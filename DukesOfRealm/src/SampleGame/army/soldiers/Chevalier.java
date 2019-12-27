package SampleGame.army.soldiers;

import SampleGame.Settings;
import SampleGame.army.Soldier;
import SampleGame.tiles.Castle;
import javafx.scene.layout.Pane;

public class Chevalier extends Soldier {
	
	public Chevalier(Pane layer, int x, int y, String duke_owner) {
		super(Settings.PiquierImage, x, y);
		
		this.duke_owner = duke_owner;
		this.speed = 6;
		this.health = 3;
		this.damage = 5;
		this.cost = 500;
		this.time_prod = 20;
		this.name = "Chevalier";
	}
	
	public Chevalier(Pane layer, Castle home) {
		super(Settings.PiquierImage, home.getX(), home.getY());
		
		this.duke_owner = home.getDuke_owner();
		this.speed = 6;
		this.health = 3;
		this.damage = 5;
		this.cost = 500;
		this.time_prod = 20;
		this.name = "Chevalier";
	}
	
	@Override
	public Soldier trainNew() {
		return new Chevalier(Settings.field, this.getX(),this.getY(), this.duke_owner);
	}
}
