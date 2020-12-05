package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * This class is for high score setting, get and set score from each game
 *
 */
public class HiScore {
	
	private IntegerProperty blockBreakerScoreProperty;
	private IntegerProperty pongScoreProperty;
	private IntegerProperty tetrisScoreProperty;
	private IntegerProperty snakeScoreProperty;
	private IntegerProperty totalScoreProperty;
	
	public HiScore() {
		this.blockBreakerScoreProperty = new SimpleIntegerProperty();
		this.pongScoreProperty = new SimpleIntegerProperty();
		this.snakeScoreProperty = new SimpleIntegerProperty();
		this.tetrisScoreProperty = new SimpleIntegerProperty();
		this.totalScoreProperty = new SimpleIntegerProperty();
	}
	
	/**
	 * This method is for get block breaker score from list
	 * @return integer data type
	 */
	//this is for block breaker game score
	public int getBBScore() {
		return this.blockBreakerScoreProperty.get();
	}
	
	/**
	 * This method is for setting block breaker score 
	 * @param score is block breaker score in integer
	 */
	public void setBBScore(int score) {
		this.blockBreakerScoreProperty.set(score);
	}
	
	/**
	 * This method is for getting block breaker score
	 * @return IntegerProperty data type
	 */
	public IntegerProperty getBlockBreakerScore() {
		return this.blockBreakerScoreProperty;
	}
	
	/**
	 * This method is for getting tetris score from list
	 * @return integer data type
	 */
	public int getTetScore() {
		return this.tetrisScoreProperty.get();
	}
	
	/**
	 * This method is for setting tetris score
	 * @param score is tetris score in integer
	 */
	public void setTetScore(int score) {
		this.tetrisScoreProperty.set(score);
	}
	
	/**
	 * This method is for setting tetris score 
	 * @return IntegerProperty data type
	 */
	public IntegerProperty getTetrisScore() {
		return this.tetrisScoreProperty;
	}
	
	/**
	 * This method is for getting Pong score from list
	 * @return integer data type
	 */
	public int getPoScore() {
		return this.pongScoreProperty.get();
	}
	
	/**
	 * This method is for setting Pong score
	 * @param score is pong score in integer
	 */
	public void setPoScore(int score) {
		this.pongScoreProperty.set(score);
	}
	
	/**
	 * This method is for getting Pong score
	 * @return IntegerProperty data type
	 */
	public IntegerProperty getPongScore() {
		return this.pongScoreProperty;
	}
	
	/**
	 * This method is for getting Snake score from list
	 * @return integer data type
	 */
	public int getSnaScore() {
		return this.snakeScoreProperty.get();
	}
	
	public void setSnaScore(int score) {
		this.snakeScoreProperty.set(score);
	}
	
	public IntegerProperty getSnakeScore() {
		return snakeScoreProperty;
	}
	
	//this is for block breaker game score
	public int getTotScore() {
		return this.totalScoreProperty.get();
	}
	
	public void setTotScore(int score) {
		this.totalScoreProperty.set(score);
	}
	
	public IntegerProperty getTotalScore() {
		return this.totalScoreProperty;
	}
}
