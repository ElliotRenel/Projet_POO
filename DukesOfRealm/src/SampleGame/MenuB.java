package SampleGame;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuB extends MenuBar {

	public MenuB() {
		super();
		this.setId("Bob");
		
		Menu save = new Menu("Save");
		Menu load = new Menu("Load");
		Menu pause = new Menu("Pause");
		
		this.getMenus().addAll( save, load, pause);
	}
}
