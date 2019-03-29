package fvarrui.games.turtlegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Ocean extends TexturedActor {
	
	public Ocean() {
		super(new Texture(Gdx.files.internal("assets/water-border.jpg")));
	}

}
