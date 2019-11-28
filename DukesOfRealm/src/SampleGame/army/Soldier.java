package SampleGame.army;

import SampleGame.tiles.Castle;

public class Soldier {
	protected String name, duke_owner;
	protected int cost, time_prod;
	protected int speed, health, damage;
	protected int tile_x,tile_y;
	
	public Soldier() {}
	
	public void executeOrder66(Castle target) {
		int moves = speed;
		int c_x = target.getX();
		if(c_x != tile_x) {
			int d_x = c_x - tile_x;
			int d_ux = d_x<0?-1:1;
			while(c_x != tile_x && moves>0) {
				tile_x =+ d_ux;
				moves --;
			}			
		}
		int c_y = target.getY();
		if(c_y != tile_y) {
			int d_y = c_y - tile_y;
			int d_uy = d_y<0?-1:1;
			while(c_y != tile_y && moves>0) {
				tile_y =+ d_uy;
				moves --;
			}			
		}
		if(c_x == tile_x && c_y == tile_y) {
			arrivedCastle(target);
		}
	}
	
	public void arrivedCastle(Castle target) {
		if(target.getDuke_owner()==this.duke_owner) {
			target.addToArmy(this);
		}else {
			target.getAttacked(this);
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
