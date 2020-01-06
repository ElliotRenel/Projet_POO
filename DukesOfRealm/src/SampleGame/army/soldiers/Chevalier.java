package SampleGame.army.soldiers;

import SampleGame.Settings;
import SampleGame.army.Soldier;
import SampleGame.player.Player;
import SampleGame.tiles.Castle;
import javafx.scene.layout.Pane;

public class Chevalier extends Soldier {
	
	public Chevalier(Pane layer, int x, int y, Player owner) {
		super(Settings.PiquierImage, x, y, owner);
		
		this.owner = owner;
		this.speed = 6;
		this.health = 3;
		this.damage = 5;
		this.cost = 500;
		this.time_prod = 20;
		this.type = SoldierType.C;
	}
	
	public Chevalier(Castle home) {
		super(Settings.PiquierImage, home.getX(), home.getY(), home.getOwner());
		
		this.owner = home.getOwner();
		this.speed = 6;
		this.health = 3;
		this.damage = 5;
		this.cost = 500;
		this.time_prod = 20;
		this.type = SoldierType.C;
	}
	
	@Override
	public Soldier trainNew() {
		return new Chevalier(Settings.field, this.getX(),this.getY(), this.owner);
	}
}
