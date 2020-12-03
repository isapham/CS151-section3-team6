package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.tetris.gui.GuiController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ItemShopController implements Initializable
{
	@FXML ImageView storeIcon;
	@FXML Button blockBreakerButton, pongButton, snakeButton, tetrisButton, ogBB, ogTetris, ogSnake, ogPong;
	@FXML  ImageView pointIcon;
	@FXML TextField totalScore;
	@FXML TextArea tetrisRule;
	
	//initialize images
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		pointIcon.setImage(new Image("/main/resources/cs151GameGalPointsIcon1.png"));
		totalScore.setStyle("-fx-text-fill: white; -fx-background-color: black");
		totalScore.setText(Integer.toString(GamesController.totalPoints));
		storeIcon.setImage(new Image("/main/resources/cs151GameShopIcon.png"));
		if (GamesController.totalPoints >= 10 && GamesController.totalPoints < 20) 
		{
			pongButton.setDisable(false);
			ogBB.setDisable(false);
			ogTetris.setDisable(false);
			ogSnake.setDisable(false);
			ogPong.setDisable(false);
		}
		else if(GamesController.totalPoints >= 20 && GamesController.totalPoints < 30) 
		{
			pongButton.setDisable(false);
			tetrisButton.setDisable(false);
			ogBB.setDisable(false);
			ogTetris.setDisable(false);
			ogSnake.setDisable(false);
			ogPong.setDisable(false);
		}
		else if (GamesController.totalPoints >= 30 && GamesController.totalPoints < 40) 
		{
			blockBreakerButton.setDisable(false);
			tetrisButton.setDisable(false);
			pongButton.setDisable(false);
			ogBB.setDisable(false);
			ogTetris.setDisable(false);
			ogSnake.setDisable(false);
			ogPong.setDisable(false);
		}
		else if (GamesController.totalPoints >= 40) 
		{
			snakeButton.setDisable(false);
			blockBreakerButton.setDisable(false);
			tetrisButton.setDisable(false);
			pongButton.setDisable(false);
			ogBB.setDisable(false);
			ogTetris.setDisable(false);
			ogSnake.setDisable(false);
			ogPong.setDisable(false);
		}
	}
	
	public void error()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Not enough points to purchase");
		alert.setContentText("Score more points to purchase this bonus!");
		alert.showAndWait();
	}
	
	/**
	 * This method is the button function to switch back to home page
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void switchToMainMenu(ActionEvent event) throws IOException
	{
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
		windowView.setTitle("Main Menu Homepage");
		windowView.show();
    }

	/**
	 * This method is for switch back to home page.
	 * However, this is not a button function.
	 * @throws IOException
	 */
	public void switchToMainMenu() throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/MainMenu.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent main_menu_page = fxmlLoader.load();    
		
		Scene mainMenuScene = new Scene (main_menu_page);
		Stage windowView = new Stage();
		windowView.setScene(mainMenuScene);
		windowView.setTitle("Main Menu Homepage");
		windowView.show();
    }
	
	/**
	 * This method is for points trade-off function for BlockBreaker game
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void changeToSquareBall(ActionEvent event) throws IOException 
	{
		if(GamesController.totalPoints - 30 < 0)
		{
			error();
		}
		else
		{
			GamesController.totalPoints = GamesController.totalPoints - 30;
			GamesController.ballShape = 1; 
		}
		switchToMainMenu();
	}
	
	/**
	 * This method is for points trade-off function for Pong game
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void changeToSquarePongBall(ActionEvent event) throws IOException 
	{
		if(GamesController.totalPoints - 10 < 0)
		{
			error();
		}
		else
		{
			GamesController.totalPoints = GamesController.totalPoints - 10;
			GamesController.circleBall = false; 
		}
		switchToMainMenu();
	}
	
	/**
	 * This method is for points trade-off function for Snake game
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void changeFruitToTriangle(ActionEvent event) throws IOException 
	{
		if(GamesController.totalPoints - 40 < 0)
		{
			error();
		}
		else
		{
			GamesController.totalPoints = GamesController.totalPoints - 40;
			GamesController.fruitRoundShape = false; 
		}
		switchToMainMenu();
	}
	
	/**
	 * This method is for points trade-off function for Tetris game
	 * @param event
	 * @throws IOException 
	 */
	@FXML
	public void changeToTetrisEffect(ActionEvent event) throws IOException 
	{
		if(GamesController.totalPoints - 20 < 0)
		{
			error();
		}
		else
		{
			GamesController.totalPoints = GamesController.totalPoints - 20;
			GuiController.tetrisColor = 1;
		}
		switchToMainMenu();
	}
	
	/**
	 * This method is for switch back to round shape ball (Pong)
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void switchToOGPong (ActionEvent event) throws IOException 
	{
		if(GamesController.totalPoints - 10 < 0)
		{
			error();
		}
		else
		{
			GamesController.totalPoints = GamesController.totalPoints - 10;
			GamesController.circleBall = true; 
		}
		switchToMainMenu();
	}
	
	/**
	 * This method is for switch back to original black and white color palette (Tetris)
	 * @param event
	 * @throws IOException 
	 */
	@FXML 
	public void switchToOGTetris (ActionEvent event) throws IOException 
	{
		if(GamesController.totalPoints - 10 < 0)
		{
			error();
		}
		else
		{
			GamesController.totalPoints = GamesController.totalPoints - 10;
			GuiController.tetrisColor = 0;
		}
		switchToMainMenu();
	}
	
	/**
	 * This method is for switching back to original round ball (BlockBreaker) 
	 * @param event
	 * @throws IOException 
	 */
	//switch back to round shape ball (BlockBreaker)
	@FXML 
	public void switchToOGBlockBreaker (ActionEvent event) throws IOException 
	{	
		if(GamesController.totalPoints - 10 < 0)
		{
			error();
		}
		else
		{
			GamesController.totalPoints = GamesController.totalPoints - 10;
			GamesController.ballShape = 0;
		}	
		switchToMainMenu();
	}
	
	/**
	 * This method is for switch back to original circle food (Snake)
	 * @param event
	 * @throws IOException 
	 */
	//switch back to round shape fruit (Snake)
	@FXML 
	public void switchToOGSnake (ActionEvent event) throws IOException 
	{
		if(GamesController.totalPoints - 10 < 0)
		{
			error();
		}
		else
		{
			GamesController.totalPoints = GamesController.totalPoints - 10;
			GamesController.fruitRoundShape = true; 
		}	
		switchToMainMenu();
	}
}
