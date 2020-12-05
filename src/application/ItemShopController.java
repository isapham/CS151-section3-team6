package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.tetris.gui.GuiController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ItemShopController implements Initializable{
	@FXML ImageView storeIcon;
	@FXML Label alertText, alertText1;
	@FXML Button blockBreakerButton, pongButton, snakeButton, tetrisButton, ogBB, ogTetris, ogSnake, ogPong;
	@FXML ImageView pointIcon;
	@FXML TextField totalScore;
	@FXML TextArea tetrisRule;
	
	//initialize images
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pointIcon.setImage(new Image("/main/resources/cs151GameGalPointsIcon1.png"));
		totalScore.setStyle("-fx-text-fill: white; -fx-background-color: black");
		totalScore.setText(Integer.toString(GamesController.totalPoints));
		storeIcon.setImage(new Image("/main/resources/cs151GameShopIcon.png"));
		if (GamesController.totalPoints >= 10 && GamesController.totalPoints < 20) {
			pongButton.setDisable(false);
			ogBB.setDisable(false);
			if (GamesController.cbEnabled == 0 ) {
				ogTetris.setDisable(false);
			}
			ogSnake.setDisable(false);
			ogPong.setDisable(false);
		}else if(GamesController.totalPoints >= 20 && GamesController.totalPoints < 30) {
			pongButton.setDisable(false);
			tetrisAlert();
			ogBB.setDisable(false);
			ogSnake.setDisable(false);
			ogPong.setDisable(false);
		}else if (GamesController.totalPoints >= 30 && GamesController.totalPoints < 40) {
			blockBreakerButton.setDisable(false);
			tetrisAlert();
			pongButton.setDisable(false);
			ogBB.setDisable(false);
			ogSnake.setDisable(false);
			ogPong.setDisable(false);
		}else if (GamesController.totalPoints >= 40) {
			snakeButton.setDisable(false);
			blockBreakerButton.setDisable(false);
			tetrisAlert();
			pongButton.setDisable(false);
			ogBB.setDisable(false);
			ogSnake.setDisable(false);
			ogPong.setDisable(false);
		}
	}
	
	/**
	 * This method is tetris game.
	 * if user is using colorblind mode, they cannot trade points for tetris effect. 
	 */
	public void tetrisAlert() {
		if (GamesController.cbEnabled == 0 ) {
			tetrisButton.setDisable(false);
			ogTetris.setDisable(false);
		}
		else {
			alertText.setTextFill(Color.WHITE);
			alertText.setVisible(true);
			alertText1.setTextFill(Color.WHITE);
			alertText1.setVisible(true);
		}
	}
	/**
	 * This method is the button function to switch back to home page
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void switchToMainMenu(ActionEvent event) throws IOException{
		Stage currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currStage.close();
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/MainMenu.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent main_menu_page = fxmlLoader.load();    
		
		Scene mainMenuScene = new Scene (main_menu_page);
		Stage windowView = (Stage) ((Node)event.getSource()).getScene().getWindow();
		windowView.setScene(mainMenuScene);
		windowView.setTitle("Main Menu Homepage");
		windowView.show();
    }
	
	/**
	 * This method is for points trade-off function for BlockBreaker game
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void changeToSquareBall(ActionEvent event) throws IOException {
		GamesController.ballShape = 1; 
		GamesController.totalPoints = GamesController.totalPoints - 30;
		switchToMainMenu(event);
	}
	
	/**
	 * This method is for points trade-off function for Pong game
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void changeToSquarePongBall(ActionEvent event) throws IOException {
		GamesController.circleBall = false; 
		GamesController.totalPoints = GamesController.totalPoints - 10;
		switchToMainMenu(event);
	}
	
	/**
	 * This method is for points trade-off function for Snake game
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void changeFruitToTriangle(ActionEvent event) throws IOException {
		GamesController.fruitRoundShape = false; 
		GamesController.totalPoints = GamesController.totalPoints - 40;
		switchToMainMenu(event);
	}
	
	/**
	 * This method is for points trade-off function for Tetris game
	 * @param event
	 * @throws IOException 
	 */
	@FXML
	public void changeToTetrisEffect(ActionEvent event) throws IOException {
		GuiController.tetrisColor = 1;
		GamesController.totalPoints = GamesController.totalPoints - 20;
		switchToMainMenu(event);
	}
	
	/**
	 * This method is for switch back to round shape ball (Pong)
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void switchToOGPong (ActionEvent event) throws IOException {
		GamesController.circleBall = true; 
		GamesController.totalPoints = GamesController.totalPoints - 10;
		switchToMainMenu(event);
	}
	
	/**
	 * This method is for switch back to original black and white color palette (Tetris)
	 * @param event
	 * @throws IOException 
	 */
	@FXML 
	public void switchToOGTetris (ActionEvent event) throws IOException {
		GuiController.tetrisColor = 0;
		GamesController.totalPoints = GamesController.totalPoints - 10;
		switchToMainMenu(event);
	}
	
	/**
	 * This method is for switch back to original original round ball shape (Block Breaker)
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void switchToOGBlockBreaker (ActionEvent event) throws IOException {
		GamesController.ballShape = 0;
		GamesController.totalPoints = GamesController.totalPoints - 10;
		switchToMainMenu(event);
	}
	
	/**
	 * This method is for switch back to original round shape fruit (snake)
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void switchToOGSnake (ActionEvent event) throws IOException {
		GamesController.fruitRoundShape = true; 
		GamesController.totalPoints = GamesController.totalPoints - 10;
		switchToMainMenu(event);
	}
}
