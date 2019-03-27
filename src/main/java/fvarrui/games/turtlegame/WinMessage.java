package fvarrui.games.turtlegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class WinMessage extends TexturedActor {
	
	public WinMessage() {
		super(new Texture(Gdx.files.internal("assets/you-win.png")));
		center();
	}

}
