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

public class SettingsController implements Initializable{
	@FXML  CheckBox check1;
	@FXML  ImageView gearIcon;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gearIcon.setImage(new Image("/main/resources/cs151GameGearIconThing.png"));
		check1 = new CheckBox();
	}


	/*
	@FXML
	public void colorBlindMode(ActionEvent event) throws IOException {
		boolean isSelected = check1.isSelected();
		if(isSelected) {
			
		}
		
		
		
	}
	
	*/

	

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
