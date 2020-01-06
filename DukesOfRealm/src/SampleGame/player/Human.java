package SampleGame.player;

import SampleGame.Main;
import SampleGame.army.Soldier.SoldierType;
import SampleGame.tiles.Castle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;


/**
 * Subclass of Player taking care of human type interaction
 * 
 * @author elliotrenel
 *
 */

public class Human extends Player {

	public Human(String name, Castle[] initial_castles) {
		super(name,initial_castles);
		this.type = PlayerType.H;
	}
	
	public Human(String name) {
		super(name);
		this.type = PlayerType.H;
	}

	@Override
	protected void doStuff() {
		for(Castle c : owned_castle) {
			c.updateRound();
		}
	}

	@Override
	public ContextMenu giveMenu(Castle castle) {
		PlayerMenu menu = new PlayerMenu(castle);
		return menu;
	}	
	
	public void sendArmy() {
		
	}
	
}
