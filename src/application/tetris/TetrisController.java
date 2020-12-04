package application.tetris;

import application.tetris.gui.GuiController;
import application.tetris.logic.*;
import application.tetris.logic.events.EventSource;
import application.tetris.logic.events.InputEventListener;
import application.tetris.logic.events.MoveEvent;

/**
 * 
 * This class contains functions to control Tetris Game
 *
 */
public class TetrisController implements InputEventListener{
	private Board board = new SimpleBoard(25, 10);

    private final GuiController viewGuiController;

    public TetrisController(GuiController c) {
        viewGuiController = c;
        board.createNewBrick();
        viewGuiController.setEventListener(this);
        viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData());
        viewGuiController.bindScore(board.getScore().scoreProperty());
    }
    
    
    @Override
    public DownData onDownEvent(MoveEvent event) {
        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = null;
        if (!canMove) {
            board.mergeBrickToBackground();
            clearRow = board.clearRows();
            if (clearRow.getLinesRemoved() > 0) {
                board.getScore().add(clearRow.getScoreBonus());
            }
            if (board.createNewBrick()) {
                viewGuiController.gameOver();
            }

            viewGuiController.refreshGameBackground(board.getBoardMatrix());

        } else {
            if (event.getEventSource() == EventSource.USER) {
                board.getScore().add(1);
            }
        }
        return new DownData(clearRow, board.getViewData());
    }
    
    /**
     * this method updates data about moving a peice right
     * 
     * @param event
     * @return updated info about piece placement
     */
    @Override
    public ViewData onLeftEvent(MoveEvent event) {
        board.moveBrickLeft();
        return board.getViewData();
    }

    /**
     * this method updates data about moving a peice right
     * 
     * @param event
     * @return updated info about piece placement
     */
    @Override
    public ViewData onRightEvent(MoveEvent event) {
        board.moveBrickRight();
        return board.getViewData();
    }
    
    /**
     * this method updates data on rotating a piece
     * 
     * @param event
     * @return returns updated info about piece placement
     */
    @Override
    public ViewData onRotateEvent(MoveEvent event) {
        board.rotateLeftBrick();
        return board.getViewData();
    }

    /**
     * This method refreshes new game board
     * 
     * @return void
     */
    @Override
    public void createNewGame() {
        board.newGame();
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
    }
}