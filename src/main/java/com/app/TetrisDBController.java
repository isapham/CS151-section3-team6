package main.java.com.app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.com.app.tetris.logic.Score;

public class TetrisDBController implements Initializable{
	
	@FXML
	private TextField gameScoreTxt,totalScoreTxt;
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		try {
			GamesController.insertUsernameTetris();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gameScoreTxt.setText(Score.score.getValue().toString());
		totalScoreTxt.setText(Integer.toString(GamesController.totalPoints));
	}
	
	
	@FXML
	public void switchToGameMenu(ActionEvent event) throws IOException {
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


