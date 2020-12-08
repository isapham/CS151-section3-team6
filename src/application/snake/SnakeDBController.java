package application.snake;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import application.GamesController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This class is for saving score of snake game
 *
 */
public class SnakeDBController extends SnakeController implements Initializable{
	@FXML
	private TextField gameScoreTxt,totalScoreTxt;
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		GamesController.totalPoints = snakeScore+GamesController.totalPoints;
		snakeScoreList.add(snakeScore);
		Collections.sort(snakeScoreList,Collections.reverseOrder()); 
		GamesController.saveToFile("src/main/resources/outputSnake.txt",snakeScoreList);

		gameScoreTxt.setText(Integer.toString(snakeScore));
		totalScoreTxt.setText(Integer.toString(GamesController.totalPoints));
	}
	
	/**
	 * This method is for switch back to main menu
	 */
	@FXML
	public void switchToGameMenu(ActionEvent event) throws IOException {
		Stage currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currStage.close();
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/Games.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent gamesMenu = fxmlLoader.load();    
		
		Scene gamesMenuScene = new Scene (gamesMenu);
		Stage windowView = (Stage) ((Node)event.getSource()).getScene().getWindow();
		windowView.setScene(gamesMenuScene);
		windowView.show();
	 }
}


