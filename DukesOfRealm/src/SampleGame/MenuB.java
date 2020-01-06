package SampleGame;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
/**
 * 
 * @author theod
 *
 *MenuB allows to display a menu bar at the top of the game window
 */
public class MenuB extends MenuBar {
	
	/**
	 * Constructor for MenuB.
	 * rounds, save and load are 3 different menus.
	 */
	public MenuB() {
		super();
		
		Menu rounds = new Menu();
		rounds.setText("Rounds: " + Settings.NB_CURRENT_ROUND);
		Menu save = new Menu("Save");
		Menu load = new Menu("Load");
		
		this.getMenus().addAll(rounds, save, load);
		this.setPrefSize(Settings.SCENE_WIDTH, 20);
	}
	
	
	/**
	 * Allows the menu rounds to be updated by the game.
	 * @param rounds the number of rounds to be printed.
	 */
	public void updateMenuBar(String rounds) {
		Menu roundCount = new Menu("Round: " + rounds);
		this.getMenus().set(0, roundCount);
		
	}
}

