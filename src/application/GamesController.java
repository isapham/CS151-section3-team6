package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.blockBreaker.BlockBreakerMainController;
import application.pong.PongController;
import application.snake.SnakeController;
import application.snake.SnakeController.Move;
import application.tetris.TetrisController;
import application.tetris.gui.GuiController;
import application.tetris.logic.Score;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * This class implements main functions between all the buttons 
 * from games menu page to each game 
 */
public class GamesController implements Initializable{
	//set image for buttons
	@FXML TextField totalScore;
	@FXML  ImageView pongIcon;
	@FXML  ImageView snakeIcon;
	@FXML  ImageView blockBreakerIcon;
	@FXML  ImageView tetrisIcon;
	@FXML  ImageView controllerIcon;
	@FXML  ImageView pointIcon;
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		controllerIcon.setImage(new Image("/main/resources/cs151GameConsoleIcon.png"));
		pongIcon.setImage(new Image("/main/resources/cs151GameGalPongIcon.png"));
		snakeIcon.setImage(new Image("/main/resources/cs151GameGalSnakeIcon.png"));
		blockBreakerIcon.setImage(new Image("/main/resources/cs151GameGalBlockBreakerIcon.png"));
		tetrisIcon.setImage(new Image("/main/resources/cs151GameGalTetrisIcon.png"));
		pointIcon.setImage(new Image("/main/resources/cs151GameGalPointsIcon1.png"));
		totalScore.setStyle("-fx-text-fill: white; -fx-background-color: black");
		totalScore.setText(Integer.toString(totalPoints));
	}
	
	//instances for block breaker
	public static boolean goLeft = false;
	public static boolean goRight = false;
	public static boolean start = false;
	public static int ballShape = 0; //round ball = 0, square ball = 1
	public static int cbEnabled = 0; // color blind enabled = 1, disabled = 0 (normal mode);
	
	/**
	 * This method is used to create scene of block breaker game
	 * and start the game
	 * 
	 * @param event User clicks button
	 * @throws IOException
	 */
	@FXML
	public void switchToBlockBreaker(ActionEvent event)throws IOException {
		Stage currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currStage.close();
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "";
		
		if(cbEnabled == 1) {
			pathToFxml = "src/main/resources/ColorBlindBlockBreaker.fxml";
		}else {
			if (ballShape==1) {
				pathToFxml = "src/main/resources/SquareBallBlockBreaker.fxml";
			} else {
				pathToFxml = "src/main/resources/BlockBreaker.fxml";
			}	
		}
		
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent block_breaker_page = fxmlLoader.load();
		
		Scene block_breaker_scene = new Scene(block_breaker_page);
		
		block_breaker_scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case LEFT: goLeft = true;
					break;
				case RIGHT: goRight = true;
					break;
				case SPACE: start = true;
					break;
				default:
					break;
				}	
			}	
		});
		
		block_breaker_scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case LEFT: goLeft = false;
					break;
				case RIGHT: goRight = false;
					break;
				default:
					break;
				}
			}
		});

		Stage scene3 = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene3.setTitle("Block Breaker"); 
		scene3.setScene(block_breaker_scene);
		scene3.show();
	}
	
	/**
	 * This method is a button function to switch back to home page
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void switchToMainMenu(ActionEvent event) throws IOException{
		Stage currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currStage.close();
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/MainMenu.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent main_menu_page = fxmlLoader.load();    
		
		Scene mainMenuScene = new Scene (main_menu_page);
		Stage windowView = (Stage) ((Node)event.getSource()).getScene().getWindow();
		windowView.setScene(mainMenuScene);
		windowView.show();
    }
	
	//instances for pong
	//paddle is what the user uses to hit the ball with in the game
	public static int PADDLE_HEIGHT = 100;
	public static int PADDLE_WIDTH = 15;
	//game canvas
	public static int PONG_HEIGHT = 552;
	public static int PONG_WIDTH = 600;
	
    //indicator if game started or not
	public static boolean gameStarted=false;
	
    //player's paddle initial location (note that paddle movement is only up and down):
	public static double playerPaddleYPos = PONG_HEIGHT/2;
	public static double compPaddleYPos = PONG_HEIGHT/2;
	public static boolean circleBall = true; // 0 = round
	
	/**
	 * This method is a button function to switch to Pong game and play
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void switchToPong(ActionEvent event) throws IOException{
		Stage currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currStage.close();
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml ="src/main/resources/Pong.fxml";
		
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent pong_page = fxmlLoader.load();

		Scene pong_scene = new Scene(pong_page);
		Stage scene4 = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		scene4.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            switch (key.getCode()) {
                case ENTER:
                    gameStarted = true;
                    break;
                case UP:
                    if (playerPaddleYPos > 0)
                        playerPaddleYPos -= 20;
                    break;
                case DOWN:
                    if (playerPaddleYPos < PONG_HEIGHT - PADDLE_HEIGHT)
                        playerPaddleYPos += 20;
                    break;
                default:
                	break;
            }
        });
		scene4.setTitle("Pong"); 
		scene4.setScene(pong_scene);
		scene4.show();
	}
	
	/**
	 * This method is a button funciton to switch to Tetris game and play
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void switchToTetris(ActionEvent event) throws IOException{
		Stage currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currStage.close();
		
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/Tetris.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent tetris_page = fxmlLoader.load();
		
        GuiController c = fxmlLoader.getController();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Tetris Game");
        Scene scene = new Scene(tetris_page);
        primaryStage.setScene(scene);
        primaryStage.show();
        new TetrisController(c);
	}
	
	public static boolean fruitRoundShape = true; //true=round, false=square
	/**
	 * This is the button function to switch to Snake game and play
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void switchToSnake(ActionEvent event) throws IOException{
		Stage currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currStage.close();
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "";
		if(cbEnabled == 1) {
			pathToFxml = "src/main/resources/ColorBlindSnake.fxml";
			
		}else {
			pathToFxml = "src/main/resources/Snake.fxml";
		}
		
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent snake_page = fxmlLoader.load();
    	
    	Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(snake_page,844,750);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key ->      					 //controls for the snake on keyboard (snake cannot reverse direction)
        {
            if(key.getCode() == KeyCode.UP && SnakeController.direction != Move.down) 	//if up key is pressed AND the snake is not moving down
            {
            	SnakeController.direction = Move.up;									//move up	
            }
            if(key.getCode() == KeyCode.DOWN && SnakeController.direction != Move.up)	//if down key is pressed AND the snake is not moving up
            {
            	SnakeController.direction = Move.down;									//move down
            }
            if(key.getCode() == KeyCode.LEFT && SnakeController.direction != Move.right)//if left key is pressed AND the snake is not moving right
            {
            	SnakeController.direction = Move.left;									//move left
            }                 
            if(key.getCode() == KeyCode.RIGHT && SnakeController.direction != Move.left)//if right key is pressed AND the snake is not moving left
            {
            	SnakeController.direction = Move.right;									//move right
            }
           
            if(key.getCode() == KeyCode.R)
            {
            	SnakeController.gameOver = false;										//reset game variables
            	SnakeController.direction = Move.left;				
            	SnakeController.snakeScore = -1;
            	SnakeController.newFood();
            	SnakeController.snake.clear();
            	SnakeController.snake.add(new SnakeController.Corner(SnakeController.width / 2, SnakeController.height / 2));                    	
            }
        }
        );

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game");
        primaryStage.show();
	}
	
	
	public static int totalPoints = 0;

	 /**
	  * This method is for retrieving Pong points from database
	  * @return ObservableList<HiScore> This is the list of user's Pong score 
	  */
	public static ObservableList<HiScore> getPongRecords() {
		ObservableList<HiScore> list = getPongScoreObjects(PongController.pongScoreList);
		return list;
	}
	
	/**
	 * This method is for fetching Pong points from database
	 * @param arrayList
	 * @return
	 */
	private static ObservableList<HiScore> getPongScoreObjects(ArrayList<Integer> arrayList) {
		ObservableList<HiScore> list = FXCollections.observableArrayList();
		for (int i =0; i <arrayList.size(); i++) {
			HiScore hiScore = new HiScore();
			hiScore.setPoScore(arrayList.get(i));
			list.add(hiScore);
		}
		return list;
	}
	
	/**
	 * This method is for retrieving Block Breaker points from database
	 * @return
	 */
	public static ObservableList<HiScore> getBlockBreakerRecords() {
		
		ObservableList<HiScore> list = getBlockBreakerScoreObjects(BlockBreakerMainController.bbScoreList);
		return list;
	}
	
	private static ObservableList<HiScore> getBlockBreakerScoreObjects(ArrayList<Integer> arrayList) {
		ObservableList<HiScore> list = FXCollections.observableArrayList();
		for (int i =0; i <arrayList.size(); i++) {
			HiScore hiScore = new HiScore();
			hiScore.setBBScore(arrayList.get(i));
			list.add(hiScore);
		}
		return list;
	}
	
	 /**
	  * This method is for retrieving Pong points from database
	  * @return ObservableList<HiScore> This is the list of user's Pong score 
	  */
	public static ObservableList<HiScore> getTetrisRecords() {
		ObservableList<HiScore> list = getTetrisScoreObjects(Score.tetrisScoreList);
		return list;
	}
	
	/**
	 * This method is for fetching Pong points from database
	 * @param arrayList
	 * @return
	 */
	private static ObservableList<HiScore> getTetrisScoreObjects(ArrayList<Integer> arrayList) {
		ObservableList<HiScore> list = FXCollections.observableArrayList();
		for (int i =0; i <arrayList.size(); i++) {
			HiScore hiScore = new HiScore();
			hiScore.setTetScore(arrayList.get(i));
			list.add(hiScore);
		}
		return list;
	}
	
	/**
	  * This method is for retrieving Pong points from database
	  * @return ObservableList<HiScore> This is the list of user's Pong score 
	  */
	public static ObservableList<HiScore> getSnakeRecords() {
		ObservableList<HiScore> list = getSnakeScoreObjects(SnakeController.snakeScoreList);
		return list;
	}
	
	/**
	 * This method is for fetching Pong points from database
	 * @param arrayList
	 * @return
	 */
	private static ObservableList<HiScore> getSnakeScoreObjects(ArrayList<Integer> arrayList) {
		ObservableList<HiScore> list = FXCollections.observableArrayList();
		for (int i =0; i <arrayList.size(); i++) {
			HiScore hiScore = new HiScore();
			hiScore.setSnaScore(arrayList.get(i));
			list.add(hiScore);
		}
		return list;
	}
}
