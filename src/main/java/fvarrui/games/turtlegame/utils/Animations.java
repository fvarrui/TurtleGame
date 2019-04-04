package fvarrui.games.turtlegame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animations {

	public static Animation<TextureRegion> loadFromFiles(float frameDuration, boolean loop, String... files) {
		Array<TextureRegion> textures = new Array<>();
		for (String file : files) {
			Texture texture = new Texture(Gdx.files.internal(file));
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			textures.add(new TextureRegion(texture));
		}
		Animation<TextureRegion> animation = new Animation<>(frameDuration, textures);
		animation.setPlayMode(loop ? PlayMode.LOOP : PlayMode.NORMAL);
		return animation;
	}

	public static Animation<TextureRegion> loadFromSheet(String file, int rows, int cols, float frameDuration) {
		Texture texture = new Texture(Gdx.files.internal(file), true);
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		int frameWidth = texture.getWidth() / cols;
		int frameHeight = texture.getHeight() / rows;
		TextureRegion[][] temp = TextureRegion.split(texture, frameWidth, frameHeight);
		Array<TextureRegion> textureArray = new Array<TextureRegion>();
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++)
				textureArray.add(temp[r][c]);
		return new Animation<TextureRegion>(frameDuration, textureArray);
	}

}
