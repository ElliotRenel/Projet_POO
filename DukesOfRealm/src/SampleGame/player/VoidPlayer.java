package SampleGame.player;

import java.util.Random;

import SampleGame.army.Soldier.SoldierType;
import SampleGame.tiles.Castle;

/**
 * Subclass of Player describing the absence of a player.
 * Each objects are here to describe the none presence of a player on a castle.
 * This class was made to prevent the use of null objects while initializing castles,
 * and modify the behavior of some methods.
 * 
 * @author elliotrenel
 *
 */
public class VoidPlayer extends Player {
	Random rand = new Random();
	
	public VoidPlayer() {
		super();
	}

	public void generateRandomDifficulty(Castle c) {
		c.setLevel(rand.nextInt(3)+1);
		
		c.addToArmy(SoldierType.P, c.getLevel()*10);
		c.addToArmy(SoldierType.C, c.getLevel()*5);
		c.addToArmy(SoldierType.O, c.getLevel()*2);
	}

}
