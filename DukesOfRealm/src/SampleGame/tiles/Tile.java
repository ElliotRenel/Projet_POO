package SampleGame.tiles;

import java.util.LinkedList;
import java.util.Queue;

import SampleGame.Sprite;
import SampleGame.army.Soldier;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Tile extends Sprite{
	
	protected Queue <Soldier> present_army;
	
	public Tile(Pane layer, Image image, int x, int y) {
		super(layer,image,x,y);
		present_army = new LinkedList<Soldier>();
	}
	
	public void updateRound() {
		for(Soldier s : present_army)
			s.updateRound();
	}

}
