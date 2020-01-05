package SampleGame;

import SampleGame.army.*;
import SampleGame.army.soldiers.Piquier;
import SampleGame.player.*;
import SampleGame.tiles.Castle;
import SampleGame.tiles.Castle.Orientation;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class Kingdom {
	public static Castle[] castles = new Castle[Settings.NB_CASTLE];
	public static Queue<Player> players = new LinkedList<Player>();
	Random rand = new Random();
	
	public Kingdom(Pane field) {
		
		
		Settings.CastleImage = new Image(getClass().getResource("/images/CastleBis.jpg").toExternalForm(), 75, 75, true, true);
		Settings.PiquierImage = new Image(getClass().getResource("/images/Soldier.jpg").toExternalForm(), 25 , 25, false, true);
		Settings.DoorImage = new Image(getClass().getResource("/images/Door_Castle.jpg").toExternalForm(), 25 , 25, false, true);
		Settings.field = field;
		
		for(int i=0; i<Settings.NB_CASTLE; i++) {
			Castle c;
			Player duke = new VoidPlayer();
			Soldier[] init_army = new Soldier[Settings.NB_TROUPE];
			if(i<Settings.NB_DUKES) {
				duke = new AI(Settings.DUKES[i]);
				players.add(duke);
			}
			int pos_x = rand.nextInt((int) Settings.SCENE_WIDTH-100) + 50;
			int pos_y = rand.nextInt((int) Settings.SCENE_HEIGHT-100) + 50;
			for(int j=0; j<Settings.NB_TROUPE;j++) {
				init_army[j] = new Piquier(pos_x, pos_y , duke);
			}
			c = new Castle(pos_x , pos_y , 
					duke, 1000, init_army, Orientation.E, new Factory());
			
			c.getView().setOnContextMenuRequested(e -> {c.showMenu();});
			
			castles[i] = c;		
		}		
	}
	
	public Player update() {
		if(players.size()==1)
			return players.remove();
		
		for(Player p : players)
			if(!p.update()) {
				players.remove(p);
				System.out.println(p.getName()+" is no more.");
			}

		
		return null;
	}
	
	
	
}
