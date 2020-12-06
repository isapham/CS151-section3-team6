package application.tetris;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import application.GamesController;
import application.tetris.logic.Score;
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
 * This class is for saving user tetris score 
 *
 */
public class TetrisDBController implements Initializable{
	
	@FXML
	private TextField gameScoreTxt,totalScoreTxt;
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		GamesController.totalPoints = Score.score.getValue()+GamesController.totalPoints;
		Score.tetrisScoreList.add(Score.score.getValue());
		Collections.sort(Score.tetrisScoreList,Collections.reverseOrder()); 
		gameScoreTxt.setText(Score.score.getValue().toString());
		totalScoreTxt.setText(Integer.toString(GamesController.totalPoints));
	}
	
	/**
	 * This method is button function for user to switch back to menu
	 * @param event
	 * @throws IOException
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


