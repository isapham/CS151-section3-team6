package application.pong;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import application.GamesController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PongController {
	@FXML protected Canvas canvas;
	@FXML protected ImageView playerPointIcon;
	@FXML protected TextField playerScoreTextField;
	//ball attributes or variables
	public static double BALL_RADIUS = 10; //radius of ball!
	public static int ballYSpeed = 1;
	public static int ballXSpeed = 1;
	public static Integer ppoints = 0;
	 //ball's initial location
    public static double ballXPos = GamesController.PONG_WIDTH/2;
    public static double ballYPos = GamesController.PONG_WIDTH/2;
    
    //score
    public static int playerScore = 0;
    public static int compScore = 0;
    
    //X-Position of the paddles
    public int playerPaddleXPos = 0;
    public double compPaddleXPos = GamesController.PONG_WIDTH - GamesController.PADDLE_WIDTH;
    
	// Add points to score
	protected void pong_score(int num) {
		ppoints += num;
		playerScoreTextField.setStyle("-fx-text-fill: white; -fx-background-color: black");
		playerScoreTextField.setText(ppoints.toString());
	}
	
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
		windowView.setTitle("Games Home Page");
		windowView.show();
	 }
	
	public void switchToSavePoint(ActionEvent event) throws IOException {
		Stage currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currStage.close();
    	FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/PongDB.fxml";
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
