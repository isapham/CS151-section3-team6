package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class GamesController implements Initializable{
	@FXML  ImageView pongIcon;
	@FXML  ImageView snakeIcon;
	@FXML  ImageView blockBreakerIcon;
	@FXML  ImageView tetrisIcon;
	@FXML  ImageView controllerIcon;
	@FXML  ImageView pointIcon;
	public static boolean goLeft = false;
	public static boolean goRight = false;
	public static boolean start = false;
	
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
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		controllerIcon.setImage(new Image("/app/cs151GameConsoleIcon.png"));
		pongIcon.setImage(new Image("/app/cs151GameGalPongIcon.png"));
		snakeIcon.setImage(new Image("/app/cs151GameGalSnakeIcon.png"));
		blockBreakerIcon.setImage(new Image("/app/cs151GameGalBlockBreakerIcon.png"));
		tetrisIcon.setImage(new Image("/app/cs151GameGalTetrisIcon.png"));
		pointIcon.setImage(new Image("/app/cs151GameGalPointsIcon1.png"));
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
	
}
