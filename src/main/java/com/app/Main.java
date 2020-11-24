package main.java.com.app;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * @author Ngan Luu, Isabel Pham, John Hoang, Anthony Nguyen
 *
 */
public class Main extends Application {
	/**
	 * Start application
	 */
	@Override
	public void start(Stage primaryStage) {
		try {			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(root,844,750);
			primaryStage.setScene(scene);
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
