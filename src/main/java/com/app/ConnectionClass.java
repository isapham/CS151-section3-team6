package main.java.com.app;
import java.io.IOException;
//this class is used for mysql store and retrieve
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.com.app.blockBreaker.BlockBreakerController;

public class ConnectionClass {
	public Connection connection;
	public Connection getConnection() {
		String dbName="Points_List";
		String userName ="root";
		String gamePoint = "";
		String totalScore = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,gamePoint);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public TextField userNameText;
	public Button closeButton;
	public void saveDataButton(ActionEvent event) throws IOException, SQLException{
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();
		
		String sql ="INSERT INTO USER VALUES('"+userNameText.getText()+"')";
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);
		
		BlockBreakerController.window.close();
		switchToGameMenu();
		
		
	}
	
	public void switchToGameMenu() {
    	Parent gamesMenu;
    
		try {
			gamesMenu = FXMLLoader.load(getClass().getResource("Games.fxml"));
			Scene gamesMenuScene = new Scene (gamesMenu);
			Stage window = new Stage();
			window.setScene(gamesMenuScene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

}
