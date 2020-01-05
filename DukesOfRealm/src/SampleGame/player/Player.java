package SampleGame.player;


import SampleGame.Settings;
import SampleGame.tiles.Castle;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

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
	protected Color Player_Color;
	protected WritableImage Castle_Image;
	
	/**
	 * Class constructor.
	 * Create a player with an initial set of castles.
	 * 
	 * @param name A String containing the name of the player (Human or AI)
	 * @param initial_castles Array of initial castles
	 */
	protected Player(String name,Castle[] initial_castles, Color color) {
		this.name = name;
		this.owned_castle = new LinkedList<Castle>();
		for(Castle c : initial_castles)
			this.owned_castle.add(c);
		
		this.Player_Color = color;
		int width = (int)Settings.CastleImage.getWidth();
		int height = (int)Settings.CastleImage.getHeight();
		
		Castle_Image =  new WritableImage(width , height );
		
		PixelReader pixRead = Settings.CastleImage.getPixelReader();
		PixelWriter pixWrite = Castle_Image.getPixelWriter();
		Color tokeep = pixRead.getColor(0, 0);
		
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				if(!tokeep.equals(pixRead.getColor(x, y))){
					if(pixRead.getColor(x, y).equals(Color.BLACK)) {
						pixWrite.setColor(x,y, Color.BLACK);
					}else {
						pixWrite.setColor(x,y,Player_Color);
					}
				}
			}
		}
		
	}
	
	/**
	 * Second constructor.
	 * Create a player without initial castles
	 * 
	 * @param name A String containing the name of the player (Human or AI)
	 */
	protected Player(String name) {
		this.name = name;
		this.owned_castle = new LinkedList<Castle>();
		
		this.Player_Color = Color.PURPLE;
		int width = (int)Settings.CastleImage.getWidth();
		int height = (int)Settings.CastleImage.getHeight();
		
		Castle_Image =  new WritableImage(width , height );
		
		PixelReader pixRead = Settings.CastleImage.getPixelReader();
		PixelWriter pixWrite = Castle_Image.getPixelWriter();
		Color tokeep = pixRead.getColor(0, 0);
		
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				if(!tokeep.equals(pixRead.getColor(x, y))){
					if(pixRead.getColor(x, y).equals(Color.BLACK)) {
						pixWrite.setColor(x,y, Color.BLACK);
					}else {
						pixWrite.setColor(x,y,Player_Color);
					}
				}
			}
		}
		
	}
	
	/**
	 * A void constructor used when creating a "VoidPlayer" object.
	 */
	protected Player() {
		this.name = "Neutral";
		this.owned_castle = new LinkedList<Castle>();
		
		this.Player_Color = Color.RED;
		int width = (int)Settings.CastleImage.getWidth();
		int height = (int)Settings.CastleImage.getHeight();
		
		Castle_Image =  new WritableImage(width , height );
		
		PixelReader pixRead = Settings.CastleImage.getPixelReader();
		PixelWriter pixWrite = Castle_Image.getPixelWriter();
		Color tokeep = pixRead.getColor(0, 0);
		
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				if(!tokeep.equals(pixRead.getColor(x, y))) {
					if(pixRead.getColor(x, y).equals(Color.BLACK)) {
						pixWrite.setColor(x,y, Color.BLACK);
					}else {
						pixWrite.setColor(x,y,Player_Color);
					}
				}
			}
		}
	}
	
	/**
	 * Getter for name property of object.
	 * 
	 * @return String : the name of the player
	 */
	public String getName() {
		return name;
	}
	
	public Image getCastleImage() {
		return Castle_Image;
	}
	
	/**
	 * Getter for the type of player.
	 * 
	 * @return Player_Type : the type of player
	 */
	public Player_Type getType() {
		return type;
	}
	
	/**
	 * Add a new castle to the list of owned castles by the player.
	 * 
	 * @param c The castle to add.
	 */
	public void addCastle(Castle c) {
		if(!this.owned_castle.contains(c))
			this.owned_castle.add(c);
	}
	
	/**
	 * Revoke a castle ownership
	 * 
	 * @param c The castle lost by the player
	 */
	public void removeCastle(Castle c) {
		if(this.owned_castle.contains(c))
			this.owned_castle.remove(c);
	}
	
	/**
	 * Check if a specific castle is owned by the player
	 * 
	 * @param c The castle to test
	 * @return True if it is owned by the player, false otherwise.
	 */
	public boolean isMine(Castle c) {
		return owned_castle.contains(c);
	}
	
	/**
	 * Update function called at each turn
	 */
	public boolean update() {
		if(!owned_castle.isEmpty())
			for(Castle c : owned_castle)
				c.updateRound();
		else
			return false;
		return true;
	}

}
