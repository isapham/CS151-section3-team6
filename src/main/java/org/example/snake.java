package application;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application
{
    static int speed = 5;
    static int width = 20;
    static int height = 20;
    static int cornerSize = 25;
    static int foodX = 0;
    static int foodY = 0;
    static boolean gameOver = false;
    static Random random = new Random();
    static Move direction = Move.left;
    static List<Corner> snake = new ArrayList<>();

    public enum Move
    {
        left,right,up,down
    }

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

    public static void newFood()    //function for spawning food at random coordinates
    {
        start: while(true)
        {
            foodX = random.nextInt(width);
            foodY = random.nextInt(height);

            for(Corner canvas : snake)
            {
                if(canvas.x == foodX && canvas.y == foodY)
                {
                    continue start;
                }
            }
            speed++;
            break;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        newFood();

        VBox root = new VBox();
        Canvas canvas = new Canvas(width * cornerSize, height * cornerSize);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        new AnimationTimer()                //ticks of the game, less ticks = more frames = faster snake animation
        {
            long lastTick = 0;
            @Override
            public void handle(long now)
            {
                if(lastTick == 0)
                {
                    lastTick = now;
                    tick(graphicsContext);
                }
                if(now - lastTick > 900000000/speed)
                {
                    lastTick = now;
                    tick(graphicsContext);
                }
            }
        }.start();

        Scene scene = new Scene(root, width * cornerSize, height * cornerSize);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, key ->       //controls for the snake on keyboard
                {
                    if(key.getCode() == KeyCode.UP)
                    {
                        direction = Move.up;
                    }
                    if(key.getCode() == KeyCode.DOWN)
                    {
                        direction = Move.down;
                    }
                    if(key.getCode() == KeyCode.LEFT)
                    {
                        direction = Move.left;
                    }
                    if(key.getCode() == KeyCode.RIGHT)
                    {
                        direction = Move.right;
                    }

                    /* escape key for pause menu
                    if(key.getCode() == KeyCode.ESCAPE)
                    {
                    }
                    */
                }
                );

        snake.add(new Corner(width / 2, height / 2));            //body of the snake at start, 1 block, copy and paste this line for more body parts

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake");
        primaryStage.show();
    }

    public static void tick(GraphicsContext graphicsContext)
    {
        if(gameOver)                                            //game over screen
        {
            graphicsContext.setFill(Color.RED);                 //font color of game over text
            graphicsContext.setFont(new Font("", 50));          //font size
            graphicsContext.fillText("GAME OVER", 100, 250);    //Display text at x, y position
            return;
        }

        for(int i = snake.size() - 1; i >= 1; i--)
        {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        switch (direction)                  //switch case if snake touches the borders of the game
        {
            case down:
                snake.get(0).y++;
                if(snake.get(0).y < 0)      //if snake goes past bottom border, then game is over
                {
                    gameOver = true;
                }
                break;

            case up:
                snake.get(0).y--;
                if(snake.get(0).y > height) //if snake goes past top border, then game is over
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
                if(snake.get(0).x > width)  //if snake goes past right border, then game is over
                {
                    gameOver = true;
                }
                break;
        }

        if(foodX == snake.get(0).x && foodY == snake.get(0).y)  //snake eats food
        {
            snake.add(new Corner(-1, -1));                      //add a body part to end of snake
            newFood();                                          //spawn a new food randomly on map
        }

        for(int i = 1; i < snake.size(); i++)                   //if snake makes contact with its own body
        {
            if(snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y)
            {
                gameOver = true;
            }
        }

        graphicsContext.setFill(Color.BLACK);                                                       //game background color
        graphicsContext.fillRect(0, 0, width * cornerSize, height * cornerSize);                    //game display resolution
        graphicsContext.setFill(Color.WHITE);                                                       //game score text color
        graphicsContext.setFont(new Font("", 30));                                                  //game score font size
        graphicsContext.fillText("Score: ", +(speed - 6), 30, 50);                                  //game score at position
        Color foodColor = Color.RED;                                                                //food color
        graphicsContext.setFill(foodColor);                                                         //fill food with food color
        graphicsContext.fillOval(foodX * cornerSize, foodY * cornerSize, cornerSize, cornerSize);   //shape of food

        for(Corner canvas : snake)
        {
            graphicsContext.setFill(Color.LIGHTGREEN);                                              //color of snake
            graphicsContext.fillRect(canvas.x * cornerSize, canvas.y * cornerSize, cornerSize - 1, cornerSize - 1); //shape of snake
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
