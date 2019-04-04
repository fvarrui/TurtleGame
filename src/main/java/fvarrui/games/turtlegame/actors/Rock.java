package fvarrui.games.turtlegame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Rock extends TexturedActor {
	
	public Rock() {
		super(new Texture(Gdx.files.internal("assets/rock.png")));
	}

}
