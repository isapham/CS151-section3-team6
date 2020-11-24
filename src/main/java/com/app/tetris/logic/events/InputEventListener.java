package main.java.com.app.tetris.logic.events;

import main.java.com.app.tetris.logic.DownData;
import main.java.com.app.tetris.logic.ViewData;

public interface InputEventListener {

    DownData onDownEvent(MoveEvent event);

    ViewData onLeftEvent(MoveEvent event);

    ViewData onRightEvent(MoveEvent event);

    ViewData onRotateEvent(MoveEvent event);

    void createNewGame();
}
