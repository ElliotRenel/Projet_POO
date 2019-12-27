package SampleGame.army.soldiers;

import SampleGame.Settings;
import SampleGame.army.Soldier;
import SampleGame.tiles.Castle;
import javafx.scene.layout.Pane;

/**
 * Class for creating the soldier type "Piquier"
 * @author elliotrenel
 *
 */
public class Piquier extends Soldier {
	
	/**
	 * Piquier constructor with the corresponding speeds/health/...
	 * 
	 * @param layer The global visual window
	 * @param x The sprite x position (his home position)
	 * @param y The sprite y position (his home position)
	 * @param duke_owner The Piquier's owner name
	 */
	public Piquier(Pane layer, int x, int y, String duke_owner) {
		super(layer, Settings.PiquierImage, x, y);
		
		this.duke_owner = duke_owner;
		this.speed = 5;
		this.health = 1;
		this.damage = 1;
		this.cost = 100;
		this.time_prod = 5;
		this.name = "Piquier";
		
	}
	
	/**
	 * Second constructor from Castle
	 * 
	 * @param layer The global visual window
	 * @param home The soldier's home
	 */
	public Piquier(Pane layer, Castle home) {
		super(layer, Settings.PiquierImage, home.getX(),home.getY());
		
		this.duke_owner = home.getDuke_owner();
		this.speed = 5;
		this.health = 1;
		this.damage = 1;
		this.cost = 100;
		this.time_prod = 5;
		this.name = "Piquier";
		
	}

}
