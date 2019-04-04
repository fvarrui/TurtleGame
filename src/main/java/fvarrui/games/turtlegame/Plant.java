package fvarrui.games.turtlegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Plant extends TexturedActor {
	
	public Plant() {
		super(new Texture(Gdx.files.internal("assets/ocean-plants.png")));
	}

}
