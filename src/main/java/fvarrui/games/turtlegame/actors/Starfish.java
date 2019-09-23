package fvarrui.games.turtlegame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import fvarrui.games.turtlegame.utils.TexturedActor;

public class Starfish extends TexturedActor {
	
	public Starfish() {
		super(new Texture(Gdx.files.internal("assets/starfish.png")));
		addAction(Actions.forever(Actions.rotateBy(360, 2)));
	}

}
