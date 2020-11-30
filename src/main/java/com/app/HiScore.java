package main.java.com.app;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class HiScore {
	private IntegerProperty blockBreakerScoreProperty;
	private IntegerProperty pongScoreProperty;
	private IntegerProperty tetrisScoreProperty;
	private IntegerProperty snakeScoreProperty;
	
	public HiScore() {
		this.blockBreakerScoreProperty = new SimpleIntegerProperty();
		this.pongScoreProperty = new SimpleIntegerProperty();
		this.snakeScoreProperty = new SimpleIntegerProperty();
		this.tetrisScoreProperty = new SimpleIntegerProperty();
	}
	
	//this is for block breaker game score
	public int getBBScore() {
		return blockBreakerScoreProperty.get();
	}
	
	public void setBBScore(int score) {
		this.blockBreakerScoreProperty.set(score);
	}
	
	public IntegerProperty getBlockBreakerScore() {
		return blockBreakerScoreProperty;
	}
	
	//this is for tetris game score
	public int getTetScore() {
		return tetrisScoreProperty.get();
	}
	
	public void setTetScore(int score) {
		this.tetrisScoreProperty.set(score);
	}
	
	public IntegerProperty getTetrisScore() {
		return tetrisScoreProperty;
	}
	
	//this is for pong score 
	public int getPoScore() {
		return pongScoreProperty.get();
	}
	
	public void setPoScore(int score) {
		this.pongScoreProperty.set(score);
	}
	
	public IntegerProperty getPongScore() {
		return pongScoreProperty;
	}
	
	//this is for snake score
	public int getSnaScore() {
		return snakeScoreProperty.get();
	}
	
	public void setSnaScore(int score) {
		this.snakeScoreProperty.set(score);
	}
	
	public IntegerProperty getSnakeScore() {
		return snakeScoreProperty;
	}
}
