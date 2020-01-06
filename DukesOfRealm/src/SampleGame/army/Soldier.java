package SampleGame.army;

import javafx.scene.paint.Color;

import SampleGame.Kingdom;
import SampleGame.Main;
import SampleGame.Settings;
import SampleGame.Sprite;
import SampleGame.player.Player;
import SampleGame.tiles.Castle;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Class used to represent different type of soldiers into one class.
 * 
 * @author elliotrenel
 *
 */
public class Soldier extends Sprite{
	public enum SoldierType{
		P,
		C,
		O;
	}
	protected Player owner;
	protected SoldierType type;
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
	public Soldier(Image image, int x, int y, Player owner) {
		super(Settings.field, image, x, y);
		
		int height = (int)image.getHeight();
		int width = (int)image.getWidth();
		
		WritableImage personalized_soldier = new WritableImage(width, height);
		
		PixelReader pixRd = image.getPixelReader();
		PixelWriter pixWr = personalized_soldier.getPixelWriter();
		for(int j=0; j<height; j++) {
			for(int i=0; i<width; i++) {
				if(!(pixRd.getColor(i, j).equals(Color.web("0x00000000")))) {
					pixWr.setColor(i, j, owner.getColor());
				}
			}
		}
		
		this.changeImage(personalized_soldier);
		
		
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
		if(target.getOwnerName()==this.owner.getName() || !Main.kingdom.players.contains(owner)) {
			target.addToArmy(this);
			this.owner = target.getOwner();
		}else {
			target.getAttacked(this);
		}
	}
	
	/**
	 * Simulate attack with another soldier and check who wins.
	 * @param s The other soldier
	 * @return True if the object who called the method wins, false if it is defeated
	 */
	public boolean attackSoldier(Soldier s) {
		int s_damage = s.getDamage();
		while(this.health!=0 && s.health!=0) {
			this.takeDamage(s_damage);
			s.takeDamage(this.damage);
		}
		return this.health==0?false:true;
	}
	
	/**
	 * Loose health according to the amount of damage dealt
	 * @param hitPoints The opponent's damage
	 */
	protected void takeDamage(int hitPoints) {
		this.health -= hitPoints;
		if(this.health<0)
			this.health = 0;
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
	 * Getter for soldier type
	 * @return enum SoldierType : soldier type
	 */
	public SoldierType getType() {
		return type;
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

	/**
	 * Getter for the Soldier corresponding Duke
	 * @return The Player object representing the soldier's Duke
	 */
	public Player getOwner() {
		return owner;
	}
	
	/**
	 * Tells if the soldier is moving or not
	 * @return True if it is, false otherwise
	 */
	public boolean isMoving() {
		return moving;
	}
	

}
