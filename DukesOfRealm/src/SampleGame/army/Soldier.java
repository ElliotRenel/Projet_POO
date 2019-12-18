package SampleGame.army;

import SampleGame.Sprite;
import SampleGame.tiles.Castle;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Soldier extends Sprite{
	protected String name, duke_owner;
	protected int cost, time_prod;
	protected int speed, health, damage;
	protected int x, y;
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
		int moves = speed;
		int c_x = target.getX();
		if(c_x != x) {
			int d_x = c_x - x;
			dx = d_x<0?-1:1;
			while(c_x != x && moves>0) {
				move();
				moves --;
			}			
		}
		dx = 0;
		int c_y = target.getY();
		if(c_y != y) {
			int d_y = c_y - y;
			dy = d_y<0?-1:1;
			while(c_y != y && moves>0) {
				move();
				moves --;
			}			
		}
		dy = 0;
		if(c_x == x && c_y == y) {
			arrivedCastle(target);
		}
	}
	
	public void arrivedCastle(Castle target) {
		moving = false;
		if(target.getDuke_owner()==this.duke_owner) {
			target.addToArmy(this);
		}else {
			target.getAttacked(this);
		}
	}
	
	public void updateRound() {
		if(moving)
			updateOrder();
			updateUI();
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
