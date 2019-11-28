package SampleGame.tiles;

import java.util.LinkedList;
import java.util.Queue;

import SampleGame.army.Soldier;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Tile {
	
	int x,y;
	Queue <Soldier> present_army;
	
	public Tile(Pane layer, Image image, int x, int y) {
		this.x = x;
		this.y = y;
		present_army = new LinkedList<Soldier>();
	}
	
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
