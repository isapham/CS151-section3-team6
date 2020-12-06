package application;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * 
 * This class is responsible for the functions of settings page
 *
 */
public class SettingsController implements Initializable{
	@FXML  CheckBox check1;
	@FXML  ImageView gearIcon;
	@FXML  ToggleButton toggle;
	private final String enabled = "ENABLE IT?";
	private final String disabled = "You are using Colorblind mode. DISABLE IT?";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gearIcon.setImage(new Image("/main/resources/cs151GameGearIconThing.png"));
		if (GamesController.cbEnabled == 0) {
			toggle.setText(enabled);
			GuiController.tetrisColor = 2;
		} else {
			toggle.setText(disabled);
			GuiController.tetrisColor = 0;
		}
	}

	/**
	 * This method is a button to change color palette for color blind accessibility
	 * @param event, button click
	 * @throws IOException
	 */
	@FXML
	public void colorBlindMode(ActionEvent event) throws IOException {
		if (GamesController.cbEnabled == 0) {
			GamesController.cbEnabled = 1;
			toggle.setText(disabled);
		} else if (GamesController.cbEnabled == 1) {
			GamesController.cbEnabled = 0;
			toggle.setText(enabled);
		}
	}

	/**
	 * This method is a button to switch to main menu 
	 * @param event, button click
	 * @throws IOException
	 */
	@FXML
	public void switchToMainMenu(ActionEvent event) {
		Stage currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currStage.close();
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
