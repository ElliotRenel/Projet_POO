package SampleGame.army;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Piquier extends Soldier {
	
	public Piquier(Pane layer, Image image, int x, int y, String duke_owner) {
		super(layer, image, x, y, duke_owner, 27, 1, 1);
		this.cost = 100;
		this.time_prod = 5;
		this.name = "peasant";
		
	}

}
