package application.blockBreaker;

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

public class BlockBreakerDBController extends BlockBreakerMainController implements Initializable {
	@FXML
	private TextField gameScoreTxt,totalScoreTxt;
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		GamesController.totalPoints = points+GamesController.totalPoints;
		bbScoreList.add(points);
		Collections.sort(bbScoreList,Collections.reverseOrder());  
		gameScoreTxt.setText(points.toString());
		totalScoreTxt.setText(Integer.toString(GamesController.totalPoints));
	}
	
	
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


