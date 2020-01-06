package SampleGame.player;

import java.util.LinkedList;
import java.util.Random;

import SampleGame.Kingdom;
import SampleGame.Main;
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
				if(rand.nextGaussian()>0.99)
					decideWhatToDo(c);
			}
	}
	
	private void decideWhatToDo(Castle castle) {
		Double randomizer = rand.nextGaussian();
		if(randomizer>0.65) {
			SoldierType t = SoldierType.values()[rand.nextInt(3)];
			int n = castle.howMuchICanMake(t);
			if(n>0)
				castle.produceArmy(t, rand.nextInt(n));
		}
		if(randomizer<0.2) {
			SoldierType t = SoldierType.values()[rand.nextInt(3)];
			if(!castle.noMoreArmy() && rand.nextBoolean()) {
				while(castle.getNbTroupe(t)==0)
					t = SoldierType.values()[rand.nextInt(3)];
				castle.giveOrder(new Order(Main.kingdom.castles[rand.nextInt(Settings.NB_CASTLE)],rand.nextInt(castle.getNbTroupe(t)),t));
			}else {
				int n = castle.howMuchICanMake(t);
				if(n>0)
					castle.produceArmy(t, rand.nextInt(n));
			}
		}
	}
}

