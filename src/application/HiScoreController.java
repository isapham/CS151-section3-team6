package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import application.pong.PongController;
import application.snake.SnakeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HiScoreController {
	
	
	@FXML private ImageView hiScoreIcon1, hiScoreIcon2;
	
	@FXML private TableColumn<HiScore, Integer> colPongScore, colBlockBreakerScore, colTetrisScore, colSnakeScore;
	
	@FXML private TableView<HiScore> pongTable, blockBreakerTable, tetrisTable, snakeTable ;
	
	@FXML
	public void initialize () throws Exception{
		hiScoreIcon1.setImage(new Image("/main/resources/cs151GameHiScoreIcon.png"));
		hiScoreIcon2.setImage(new Image("/main/resources/cs151GameHiScoreIcon.png"));
		colBlockBreakerScore.setCellValueFactory(data -> data.getValue().getBlockBreakerScore().asObject());
		colBlockBreakerScore.setStyle("-fx-alignment: CENTER");
		colTetrisScore.setCellValueFactory(data -> data.getValue().getTetrisScore().asObject());
		colTetrisScore.setStyle("-fx-alignment: CENTER");
		colSnakeScore.setCellValueFactory(data -> data.getValue().getSnakeScore().asObject());
		colSnakeScore.setStyle("-fx-alignment: CENTER");
		colPongScore.setCellValueFactory(data -> data.getValue().getPongScore().asObject());
		colPongScore.setStyle("-fx-alignment: CENTER");
		
		ObservableList<HiScore> list1 =GamesController.getPongRecords();
		pongTable.setItems(list1);
		
		ObservableList<HiScore> list2 =GamesController.getBlockBreakerRecords();
		blockBreakerTable.setItems(list2);
		
		ObservableList<HiScore> list3 =GamesController.getSnakeRecords();
		snakeTable.setItems(list3);
		
		ObservableList<HiScore> list4 =GamesController.getTetrisRecords();
		tetrisTable.setItems(list4);
	}
	
	//Button function to switch back to main menu
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
}
