package main.java.com.app;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.com.app.snake.SnakeController;
import main.java.com.app.tetris.TetrisController;
import main.java.com.app.tetris.gui.GuiController;

public class GamesController implements Initializable{

	@FXML  ImageView pongIcon;
	@FXML  ImageView snakeIcon;
	@FXML  ImageView blockBreakerIcon;
	@FXML  ImageView tetrisIcon;
	@FXML  ImageView controllerIcon;
	@FXML  ImageView pointIcon;
	
	//set image for buttons
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		controllerIcon.setImage(new Image("/main/java/com/app/cs151GameConsoleIcon.png"));
		pongIcon.setImage(new Image("/main/java/com/app/cs151GameGalPongIcon.png"));
		snakeIcon.setImage(new Image("/main/java/com/app/cs151GameGalSnakeIcon.png"));
		blockBreakerIcon.setImage(new Image("/main/java/com/app/cs151GameGalBlockBreakerIcon.png"));
		tetrisIcon.setImage(new Image("/main/java/com/app/cs151GameGalTetrisIcon.png"));
		pointIcon.setImage(new Image("/main/java/com/app/cs151GameGalPointsIcon1.png"));
	}
	
	//instances for block breaker
	public static boolean goLeft = false;
	public static boolean goRight = false;
	public static boolean start = false;
	
    //create scene to begin game
	public void switchToBlockBreaker(ActionEvent event)throws IOException {
		Parent block_breaker_page = FXMLLoader.load(getClass().getResource("BlockBreaker.fxml"));
		Scene block_breaker_scene = new Scene(block_breaker_page);
		
		block_breaker_scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case LEFT: goLeft = true;
					break;
				case RIGHT: goRight = true;
					break;
				case SPACE: start = true;
					break;
				default:
					break;
				}	
			}	
		});
		
		block_breaker_scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case LEFT: goLeft = false;
					break;
				case RIGHT: goRight = false;
					break;
				default:
					break;
				}
			}
		});

		Stage scene3 = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene3.setTitle("Block Breaker"); 
		scene3.setScene(block_breaker_scene);
		scene3.show();
	}
	

	
	public void switchToMainMenu(ActionEvent event) {
    	Parent mainMenu;
    
		try {
			mainMenu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene mainMenuScene = new Scene (mainMenu);
			Stage windowView = (Stage) ((Node)event.getSource()).getScene().getWindow();
			windowView.setScene(mainMenuScene);
			windowView.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
	//instances for pong
	//paddle is what the user uses to hit the ball with in the game
	public static int PADDLE_HEIGHT = 100;
	public static int PADDLE_WIDTH = 15;
	//game canvas
	public static int PONG_HEIGHT = 609;
	public static int PONG_WIDTH = 602;
	
    //indicator if game started or not
	public static boolean gameStarted=false;
	
    //player's paddle initial location (note that paddle movement is only up and down):
	public static double playerPaddleYPos = PONG_HEIGHT/2;
	public static double compPaddleYPos = PONG_HEIGHT/2;
	
	public void switchToPong(ActionEvent event) throws IOException{
		Parent pong_page = FXMLLoader.load(getClass().getResource("Pong.fxml"));
		Scene pong_scene = new Scene(pong_page);
		Stage scene4 = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		scene4.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            switch (key.getCode()) {
                case ENTER:
                    gameStarted = true;
                    break;
                case UP:
                    if (playerPaddleYPos > 0)
                        playerPaddleYPos -= 20;
                    break;
                case DOWN:
                    if (playerPaddleYPos < PONG_HEIGHT - PADDLE_HEIGHT)
                        playerPaddleYPos += 20;
                    break;
                default:
                	break;
            }
        });
		scene4.setTitle("Pong"); 
		scene4.setScene(pong_scene);
		scene4.show();
	}
	
	public void switchToTetris(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../app/Tetris.fxml"));
        Parent root = fxmlLoader.load();
        GuiController c = fxmlLoader.getController();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("TetrisJFX");
        Scene scene = new Scene(root, 400, 510);
        primaryStage.setScene(scene);
        primaryStage.show();
        new TetrisController(c);
	}
	
	
	
	public void switchToSnake(ActionEvent event) throws IOException{
        SnakeController snakeGame = new SnakeController();
        snakeGame.start();
	}
	
	
	

}
