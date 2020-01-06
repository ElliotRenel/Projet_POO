package SampleGame;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
/**
 * Settings regarding the game in general
 * @author thdupont
 *
 */
public class Settings {

	public static final double SCENE_WIDTH = 1000;
    public static final double SCENE_HEIGHT = 1000;
	public static final double STATUS_BAR_HEIGHT = 50;
	public static final int ROUND_TIME = 50;
	
	public static final int NB_CASTLE = 10;
	public static final int NB_AI = 3;
	public static final String[] DUKES = {"De Valois","De Bougogne","Du Chatelait"};
	
	public static final int DIST_CASTLE = 150;
	
	public static Image CastleImages[] = new Image[5];
	public static Image CastlePlayer;
	public static Image PiquierImage;
	public static Image ChevalierImage;
	public static Image OnagreImage;
	
	
	
	public static Image DoorImage;
	
	public static int width_DoorImage = 20;
	public static int height_DoorImage = 10;
	
	public static Pane field;
	
	public static int NB_CURRENT_ROUND = 0;
	
	

}
