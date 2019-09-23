package fvarrui.games.turtlegame.actors;

import fvarrui.games.turtlegame.utils.TextActor;

public class FPS extends TextActor {
	
	private float elapsedTime = 0f;
	private float refreshRate = 0.25f;
		
	public FPS() {
		super("assets/deltaphoenixchromeital.ttf", 50);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		elapsedTime += delta;
		if (elapsedTime >= refreshRate) {
			elapsedTime = 0f;
			setText("FPS: " + String.format("%.0f", 1/delta));		
		}
	}
	
}
