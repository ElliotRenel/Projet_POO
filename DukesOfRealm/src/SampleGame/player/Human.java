package SampleGame.player;

import SampleGame.tiles.Castle;


/**
 * Subclass of Player taking care of human type interaction
 * 
 * @author elliotrenel
 *
 */

public class Human extends Player {

	public Human(String name, Castle[] initial_castles) {
		super(name,initial_castles);
		this.type = Player_Type.H;
	}
	
	public Human(String name) {
		super(name);
		this.type = Player_Type.H;
	}
	
	
}
