package SampleGame.army.soldiers;

import SampleGame.Settings;
import SampleGame.army.Soldier;
import SampleGame.player.Player;
import SampleGame.tiles.Castle;
import javafx.scene.layout.Pane;

public class Chevalier extends Soldier {
	public static final int COST = 500;
	public static final int HEALTH = 3;
	public static final int DAMAGE = 5;
	
	
	public Chevalier(Pane layer, int x, int y, Player owner) {
		super(Settings.ChevalierImage, x, y, owner);
		
		this.owner = owner;
		this.speed = 4;
		this.health = HEALTH;
		this.damage = DAMAGE;
		this.cost = COST;
		this.time_prod = 20;
		this.type = SoldierType.C;
	}
	
	public Chevalier(Castle home) {
		super(Settings.ChevalierImage, home.getDoorImg().getX_out(), home.getDoorImg().getY_out(), home.getOwner());
		
		this.owner = home.getOwner();
		this.speed = 6;
		this.health = 3;
		this.damage = 5;
		this.cost = COST;
		this.time_prod = 20;
		this.type = SoldierType.C;
	}
	
	@Override
	public Soldier trainNew() {
		return new Chevalier(Settings.field, this.getX(),this.getY(), this.owner);
	}
}
