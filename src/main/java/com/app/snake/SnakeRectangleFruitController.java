package main.java.com.app.snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SnakeRectangleFruitController extends SnakeController{
	@Override
	public void tick(GraphicsContext gc)
    {
        if(gameOver)                                            //game over screen
        {
            gc.setFill(Color.RED);                 //font color of game over text
            gc.setFont(new Font("", 50));          //font size
            gc.fillText("GAME OVER", 100, 250);    //Display text at x, y position
            
            //assume user will exit game and reenter the game
//            gc.setFill(Color.WHITE);                 //font color of game over text
//            gc.setFont(new Font("", 20));          //font size
//            gc.fillText("Press SPACEBAR to retry", 125, 275);    //Display text at x, y position                       
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

        if(foodX == snake.get(0).x && foodY == snake.get(0).y){  //snake eats food
            snake.add(new Corner(-1, -1));                      //add a body part to end of snake
            newFood();                                          //spawn a new food randomly on map
        }

        for(int i = 1; i < snake.size(); i++){                   //if snake makes contact with its own body
            if(snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y){
                gameOver = true;
            }
        }

        gc.setFill(Color.BLACK);                                                       //game background color
        gc.fillRect(0, 0, width * cornerSize, height * cornerSize);                    //game display resolution
        
        gc.setFill(Color.WHITE);                                                       //game score text color
        gc.setFont(new Font("", 20));                                                  //game score font size
        gc.fillText("Score: " + snakeScore, 10, 20);                                 	//game score at position
        
        Color foodColor = Color.RED;                                                                //food color
        gc.setFill(foodColor);                                                         //fill food with food color
        gc.fillRect(foodX * cornerSize, foodY * cornerSize, cornerSize, cornerSize);   //shape of food


        for(Corner canvas : snake){
            gc.setFill(Color.LIGHTGREEN);                                              //color of snake
            gc.fillRect(canvas.x * cornerSize, canvas.y * cornerSize, cornerSize - 1, cornerSize - 1); //shape of snake
        }
    }
}
