package SampleGame.player;

import java.util.Random;

import SampleGame.Kingdom;
import SampleGame.Settings;
import SampleGame.army.Order;
import SampleGame.army.Soldier.SoldierType;
import SampleGame.army.soldiers.Piquier;
import SampleGame.tiles.Castle;

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
		this.type = Player_Type.C;
	}
	
	public AI(String name) {
		super(name);
		this.type = Player_Type.C;
	}
	
	
	@Override
	public boolean update() {
		if(owned_castle.isEmpty())
			return false;
		
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
		return true;
	}
}

