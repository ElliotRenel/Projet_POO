package SampleGame.cases;

import SampleGame.army.Order;
import SampleGame.army.Soldier;

public class Castle extends Tile{
	private String duke_owner;
	private int treasure;
	private Soldier[] army;
	private Order target; 
	private int door;						/* Not used*/
}
