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
		ContextMenu menu = new ContextMenu();
		
		Menu infos = new Menu("Show my infos");
		MenuItem inf_item = new MenuItem();
        		
        inf_item.setText("Treasure : "+castle.getTreasure()+"\n"
        	+ "Army count : \n"
        	+ "\t> Stinger : "+castle.getNbTroupe(SoldierType.P)+"\n"
        	+ "\t> Knights : "+castle.getNbTroupe(SoldierType.C)+"\n"
        	+ "\t> Onagra : "+castle.getNbTroupe(SoldierType.O)+"\n");
        
        inf_item.setOnAction(e -> {Main.unpause();});
            	
        infos.getItems().add(inf_item);

		MenuItem item2 = new MenuItem("Item 2");
		
		item2.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	Main.unpause();
            }
        });
		
		Menu sub =new Menu("submenu");
		sub.getItems().addAll(item2);
		
		menu.getItems().addAll(infos,sub);
	
		return menu;
	}
	
	
}
