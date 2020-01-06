package SampleGame;

import SampleGame.army.*;
import SampleGame.player.*;
import SampleGame.player.Player.PlayerType;
import SampleGame.tiles.Castle;
import SampleGame.tiles.Castle.Orientation;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class Kingdom implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1596994732184530591L;
	public Castle[] castles = new Castle[Settings.NB_CASTLE];
	public Queue<Player> players = new LinkedList<Player>();
	Random rand = new Random();
	
	public Kingdom(Pane field) {
		
		
		for(int i=0; i<Settings.CastleImages.length; i++) {
			Settings.CastleImages[i] = new Image(getClass().getResource("/images/Castle"+ i + ".png").toExternalForm() , 60, 60, true, true);
		}
		
		Settings.CastlePlayer = new Image(getClass().getResource("/images/CastlePlayer.png").toExternalForm() , 60, 60, true, true);
		Settings.PiquierImage = new Image(getClass().getResource("/images/Piquier.png").toExternalForm(), 10 , 10, false, true);
		Settings.ChevalierImage = new Image(getClass().getResource("/images/Chevalier.png").toExternalForm(), 10 , 10, false, true);
		Settings.OnagreImage = new Image(getClass().getResource("/images/Onagre.png").toExternalForm(), 10 , 10, false, true);
		Settings.DoorImage = new Image(getClass().getResource("/images/Door_Castle.jpg").toExternalForm(), Settings.width_DoorImage , Settings.height_DoorImage, false, true);
		Settings.field = field;	
		
		Queue<Player> tmp = new LinkedList<Player>();
		
		Player human = new Human("Gaspare");
		human.setCastleImageAndColor(Settings.CastlePlayer);
		players.add(human); tmp.add(human);
		for(int i=0; i<Settings.NB_AI; i++) {
			Player duke = new AI(Settings.DUKES[i]);
			duke.setCastleImageAndColor(Settings.CastleImages[i+1]);
			players.add(duke); tmp.add(duke);
		}
		generateCastles(tmp);
	}
	
	/**
	 * Generate all castles in the Kingdom.
	 */
	private void generateCastles(Queue<Player> players) {
		boolean[][] not_available = new boolean[(int) (Settings.SCENE_WIDTH-100)][(int) (Settings.SCENE_HEIGHT-100)];
		for(int i=0; i<Settings.NB_CASTLE; i++) {
			Castle c;
			Player duke = new VoidPlayer();
			if(!players.isEmpty())
				duke = players.remove();
			Point p = generatePosition(not_available);
			
			Orientation oriCastle;
			
			switch(rand.nextInt(4)) {
				case 0:
					oriCastle = Orientation.N;
					break;
				case 1:
					oriCastle = Orientation.W;
					break;
				case 2:
					oriCastle = Orientation.S;
					break;
				case 3:
					oriCastle = Orientation.E;
					break;
				default:
					oriCastle = Orientation.N;
			}
			
			c = new Castle(p.x , p.y , 
					duke, 1000,  oriCastle, new Factory());	
			if(duke.getType()==PlayerType.V) {
				((VoidPlayer)duke).generateRandomDifficulty(c);
			}
			castles[i] = c;		
		}
	}
	
	/**
	 * Generate a point to be used for the position of a castle.
	 * The point is chosen from the available positions in the Kingdom and then update the
	 * table given in argument to tell which positions are unavailable.
	 * @param not_available A table of size Width x Height describing which points are not available (true when unavailable, false when it is)
	 * @return The generated points.
	 */
	private Point generatePosition(boolean[][] not_available) {
		int w = (int) Settings.SCENE_WIDTH-100;
		int h = (int) Settings.SCENE_HEIGHT-100;
		int x = rand.nextInt(w), y = rand.nextInt(h);
		int maj = Settings.DIST_CASTLE;

		while(not_available[x][y]) {
			x = rand.nextInt(w);
			y = rand.nextInt(h);
		}
		for(int i=x-maj>0?(x-maj):0; i<(x+maj<w?x+maj:w); i++) {
			for(int j=y-maj>0?(y-maj):0; j<(y+maj<h?y+maj:h); j++) {
				not_available[i][j] = true;
			}
		}				
		
		return new Point(x+50,y+50);
	}
	
	public Player update() {
		if(players.size()==1) {
			return players.remove();
		}
		for(Player p : players)
			if(!p.update()) {
				if(p.getType()!=PlayerType.H)
					players.remove(p);
				else {
					players.remove(p);//Main.gameFailed();
				}
			}
		return null;
	}
	
	
	
}
