package application.blockBreaker;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * This is the parent class of block breaker game
 * 
 *
 */
public class BlockBreakerMainController {
	@FXML protected Pane pane;
	@FXML protected GridPane brickGrid;

	@FXML protected Label messageForLose;
	@FXML protected Rectangle paddle;
    @FXML protected TextField pointsTextField;
    @FXML protected ImageView pointsIcon;
    
    // Teal Bricks - 1st Row
    @FXML protected Rectangle tb6;
    @FXML protected Rectangle tb5;
    @FXML protected Rectangle tb4;
    @FXML protected Rectangle tb3;
    @FXML protected Rectangle tb2;
    @FXML protected Rectangle tb1;
    
    // Blue Bricks - 2nd Row
	@FXML protected Rectangle bb1;
    @FXML protected Rectangle bb2;
    @FXML protected Rectangle bb3;
    @FXML protected Rectangle bb4;
    @FXML protected Rectangle bb5;
    @FXML protected Rectangle bb6;
    
    // Purple Bricks - 3rd Row
    @FXML protected Rectangle pb1;
    @FXML protected Rectangle pb2;
    @FXML protected Rectangle pb3;
    @FXML protected Rectangle pb4;
    @FXML protected Rectangle pb5;
    @FXML protected Rectangle pb6;

    // Hot Pink Bricks - 4th Row
    @FXML protected Rectangle hb1;
    @FXML protected Rectangle hb2;
    @FXML protected Rectangle hb3;
    @FXML protected Rectangle hb4;
    @FXML protected Rectangle hb5;
    @FXML protected Rectangle hb6;
    
    // Red Bricks - 5th Row
    @FXML protected Rectangle rb2;
    @FXML protected Rectangle rb5;
    @FXML protected Rectangle rb3;
    @FXML protected Rectangle rb4;
    @FXML protected Rectangle rb6;
    @FXML protected Rectangle rb1;
    
	public static Integer points = 0;
		
	//create an array list to store and sort block breaker score
	public static ArrayList<Integer> bbScoreList = new ArrayList<Integer>();
	
	// Random for ball bouncing
	SecureRandom random = new SecureRandom();
	int dx = 1 + random.nextInt(5);
	int dy = 1 + random.nextInt(5);
		
	/**
	 * This method is a button function when user wants to switch back to game menu homepage
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
		windowView.setTitle("Games Home Page");
		windowView.show();
	 }
	
	/**
	 * This method is a button function for user to save points to the DB
	 * @param event
	 * @throws IOException
	 */
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
