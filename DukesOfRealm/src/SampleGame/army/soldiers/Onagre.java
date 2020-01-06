package SampleGame.army.soldiers;

import SampleGame.Settings;
import SampleGame.army.Soldier;
import SampleGame.player.Player;
import SampleGame.tiles.Castle;
import javafx.scene.layout.Pane;

/**
 *  A soldier type to produce and send to the battlefield
 * @author theod
 *
 */
public class Onagre extends Soldier {

	public static final int COST = 1000;
	public static final int HEALTH = 5;
	public static final int DAMAGE = 10;
	
	/**
	 * Onagre constructor
	 * @param layer The pane of the game
	 * @param x The x coordinate to spawn the unit
	 * @param y The y coordinate to spawn the unit
	 * @param owner The player who owns this unit
	 */
	public Onagre(Pane layer, int x, int y, Player owner) {
		super(Settings.OnagreImage, x, y, owner);
		
		this.owner = owner;
		this.speed = COST;
		this.health = HEALTH;
		this.damage = DAMAGE;
		this.cost = 1000;
		this.time_prod = 50;
		this.type = SoldierType.O;
	}
	
	/**
	 * Constructor version where only the home castle is given
	 * @param home The castle which produces the unit
	 */
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
