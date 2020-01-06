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
		
		// Castle Infos
		MenuItem infos = new MenuItem();		
		infos.setText("Owner : You\n"
        	+ "Treasure : "+castle.getTreasure()+"\n"
        	+ "Castle Level :"+castle.getLevel()+" ("+castle.getLevel()*10+" coins per round)\n"
        	+ "Army count : \n"
        	+ "\t> Stinger : "+castle.getNbTroupe(SoldierType.P)+"\n"
        	+ "\t> Knights : "+castle.getNbTroupe(SoldierType.C)+"\n"
        	+ "\t> Onagra : "+castle.getNbTroupe(SoldierType.O)+"\n"
        );

		
		// Produce Army
		Menu army = new Menu("Produce Army");
		
		Menu piquier = new Menu("Piquier");
		Menu chevalier = new Menu("Chevalier");
		Menu onagre = new Menu("Onagre");		
		
		assignProducer(piquier, SoldierType.P);
		assignProducer(chevalier, SoldierType.C);
		assignProducer(onagre, SoldierType.O);
		
		army.getItems().addAll(piquier,chevalier,onagre);
		
		// Upgrade Castle
		MenuItem upgrade = new MenuItem("Upgrade Castle to Lvl "+(castle.getLevel()+1)+" (cost:"+1000*(castle.getLevel()+1)+")");
		
		upgrade.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				castle.upgradeCastle();
			}
		});;
		upgrade.setDisable(!castle.upgradeCastle(true) || castle.isUpgrading());
		
		
		this.getItems().addAll(infos,army,upgrade);
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
		
		produce1.setDisable(!castle.produceArmy(type, 1, true));
		produce10.setDisable(!castle.produceArmy(type, 10, true));
		produce100.setDisable(!castle.produceArmy(type, 100, true));
		
		soldier.getItems().addAll(produce1,produce10,produce100);
	}
}
