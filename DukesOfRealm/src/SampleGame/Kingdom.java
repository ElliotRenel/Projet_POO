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
	Soldier s;
	
	public Kingdom(Pane field) {
		
		Image CastleImage = new Image(getClass().getResource("/images/Castle.jpg").toExternalForm(), 50, 50, true, true);
		Image SoldierImage = new Image(getClass().getResource("/images/Soldier.jpg").toExternalForm(), 50 , 50, false, true);
		for(int i=0; i<Settings.NB_CASTLE; i++) {
			Castle c;
			String duke = "Neutral";
			Soldier[] init_army = new Soldier[1000];
			if(i<Settings.NB_DUKES)
				duke = Settings.DUKES[i];
			int pos_x = rand.nextInt((int) Settings.SCENE_WIDTH);
			int pos_y = rand.nextInt((int) Settings.SCENE_HEIGHT);
			for(int j=0; j<Settings.NB_TROUPE;j++) {
				init_army[j] = new Piquier(field, SoldierImage, pos_x, pos_y , duke);
			}
			c = new Castle(field, CastleImage, pos_x , pos_y , 
					duke, 0, init_army, Orientation.E, new Factory(init_army[0]));
			
			c.getView().setOnMousePressed(e -> {
				c.printStatus();
			});
			
			c.getView().setOnContextMenuRequested(e -> {});
			
			castles[i] = c;
		
		}
		
		
		//Test movements soldier
		s = new Soldier(field, SoldierImage, 50, 50, "De Bourgogne", 10, 1, 1);
		s.executeOrder66(castles[0]);
		
		
	}
	
	public void update() {
		for(Castle c :castles)
			c.updateRound();
		//Test
		s.updateRound();
	}
	
	
	
}
