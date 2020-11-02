package sample;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;

/**
 * Class that represents the Pong game
 */
public class Pong extends Application {
    //variables of Pong game:
    //game canvas
    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    //paddle is what the user uses to hit the ball with in the game
    private static final int PADDLE_HEIGHT = 100;
    private static final int PADDLE_WIDTH = 15;

    //ball attributes or variables
    private static final double BALL_RADIUS = 10; //radius of ball!
    private int ballYSpeed = 1;
    private int ballXSpeed = 1;

    //player's paddle initial location (note that paddle movement is only up and down):
    private double playerPaddleYPos = GAME_HEIGHT/2;
    private double compPaddleYPos = GAME_HEIGHT/2;

    //ball's initial location
    private double ballXPos = GAME_WIDTH/2;
    private double ballYPos = GAME_WIDTH/2;

    //score
    private int playerScore = 0;
    private int compScore = 0;

    //indicator if game started or not
    private boolean gameStarted;

    //X-Position of the paddles
    private int playerPaddleXPos = 0;
    private double compPaddleXPos = GAME_WIDTH - PADDLE_WIDTH;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("PONG");
        Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(10), e ->run(gc))); //questionable...
        t1.setCycleCount(Timeline.INDEFINITE);

        Scene scene = new Scene(new StackPane(canvas));

        //key controls for Pong
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            switch (key.getCode()) {
                case ENTER:
                    gameStarted = true;
                    break;
                case UP:
                    if (playerPaddleYPos > 0)
                        playerPaddleYPos -= 20;
                    break;
                case DOWN:
                    if (playerPaddleYPos < GAME_HEIGHT - PADDLE_HEIGHT)
                        playerPaddleYPos += 20;
                    break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
        t1.play();
    }

    private void run(GraphicsContext gc){
        //set background color
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, GAME_WIDTH, GAME_HEIGHT);
        //set text color
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));

        if(gameStarted){
            //set ball movement
            ballXPos += ballXSpeed;
            ballYPos += ballYSpeed;

            //computer or CPU
            if(ballXPos <  GAME_WIDTH - GAME_WIDTH/4){
                compPaddleYPos = ballYPos - PADDLE_HEIGHT/2;
            }
            else{
                compPaddleYPos =  ballYPos > compPaddleYPos + PADDLE_HEIGHT/2 ? compPaddleYPos += 1: compPaddleYPos - 1;
            }

            //draw ball
            gc.fillOval(ballXPos, ballYPos, BALL_RADIUS, BALL_RADIUS);
        }
        else{
            //set the start text
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("Click Enter to Start", GAME_WIDTH/2, GAME_HEIGHT/2 );

            //reset ball start position
            ballXPos = GAME_WIDTH / 2;
            ballYPos = GAME_HEIGHT / 2;

            //reset speed and direction
            ballXSpeed = new Random().nextInt(2) == 0 ? 1: - 1;
            ballYSpeed = new Random().nextInt(2) == 0 ? 1: - 1;

        }

        //ball stays in canvas
        if(ballYPos > GAME_HEIGHT || ballYPos < 0){
            ballYSpeed *= -1;
        }

        //computer gets a point
        if(ballXPos < playerPaddleXPos - PADDLE_WIDTH){
            compScore += 1;
            gameStarted = false;
        }

        //human player
        if(ballXPos > compPaddleXPos + PADDLE_WIDTH){
            playerScore += 1;
            gameStarted = false;
        }

        //increase ball speed
        if(((ballXPos + BALL_RADIUS > compPaddleXPos) && ballYPos >= compPaddleYPos && ballYPos <= compPaddleYPos + PADDLE_HEIGHT)
                || ((ballXPos < playerPaddleXPos + PADDLE_WIDTH) && ballYPos >= playerPaddleYPos && ballYPos <= playerPaddleYPos + PADDLE_HEIGHT)){
            ballYSpeed += 1 * Math.signum(ballYSpeed);
            ballXSpeed += 1 * Math.signum(ballXSpeed);
            ballXSpeed *= -1;
            ballYSpeed *= -1;
        }

        //draw score
        gc.fillText(playerScore + "\t\t\t\t\t\t\t" + compScore, GAME_WIDTH/2, 100);

        //draw player one and CPU
        gc.fillRect(compPaddleXPos, compPaddleYPos, PADDLE_WIDTH, PADDLE_HEIGHT);
        gc.fillRect(playerPaddleXPos, playerPaddleYPos, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
