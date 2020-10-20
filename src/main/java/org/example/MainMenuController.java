package org.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainMenuController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("gamesMenu");
    }
}
