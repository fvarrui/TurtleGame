package fvarrui.games.turtlegame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fvarrui.games.turtlegame.utils.TexturedActor;

public class Plant extends TexturedActor {
	
	public Plant() {
		super(new TextureRegion(new Texture(Gdx.files.internal("assets/ocean-plants.png")), 0, 0, 32, 32));
	}

}
