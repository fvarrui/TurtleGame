package fvarrui.games.turtlegame.actors;

import fvarrui.games.turtlegame.utils.TextActor;

public class WinMessage extends TextActor {
	
	public WinMessage() {
		super("assets/deltaphoenixchromeital.ttf", 120, true);
		setText("Ganaste");
		center();
	}

}
