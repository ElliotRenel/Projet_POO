package SampleGame.player;

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
	
	public VoidPlayer() {
		super();
	}

}
