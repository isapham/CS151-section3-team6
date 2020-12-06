package application.tetris.logic;

/**
 * This class is for clearing rows when 1 row is filled up
 *
 */
public final class ClearRow {
	private final int linesRemoved;
    private final int[][] newMatrix;
    private final int scoreBonus;

    public ClearRow(int linesRemoved, int[][] newMatrix, int scoreBonus) {
        this.linesRemoved = linesRemoved;
        this.newMatrix = newMatrix;
        this.scoreBonus = scoreBonus;
    }

    /**
     * this method is for getting lines remove
     * @return lineRemoved is the line needs to be removed
     */
    public int getLinesRemoved() {
        return linesRemoved;
    }

    /**
     * This method is create a new matrix for game
     * @return newMatrix
     */
    public int[][] getNewMatrix() {
        return MatrixOperations.copy(newMatrix);
    }

    /**
     * This method is for get score bonus
     * @return scoreBonus
     */
    public int getScoreBonus() {
        return scoreBonus;
    }
}
