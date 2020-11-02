package app;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//FXMLLoader loader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			//Parent root = loader.load();
			Scene scene = new Scene(root,844,750);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
