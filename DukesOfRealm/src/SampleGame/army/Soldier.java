package SampleGame.army;

import SampleGame.Sprite;
import SampleGame.tiles.Castle;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Soldier extends Sprite{
	protected String name, duke_owner;
	protected int cost, time_prod;
	protected int speed, health, damage;
	protected boolean moving;
	protected Castle target;
	
	public Soldier(Pane layer, Image image, int x, int y, String duke_owner, int speed, int health, int damage) {
		super(layer, image, x, y);
		
		this.duke_owner = duke_owner;
		this.speed = speed;
		this.health = health;
		this.damage = damage;
		
		dx = 0; dy = 0;		
		moving = false;
	}
	
	public void executeOrder66(Castle target) {
		this.target = target;
		this.moving = true;
	}
	
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
	
	public void arrivedCastle(Castle target) {
		removeFromLayer();
		System.out.println("Arrived !!");
		moving = false;
		if(target.getDuke_owner()==this.duke_owner) {
			target.addToArmy(this);
		}else {
			target.getAttacked(this);
		}
	}
	
	public void updateRound() {
		if(moving) {
			updateOrder();
			updateUI();
		}
	}

	public String getName() {
		return name;
	}

	public String getDuke_owner() {
		return duke_owner;
	}	
	
	public int getCost() {
		return cost;
	}

	public int getTime_prod() {
		return time_prod;
	}

	public int getSpeed() {
		return speed;
	}

	public int getHealth() {
		return health;
	}

	public int getDamage() {
		return damage;
	}

}
