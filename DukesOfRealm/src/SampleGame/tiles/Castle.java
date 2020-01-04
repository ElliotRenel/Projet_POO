package SampleGame.tiles;

import SampleGame.Kingdom;
import SampleGame.Settings;
import SampleGame.Sprite;
import SampleGame.army.*;
import SampleGame.army.Soldier.SoldierType;
import SampleGame.player.*;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


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
	private Hashtable<SoldierType, Queue<Soldier>> army;
	private Queue<Order> orders;
	private Order current_order;
	@SuppressWarnings("unused")
	private final Orientation door;						/* Not used*/
	private Factory fact;
	private Random rand = new Random();
	
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
		
		army = new Hashtable<SoldierType, Queue<Soldier>>();
		army.put(SoldierType.P, new LinkedList<Soldier>());
		army.put(SoldierType.C, new LinkedList<Soldier>());
		army.put(SoldierType.O, new LinkedList<Soldier>());
		for(Soldier s : initial_army)
			addToArmy(s);
		this.door = door;
		this.fact = fact;
		this.orders = new LinkedList<Order>();
		this.current_order = null;
	}
	
		
	/** 
	 * Checks if there is a new soldier to add from production and then adds or not the right soldier to the army
	*/
	private void addToArmy() {
		Soldier result = fact.update();
		if(result!=null) {
			addToArmy(result);
		}
	}
	
	/**
	 * Adds a soldier to the queue "army".
	 * 
	 * @param s The soldier to add
	*/
	public void addToArmy(Soldier s) {
		army.get(s.getType()).add(s);
	}
	
	/**
	 * Remove a specific type of soldiers from the castle army
	 * 
	 * @param t The type of soldier
	 * @return The removed soldier
	 */
	private Soldier removeFromArmy(SoldierType t) {
		return army.get(t).remove();
	}
	
	/**
	 * Remove a specific soldier from the castle army
	 * 
	 * @param s The soldier object to remove
	 */
	private void removeFromArmy(Soldier s) {
		army.get(s.getType()).remove(s);
	}
	
	/**
	 * When the castle gets attacked by an ennemy soldier
	 * 
	 * @param s the attacker
	 */
	public void getAttacked(Soldier s) {
		if(this.noMoreArmy()) {
			this.owner.removeCastle(this);
			this.owner = s.getOwner();
			addToArmy(s);
			this.owner.addCastle(this);
		}else {
			Soldier challenger = this.chooseChallenger();
			while(s.attackSoldier(challenger) && !this.noMoreArmy()) {
				this.removeFromArmy(challenger);
				challenger = this.chooseChallenger();
			}
			if(challenger.getHealth()==0) this.removeFromArmy(challenger);
		}
	}
	
	/**
	 * Choose a random opponent in the army to fight an intruder
	 * @return The chosen warrior
	 */
	private Soldier chooseChallenger() {
		SoldierType t = SoldierType.values()[rand.nextInt(3)];
		while(army.get(t).isEmpty())
			t = SoldierType.values()[rand.nextInt(3)];
		Soldier s = this.removeFromArmy(t);
		this.addToArmy(s);
		return s;
	}


	/**
	 * Check if the castle has any soldier left.
	 * @return True if there are no more soldiers, false if it found at least one.
	 */
	private boolean noMoreArmy() {
		for(SoldierType t : SoldierType.values())
			if(!army.get(t).isEmpty())
				return false;
		return true;
	}
	
	
	/**
	 * Sets a new order
	 * 
	 * @param order The new order to assign
	 */
	public void giveOrder(Order order) {
		if(order.getTroops()<=army.size()) {
			this.orders.add(order);
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
		if(current_order==null && !orders.isEmpty()) {
			current_order=orders.remove();
		}
		if(current_order!=null){
			if (current_order.getTroops()!=0) {
				for(int i=0; i<3 && current_order.getTroops()>0;i++, current_order.sendTroops()) {
					Soldier s = removeFromArmy(current_order.getType());
					Kingdom.moving_soldier.add(s);
					s.executeOrder66(current_order.getTarget());
				}
			}else {
				this.current_order = null;
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
				+ "Army count : \n"
				+ "\t> Stinger : "+army.get(SoldierType.P).size()+"\n"
				+ "\t> Knights : "+army.get(SoldierType.C).size()+"\n"
				+ "\t> Onagra : "+army.get(SoldierType.O).size()+"\n"
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
