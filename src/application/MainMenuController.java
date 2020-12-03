package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * This class is for all the button functions that redirect to another pages
 *
 *
 */
public class MainMenuController implements Initializable{
	@FXML  ImageView playIcon;
	@FXML  ImageView snowFlakeIcon;
	@FXML  ImageView houseIcon;
	@FXML  ImageView championIcon;
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		houseIcon.setImage(new Image("/main/resources/cs151GameShopIcon.png"));
		playIcon.setImage(new Image("/main/resources/cs151GameConsoleIcon.png"));
		snowFlakeIcon.setImage(new Image("/main/resources/cs151GameGearIconThing.png"));
		championIcon.setImage(new Image("/main/resources/cs151GameHiScoreIcon.png"));
	}
	
	@FXML
	public void buttonAction(ActionEvent event) throws IOException{
		Stage currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currStage.close();
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/Games.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent home_page_parent = fxmlLoader.load();
		
		Scene home_page_scene = new Scene(home_page_parent,844,750);
		Stage scene2 = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene2.setTitle("Games Home Page");
		scene2.setScene(home_page_scene);
		scene2.show();
	}
	
	@FXML
	public void switchToItemShop(ActionEvent event) throws IOException{
		Stage currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currStage.close();
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/itemShop.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent itemShopPage = fxmlLoader.load();
		
		Scene itemShopScene = new Scene(itemShopPage,844,750);
		Stage itemShopStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		itemShopStage.setScene(itemShopScene);
		itemShopStage.setTitle("Item Shop");
		itemShopStage.show();	
	}
	
	@FXML
	public void switchToHiScore(ActionEvent event) throws IOException{
		Stage currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currStage.close();
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/HiScore.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent home_page_parent = fxmlLoader.load();
		
		Scene home_page_scene = new Scene(home_page_parent,844,750);
		Stage scene2 = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene2.setScene(home_page_scene);
		scene2.setTitle("Hi-Score Page");
		scene2.show();
	}
	
	@FXML
	public void switchToGameSetting(ActionEvent event) throws IOException{
		Stage currStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        currStage.close();
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/gameSettings.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent settingPage = fxmlLoader.load();
		
		Scene setting_scene = new Scene(settingPage,844,750);
		Stage scene2 = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene2.setScene(setting_scene);
		scene2.setTitle("Settings Page");
		scene2.show();	
	}
	
}
