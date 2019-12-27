package SampleGame.army;

import SampleGame.Settings;
import SampleGame.Sprite;
import SampleGame.tiles.Castle;
import javafx.scene.image.Image;

/**
 * Class used to represent different type of soldiers into one class.
 * 
 * @author elliotrenel
 *
 */
public class Soldier extends Sprite{
	protected String name, duke_owner;
	protected int cost, time_prod;
	protected int speed, health, damage;
	protected boolean moving;
	protected Castle target;
	
	
	/**
	 * 
	 * Soldier constructor
	 * 
	 * @param layer The global visual window
	 * @param image	The image of the Soldier
	 * @param x	The x coordinate of the Soldier's castle in the window
	 * @param y The y coordinate of the Soldier's castle in the window
	 * @param duke_owner Owner of the Soldier's castle
	 * @param speed Number of coordinates movement in one turn
	 * @param health Number of hitpoints of the soldier
	 * @param damage Damage dealt upon attack
	 * 
	 */
	public Soldier(Image image, int x, int y) {
		super(Settings.field, image, x, y);
		
		removeFromLayer();
		dx = 0; dy = 0;		
		moving = false;
	}
	
	
	/**
	 * Initialize a target for the the soldier and set him to move
	 * 
	 * @param target The Castle object targeted by the order
	 */
	public void executeOrder66(Castle target) {
		this.addToLayer();
		this.target = target;
		this.moving = true;
	}
	
	/**
	 * Change the dx and dy of the Sprite according the desired target and speed
	 */
	private void updateOrder() {
		int c_x = target.getX(), c_y = target.getY();
		dx = 0;dy=0;
		
		if(c_x == x && c_y == y) 
			arrivedCastle(target);
				
		int r_x = c_x-x, r_y = c_y-y;
		if(Math.abs(r_x)>speed)
			dx = (r_x>0?1:-1)*speed;
		else {
			dx = r_x;
			if(Math.abs(r_y)>(speed-Math.abs(dx)))
				dy = (r_y>0?1:-1)*(speed-Math.abs(dx));
			else {
				x=c_x; y=c_y;
			}	
		}
		move();
	}
	
	/**
	 * Function called when the soldier has arrived at it's target and check if he must attack the castle or join its army
	 * 
	 * @param target Targeted castle the soldier arrived at
	 */
	public void arrivedCastle(Castle target) {
		removeFromLayer();
		moving = false;
		if(target.getDuke_owner()==this.duke_owner) {
			target.addToArmy(this);
		}else {
			target.getAttacked(this);
		}
	}

	/**
	 * Update function for the Soldier object
	 */
	public void updateRound() {
		if(moving) {
			updateOrder();
			updateUI();
		}
	}

	/**
	 * Getter for soldier name
	 * @return String : soldier name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for duke name
	 * @return String : duke name
	 */
	public String getDuke_owner() {
		return duke_owner;
	}	

	/**
	 * Getter for soldier costs
	 * @return int : soldier cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Getter for soldier training time
	 * @return int : training time
	 */
	public int getTime_prod() {
		return time_prod;
	}

	/**
	 * Getter for soldier speed
	 * @return int : soldier speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Getter for soldier hitpoints
	 * @return int : soldier health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Getter for soldier damage
	 * @return int : soldier damage
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * Create a new Soldier object of the same type and owner as the current object (implemented in daughter class)
	 * @return a copy of the current soldier (similar to clone())
	 */
	public Soldier trainNew() {
		return null;
	}
	

}
