package SampleGame.player;

import SampleGame.Main;
import SampleGame.army.Soldier.SoldierType;
import SampleGame.tiles.Castle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class PlayerMenu extends ContextMenu {
	
	Castle castle;
	Human owner;
	
	public PlayerMenu(Castle castle) {
		this.castle = castle;
		this.owner = (Human)castle.getOwner();
		Menu infos = new Menu("Show my infos");
		MenuItem inf_item = new MenuItem();
        		
        inf_item.setText("Treasure : "+castle.getTreasure()+"\n"
        	+ "Army count : \n"
        	+ "\t> Stinger : "+castle.getNbTroupe(SoldierType.P)+"\n"
        	+ "\t> Knights : "+castle.getNbTroupe(SoldierType.C)+"\n"
        	+ "\t> Onagra : "+castle.getNbTroupe(SoldierType.O)+"\n");
            	
        infos.getItems().add(inf_item);

		Menu army = new Menu("Produce Army");
		
		Menu piquier = new Menu("Piquier");
		Menu chevalier = new Menu("Chevalier");
		Menu onagre = new Menu("Onagre");		
		
		assignProducer(piquier, SoldierType.P);
		assignProducer(chevalier, SoldierType.C);
		assignProducer(onagre, SoldierType.O);
		
		army.getItems().addAll(piquier,chevalier,onagre);
		
		this.getItems().addAll(infos,army);
	}
	
	private void assignProducer(Menu soldier, SoldierType type) {
		MenuItem produce1 = new MenuItem("1");
		produce1.setOnAction(e -> {
			castle.produceArmy(type, 1);
		});
		MenuItem produce10 = new MenuItem("10");
		produce10.setOnAction(e -> {
			castle.produceArmy(type, 10);
		});
		MenuItem produce100 = new MenuItem("100");
		produce100.setOnAction(e -> {
			castle.produceArmy(type, 100);
		});		
		soldier.getItems().addAll(produce1,produce10,produce100);
	}
}
