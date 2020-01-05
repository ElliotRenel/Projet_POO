package SampleGame.player;

import java.util.LinkedList;
import java.util.Random;

import SampleGame.Kingdom;
import SampleGame.Settings;
import SampleGame.army.Order;
import SampleGame.army.Soldier;
import SampleGame.army.Soldier.SoldierType;
import SampleGame.army.soldiers.Piquier;
import SampleGame.tiles.Castle;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 * Subclass of Player creating computer type actions.
 * Each object will represent a single AI taking actions at random.
 * 
 * @author elliotrenel
 *
 */

public class AI extends Player {
	Random rand = new Random();
	
	public AI(String name, Castle[] initial_castles) {
		super(name,initial_castles);
		this.type = PlayerType.C;
	}
	
	public AI(String name) {
		super(name);
		this.type = PlayerType.C;
	}
	
	@Override
	protected void doStuff() {
		if(!owned_castle.isEmpty())
			for(Castle c : owned_castle) {
				c.updateRound();
				if(Settings.NB_CURRENT_ROUND%100 == 1)
					c.produceArmy(new Piquier(c), 100);
				if(Settings.NB_CURRENT_ROUND!=0 && Settings.NB_CURRENT_ROUND%100==0) {
					Castle cible = Kingdom.castles[rand.nextInt(Settings.NB_CASTLE)];
						while(isMine(cible))
							cible = Kingdom.castles[rand.nextInt(Settings.NB_CASTLE)];
						c.giveOrder(new Order(cible,c.getNbTroupe(SoldierType.P)-1,SoldierType.P));
				}
			}
	}
	
	@Override
	public ContextMenu giveMenu(Castle castle) {
		ContextMenu menu = new ContextMenu();
		MenuItem info = new MenuItem();
		
		info.setText("Owner : "+name+"\n"
				+ "Treasure : "+castle.getTreasure()+"\n"
				+ "Army count : \n"
				+ "\t> Stinger : "+castle.getNbTroupe(SoldierType.P)+"\n"
				+ "\t> Knights : "+castle.getNbTroupe(SoldierType.C)+"\n"
				+ "\t> Onagra : "+castle.getNbTroupe(SoldierType.O)+"\n");
		menu.getItems().add(info);
		return menu;
	}
}

