package main.java.com.app.tetris.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameOverPanel extends BorderPane{
	public GameOverPanel() {
        final Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.getStyleClass().add("gameOverStyle");
        setCenter(gameOverLabel);
    }
}
