package fvarrui.games.turtlegame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TexturedActor extends BaseActor {

	private TextureRegion texture;

	public TexturedActor(Texture texture) {
		super();
		setTexture(new TextureRegion(texture));
	}

	public TexturedActor(TextureRegion textureRegion) {
		super();
		setTexture(textureRegion);
	}

	public void setTexture(TextureRegion texture) {
		this.texture = texture;
		setSize(texture.getRegionWidth(), texture.getRegionHeight());
		setOrigin(texture.getRegionWidth() / 2, texture.getRegionHeight() / 2);
	}

	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.setColor(getColor());
		if (isVisible()) {
			batch.draw(
					texture, 
					getX(), getY(), 
					getOriginX(), getOriginY(), 
					getWidth(), getHeight(), 
					getScaleX(), getScaleY(), 
					getRotation()
				);
		}
	}

}
