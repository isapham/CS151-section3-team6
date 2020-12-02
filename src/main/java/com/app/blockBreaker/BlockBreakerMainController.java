package main.java.com.app.blockBreaker;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.com.app.GamesController;

public class BlockBreakerMainController {
	public static Integer points = 0;
	
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
	
	@FXML
	public void switchToSavePoint(ActionEvent event) throws IOException {
		Stage currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currStage.close();
    	FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/BlockBreakerDB.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent save_point_page = fxmlLoader.load();

		Scene save_point_scene = new Scene(save_point_page);
		Stage scene4 = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		scene4.setTitle("Save Point to DB"); 
		scene4.setScene(save_point_scene);
		scene4.show();
	 }
}
