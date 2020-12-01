package main.java.com.app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ItemShopController implements Initializable{
	@FXML ImageView storeIcon;
	@FXML Button blockBreakerButton, pongButton, snakeButton, tetrisButton;
	
	
	//initialize images
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		storeIcon.setImage(new Image("/main/resources/cs151GameShopIcon.png"));
		if (GamesController.totalPoints >= 10 && GamesController.totalPoints < 20) {
			pongButton.setDisable(false);
		}else if(GamesController.totalPoints >= 20 && GamesController.totalPoints < 30) {
			pongButton.setDisable(false);
			tetrisButton.setDisable(false);
		}else if (GamesController.totalPoints >= 30 && GamesController.totalPoints < 40) {
			blockBreakerButton.setDisable(false);
			tetrisButton.setDisable(false);
			pongButton.setDisable(false);
		}else if (GamesController.totalPoints >= 40) {
			snakeButton.setDisable(false);
			blockBreakerButton.setDisable(false);
			tetrisButton.setDisable(false);
			pongButton.setDisable(false);
		}
	
	}
	
	//Button function to switch back to main menu
	@FXML
	public void switchToMainMenu(ActionEvent event) throws IOException{
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

	//non-action function
	public void switchToMainMenu() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/MainMenu.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent main_menu_page = fxmlLoader.load();    
		
		Scene mainMenuScene = new Scene (main_menu_page);
		Stage windowView = new Stage();
		windowView.setScene(mainMenuScene);
		windowView.setTitle("Main Menu Homepage");
		windowView.show();
    }
	
	//points trade-off function for BlockBreaker
	@FXML
	public void changeToSquareBall(ActionEvent event) throws IOException {
		GamesController.ballShape = 1; 
		GamesController.totalPoints = GamesController.totalPoints - 30;
		switchToMainMenu();
	}
	
	//points trade-off function for Pong
	@FXML
	public void changeToSquarePongBall(ActionEvent event) throws IOException {
		GamesController.circleBall = false; 
		GamesController.totalPoints = GamesController.totalPoints - 10;
		switchToMainMenu();
	}
	
	//points trade-off function for Snake
	@FXML
	public void changeFruitToTriangle(ActionEvent event) throws IOException {
		GamesController.fruitRoundShape = false; 
		GamesController.totalPoints = GamesController.totalPoints - 40;
		switchToMainMenu();
	}
	
	//points trade-off function for Tetris
	@FXML
	public void changeToTetrisEffect(ActionEvent event) {
		
	}
	
	//switch back to round shape ball (Pong)
	@FXML 
	public void switchToOGPong (ActionEvent event) throws IOException {
		GamesController.circleBall = true; 
		switchToMainMenu();
	}
	
	//switch back ... (Tetris)
	@FXML 
	public void switchToOGTetris (ActionEvent event) {
		
	}
	
	//switch back to round shape ball (BlockBreaker)
	@FXML 
	public void switchToOGBlockBreaker (ActionEvent event) throws IOException {
		GamesController.ballShape = 0;
		switchToMainMenu();
	}
	
	//switch back to round shape fruit (Snake)
	@FXML 
	public void switchToOGSnake (ActionEvent event) throws IOException {
		GamesController.fruitRoundShape = true; 
		switchToMainMenu();
	}
	
//	public static ObservableList<HiScore> getTotalScoreRecords() throws ClassNotFoundException, SQLException{
//		String sql = "SELECT sumPoint FROM users"+" ORDER BY sumPoint DESC LIMIT 1";
//		try {
//			ResultSet lastestScore = ConnectionClass.dbExecute(sql);
//			//ResultSet rsSet = ConnectionClass.dbExecute(sql);
//			ObservableList<HiScore> list = getTotalScoreObjects(lastestScore);
//			return list;
//		}
//		catch (SQLException e) {
//			System.out.println("Error occured while fetching the records from DB"+e);
//			e.printStackTrace();
//			throw e;
//		}
//	}
//	
//	private static ObservableList<HiScore> getTotalScoreObjects(ResultSet rsSet)throws ClassNotFoundException, SQLException {
//		try{
//			ObservableList<HiScore> list = FXCollections.observableArrayList();
//			
//			while(rsSet.next()) {
//				HiScore hiScore = new HiScore();
//				hiScore.setPoScore(rsSet.getInt("PongScore"));
//				list.add(hiScore);
//			}
//			return list;
//		}catch (SQLException e){
//			System.out.println("Error ocurred while fetching the data from DC"+e);
//			e.printStackTrace();
//			throw e;
//		}
//	}
}
