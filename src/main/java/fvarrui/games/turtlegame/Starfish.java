package fvarrui.games.turtlegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Starfish extends TexturedActor {
	
	public Starfish() {
		super(new Texture(Gdx.files.internal("assets/starfish.png")));
		addAction(Actions.forever(Actions.rotateBy(360, 2)));
	}

}
