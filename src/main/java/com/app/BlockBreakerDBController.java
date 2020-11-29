package main.java.com.app;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.java.com.app.blockBreaker.BlockBreakerController;

public class BlockBreakerDBController {

	@FXML
	private TextField txtName,gameScoreTxt,totalScoreTxt;
	
	@FXML 
	private void insertUsernameBlockBreaker(ActionEvent event) throws ClassNotFoundException, SQLException{

		GamesController.insertUsernameBlockBreaker(txtName.getText());
		gameScoreTxt.setText(BlockBreakerController.points.toString());
		totalScoreTxt.setText(BlockBreakerController.points.toString());
	}
}


