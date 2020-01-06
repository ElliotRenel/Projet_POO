package SampleGame.tiles;

import SampleGame.Settings;
import SampleGame.Sprite;

public class Door extends Sprite {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4498527044132721240L;
	
	int x_out;
	int y_out;
	
	public Door(int x, int y) {
		super(Settings.field, Settings.DoorImage, x, y);
		
		this.x_out = x+30;
		this.y_out = y;
	}
}
