package SampleGame.player;


import SampleGame.tiles.Castle;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Player class in charge of taking care of player actions, most methods implemented in subclasses Human and AI
 * 
 * @author elliotrenel
 *
 */

public class Player {
	
	public enum Player_Type{H,C,V;}
	
	protected String name;
	protected Queue<Castle> owned_castle;
	protected Player_Type type;
	
	protected Player(String name,Castle[] initial_castles) {
		this.name = name;
		this.owned_castle = new LinkedList<Castle>();
		for(Castle c : initial_castles)
			this.owned_castle.add(c);
	}
	
	protected Player(String name) {
		this.name = name;
		this.owned_castle = new LinkedList<Castle>();
	}
	
	protected Player() {
		this.name = "Neutral";
		this.owned_castle = new LinkedList<Castle>();
	}
	
	public String getName() {
		return name;
	}
	public Player_Type getType() {
		return type;
	}
	
	public void addCastle(Castle c) {
		this.owned_castle.add(c);
	}
	
	public void removeCastle(Castle c) {
		this.owned_castle.remove(c);
	}
	
	public boolean isMine(Castle c) {
		return owned_castle.contains(c);
	}
	
	public void update() {
		for(Castle c : owned_castle)
			c.updateRound();
	}

}
