package SampleGame.army.soldiers;

import SampleGame.Settings;
import SampleGame.army.Soldier;
import SampleGame.player.Player;
import SampleGame.tiles.Castle;
import javafx.scene.layout.Pane;

public class Onagre extends Soldier {

	public static int COST = 1000;
	
	public Onagre(Pane layer, int x, int y, Player owner) {
		super(Settings.OnagreImage, x, y, owner);
		
		this.owner = owner;
		this.speed = 1;
		this.health = 5;
		this.damage = 10;
		this.cost = 1000;
		this.time_prod = 50;
		this.type = SoldierType.O;
	}
	
	public Onagre(Castle home) {
		super(Settings.OnagreImage, home.getDoorImg().getX_out(), home.getDoorImg().getY_out(), home.getOwner());
		
		this.owner = home.getOwner();
		this.speed = 1;
		this.health = 5;
		this.damage = 10;
		this.cost = 1000;
		this.time_prod = 50;
		this.type = SoldierType.O;
	}
	
	@Override
	public Soldier trainNew() {
		return new Onagre(Settings.field, this.getX(),this.getY(), this.owner);
	}
}
