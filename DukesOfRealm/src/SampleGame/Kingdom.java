package SampleGame;

import SampleGame.army.*;
import SampleGame.tiles.Castle;
import SampleGame.tiles.Castle.Orientation;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Random;


public class Kingdom {
	Castle[] castles = new Castle[Settings.NB_CASTLE];
	Random rand = new Random();
	
	public Kingdom(Pane field) {
		
		Image CastleImage = new Image(getClass().getResource("/images/Castle.jpg").toExternalForm(), 50, 50, true, true);
		for(int i=0; i<Settings.NB_CASTLE; i++) {
			Castle c;
			String duke = "";
			Soldier[] init_army = new Soldier[1000];
			if(i<Settings.NB_DUKES)
				duke = Settings.DUKES[i];
			for(int j=0; j<Settings.NB_TROUPE;j++) {
				init_army[j] = new Piquier(duke);
			}
			c = new Castle(field, CastleImage, rand.nextInt((int) Settings.SCENE_WIDTH), rand.nextInt((int) Settings.SCENE_HEIGHT), 
					duke, 0, init_army, Orientation.E, new Factory(init_army[0]));
			
			c.getView().setOnMousePressed(e -> {
				c.printStatus();
			});
			
			c.getView().setOnContextMenuRequested(e -> {});
			
			castles[i] = c;
		
		}
	}
	
	public void update() {
		for(Castle c :castles)
			c.updateRound();
	}
	
	
}
