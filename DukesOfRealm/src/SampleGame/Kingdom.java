package SampleGame;

import SampleGame.army.*;
import SampleGame.army.soldiers.Piquier;
import SampleGame.tiles.Castle;
import SampleGame.tiles.Castle.Orientation;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class Kingdom {
	Castle[] castles = new Castle[Settings.NB_CASTLE];
	Random rand = new Random();
	public static Queue<Soldier> moving_soldier;
	
	public Kingdom(Pane field) {
		
		Settings.CastleImage = new Image(getClass().getResource("/images/Castle.jpg").toExternalForm(), 50, 50, true, true);
		Settings.PiquierImage = new Image(getClass().getResource("/images/Soldier.jpg").toExternalForm(), 25 , 25, false, true);
		for(int i=0; i<Settings.NB_CASTLE; i++) {
			Castle c;
			String duke = "Neutral";
			Soldier[] init_army = new Soldier[Settings.NB_TROUPE];
			if(i<Settings.NB_DUKES)
				duke = Settings.DUKES[i];
			int pos_x = rand.nextInt((int) Settings.SCENE_WIDTH-100) + 50;
			int pos_y = rand.nextInt((int) Settings.SCENE_HEIGHT-100) + 50;
			for(int j=0; j<Settings.NB_TROUPE;j++) {
				init_army[j] = new Piquier(field, pos_x, pos_y , duke);
			}
			c = new Castle(field, pos_x , pos_y , 
					duke, 0, init_army, Orientation.E, new Factory(init_army[0]));
			
			c.getView().setOnMousePressed(e -> {
				c.printStatus();
			});
			
			c.getView().setOnContextMenuRequested(e -> {});
			
			castles[i] = c;
		
		}
		for(Castle c : castles) {
			Castle cible = castles[rand.nextInt(Settings.NB_CASTLE)];
			while(c==cible)
				c = castles[rand.nextInt(Settings.NB_CASTLE)];
			if(c.getDuke_owner()!="Neutral")
				c.giveOrder(new Order(cible,7));
		}
		moving_soldier = new LinkedList<Soldier>();
		
		
	}
	
	public void update() {
		for(Castle c :castles)
			c.updateRound();
		for(Soldier s:moving_soldier)
			s.updateRound();
	}
	
	
	
}
