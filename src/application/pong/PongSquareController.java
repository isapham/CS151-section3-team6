package application.pong;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import application.GamesController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class that generates the Pong game with the ball as a square instead of a circle (alternative shape)
 *
 */
public class PongSquareController extends PongController{
	
	/**
	 * Method that initialize the game and points
	 */
	public void initialize() {
		ppoints=0;
		playerPointIcon.setImage(new Image("/main/resources/cs151GameGalPointsIcon1.png"));
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Timeline t1 = new Timeline(new KeyFrame(Duration.millis(10), e ->run(gc)));
		t1.setCycleCount(Timeline.INDEFINITE);
		t1.play();
	}
	
	/**
	 * Method that runs the game with the ball as a square
	 * @param gc
	 */
	private void run(GraphicsContext gc){
        //set background color
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, GamesController.PONG_WIDTH , GamesController.PONG_HEIGHT);
        //set text color
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));

        if(GamesController.gameStarted){
            //set ball movement
        	ballXPos += ballXSpeed;
            ballYPos += ballYSpeed;

            //computer or CPU
            if(ballXPos <  GamesController.PONG_WIDTH - GamesController.PONG_WIDTH/4){
                GamesController.compPaddleYPos = ballYPos - GamesController.PADDLE_HEIGHT/2;
            }
            else{
            	GamesController.compPaddleYPos =  ballYPos > GamesController.compPaddleYPos + GamesController.PADDLE_HEIGHT/2 ? GamesController.compPaddleYPos += 1: GamesController.compPaddleYPos - 1;
            }

            //draw ball
            //gc.fillOval(ballXPos, ballYPos, BALL_RADIUS, BALL_RADIUS);
            gc.fillRect(ballXPos, ballYPos, BALL_RADIUS, BALL_RADIUS);
        }
        else{
            //set the start text
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("Click Enter to Start", GamesController.PONG_WIDTH/2, GamesController.PONG_HEIGHT/2 );

            //reset ball start position
            ballXPos = GamesController.PONG_WIDTH / 2;
            ballYPos = GamesController.PONG_HEIGHT / 2;

            //reset speed and direction
            ballXSpeed = new Random().nextInt(2) == 0 ? 1: - 1;
            ballYSpeed = new Random().nextInt(2) == 0 ? 1: - 1;

        }

        //ball stays in canvas
        if(ballYPos > GamesController.PONG_HEIGHT || ballYPos < 0){
            ballYSpeed *= -1;
        }

        //computer gets a point
        if(ballXPos < playerPaddleXPos - GamesController.PADDLE_WIDTH){
            compScore += 1;
            GamesController.gameStarted = false;
        }

        
    	
        //human player
        if(ballXPos > compPaddleXPos + GamesController.PADDLE_WIDTH){
        	pong_score(1);
        	playerScore += 1;
        	GamesController.gameStarted = false;
            
        }

        //increase ball speed
        if(((ballXPos + BALL_RADIUS > compPaddleXPos) && ballYPos >= GamesController.compPaddleYPos && ballYPos <= GamesController.compPaddleYPos + GamesController.PADDLE_HEIGHT)
                || ((ballXPos < playerPaddleXPos + GamesController.PADDLE_WIDTH) && ballYPos >= GamesController.playerPaddleYPos && ballYPos <= GamesController.playerPaddleYPos + GamesController.PADDLE_HEIGHT)){
            ballYSpeed += 1 * Math.signum(ballYSpeed);
            ballXSpeed += 1 * Math.signum(ballXSpeed);
            ballXSpeed *= -1;
            ballYSpeed *= -1;
        }

        //draw score
        gc.fillText(playerScore + "\t\t\t\t\t\t\t" + compScore, GamesController.PONG_WIDTH/2, 100);

        
        //draw player one and CPU
        gc.fillRect(compPaddleXPos, GamesController.compPaddleYPos, GamesController.PADDLE_WIDTH, GamesController.PADDLE_HEIGHT);
        gc.fillRect(playerPaddleXPos, GamesController.playerPaddleYPos, GamesController.PADDLE_WIDTH, GamesController.PADDLE_HEIGHT);
    }
	
	/**
	 * Method that switches to the game menu when the event occurs
	 * @param event - button click on "Exit Game"
	 */
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
	 * Method that swtiches to the save point mini menu when the event occurs
	 * @param event - button click on "Save Point"
	 */
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
