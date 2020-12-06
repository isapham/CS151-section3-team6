package application.tetris.logic;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * This class is for generating score that user earns
 *
 */
public final class Score {
	//create an array list to store and sort tetris score
	public static ArrayList<Integer> tetrisScoreList = new ArrayList<Integer>();
	public static final IntegerProperty score = new SimpleIntegerProperty(0);

	/**
	 * This method is for getting score
	 * @return score
	 */
    public IntegerProperty scoreProperty() {
        return score;
    }
    
	/**
	 * This method is for adding score value
	 * @param i
	 */
    public void add(int i){
        score.setValue(score.getValue() + i);
    }

    /**
     * This method reset score to zero
     */
    public static void reset() {
        score.setValue(0);
    }
}
