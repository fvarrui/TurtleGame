package fvarrui.games.turtlegame.actors;

import fvarrui.games.turtlegame.utils.TextActor;

public class Score extends TextActor {
	
	private int score;
	
	public Score() {
		super("assets/deltaphoenixchromeital.ttf", 50);
	}
	
	public void reset() {
		score = 0;
	}
	
	public void increase() {
		score++;
	}
	
	public int getScore() {
		return score;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		setText("Score: " + getScore());		
	}
	
}
