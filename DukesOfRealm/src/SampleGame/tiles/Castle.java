package SampleGame.tiles;

import SampleGame.Kingdom;
import SampleGame.Settings;
import SampleGame.Sprite;
import SampleGame.army.*;
import SampleGame.player.*;

import java.util.LinkedList;
import java.util.Queue;


/**
 * An entity of the game: Controlling a castle as a player allows to produce and stack units, and to order armies to attack other castles.
 * @author thdupont
 *
 */
public class Castle extends Sprite{
	
	public enum Orientation{
		N,
		E,
		S,
		W;
	}
	
	private Player owner;
	private int treasure;
	private Queue<Soldier> army;
	private Order order;
	@SuppressWarnings("unused")
	private final Orientation door;						/* Not used*/
	private Factory fact;
	
	/**
	 * 
	 * Creates a new castle in the layer.
	 * 
	 * @param layer The global visual window
	 * @param image	The image of the Castle
	 * @param x	The x coordinate of the castle in the window
	 * @param y The y coordinate of the castle in the window
	 * @param duke_owner The player who owns the castle
	 * @param treasure The base money for the castle
	 * @param initial_army The army wanted in the castle
	 * @param door	The orientation of the exit
	 * @param fact The class who allows production @see Factory.java
	 * @return A new castle printed on the window
	 * 
	 **/
	
	public Castle(int x, int y, Player owner, int treasure, Soldier[] initial_army, Orientation door, Factory fact) {
		super(Settings.field, Settings.CastleImage, x, y );
		this.owner = owner;
		owner.addCastle(this);
		this.treasure = treasure;
		
		army = new LinkedList<Soldier>();
		for(Soldier s : initial_army) this.army.add(s);
		this.door = door;
		this.fact = fact;
		this.order = null;
	}
	
		
	/** 
	 * Checks if there is a new soldier to add from production and then adds or not the right soldier to the army
	*/
	private void addToArmy() {
		Soldier result = fact.update();
		if(result!=null) {
			army.add(result);
		}
	}
	
	/**
	 * Adds a soldier to the queue "army".
	 * 
	 * @param s The soldier to add
	*/
	public void addToArmy(Soldier s) {
		army.add(s);
	}
	
	
	/**
	 * When the castle gets attacked by an ennemy soldier
	 * 
	 * @param s the attacker
	 */
	public void getAttacked(Soldier s) {
		if(army.isEmpty()) {
			this.owner.removeCastle(this);
			this.owner = s.getOwner();
			army.add(s);
			this.owner.addCastle(this);
		}else {
			army.remove();
		}
	}
	
	
	/**
	 * Sets a new order
	 * 
	 * @param order The new order to assign
	 */
	public void giveOrder(Order order) {
		if(order.getTroops()<=army.size()) {
			this.order = order;
		}else {
			System.out.println("Not enough troops");
		}
		
	}	
	
	/**
	 * Sends maximum 3 soldiers through the gates to attack the castle pointed by order
	 * Puts order back to null if no more troops to send.
	 * 
	 */
	
	private void executeOrder() {
		if(order!=null) {		
			if (order.getTroops()!=0) {
				for(int i=0; i<3 && order.getTroops()>0;i++, order.sendTroops()) {
					Soldier s = army.remove();
					Kingdom.moving_soldier.add(s);
					s.executeOrder66(order.getTarget());
				}
			}else {
				this.order = null;
			}
		}
	}
	
	/**
	 * Getter for owner's name
	 * @return String : the player's name
	 */
	public String getOwnerName() {
		return owner.getName();
	}

	/**
	 * Set castle's owner
	 * @param owner The Player object 
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	/**
	 * Get castle's owner
	 * @return Player : the owner of the castle
	 */
	public Player getOwner() {
		return this.owner;
	}


	/**
	 * Prints the status of a castle in the console
	 */
	public void printStatus() {
		System.out.println("Owner : "+owner.getName()+"\n"
				+ "Treasure : "+treasure+"\n"
				+ "Army count : "+army.size()+"\n"
				+ "Current round : "+Settings.NB_CURRENT_ROUND+"\n");
		
	}
	
	/**
	 * Getter for army size
	 * @return int : Size of the current army
	 */
	public int getNbTroupe() {
		return army.size();
	}
	
	/**
	 * Add a training order to the factory
	 * @param type The soldier you want to train
	 * @param quantity The quantity of this specific soldiers you want to train
	 */
	public void produceArmy(Soldier type, int quantity) {
		fact.addTraining(type, quantity);
	}
	
	/**
	 * Show castle menu when the castle owner is the player, only show information if the castle
	 * is owned by an opponent.
	 */
	public void showMenu() {
		this.printStatus();
	}
	
	
	/**
	 * Called at each turn to update the different mechanics
	 */
	public void updateRound(){
		
		addToArmy();
		
		executeOrder();
		
		updateUI();
		
		treasure += 10;
		
	}
	
}
