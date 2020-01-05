package SampleGame;

import SampleGame.player.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
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

	private Pane playfieldLayer;

	//HUD
	private Text scoreMessage = new Text();

	//Things
	private Scene scene;
	//private Input input;
	private AnimationTimer gameLoop;
	
	private boolean pause = false;	
	private boolean released = false;
	
	Group root;
	
	Kingdom kingdom;

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

		createKingdom();
		createStatusBar();

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().toString()=="P") {
					if(!pause) {
						pause = true;
						gameLoop.stop();
					}else if(released) {
						pause = false;
						released = false;
						gameLoop.start();
					}
				}
			}
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) { 	
				if(event.getCode().toString()=="P" && pause) {
					released = true;
				}				
			}
		});
	}

	/**
	 * To BE INPLEMENTED
	 * Allows to print status of a castle in the window
	 */
	
	public void createStatusBar() {
		HBox statusBar = new HBox();
		//scoreMessage.setText("Score : 0          Life : " + player.getHealth());
		statusBar.getChildren().addAll(scoreMessage);
		statusBar.getStyleClass().add("statusBar");
		//statusBar.relocate(0, Settings.SCENE_HEIGHT);
		//statusBar.setPrefSize(Settings.SCENE_WIDTH, Settings.STATUS_BAR_HEIGHT);
		root.getChildren().add(statusBar);
	}

	/**
	 * Creates a new game manager.
	 */
	private void createKingdom() {
		kingdom = new Kingdom(playfieldLayer);
	}

	
	/**
	 * TO BE IMPLEMENTED
	 * Quits the game when the player has won or lose
	 */
	private void gameOver(Player p) {
		p.won();
		HBox hbox = new HBox();
		hbox.setPrefSize(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		hbox.getStyleClass().add("message");
		Text message = new Text();
		message.getStyleClass().add("message");
		message.setText(p.getName()+" has won !\nGame Over !");
		hbox.getChildren().add(message);
		root.getChildren().add(hbox);
		gameLoop.stop();
	}

	/**
	 * Updates game data and actions in the game.
	 * 
	 * @throws InterruptedException ?
	 */
	private void update() throws InterruptedException {
		Settings.NB_CURRENT_ROUND++;
		Player p;
		if((p = kingdom.update())!=null)
			gameOver(p);
		Thread.sleep(Settings.ROUND_TIME);
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