package main.java.com.app;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.java.com.app.snake.SnakeController;

public class SnakeDBController {

	@FXML
	private TextField txtName,gameScoreTxt,totalScoreTxt;
	
	@FXML 
	private void insertUsernameSnake(ActionEvent event) throws ClassNotFoundException, SQLException{

		GamesController.insertUsernameSnake(txtName.getText());
		gameScoreTxt.setText(Integer.toString(SnakeController.snakeScore));
		//not done yet 
		totalScoreTxt.setText(Integer.toString(SnakeController.snakeScore));
	}
}


