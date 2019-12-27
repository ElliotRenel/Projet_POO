package SampleGame.army.soldiers;

import SampleGame.Settings;
import SampleGame.army.Soldier;
import javafx.scene.layout.Pane;

/**
 * Class for creating the soldier type "Piquier"
 * @author elliotrenel
 *
 */
public class Piquier extends Soldier {
	
	public Piquier(Pane layer, int x, int y, String duke_owner) {
		super(layer, Settings.PiquierImage, x, y, duke_owner, 5, 1, 1);
		this.cost = 100;
		this.time_prod = 5;
		this.name = "Piquier";
		
	}

}
