package main.java.com.app.tetris.logic.bricks;

import java.util.ArrayList;
import java.util.List;

import main.java.com.app.tetris.logic.MatrixOperations;

public final class OBrick implements Brick{
	private final List<int[][]> brickMatrix = new ArrayList<>();

    public OBrick() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 4, 4, 0},
                {0, 4, 4, 0},
                {0, 0, 0, 0}
        });
    }

    @Override
    public List<int[][]> getShapeMatrix() {
        return MatrixOperations.deepCopyList(brickMatrix);
    }
}
