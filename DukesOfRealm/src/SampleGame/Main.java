package SampleGame;

import java.io.*;

import SampleGame.player.Human;
import SampleGame.player.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Allows to run application.
 * Main gestion of the window parameters, round time, end game, etc...
 * 
 * @author thdupont
 *
 */
public class Main extends Application {

	private static Pane playfieldLayer;

	//HUD
	private Text round = new Text();

	//Things
	private Scene scene;
	//private Input input;
	private static AnimationTimer gameLoop;
	
	private boolean pause = false;	
	private boolean released = false;
	
	Group root;
	
	public static Kingdom kingdom;

	@Override
	/**
	 * Called at the start of execution to launch the application and window
	 * 
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) {

		root = new Group();
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT + Settings.STATUS_BAR_HEIGHT);
		//scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		
		
		// create layers
		playfieldLayer = new Pane();
		root.getChildren().add(playfieldLayer);
		
		loadGame();
		
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {

				if(!pause){
					try {
						update();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		};
		gameLoop.start();
	}

	/**
	 * Allows to load game parameters and initialize the game
	 */
	private void loadGame() {
		if(kingdom==null)
			createKingdom();
		createStatusBar();
		createMenuBar();

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				String key =event.getCode().toString(); 
				switch (key) {
				case "SPACE":
					if(!pause) {
						pause = true;
						pause();
					}else if(released) {
						pause = false;
						released = false;
						unpause();
					}
					break;
				case "S":
					try {
						saveKingdom();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case "L":
					try {
						loadKingdom();
					} catch (IOException e) {
						e.printStackTrace();
					} catch(ClassNotFoundException e) {
						e.printStackTrace();
					}
					
					break;
				default:
					System.out.println(key);
					break;
				}
			}
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) { 	
				if(event.getCode().toString()=="SPACE" && pause) {
					released = true;
				}				
			}
		});
	}
	
	private static void saveKingdom() throws IOException {
		FileOutputStream fos = new FileOutputStream("t.tmp");
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(kingdom);
	    oos.close();
	}
	
	private static void loadKingdom() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("t.tmp");
	    ObjectInputStream ois = new ObjectInputStream(fis);
	    
	    kingdom = (Kingdom)ois.readObject();
	    
	    ois.close();
	 
	}

	/**
	 * To BE INPLEMENTED
	 * Allows to print status of a castle in the window
	 */
	
	public void createStatusBar() {
		HBox statusBar = new HBox();
		statusBar.getChildren().addAll(round);
		statusBar.getStyleClass().add("statusBar");
		statusBar.relocate(0, 25);
		//statusBar.setPrefSize(Settings.SCENE_WIDTH, Settings.STATUS_BAR_HEIGHT);
		root.getChildren().add(statusBar);
	}
	
	public void createMenuBar() {
		MenuB menuBar = new MenuB();
		root.getChildren().add(menuBar);
	}

	/**
	 * Creates a new game manager.
	 */
	private void createKingdom() {
		kingdom = new Kingdom(playfieldLayer);
	}

	
	/**
	 * Quits the game when only one player is alive and holds at least one castle
	 */
	private void gameOver(Player p) {
		p.won();
		HBox hbox = new HBox();
		hbox.setPrefSize(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		hbox.getStyleClass().add("message");
		Text message = new Text();
		message.getStyleClass().add("message");
		message.setText(p.getName()+" has won !\nGame Over !");
		hbox.relocate(0,40);
		hbox.getChildren().add(message);
		root.getChildren().add(hbox);
		gameLoop.stop();
	}
	
	public static void pause() {
		gameLoop.stop();
	}
	
	public static void unpause() {
		gameLoop.start();
	}

	/**
	 * Updates game data and actions in the game.
	 * 
	 * @throws InterruptedException ?
	 */
	private void update() throws InterruptedException {
		Settings.NB_CURRENT_ROUND++;
		round.setText(""+Settings.NB_CURRENT_ROUND);
		Player p;
		if((p = kingdom.update())!=null)
			gameOver(p);
		Thread.sleep(Settings.ROUND_TIME);
	}

	public static Pane getPlayfieldLayer() {
		return playfieldLayer;
	}
	
	/**
	 * Allows to launch an applications with arguments 
	 * @see launch
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}