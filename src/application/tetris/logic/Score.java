package application.tetris.logic;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public final class Score {
	//create an array list to store and sort tetris score
	public static ArrayList<Integer> tetrisScoreList = new ArrayList<Integer>();
	public static final IntegerProperty score = new SimpleIntegerProperty(0);

    public IntegerProperty scoreProperty() {
        return score;
    }

    public void add(int i){
        score.setValue(score.getValue() + i);
    }

    public static void reset() {
        score.setValue(0);
    }
}
