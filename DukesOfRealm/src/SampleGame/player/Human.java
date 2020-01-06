package SampleGame.player;

import SampleGame.Main;
import SampleGame.army.Order;
import SampleGame.army.Soldier.SoldierType;
import SampleGame.player.Player.PlayerType;
import SampleGame.tiles.Castle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


/**
 * Subclass of Player taking care of human type interaction
 * 
 * @author elliotrenel
 *
 */

public class Human extends Player {
	
	private Castle highlight = null;
	private Castle target = null;

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
	
	public Castle getTarget() {
		return this.target;
	}
	
	public void sendArmy(Castle castle) {
		Main.pause();
		highlight = castle;
		for(Castle c : Main.kingdom.castles) {
			c.setYourselfAsTarget(this);
		}
	}
	
	public void targetAcquired(Castle target, MouseEvent event) {
		if(highlight!=target) {
			SendArmyMenu menu = new SendArmyMenu(highlight, target);
			menu.show(Main.getPlayfieldLayer(),event.getScreenX(),event.getScreenY());
		}else {
			armySent();
		}
	}
	
	public void armySent() {
		for(Castle c : Main.kingdom.castles) {
			c.updateUI();
		}
		target = null;
		highlight=null;
		Main.unpause();
	}
	
}
