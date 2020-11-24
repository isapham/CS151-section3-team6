package main.java.com.app.tetris.logic.bricks;

import java.util.ArrayList;
import java.util.List;

import main.java.com.app.tetris.logic.MatrixOperations;

public final class SBrick implements Brick {
	private final List<int[][]> brickMatrix = new ArrayList<>();

    public SBrick() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 5, 5, 0},
                {5, 5, 0, 0},
                {0, 0, 0, 0}
        });
        brickMatrix.add(new int[][]{
                {5, 0, 0, 0},
                {5, 5, 0, 0},
                {0, 5, 0, 0},
                {0, 0, 0, 0}
        });
    }

    @Override
    public List<int[][]> getShapeMatrix() {
        return MatrixOperations.deepCopyList(brickMatrix);
    }
}
