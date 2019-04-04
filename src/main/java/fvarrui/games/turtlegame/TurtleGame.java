package fvarrui.games.turtlegame;

import com.badlogic.gdx.Game;

public class TurtleGame extends Game {

	public void create() {
		setScreen(new GameScreen(this));
	}

}
