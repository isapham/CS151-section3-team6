package application.tetris.logic.rotator;

import application.tetris.logic.bricks.Brick;

/**
 * This class is for rotating brick
 *
 */
public class BrickRotator {
	private Brick brick;
    private int currentShape = 0;

    /**
     * This method is for getting next brick shape
     * @return next brick shape
     */
    public NextShapeInfo getNextShape() {
        int nextShape = currentShape;
        nextShape = (++nextShape) % brick.getShapeMatrix().size();
        return new NextShapeInfo(brick.getShapeMatrix().get(nextShape), nextShape);
    }

    /**
     * This method is getting current shape of brick
     * @return current brick shape
     */
    public int[][] getCurrentShape() {
        return brick.getShapeMatrix().get(currentShape);
    }

    /**
     * This method is for setting current brick shape
     * @param currentShape
     */
    public void setCurrentShape(int currentShape) {
        this.currentShape = currentShape;
    }

    /**
     * This method is for setting brick shape
     * @param brick
     */
    public void setBrick(Brick brick) {
        this.brick = brick;
        currentShape = 0;
    }
}
