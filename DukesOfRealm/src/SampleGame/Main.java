package SampleGame;

import java.util.Random;

import SampleGame.army.Soldier;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Main extends Application {

	private Pane playfieldLayer;

	
	//private Player player;									//Dukes
	
	//Elements
	//private List<Enemy> enemies = new ArrayList<>();			//Castles? Tiles? Map?
	//private List<Missile> missiles = new ArrayList<>();		//Soldiers

	//HUD
	private Text scoreMessage = new Text();

	//Things
	private Scene scene;
	//private Input input;
	private AnimationTimer gameLoop;
	
	private boolean pause = false;	
	
	private int nb_tour = 0;
	
	Group root;
	
	Kingdom kingdom;

	@Override
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

	private void loadGame() {

		createKingdom();
		createStatusBar();
		
		
		
		scene.setOnMousePressed(e -> {
			//player.setX(e.getX() - (player.getWidth() / 2));
			//player.setY(e.getY() - (player.getHeight() / 2));
		});
	}


	public void createStatusBar() {
		HBox statusBar = new HBox();
		//scoreMessage.setText("Score : 0          Life : " + player.getHealth());
		statusBar.getChildren().addAll(scoreMessage);
		statusBar.getStyleClass().add("statusBar");
		//statusBar.relocate(0, Settings.SCENE_HEIGHT);
		//statusBar.setPrefSize(Settings.SCENE_WIDTH, Settings.STATUS_BAR_HEIGHT);
		root.getChildren().add(statusBar);
	}

	private void createKingdom() {
		
		kingdom = new Kingdom(playfieldLayer);
		
	}

	private void gameOver() {
		HBox hbox = new HBox();
		hbox.setPrefSize(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		hbox.getStyleClass().add("message");
		Text message = new Text();
		message.getStyleClass().add("message");
		message.setText("Game over");
		hbox.getChildren().add(message);
		root.getChildren().add(hbox);
		gameLoop.stop();
	}

	private void update() throws InterruptedException {
		nb_tour++;
		kingdom.update();
		//System.out.println(nb_tour);
		Thread.sleep(Settings.ROUND_TIME);
	}

	public static void main(String[] args) {
		launch(args);
	}

}