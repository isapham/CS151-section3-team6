package application.snake;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * This is the class that creates the Snake game page and contains its methods and attributes
 *
 */
public class SnakeController 
{
	//@FXML private VBox root;
	
	static int speed = 10;
    public static int snakeScore = -1;
    public static int width = 24;
    public static int height = 22;
    static int cornerSize = 25;
    static int foodX = 0;
    static int foodY = 0;
    public static boolean gameOver = false;
    static Random random = new Random();
    public static Move direction = Move.left;
    public static List<Corner> snake = new ArrayList<>();
	private Timeline timeLine;
    
	@FXML private Canvas canvasSnake;
	
	/**
	 * Enums for moving the snake
	 */
    public enum Move	
    {
        left,right,up,down
    }
    
    /**
	 * This method is used to create the game box surrounding the snake
	 */
    public static class Corner
    {
        int x;
        int y;

        public Corner(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
     
    /**
	 * This method is used to create a new food for the snake 
	 */
    public static void newFood() //function for spawning food at random coordinates
    {   
        while(true)
        {
            foodX = random.nextInt(width);
            foodY = random.nextInt(height);

            for(Corner canvas : snake)
            {
                if(canvas.x == foodX && canvas.y == foodY)
                {
                	continue;
                }
            }
            snakeScore++;
            break;
        }
    }
    
    /**
	 * This method starts the graphics of the snake game and place the starting snake
	 */
    public void initialize() //start the graphics context and the game
    {
    	GraphicsContext gc = canvasSnake.getGraphicsContext2D();
    	start(gc);
		snake.add(new Corner(width / 2, height / 2));            //body of the snake at start, 1 block, copy and paste this line for more body parts
    }
  
    /**
	 * This method starts the game and creates a timeline to animated the snake
	 * @param gc
	 */
    public void start(GraphicsContext gc)  //set the score and spawn new food and start animations
    {
    	snakeScore=-1;
        newFood();
        timeLine = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.10),event -> tick(gc));
        timeLine.getKeyFrames().addAll(keyFrame);
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }
    
    
    /**
	 * This method creates all the animation in the snake game
	 * Animation for game over when the snake touches any of the 4 surrounding walls or itself
	 * Animate a new food spawning when the snake head touches a food
	 * Sets all the colors in the game
	 * @param gc
	 */
    protected void tick(GraphicsContext gc)
    {
    	if(gameOver)                               //game over screen
        {
            gc.setFill(Color.RED);                 //font color of game over text
            gc.setFont(new Font("", 50));          //font size
            gc.fillText("GAME OVER", 100, 250);    //Display text at x, y position   
	
            gc.setFill(Color.WHITE);                //font color of game over text
            gc.setFont(new Font("", 20));          //font size
            gc.fillText("Press the R key to retry", 125, 275);    //Display text at x, y position                       
            return;
        }

        for(int i = snake.size() - 1; i >= 1; i--)
        {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        switch (direction)                  //switch case if snake touches the borders of the game while moving
        {
            case down:
                snake.get(0).y++;
                if(snake.get(0).y > height-1) //if snake goes past bottom border, then game is over
                {
                    gameOver = true;
                }
                break;

            case up:
                snake.get(0).y--;
                if(snake.get(0).y < 0)      //if snake goes past top border, then game is over
                {
                    gameOver = true;
                }
                break;

            case left:
                snake.get(0).x--;
                if(snake.get(0).x < 0)     //if snake goes past left border, then game is over
                {
                    gameOver = true;
                }
                break;

            case right:
                snake.get(0).x++;
                if(snake.get(0).x > width-1)  //if snake goes past right border, then game is over
                {
                    gameOver = true;
                }
                break;
        }

        if(foodX == snake.get(0).x && foodY == snake.get(0).y)	//snake eats food
        {  
            snake.add(new Corner(-1, -1));                      //add a body part to end of snake
            newFood();                                          //spawn a new food randomly on map
        }

        for(int i = 1; i < snake.size(); i++)		//if snake makes contact with its own body
        {                   
            if(snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y)
            {
                gameOver = true;
            }
        }

        gc.setFill(Color.BLACK);                                                       //game background color
        gc.fillRect(0, 0, width * cornerSize, height * cornerSize);                    //game display resolution
        
        gc.setFill(Color.WHITE);                                                       //game score text color
        gc.setFont(new Font("", 20));                                                  //game score font size
        gc.fillText("Score: " + snakeScore, 10, 20);                                 	//game score at position
        
        //fruitRoundShape = true; //true=round, false=square
        Color foodColor = Color.RED;                                                                //food color
        gc.setFill(foodColor);                                                         //fill food with food color
        if (GamesController.fruitRoundShape = true) {
        	gc.fillOval(foodX * cornerSize, foodY * cornerSize, cornerSize, cornerSize);   //shape of food
        } else if (GamesController.fruitRoundShape = false) {
        	gc.fillRect(foodX * cornerSize, foodY * cornerSize, cornerSize, cornerSize);   //shape of food
        }

        for(Corner canvas : snake){
            gc.setFill(Color.LIGHTGREEN);                                              //color of snake
            gc.fillRect(canvas.x * cornerSize, canvas.y * cornerSize, cornerSize - 1, cornerSize - 1); //shape of snake
        }
    }
    
    /**
	 * This method switches window to main menu when user clicks on the switchToGameMenu button 
	 * Stops the animation for snake when user leaves
	 * @param event
	 * @throws IOException
	 */
    @FXML
    public void switchToGameMenu(ActionEvent event) throws IOException 
    {
    	timeLine.stop();
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
	
    /**
	 * This method saves the points accumulated in the snake game to the database to interact with the item shop when user clicks on the switchToSavePoint button 
	 * Sends user to games menu and stops the animation for snake when user leaves
	 * @param event
	 * @throws IOException
	 */
    @FXML
	public void switchToSavePoint(ActionEvent event) throws IOException 
	{
    	timeLine.stop();
    	Stage currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currStage.close();
    	FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/SnakeDB.fxml";
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
