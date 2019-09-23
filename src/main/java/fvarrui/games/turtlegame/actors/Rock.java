package fvarrui.games.turtlegame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import fvarrui.games.turtlegame.utils.TexturedActor;

public class Rock extends TexturedActor {
	
	public Rock() {
		super(new Texture(Gdx.files.internal("assets/rock.png")));
	}

}
