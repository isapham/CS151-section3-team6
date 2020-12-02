package main.java.com.app;
	
import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The program implements an application that allow users
 * to play 4 retro games
 * 
 * @author Ngan Luu, Isabel Pham, John Hoang, Anthony Nguyen
 * @since 2020-08
 */
public class Main extends Application {
	/**
	 * Start application
	 * @param primaryStage is the first stage 
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			String pathToFxml = "src/main/resources/MainMenu.fxml";
			URL fxmlUrl = new File(pathToFxml).toURI().toURL();
			fxmlLoader.setLocation(fxmlUrl);
			BorderPane root = fxmlLoader.load();
			
			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(root,844,750);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Main Menu Homepage");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Launch application
     * 
     * @param args
     */
	public static void main(String[] args) {
		Application.launch(args);
	}
}
