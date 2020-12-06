package application.tetris.logic.rotator;

import application.tetris.logic.MatrixOperations;

/**
 * This class is for next brick shape
 *
 */
public final class NextShapeInfo {
	private final int[][] shape;
    private final int position;

    /**
     * Constructor for next shape of brick
     * @param shape is a matrix
     * @param position is coordinate position
     */
    public NextShapeInfo(final int[][] shape, final int position) {
        this.shape = shape;
        this.position = position;
    }

    /**
     * This method is for getting shape of brick
     * @return matrix
     */
    public int[][] getShape() {
        return MatrixOperations.copy(shape);
    }

    /**
     * This method is for getting brick position
     * @return position of brick
     */
    public int getPosition() {
        return position;
    }
}
