package SampleGame.player;


import SampleGame.Settings;
import SampleGame.army.*;
import SampleGame.tiles.Castle;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Player class in charge of taking care of player actions, most methods implemented in subclasses Human and AI
 * 
 * @author elliotrenel
 *
 */

public class Player {
	public enum Player_Type{H,C,V;}
	
	protected String name;
	protected Queue<Castle> owned_castle;
	protected Queue<Soldier> moving_soldiers;
	protected Player_Type type;
	protected Image castleImage;
	
	/**
	 * Class constructor.
	 * Create a player with an initial set of castles.
	 * 
	 * @param name A String containing the name of the player (Human or AI)
	 * @param initial_castles Array of initial castles
	 */
	protected Player(String name,Castle[] initial_castles) {
		this.name = name;
		this.owned_castle = new LinkedList<Castle>();
		this.moving_soldiers = new LinkedList<Soldier>();
		
		for(Castle c : initial_castles)
			this.owned_castle.add(c);
			
	}
	
	/**
	 * Second constructor.
	 * Create a player without initial castles
	 * 
	 * @param name A String containing the name of the player (Human or AI)
	 */
	protected Player(String name) {
		this.name = name;
		this.owned_castle = new LinkedList<Castle>();
		this.moving_soldiers = new LinkedList<Soldier>();
		
		
	}
	
	/**
	 * A void constructor used when creating a "VoidPlayer" object.
	 */
	protected Player() {
		this.name = "Neutral";
		this.owned_castle = new LinkedList<Castle>();
		this.moving_soldiers = new LinkedList<Soldier>();
		this.castleImage = Settings.CastleImages[0];
		
	}
	
	/**
	 * Getter for name property of object.
	 * 
	 * @return String : the name of the player
	 */
	public String getName() {
		return name;
	}
	
	public Image getCastleImage() {
		return castleImage;
	}
	
	public void setCastleImage(Image castleImage) {
		this.castleImage = castleImage;
	}
	
	/**
	 * Getter for the type of player.
	 * 
	 * @return Player_Type : the type of player
	 */
	public Player_Type getType() {
		return type;
	}
	
	/**
	 * Add a new castle to the list of owned castles by the player.
	 * 
	 * @param c The castle to add.
	 */
	public void addCastle(Castle c) {
		if(!this.owned_castle.contains(c))
			this.owned_castle.add(c);
	}
	
	/**
	 * Revoke a castle ownership
	 * 
	 * @param c The castle lost by the player
	 */
	public void removeCastle(Castle c) {
		if(this.owned_castle.contains(c))
			this.owned_castle.remove(c);
	}
	
	/**
	 * Check if a specific castle is owned by the player
	 * 
	 * @param c The castle to test
	 * @return True if it is owned by the player, false otherwise.
	 */
	public boolean isMine(Castle c) {
		return owned_castle.contains(c);
	}
	
	public void addToMovingArmy(Soldier s) {
		moving_soldiers.add(s);
	}
	
	public void removeFromMovingArmy(Soldier s) {
		moving_soldiers.remove(s);
	}
	
	/**
	 * Update function called at each turn
	 */
	public boolean update() {
		
		return true;
	}

}
