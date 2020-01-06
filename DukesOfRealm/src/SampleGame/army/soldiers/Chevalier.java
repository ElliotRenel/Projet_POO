package SampleGame.army.soldiers;

import SampleGame.Settings;

import SampleGame.army.Soldier;
import SampleGame.player.Player;
import SampleGame.tiles.Castle;
import javafx.scene.layout.Pane;
/**
 * A soldier type to produce and send to the battlefield
 * @author theod
 *
 */
public class Chevalier extends Soldier {
	public static final int COST = 500;
	public static final int HEALTH = 3;
	public static final int DAMAGE = 5;
	
	/**
	 * Chevalier constructor
	 * @param layer The pane of the game
	 * @param x The x coordinate to spawn the unit
	 * @param y The y coordinate to spawn the unit
	 * @param owner The player who owns this unit
	 */
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
	
	/**
	 * Constructor version where only the home castle is given
	 * @param home The castle which produces the unit
	 */
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
