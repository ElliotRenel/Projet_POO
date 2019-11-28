package SampleGame.tiles;

import java.util.Queue;

import SampleGame.army.Soldier;

public abstract class Tile {
	
	int x,y;
	Queue <Soldier> present_army;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void updateRound() {
		for(Soldier s : present_army)
			s.updateRound();
	}

}
