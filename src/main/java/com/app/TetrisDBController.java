package main.java.com.app;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.java.com.app.blockBreaker.BlockBreakerController;
import main.java.com.app.tetris.logic.Score;

public class TetrisDBController {

	@FXML
	private TextField txtName,gameScoreTxt,totalScoreTxt;
	
	@FXML 
	private void insertUsernameTetris(ActionEvent event) throws ClassNotFoundException, SQLException{

		GamesController.insertUsernameTetris(txtName.getText());
		gameScoreTxt.setText(Score.score.getValue().toString());
		//not done here
		totalScoreTxt.setText(Score.score.getValue().toString());
	}
}


