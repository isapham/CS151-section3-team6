package main.java.com.app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import main.java.com.app.blockBreaker.BlockBreakerController;
import main.java.com.app.blockBreaker.BlockBreakerMainController;
import main.java.com.app.pong.PongController;
import main.java.com.app.snake.SnakeController;
import main.java.com.app.snake.SnakeController.Move;
import main.java.com.app.tetris.TetrisController;
import main.java.com.app.tetris.gui.GuiController;
import main.java.com.app.tetris.logic.Score;

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
	
    //create scene to begin game
	public void switchToBlockBreaker(ActionEvent event)throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "";
		
		if (ballShape==1) {
			pathToFxml = "src/main/resources/SquareBallBlockBreaker.fxml";
		} else {
			pathToFxml = "src/main/resources/BlockBreaker.fxml";
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
	
	//Button function to switch back to main menu
	public void switchToMainMenu(ActionEvent event) throws IOException{
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
	
	public void switchToPong(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml ="";
		if (circleBall==true) {
			pathToFxml = "src/main/resources/Pong.fxml";
		} else {
			pathToFxml = "src/main/resources/PongSquare.fxml";
		}
		
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
	
	@FXML
	public void switchToTetris(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/Tetris.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent tetris_page = fxmlLoader.load();
		
        GuiController c = fxmlLoader.getController();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("TetrisJFX");
        Scene scene = new Scene(tetris_page);
        primaryStage.setScene(scene);
        primaryStage.show();
        new TetrisController(c);
	}
	
	//Switch to SnakeGame button
	public static boolean fruitRoundShape = true; //true=round, false=square
	@FXML
	public void switchToSnake(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "";
		
		if (fruitRoundShape==true) {
			pathToFxml = "src/main/resources/SnakeCircleFruit.fxml";
		} else {
			pathToFxml = "src/main/resources/SnakeRectangleFruit.fxml";
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
	//BlockBreakerController.points + PongController.ppoints + SnakeController.snakeScore + Score.score.getValue();

	public static void insertUsernameBlockBreaker() throws SQLException, ClassNotFoundException{
		totalPoints = BlockBreakerMainController.points+totalPoints;
		String sql = "insert into users(BlockBreakerScore, sumPoint, PongScore, TetrisScore, SnakeScore) values('"+BlockBreakerController.points+"','"+totalPoints+"','"+0+"','"+0+"','"+0+"')";
		try {
			ConnectionClass.dbExecuteQuery(sql);
		}
		catch(SQLException e) {
			System.out.println("Exception occur while inserting the data "+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void insertUsernameTetris() throws SQLException, ClassNotFoundException{
		totalPoints = Score.score.getValue()+totalPoints;
		String sql = "insert into users(TetrisScore, sumPoint, PongScore, BlockBreakerScore, SnakeScore) values('"+Score.score.getValue()+"','"+totalPoints+"','"+0+"','"+0+"','"+0+"')";
		try {
			ConnectionClass.dbExecuteQuery(sql);
		}
		catch(SQLException e) {
			System.out.println("Exception occur while inserting the data "+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void insertUsernamePong() throws SQLException, ClassNotFoundException{
		totalPoints = PongController.ppoints+totalPoints;
		String sql = "insert into users(PongScore, sumPoint, TetrisScore, BlockBreakerScore, SnakeScore) values('"+PongController.ppoints+"','"+totalPoints+"','"+0+"','"+0+"','"+0+"')";
		try {
			ConnectionClass.dbExecuteQuery(sql);
		}
		catch(SQLException e) {
			System.out.println("Exception occur while inserting the data "+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void insertUsernameSnake() throws SQLException, ClassNotFoundException{
		totalPoints = SnakeController.snakeScore+totalPoints;
		String sql = "insert into users(SnakeScore, sumPoint, TetrisScore, BlockBreakerScore, PongScore) values('"+SnakeController.snakeScore+"','"+totalPoints+"','"+0+"','"+0+"','"+0+"')";
		try {
			ConnectionClass.dbExecuteQuery(sql);
		}
		catch(SQLException e) {
			System.out.println("Exception occur while inserting the data "+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void insertTotalScore() throws SQLException, ClassNotFoundException{
		String sql = "insert into users(BlockBreakerScore, sumPoint, PongScore, TetrisScore, SnakeScore) values('"+0+"','"+totalPoints+"','"+0+"','"+0+"','"+0+"')";
		try {
			ConnectionClass.dbExecuteQuery(sql);
		}
		catch(SQLException e) {
			System.out.println("Exception occur while inserting the data "+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	public static ObservableList<HiScore> getPongRecords() throws ClassNotFoundException, SQLException{
		String sql = "SELECT PongScore FROM users"+" ORDER BY PongScore DESC";
		try {
			ResultSet rsSet = ConnectionClass.dbExecute(sql);
			ObservableList<HiScore> list = getPongScoreObjects(rsSet);
			return list;
		}
		catch (SQLException e) {
			System.out.println("Error occured while fetching the records from DB"+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	private static ObservableList<HiScore> getPongScoreObjects(ResultSet rsSet)throws ClassNotFoundException, SQLException {
		try{
			ObservableList<HiScore> list = FXCollections.observableArrayList();
			
			while(rsSet.next()) {
				HiScore hiScore = new HiScore();
				hiScore.setPoScore(rsSet.getInt("PongScore"));
				list.add(hiScore);
			}
			return list;
		}catch (SQLException e){
			System.out.println("Error ocurred while fetching the data from DC"+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	public static ObservableList<HiScore> getBlockBreakerRecords() throws ClassNotFoundException, SQLException{
		String sql = "SELECT BlockBreakerScore FROM users"+" ORDER BY BlockBreakerScore DESC";
		try {
			ResultSet rsSet = ConnectionClass.dbExecute(sql);
			ObservableList<HiScore> list = getBlockBreakerScoreObjects(rsSet);
			return list;
		}catch (SQLException e) {
			System.out.println("Error occured while fetching the records from DB"+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	private static ObservableList<HiScore> getBlockBreakerScoreObjects(ResultSet rsSet)throws ClassNotFoundException, SQLException {
		try{
			ObservableList<HiScore> list = FXCollections.observableArrayList();
			
			while(rsSet.next()) {
				HiScore hiScore = new HiScore();
				hiScore.setBBScore(rsSet.getInt("BlockBreakerScore"));
				list.add(hiScore);
			}
			return list;
		}catch (SQLException e){
			System.out.println("Error ocurred while fetching the data from DC"+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	public static ObservableList<HiScore> getTetrisRecords() throws ClassNotFoundException, SQLException{
		String sql = "SELECT TetrisScore FROM users"+" ORDER BY TetrisScore DESC";
		try {
			ResultSet rsSet = ConnectionClass.dbExecute(sql);
			ObservableList<HiScore> list = getTetrisScoreObjects(rsSet);
			return list;
		}catch (SQLException e) {
			System.out.println("Error occured while fetching the records from DB"+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	private static ObservableList<HiScore> getTetrisScoreObjects(ResultSet rsSet)throws ClassNotFoundException, SQLException {
		try{
			ObservableList<HiScore> list = FXCollections.observableArrayList();
			
			while(rsSet.next()) {
				HiScore hiScore = new HiScore();
				hiScore.setTetScore(rsSet.getInt("TetrisScore"));
				list.add(hiScore);
			}
			return list;
		}catch (SQLException e){
			System.out.println("Error ocurred while fetching the data from DC"+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	public static ObservableList<HiScore> getSnakeRecords() throws ClassNotFoundException, SQLException{
		String sql = "SELECT SnakeScore FROM users"+" ORDER BY SnakeScore DESC";
		try {
			ResultSet rsSet = ConnectionClass.dbExecute(sql);
			ObservableList<HiScore> list = getSnakeScoreObjects(rsSet);
			return list;
		}catch (SQLException e) {
			System.out.println("Error occured while fetching the records from DB"+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	private static ObservableList<HiScore> getSnakeScoreObjects(ResultSet rsSet)throws ClassNotFoundException, SQLException {
		try{
			ObservableList<HiScore> list = FXCollections.observableArrayList();
			
			while(rsSet.next()) {
				HiScore hiScore = new HiScore();
				hiScore.setSnaScore(rsSet.getInt("SnakeScore"));
				list.add(hiScore);
			}
			return list;
		}catch (SQLException e){
			System.out.println("Error ocurred while fetching the data from DC"+e);
			e.printStackTrace();
			throw e;
		}
	}
//	public static ObservableList<HiScore> getAllRecords() throws ClassNotFoundException, SQLException{
//		String sql = "select * from users";
//		try {
//			ResultSet rsSet = ConnectionClass.dbExecute(sql);
//			ObservableList<HiScore> list = getScoreObjects(rsSet);
//			return list;
//		}
//		catch (SQLException e) {
//			System.out.println("Error occured while fetching the records from DB"+e);
//			e.printStackTrace();
//			throw e;
//		}
//	}

//	private static ObservableList<HiScore> getScoreObjects(ResultSet rsSet)throws ClassNotFoundException, SQLException {
//		try{
//			ObservableList<HiScore> list = FXCollections.observableArrayList();
//			
//			while(rsSet.next()) {
//				HiScore hiScore = new HiScore();
//				hiScore.setBBScore(rsSet.getInt("BlockBreakerScore"));
//				hiScore.setTetScore(rsSet.getInt("TetrisScore"));
//				hiScore.setPoScore(rsSet.getInt("PongScore"));
//				hiScore.setSnaScore(rsSet.getInt("SnakeScore"));
//				list.add(hiScore);
//			}
//			return list;
//		}
//	
//		catch (SQLException e){
//			System.out.println("Error ocurred while fetching the data from DC"+e);
//			e.printStackTrace();
//			throw e;
//		}
//	}
	
}
