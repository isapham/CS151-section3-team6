package org.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class GamesMenuController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("snakeGame");
    }
    @FXML
    private void switchToBlockBreaker() throws IOException {
        App.setRoot("blockBreaker");
    }
    @FXML
    private void switchToTetris() throws IOException{
        App.setRoot("tetrisGame");
    }
}