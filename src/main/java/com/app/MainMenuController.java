package main.java.com.app;

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

public class MainMenuController implements Initializable{
	@FXML  ImageView playIcon;
	@FXML  ImageView snowFlakeIcon;
	@FXML  ImageView houseIcon;
	@FXML  ImageView championIcon;
	
	@FXML
	public void buttonAction(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/Games.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent home_page_parent = fxmlLoader.load();
		
		Scene home_page_scene = new Scene(home_page_parent,844,750);
		Stage scene2 = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene2.setScene(home_page_scene);
		scene2.show();
	}
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		houseIcon.setImage(new Image("/main/resources/cs151GameShopIcon.png"));
		playIcon.setImage(new Image("/main/resources/cs151GameConsoleIcon.png"));
		snowFlakeIcon.setImage(new Image("/main/resources/cs151GameGearIconThing.png"));
		championIcon.setImage(new Image("/main/resources/cs151GameHiScoreIcon.png"));
	}
	
	
	public void switchToItemShop(ActionEvent event) throws IOException{
		
	}
	
	@FXML
	public void switchToHiScore(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader();
		String pathToFxml = "src/main/resources/HiScore.fxml";
		URL fxmlUrl = new File(pathToFxml).toURI().toURL();
		fxmlLoader.setLocation(fxmlUrl);
		Parent home_page_parent = fxmlLoader.load();
		
		Scene home_page_scene = new Scene(home_page_parent,844,750);
		Stage scene2 = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene2.setScene(home_page_scene);
		scene2.show();
	}
	
	@FXML
	public void switchToGameSetting(ActionEvent event) throws IOException{
		
	}
	
}
