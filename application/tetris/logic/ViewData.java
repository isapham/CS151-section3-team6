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
     * 
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
     * 
     * @return
     */
    public int[][] getBrickData() {
        return MatrixOperations.copy(brickData);
    }
    
    /**
     * 
     * @return 
     */
    public int getxPosition() {
        return xPosition;
    }

    /**
     * 
     * @return
     */
    public int getyPosition() {
        return yPosition;
    }
    
    /**
     * 
     * @return 
     */
    public int[][] getNextBrickData() {
        return MatrixOperations.copy(nextBrickData);
    }
}
