package application.tetris.logic;


/**
 * 
 * This class contains data about tetris pieces
 *
 */
public final class ViewData {
	private final int[][] brickData;
    private final int xPosition;
    private final int yPosition;
    private final int[][] nextBrickData;
    
    /**
     * This method is for viewing data
     * @param brickData
     * @param xPosition
     * @param yPosition
     * @param nextBrickData
     */
    public ViewData(int[][] brickData, int xPosition, int yPosition, int[][] nextBrickData) {
        this.brickData = brickData;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.nextBrickData = nextBrickData;
    }
    /**
     * this method is for getting brick data
     * @return matrix
     */
    public int[][] getBrickData() {
        return MatrixOperations.copy(brickData);
    }
    
    /**
     * this method is for getting brick position
     * @return xPosition is a coordinate of a brick
     */
    public int getxPosition() {
        return xPosition;
    }

    /**
     * this method is for getting brick position
     * @return yPosition is a coordinate of a brick
     */
    public int getyPosition() {
        return yPosition;
    }
    
    /**
     * This method is for getting next brick data
     * @return matrix 
     */
    public int[][] getNextBrickData() {
        return MatrixOperations.copy(nextBrickData);
    }
}
