package application.pong;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class the establish the general Pong game controls
 *
 */
public class PongController {
	@FXML protected Canvas canvas;
	@FXML protected ImageView playerPointIcon;
	//ball attributes or variables
	public static double BALL_RADIUS = 10; //radius of ball!
	public static int ballYSpeed = 1;
	public static int ballXSpeed = 1;
	public static Integer ppoints = 0;
	
	//create an array list to store and sort block breaker score
	public static ArrayList<Integer> pongScoreList = new ArrayList<Integer>();
	
	//ball's initial location
    public static double ballXPos = GamesController.PONG_WIDTH/2;
    public static double ballYPos = GamesController.PONG_WIDTH/2;
    
    //score
    public static int playerScore = 0;
    public static int compScore = 0;
    
    //X-Position of the paddles
    public int playerPaddleXPos = 0;
    public double compPaddleXPos = GamesController.PONG_WIDTH - GamesController.PADDLE_WIDTH;
    
    /**
     * Method that add points to the score
     * @param num - score earned
     */
	protected void pong_score(int num) {
		ppoints += num;
	}
	
	/**
	 * Method that initialize the game
	 */
	public void initialize() {
		ppoints=0;
		playerScore = 0;
		compScore = 0;
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Timeline t1 = new Timeline(new KeyFrame(Duration.millis(10), e ->run(gc)));
		t1.setCycleCount(Timeline.INDEFINITE);
		t1.play();
	}
	
	/**
	 * Method that runs the pong game controller with ball as circle
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

            if (GamesController.circleBall == true) {
            	//draw round ball
                gc.fillOval(ballXPos, ballYPos, BALL_RADIUS, BALL_RADIUS);
            } else {
            	//draw square ball
                gc.fillRect(ballXPos, ballYPos, BALL_RADIUS, BALL_RADIUS);
            }
            
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
	 * Method that switch to the game menu when event occurs
	 * @param event - button click on "Exit Game"
	 * @throws IOException
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
	 * Method that switch to the save point mini menu when event occurs
	 * @param event - button click on "Save Point"
	 * @throws IOException
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
