package main.java.com.app;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.java.com.app.pong.PongController;

public class PongDBController {

	@FXML
	private TextField txtName,gameScoreTxt,totalScoreTxt;
	
	@FXML 
	private void insertUsernamePong(ActionEvent event) throws ClassNotFoundException, SQLException{

		GamesController.insertUsernamePong(txtName.getText());
		gameScoreTxt.setText(PongController.ppoints.toString());
		//not done yey
		totalScoreTxt.setText(PongController.ppoints.toString());
	}
}


