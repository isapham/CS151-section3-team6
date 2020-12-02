package main.java.com.app.tetris.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.com.app.tetris.logic.DownData;
import main.java.com.app.tetris.logic.Score;
import main.java.com.app.tetris.logic.ViewData;
import main.java.com.app.tetris.logic.events.EventSource;
import main.java.com.app.tetris.logic.events.EventType;
import main.java.com.app.tetris.logic.events.InputEventListener;
import main.java.com.app.tetris.logic.events.MoveEvent;

public class GuiController implements Initializable {
	private static final int BRICK_SIZE = 20;
	public static int tetrisColor = 0; //black and white =0; colorful =1; colorblind color = 2
    @FXML
    private GridPane gamePanel;

    @FXML
    private Text scoreValue;

    @FXML
    private Group groupNotification;

    @FXML
    private GridPane nextBrick;

    @FXML
    private GridPane brickPanel;

    @FXML
    private ToggleButton pauseButton;

    @FXML
    private GameOverPanel gameOverPanel;

    private Rectangle[][] displayMatrix;

    private InputEventListener eventListener;

    private Rectangle[][] rectangles;

    private Timeline timeLine;

    private final BooleanProperty isPause = new SimpleBooleanProperty();

    private final BooleanProperty isGameOver = new SimpleBooleanProperty();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	Score.reset();
        gamePanel.setFocusTraversable(true);
        gamePanel.requestFocus();
        gamePanel.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (isPause.getValue() == Boolean.FALSE && isGameOver.getValue() == Boolean.FALSE) {
                    if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.A) {
                        refreshBrick(eventListener.onLeftEvent(new MoveEvent(EventType.LEFT, EventSource.USER)));
                        keyEvent.consume();
                    }
                    if (keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.D) {
                        refreshBrick(eventListener.onRightEvent(new MoveEvent(EventType.RIGHT, EventSource.USER)));
                        keyEvent.consume();
                    }
                    if (keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.W) {
                        refreshBrick(eventListener.onRotateEvent(new MoveEvent(EventType.ROTATE, EventSource.USER)));
                        keyEvent.consume();
                    }
                    if (keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.S) {
                        moveDown(new MoveEvent(EventType.DOWN, EventSource.USER));
                        keyEvent.consume();
                    }
                }
                if (keyEvent.getCode() == KeyCode.N) {
                    newGame(null);
                }
                if (keyEvent.getCode() == KeyCode.P) {
                    pauseButton.selectedProperty().setValue(!pauseButton.selectedProperty().getValue());
                }

            }
        });
        gameOverPanel.setVisible(false);
        pauseButton.selectedProperty().bindBidirectional(isPause);
        pauseButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    timeLine.pause();
                    pauseButton.setText("Resume");
                } else {
                    timeLine.play();
                    pauseButton.setText("Pause");
                }
            }
        });
        final Reflection reflection = new Reflection();
        reflection.setFraction(0.8);
        reflection.setTopOpacity(0.9);
        reflection.setTopOffset(-12);
        scoreValue.setEffect(reflection);
    }

    public void initGameView(int[][] boardMatrix, ViewData brick) {
        displayMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];
        for (int i = 2; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                displayMatrix[i][j] = rectangle;
                gamePanel.add(rectangle, j, i - 2);
            }
        }
        
        rectangles = new Rectangle[brick.getBrickData().length][brick.getBrickData()[0].length];
        for (int i = 0; i < brick.getBrickData().length; i++) {
            for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(getFillColor(brick.getBrickData()[i][j]));
                rectangles[i][j] = rectangle;
                brickPanel.add(rectangle, j, i);
            }
        }
    
        brickPanel.setLayoutX(gamePanel.getLayoutX() + brick.getxPosition() * brickPanel.getVgap() + brick.getxPosition() * BRICK_SIZE);
        brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() + brick.getyPosition() * brickPanel.getHgap() + brick.getyPosition() * BRICK_SIZE);

        generatePreviewPanel(brick.getNextBrickData());


        timeLine = new Timeline(new KeyFrame(
                Duration.millis(400),
                ae -> moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
        ));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    private Paint getFillColor(int i) {
    	Paint returnPaint = null; 
    	Color myColor1 = new Color(230.0/255.0, 159.0/255.0, 0.0/255.0,255/255.0);
    	Color myColor2 = new Color(86.0/255.0, 180.0/255.0, 233.0/255.0, 255/255.0);
    	Color myColor3 = new Color(0.0/255.0, 158.0/255.0, 115.0/255.0, 255/255.0);
    	Color myColor4 = new Color(240.0/255.0, 228.0/255.0, 66.0/255.0, 255/255.0);
    	Color myColor5 = new Color(0.0/255.0, 114.0/255.0, 178.0/255.0, 255/255.0);
    	Color myColor6 = new Color(213.0/255.0,94.0/255.0,0.0/255.0,255/255.0);
    	Color myColor7 = new Color(204.0/255.0,121.0/255.0,167.0/255.0,255/255.0);
    	
    	if (tetrisColor == 0) { //0 means black and white only
    		switch (i) {
            case 0:
                returnPaint = Color.TRANSPARENT;
                break;
            case 1:
                returnPaint = Color.WHITE;
                break;
            case 2:
                returnPaint = Color.WHITE;
                break;
            case 3:
                returnPaint = Color.WHITE;
                break;
            case 4:
                returnPaint = Color.WHITE;
                break;
            case 5:
                returnPaint = Color.WHITE;
                break;
            case 6:
                returnPaint = Color.WHITE;
                break;
            case 7:
                returnPaint = Color.WHITE;
                break;
            default:
                returnPaint = Color.WHITE;
                break;
    		}
    		//return returnPaint1;
    	} else if (tetrisColor == 1) { // 1 means colorful palette
    		switch (i) {
            case 0:
                returnPaint = Color.TRANSPARENT;
                break;
            case 1:
                returnPaint = Color.AQUA;
                break;
            case 2:
                returnPaint = Color.BLUEVIOLET;
                break;
            case 3:
                returnPaint = Color.DARKGREEN;
                break;
            case 4:
                returnPaint = Color.YELLOW;
                break;
            case 5:
                returnPaint = Color.RED;
                break;
            case 6:
                returnPaint = Color.BEIGE;
                break;
            case 7:
                returnPaint = Color.BURLYWOOD;
                break;
            default:
                returnPaint = Color.WHITE;
                break;
    		}
    		//return returnPaint2;
    	} else if (tetrisColor == 2) {
    		switch (i) {
            case 0:
                returnPaint = Color.TRANSPARENT;
                break;
            case 1:
                returnPaint = myColor1;
                break;
            case 2:
                returnPaint = myColor2;
                break;
            case 3:
                returnPaint = myColor3;
                break;
            case 4:
                returnPaint = myColor4;
                break;
            case 5:
                returnPaint = myColor5;
                break;
            case 6:
                returnPaint = myColor6;
                break;
            case 7:
                returnPaint = myColor7;
                break;
            default:
                returnPaint = Color.WHITE;
                break;
    		}
    		//return returnPaint3;
    	}
    	return returnPaint;
    }
    
    private void generatePreviewPanel(int[][] nextBrickData) {
        nextBrick.getChildren().clear();
        for (int i = 0; i < nextBrickData.length; i++) {
            for (int j = 0; j < nextBrickData[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                setRectangleData(nextBrickData[i][j], rectangle);
                if (nextBrickData[i][j] != 0) {
                    nextBrick.add(rectangle, j, i);
                }
            }
        }
    }

    private void refreshBrick(ViewData brick) {
        if (isPause.getValue() == Boolean.FALSE) {
            brickPanel.setLayoutX(gamePanel.getLayoutX() + brick.getxPosition() * brickPanel.getVgap() + brick.getxPosition() * BRICK_SIZE);
            brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() + brick.getyPosition() * brickPanel.getHgap() + brick.getyPosition() * BRICK_SIZE);
            for (int i = 0; i < brick.getBrickData().length; i++) {
                for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                    setRectangleData(brick.getBrickData()[i][j], rectangles[i][j]);
                }
            }
            generatePreviewPanel(brick.getNextBrickData());
        }
    }

    public void refreshGameBackground(int[][] board) {
        for (int i = 2; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                setRectangleData(board[i][j], displayMatrix[i][j]);
            }
        }
    }

    private void setRectangleData(int color, Rectangle rectangle) {
        rectangle.setFill(getFillColor(color));
        rectangle.setArcHeight(9);
        rectangle.setArcWidth(9);
    }

    private void moveDown(MoveEvent event) {
        if (isPause.getValue() == Boolean.FALSE) {
            DownData downData = eventListener.onDownEvent(event);
            if (downData.getClearRow() != null && downData.getClearRow().getLinesRemoved() > 0) {
                NotificationPanel notificationPanel = new NotificationPanel("+" + downData.getClearRow().getScoreBonus());
                groupNotification.getChildren().add(notificationPanel);
                notificationPanel.showScore(groupNotification.getChildren());
            }
            refreshBrick(downData.getViewData());
        }
        gamePanel.requestFocus();
    }

    public void setEventListener(InputEventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void bindScore(IntegerProperty integerProperty) {
        scoreValue.textProperty().bind(integerProperty.asString());
    }

    public void gameOver() {
        timeLine.stop();
        gameOverPanel.setVisible(true);
        isGameOver.setValue(Boolean.TRUE);

    }

    public void newGame(ActionEvent actionEvent) {
        timeLine.stop();
        gameOverPanel.setVisible(false);
        eventListener.createNewGame();
        gamePanel.requestFocus();
        timeLine.play();
        isPause.setValue(Boolean.FALSE);
        isGameOver.setValue(Boolean.FALSE);
    }

    public void pauseGame(ActionEvent actionEvent) {
        gamePanel.requestFocus();
    }
    
    @FXML
	public void switchToGameMenu(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/Games.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent gamesMenu = fxmlLoader.load();
		    
		Scene gamesMenuScene = new Scene (gamesMenu);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(gamesMenuScene);
		window.setTitle("Home Page");
		window.show();
	}
	
    @FXML
	public void switchToSavePoint(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/TetrisDB.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent DBMenu = fxmlLoader.load();
		    
		Scene DBScene = new Scene (DBMenu);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(DBScene);
		window.show();
	}
}
