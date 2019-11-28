package SampleGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import SampleGame.army.Factory;
import SampleGame.army.Piquier;
import SampleGame.army.Soldier;
import SampleGame.tiles.Castle;
import SampleGame.tiles.Castle.Orientation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Main extends Application {
	private Random rnd = new Random();

	private Pane playfieldLayer;

	//Images
	private Image castleImage;

	
	//private Player player;									//Dukes
	
	//Elements
	//private List<Enemy> enemies = new ArrayList<>();			//Castles? Tiles? Map?
	//private List<Missile> missiles = new ArrayList<>();		//Soldiers

	//HUD
	private Text scoreMessage = new Text();
	private int scoreValue = 0;
	private boolean collision = false;

	//Things
	private Scene scene;
	//private Input input;
	private AnimationTimer gameLoop;
	
	private boolean pause = false;
	
	Group root;

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
					//processInput(input, now);

					// player input
					//player.processInput();

					// add random enemies
					spawnEnemies(true);

					// movement
					//player.move();
					//enemies.forEach(sprite -> sprite.move());
					//missiles.forEach(sprite -> sprite.move());

					// check collisions
					checkCollisions();

					// update sprites in scene
					//player.updateUI();
					//enemies.forEach(sprite -> sprite.updateUI());
					//missiles.forEach(sprite -> sprite.updateUI());

					// check if sprite can be removed
					//enemies.forEach(sprite -> sprite.checkRemovability());
					//missiles.forEach(sprite -> sprite.checkRemovability());

					// remove removables from list, layer, etc
					//removeSprites(enemies);
					//removeSprites(missiles);

					// update score, health, etc
					update();
				}
			}

			//private void processInput(Input input, long now) {
			//	if (input.isExit()) {
			//		Platform.exit();
			//		System.exit(0);
			//	}else if (input.isFire()) {
			//		fire(now);
			//	}

			//}

		};
		gameLoop.start();
	}

	private void loadGame() {
		//playerImage = new Image(getClass().getResource("/images/alien.png").toExternalForm(), 100, 100, true, true);
		castleImage = new Image(getClass().getResource("/images/Castle.jpg").toExternalForm(), 50, 50, true, true);
		//missileImage = new Image(getClass().getResource("/images/pinapple.png").toExternalForm(), 20, 20, true, true);

		//input = new Input(scene);
		//input.addListeners();

		createCastle();
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

	private void createCastle() {
		double x = (Settings.SCENE_WIDTH - castleImage.getWidth()) / 2.0;
		double y = Settings.SCENE_HEIGHT * 0.7;
		Soldier[] army = {new Piquier("Bob", 0, 0)};
		Castle castle = new Castle(playfieldLayer, castleImage, 0, 0, "Bob", 0, army, Orientation.S, new Factory(army[0]));
		
		castle.getView().setOnMousePressed(e -> {
		//	System.out.println("Click on player");
		//	e.consume();
		//});
		
		//player.getView().setOnContextMenuRequested(e -> {
		//	ContextMenu contextMenu = new ContextMenu();
		//	MenuItem low = new MenuItem("Slow");
		//	MenuItem medium= new MenuItem("Regular");
		//	MenuItem high= new MenuItem("Fast");
		//	low.setOnAction(evt -> player.setFireFrequencyLow());
		//	medium.setOnAction(evt -> player.setFireFrequencyMedium());
		//	high.setOnAction(evt -> player.setFireFrequencyHigh());
		//	contextMenu.getItems().addAll(low, medium, high);
		//	contextMenu.show(player.getView(), e.getScreenX(), e.getScreenY());
		//});
	}

	private void spawnEnemies(boolean random) {
		if (random && rnd.nextInt(Settings.ENEMY_SPAWN_RANDOMNESS) != 0) {
			return;
		}
		double speed = rnd.nextDouble() * 3 + 1.0;
		double x = rnd.nextDouble() * (Settings.SCENE_WIDTH - enemyImage.getWidth());
		double y = -enemyImage.getHeight();
		int health = rnd.nextInt()%5 +1;
		enemyImage = new Image(getClass().getResource("/images/enemy.png").toExternalForm(), 30 + 20*health, 30 + 20*health, true, true);
		//Enemy enemy = new Enemy(playfieldLayer, enemyImage, x, y, health, 1, speed);	
		//enemies.add(enemy);
	}

	private void fire(long now) {
		//if (player.canFire(now)) {
		//	Missile missile = new Missile(playfieldLayer, missileImage, player.getCenterX(), player.getY(),
		//			Settings.MISSILE_DAMAGE, Settings.MISSILE_SPEED);
					//	missiles.add(missile);
		//	player.fire(now);
		//}
	}

	//private void removeSprites(List<? extends Sprite> spriteList) {
	//	Iterator<? extends Sprite> iter = spriteList.iterator();
	//	while (iter.hasNext()) {
	//		Sprite sprite = iter.next();

	//		if (sprite.isRemovable()) {
				// remove from layer
	//			sprite.removeFromLayer();
				// remove from list
	//			iter.remove();
	//		}
	//	}
	//}

	private void checkCollisions() {
		collision = false;

		//for (Enemy enemy : enemies) {
		//	for (Missile missile : missiles) {
		//		if (missile.collidesWith(enemy)) {
		//			enemy.damagedBy(missile);
		//			missile.remove();
		//			collision = true;
		//			scoreValue += 10 + (Settings.SCENE_HEIGHT - player.getY()) / 10;
		//		}
		//	}

		//	if (player.collidesWith(enemy)) {
		//		collision = true;
		//		enemy.remove();
		//		player.damagedBy(enemy);
		//		if (player.getHealth() < 1)
		//			gameOver();
		//	}
		//}

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

	private void update() {
		if (collision) {
			//scoreMessage.setText("Score : " + scoreValue + "          Life : " + player.getHealth());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}