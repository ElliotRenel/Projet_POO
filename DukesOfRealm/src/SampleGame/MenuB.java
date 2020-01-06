package SampleGame;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuB extends MenuBar {

	public MenuB() {
		super();
		this.setId("Bob");
		
		Menu rounds = new Menu();
		rounds.setText("Rounds: " + Settings.NB_CURRENT_ROUND);
		Menu save = new Menu("Save");
		Menu load = new Menu("Load");
		Menu pause = new Menu("Pause");
		
		this.getMenus().addAll(rounds, save, load, pause);
		this.setPrefSize(Settings.SCENE_WIDTH, 20);
	}
	
	public void updateMenuBar(String rounds) {
		Menu roundCount = new Menu("Round: " + rounds);
		this.getMenus().set(0, roundCount);
		
	}
}

