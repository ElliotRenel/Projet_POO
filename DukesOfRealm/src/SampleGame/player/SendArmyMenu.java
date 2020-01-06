package SampleGame.player;

import java.util.Hashtable;

import SampleGame.army.Order;
import SampleGame.army.Soldier.SoldierType;
import SampleGame.tiles.Castle;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;

public class SendArmyMenu extends ContextMenu {
	Hashtable<SoldierType, Integer> counters;
	Castle castle, target;
	
	public SendArmyMenu(Castle caslte, Castle target) {
		
		counters = new Hashtable<SoldierType, Integer>();
		for(SoldierType t:SoldierType.values())
			counters.put(t, 0);
		
		this.castle = caslte;
		this.target = target;
		
		createSlider(SoldierType.P, "Piquier");
		createSlider(SoldierType.C, "Chevalier");
		createSlider(SoldierType.O, "Onagre");
		
		MenuItem send = new MenuItem("Send");
		send.setOnAction(e -> {
			for(SoldierType t : SoldierType.values()) {
				int n = counters.get(t);
				if(n!=0)
					castle.giveOrder(new Order(target, n, t));
			}
			((Human)castle.getOwner()).armySent();
		});
		this.getItems().addAll(send,new MenuItem("Cancel"));
	}

	private void createSlider(SoldierType type, String name) {
		Label l = new Label(name+" 0");
		CustomMenuItem cmi = new CustomMenuItem(l);
		cmi.setHideOnClick(false);
		Slider s = new Slider(0,castle.getNbTroupe(type),0);
		s.setOnMouseDragged(e->{
			int k = (int)s.getValue();
			counters.put(type,k);
			l.setText(name+" "+k);
		});
		CustomMenuItem cmi2 = new CustomMenuItem(s);
		cmi2.setHideOnClick(false);
		
		this.getItems().addAll(cmi,cmi2);
	}
}
