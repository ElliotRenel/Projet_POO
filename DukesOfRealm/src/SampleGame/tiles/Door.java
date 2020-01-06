package SampleGame.tiles;

import SampleGame.Settings;
import SampleGame.Sprite;

public class Door extends Sprite {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4498527044132721240L;
	
	private int x_out;
	private int y_out;
	
	public Door(int x, int y) {
		super(Settings.field, Settings.DoorImage, x, y);
		
		this.x_out = x;
		this.y_out = y;
	}

	public int getX_out() {
		return x_out;
	}

	public void setX_out(int x_out) {
		this.x_out = x_out;
	}

	public int getY_out() {
		return y_out;
	}

	public void setY_out(int y_out) {
		this.y_out = y_out;
	}
	
	
}
