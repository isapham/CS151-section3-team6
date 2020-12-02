package main.java.com.app;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.com.app.snake.SnakeController;
import main.java.com.app.tetris.TetrisController;
import main.java.com.app.tetris.gui.GuiController;
import javafx.scene.control.CheckBox;

import java.io.File;
import main.java.com.app.blockBreaker.BlockBreakerController;
import main.java.com.app.pong.PongController;
import main.java.com.app.snake.SnakeController;
import main.java.com.app.snake.SnakeController.Move;
import main.java.com.app.tetris.TetrisController;
import main.java.com.app.tetris.gui.GuiController;
import main.java.com.app.tetris.logic.Score;

public class SettingsController implements Initializable{
	@FXML  CheckBox check1;
	@FXML  ImageView gearIcon;
	@FXML  ToggleButton toggle;
	private final String enabled = "ENABLED";
	private final String disabled = "DISABLED";
	
	
	
@Override
	public void initialize(URL location, ResourceBundle resources) {
		gearIcon.setImage(new Image("/main/resources/cs151GameGearIconThing.png"));
		if(GamesController.cbEnabled == 0) {
			toggle.setText(disabled);
			toggle.setSelected(false);
		}else if(GamesController.cbEnabled ==1) {
			toggle.setText(enabled);
			toggle.setSelected(true);
		}
		
	}


	
	@FXML
	public void colorBlindMode(ActionEvent event) throws IOException {
		if(toggle.isSelected() == true) {
			GamesController.cbEnabled = 1;
			toggle.setText(enabled);
			
		
		}else if(toggle.isSelected() == false) {
			toggle.setSelected(false);
			GamesController.cbEnabled = 0;
			toggle.setText(disabled);
			
			
		}
		
		
		
		
		
	}

	


	

	//switches back to menu
	public void switchToMainMenu(ActionEvent event) {
    	Parent mainMenu;
    
		try {
			mainMenu = FXMLLoader.load(getClass().getResource("/main/resources/MainMenu.fxml"));
			Scene mainMenuScene = new Scene (mainMenu);
			Stage windowView = (Stage) ((Node)event.getSource()).getScene().getWindow();
			windowView.setScene(mainMenuScene);
			windowView.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
