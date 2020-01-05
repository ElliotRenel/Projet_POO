package SampleGame;

import SampleGame.army.*;
import SampleGame.army.soldiers.Piquier;
import SampleGame.player.*;
import SampleGame.tiles.Castle;
import SampleGame.tiles.Castle.Orientation;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class Kingdom {
	public static Castle[] castles = new Castle[Settings.NB_CASTLE];
	public static Queue<Player> players = new LinkedList<Player>();
	Random rand = new Random();
	
	public Kingdom(Pane field) {
		
		
		for(int i=0; i<Settings.CastleImages.length; i++) {
			Settings.CastleImages[i] = new Image(getClass().getResource("/images/Castle"+ i + ".png").toExternalForm() , 60, 60, true, true);
		}
		
		Settings.PiquierImage = new Image(getClass().getResource("/images/Piquier.png").toExternalForm(), 25 , 25, false, true);
		Settings.DoorImage = new Image(getClass().getResource("/images/Door_Castle.jpg").toExternalForm(), 25 , 25, false, true);
		Settings.field = field;	
		
		generateCastles();
	}
	
	/**
	 * Generate all castles in the Kingdom.
	 */
	private void generateCastles() {
		boolean[][] not_available = new boolean[(int) (Settings.SCENE_WIDTH-100)][(int) (Settings.SCENE_HEIGHT-100)];
		for(int i=0; i<Settings.NB_CASTLE; i++) {
			Castle c;
			Player duke = new VoidPlayer();
			Soldier[] init_army = new Soldier[Settings.NB_TROUPE];
			if(i<Settings.NB_DUKES) {
				duke = new AI(Settings.DUKES[i]);
				duke.setCastleImageAndColor(Settings.CastleImages[i+1]);
				players.add(duke);
			}
			Point p = generatePosition(not_available);
			for(int j=0; j<Settings.NB_TROUPE;j++) {
				init_army[j] = new Piquier(p.x, p.y , duke);
			}
			c = new Castle(p.x , p.y , 
					duke, 1000, init_army, Orientation.E, new Factory());			
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
		
		return new Point(x,y);
	}
	
	public Player update() {
		if(players.size()==1)
			return players.remove();
		
		for(Player p : players)
			if(!p.update()) {
				players.remove(p);
				System.out.println(p.getName()+" is no more.");
			}
		return null;
	}
	
	
	
}
