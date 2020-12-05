package application.tetris.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * This class is for game over panel when user loses tetris game
 *
 */
public class GameOverPanel extends BorderPane{
	public GameOverPanel() {
        final Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.getStyleClass().add("gameOverStyle");
        setCenter(gameOverLabel);
    }
}
